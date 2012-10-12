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

import actor.Actor;
import actor.ActorType;
import utility.Point;
import utility.Rand;
import world.tile.Tile;

import java.util.ArrayList;
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

    @Override
    public void update() {
        //Run actor AI
        ArrayList<Actor> actorlist = new ArrayList<Actor>(actors.values());
        for (int c = 0; c < actorlist.size(); c++) {
            Actor a = actorlist.get(c);
            a.update();
        }
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
