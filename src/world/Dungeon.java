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
import actor.Player;
import actor.SensesPackage;
import entity.types.StoneStair;
import utility.Log;
import utility.Point;
import utility.Rand;
import world.tile.Tile;

import java.util.ArrayList;

public class Dungeon extends Map {


    public Dungeon(Player p) {
        super(p, 20, 20);
        player = p;


        generate();
    }

    private void generate() {
        //TODO make better generation code
        for (int c = 0; c < levels.size(); c++) {
            Level clevel = levels.get(c);
            for (int x = 0; x < sizex; x++) {

                for (int y = 0; y < sizey; y++) {
                    if (x == 0 || x == sizex - 1) {
                        clevel.tileHashMap.put(genKey(x, y), Tile.WALL_STONE);
                    } else if (y == 0 || y == sizey - 1) {
                        clevel.tileHashMap.put(genKey(x, y), Tile.WALL_STONE);
                    } else {
                        clevel.tileHashMap.put(genKey(x, y), Tile.FLOOR_STONE);

                    }


                }
            }
            //Either create stairs from previous level if it isn't the top floor;
            if (clevel.level != 0) {
                ArrayList<Point> upstairs = levels.get(clevel.level - 1).downStairLocs();
                for (int z = 0; z < upstairs.size(); z++) {
                    clevel.createEntity(new StoneStair(upstairs.get(z), true));
                }

            }
            //create a stair down
            if (clevel.level != levels.size() - 1) {
                int downstairx, downstairy;
                int runnum = 0;
                while (true) {
                    Log.print(runnum + "");
                    downstairx = Rand.randInt(2, sizex - 1);
                    downstairy = Rand.randInt(2, sizey - 1);
                    if (clevel.entityFree(downstairx, downstairy)) {
                        break;
                    }
                    runnum++;
                }
                clevel.createEntity(new StoneStair(new Point(downstairx, downstairy), false));
            }
            clevel.actorHashMap.put(genKey(10, 10), new Actor(new Point(10, 10), ActorType.goblin));
        }

    }


    @Override
    public SensesPackage getPlayerSenses() {
        return currentLevel.senses.shadowCasting(player.getPos().getX(), player.getPos().getY(), 20);//TODO implement view distance system
    }

    @Override
    public boolean inBounds(int x, int y) {
        if (x < 0 || x >= sizex || y < 0 || y >= sizey) {
            return false;
        }
        return true;
    }


}
