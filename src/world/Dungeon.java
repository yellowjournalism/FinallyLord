/*
 * This file is part of Finally Lord.
 *
 *      Finally Lord is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      Finally Lord is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with Finally Lord.  If not, see <http://www.gnu.org/licenses/>.
 */

package world;

import actor.*;
import utility.Point;
import utility.Rand;
import world.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class Dungeon extends Map {
    HashMap<Integer, Tile> level; //TODO replace this with proper level class for multiple level dungeons
    ArrayList<Actor> actors;
    HashMap<Integer, Actor> actorHash;
    Senses senses;
    Point player_pos;
    Player player;

    int sizex, sizey;

    public Dungeon(Player p) {
        player = p;
        player_pos = p.getPos();

        level = new HashMap<Integer, Tile>();
        actors = new ArrayList<Actor>();
        actorHash = new HashMap<Integer, Actor>();
        sizex = 30;
        sizey = 30;
        senses = new Senses(level, actorHash, sizex);
        generate();
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
                    if (Rand.oneIn(4)) {
                        actorHash.put(genKey(x, y), new Actor(new Point(x, y), ActorType.goblin));
                    }
                }


            }
        }
        for (int x = 5; x <= 20; x++) {
            level.put(genKey(x, 5), Tile.WALL_STONE);
        }
        for (int x = 5; x <= 20; x++) {
            level.put(genKey(x, 20), Tile.WALL_STONE);
        }
        for (int y = 5; y <= 20; y++) {
            level.put(genKey(5, y), Tile.WALL_STONE);
        }
        for (int y = 5; y <= 20; y++) {
            level.put(genKey(20, y), Tile.WALL_STONE);
        }
        level.put(genKey(10, 5), Tile.FLOOR_STONE);

    }

    public int genKey(int x, int y) {
        return x * sizex + y;
    }

    @Override
    public void update() {
        actors = new ArrayList<Actor>(actorHash.values());

        for (int c = 0; c < actors.size(); c++) {
            Actor a = actors.get(c);
            a.update();
        }
    }

    @Override
    public void runTurns() {
        for (int c = 0; c < actors.size(); c++) {
            Actor actor = actors.get(c);
            Point oldpos = actor.getPos().copy();

            actor.runTurn(senses.shadowCasting(oldpos.getX(), oldpos.getY(), 20));
            if (actor.hasMoved()) {
                actorHash.remove(genKey(oldpos.getX(), oldpos.getY()));
                Point newpos = actor.getPos().copy();
                actorHash.put(genKey(newpos.getX(), newpos.getY()), actor);
            }
            actor.moveHandled();
        }
    }

    @Override
    public SensesPackage getPlayerSenses() {
        return senses.shadowCasting(player_pos.getX(), player_pos.getY(), 20);//TODO implement view distance system
    }

    @Override
    public boolean inBounds(int x, int y) {
        if (x < 0 || x >= sizex || y < 0 || y >= sizey) {
            return false;
        }
        return true;
    }


    @Override
    public HashMap<Integer, Tile> getTileMap() {
        return level;
    }

    @Override
    public ArrayList<Actor> getActors() {
        return actors;

    }

    @Override
    public HashMap<Integer, Actor> getActorHash() {
        return actorHash;
    }
}
