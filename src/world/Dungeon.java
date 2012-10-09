package world;

import actor.Actor;
import actor.ActorType;
import utility.Point;
import utility.Rand;
import world.tile.Tile;

import java.util.HashMap;

public class Dungeon extends Map {
    HashMap<Integer, Tile> level; //TODO replace this with proper level class for multiple level dungeons
    HashMap<Integer, Actor> actors;
    Point player_pos;

    int sizex, sizey;

    public Dungeon(Point p) {
        player_pos = p;
        level = new HashMap<Integer, Tile>();
        actors = new HashMap<Integer, Actor>();
        sizex = 100;
        sizey = 100;
        generate();
    }

    public int genKey(int x, int y) {
        return x * sizex + y;
    }

    private void generate() {
        for (int x = 0; x < sizex; x++) {

            for (int y = 0; y < sizey; y++) {
                if (x == 0 || x == sizex - 1) {
                    level.put(genKey(x, y), Tile.WALL_STONE);
                } else if (y == 0 || y == sizey - 1) {
                    level.put(genKey(x, y), Tile.WALL_STONE);
                } else {
                    level.put(genKey(x, y), Tile.FLOOR_STONE);
                    if (Rand.oneIn(5)) {
                        actors.put(genKey(x, y), new Actor(new Point(x, y), ActorType.goblin));
                    }
                }


            }
        }
    }

    @Override
    public HashMap<Integer, Tile> getTileMap() {
        return level;
    }

    @Override
    public HashMap<Integer, Actor> getActorMap() {
        return actors;

    }
}
