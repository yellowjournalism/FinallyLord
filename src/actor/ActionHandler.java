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

package actor;

import utility.Point;
import world.Map;
import world.tile.Tile;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: hankbrobeck
 * Date: 10/15/12
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActionHandler {
    Player player;
    Map activemap;

    public ActionHandler(Player p) {
        player = p;
    }

    public void setActiveMap(Map amap) {
        activemap = amap;
    }

    public boolean attemptPlayerMove(Point dir) {
        HashMap<Integer, Tile> tiles = activemap.getTileMap();
        HashMap<Integer, Actor> actors = activemap.getActorHash();
        Point newloc = player.getPos().copy();
        newloc.push(dir);
        int newkey = activemap.genKey(newloc.getX(), newloc.getY());
        if (tiles.containsKey(newkey)) {
            if (tiles.get(newkey).isPassable()) {
                if (!actors.containsKey(newkey)) {
                    player.move(dir);
                    return true;
                }
            }
        }
        return false;
    }


}
