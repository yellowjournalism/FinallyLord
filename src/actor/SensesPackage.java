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
import world.tile.Tile;

import java.util.HashMap;

//Used to return a single
public class SensesPackage {
    private HashMap<Integer, Actor> actorHashMap;
    private HashMap<Integer, Tile> tileHashMap;
    int sizex;

    public SensesPackage(HashMap<Integer, Actor> actorHashMap, HashMap<Integer, Tile> tileHashMap, int sizex) {
        this.actorHashMap = actorHashMap;
        this.tileHashMap = tileHashMap;
        this.sizex = sizex;
    }

    public SensesPackage(int sizex) {
        actorHashMap = new HashMap<Integer, Actor>();
        tileHashMap = new HashMap<Integer, Tile>();
        this.sizex = sizex;
    }

    public HashMap<Integer, Actor> getActors() {
        return actorHashMap;
    }

    public HashMap<Integer, Tile> getTiles() {
        return tileHashMap;
    }

    public void putActor(int key, Actor a) {
        actorHashMap.put(key, a);
    }

    public void putTile(int key, Tile t) {
        tileHashMap.put(key, t);
    }

    public int genKey(int x, int y) {
        return x * sizex + y;
    }

    public int genKey(Point p) {
        return p.getX() * sizex + p.getY();
    }
}
