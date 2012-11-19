package world;

import actor.Actor;
import actor.Player;
import actor.Senses;
import effect.Effect;
import effect.EffectPackage;
import entity.Entity;
import entity.EntityFlag;
import logic.Command;
import utility.Point;
import world.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    HashMap<Integer, Tile> tileHashMap;
    HashMap<Integer, Actor> actorHashMap;
    HashMap<Integer, ArrayList<Entity>> entities;
    ArrayList<Actor> actors;
    Senses senses;
    Player player;
    int level, sizex, sizey;
    ArrayList<Level> levels;
    boolean levelchange;
    int changeto;

    public Level(Player p, ArrayList<Level> levels, int level, int sizex, int sizey) {
        player = p;
        this.levels = levels;
        this.level = level;
        tileHashMap = new HashMap<Integer, Tile>();
        actorHashMap = new HashMap<Integer, Actor>();
        entities = new HashMap<Integer, ArrayList<Entity>>();
        actors = new ArrayList<Actor>();
        this.sizex = sizex;
        this.sizey = sizey;
        senses = new Senses(p.getPos(), tileHashMap, actorHashMap, entities, sizex);


    }

    private int genKey(int x, int y) {
        return x * sizex + y;
    }

    public void levelChanged() {
        levelchange = false;
    }

    public void update() {
        //NOTE, X AND Y ARE NOT THE POSITIONS OF THESE ENTITIES
        //This is to check for movement requests from entities (to allow the player to move between levels)
        //TODO move this to a dedicated (maybe static?) class
        ArrayList<ArrayList<Entity>> entityarrays = new ArrayList<ArrayList<Entity>>(entities.values());
        for (int x = 0; x < entityarrays.size(); x++) {
            ArrayList<Entity> activeArray = entityarrays.get(x);
            for (int y = 0; y < activeArray.size(); y++) {
                Entity ent = activeArray.get(y);
                ArrayList<EffectPackage> effects = ent.getEffectPackages();
                for (int c = 0; c < effects.size(); c++) {
                    EffectPackage pack = effects.get(c);
                    if (pack.getEffect() == Effect.MOVE_UP) {
                        levelchange = true;
                        changeto = level - 1;
                    } else if (pack.getEffect() == Effect.MOVE_DOWN) {
                        levelchange = true;
                        changeto = level + 1;
                    }
                }
            }
        }
        //Update actores
        actors = new ArrayList<Actor>(actorHashMap.values());

        for (int c = 0; c < actors.size(); c++) {
            Actor a = actors.get(c);
            a.update();
            Point pos = a.getPos();
            if (a.delete()) {
                actorHashMap.remove(genKey(pos.getX(), pos.getY()));
            }
        }
    }

    public void runTurns() {
        for (int c = 0; c < actors.size(); c++) {
            Actor actor = actors.get(c);
            Point oldpos = actor.getPos().copy();

            actor.runTurn(senses.shadowCasting(oldpos.getX(), oldpos.getY(), 20));
            if (actor.hasMoved()) {
                actorHashMap.remove(genKey(oldpos.getX(), oldpos.getY()));
                Point newpos = actor.getPos().copy();
                actorHashMap.put(genKey(newpos.getX(), newpos.getY()), actor);
            }
            actor.moveHandled();
        }
    }

    public ArrayList<Point> downStairLocs() {
        ArrayList<Point> locs = new ArrayList<Point>();
        ArrayList<ArrayList<Entity>> entityarrays = new ArrayList<ArrayList<Entity>>(entities.values());
        for (int x = 0; x < entityarrays.size(); x++) {
            ArrayList<Entity> activeArray = entityarrays.get(x);
            for (int y = 0; y < activeArray.size(); y++) {
                Entity ent = activeArray.get(y);
                if (ent.hasFlag(EntityFlag.POINT_MOVE_DOWN)) {
                    locs.add(ent.getPosition());
                }
            }
        }
        return locs;
    }

    public boolean entityFree(int x, int y) {//Free for entities (meaning no blockable entities or tiles currently here)
        int key = genKey(x, y);
        boolean tileFree = false;
        if (tileHashMap.containsKey(key)) {
            tileFree = tileHashMap.get(key).isPassable();
        }
        boolean entFree = true;
        if (entities.containsKey(key)) {
            ArrayList<Entity> ents = entities.get(key);
            for (int c = 0; c < ents.size(); c++) {
                Entity ent = ents.get(c);
                if (!ent.isPassable() || ent.hasFlag(EntityFlag.CAN_BLOCK)) {
                    entFree = false;
                }
            }
        }
        return entFree && tileFree;

    }

    public void createEntity(Entity e) {
        Point pos = e.getPosition();
        int key = genKey(pos.getX(), pos.getY());
        if (!entities.containsKey(key)) {
            entities.put(key, new ArrayList<Entity>());
        }
        entities.get(key).add(e);
    }

    public HashMap<Integer, Tile> getTileHashMap() {
        return tileHashMap;
    }

    public HashMap<Integer, Actor> getActorHashMap() {
        return actorHashMap;
    }

    public HashMap<Integer, ArrayList<Entity>> getEntities() {
        return entities;
    }

    public void sendPlayerCommand(Command command) {
        Point playerpos = player.getPos();
        int key = genKey(playerpos.getX(), playerpos.getY());
        if (entities.containsKey(key)) {
            ArrayList<Entity> ents = entities.get(key);
            ents.get(0).interact(command);//TODO try to find a better entity to interact with + have entity selection
        }
    }

}
