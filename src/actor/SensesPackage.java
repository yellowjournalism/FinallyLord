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

import world.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

//Used to return a single
public class SensesPackage {
    private HashMap<Integer, Actor> actorHashMap;
    private HashMap<Integer, Tile> tileHashMap;

    public SensesPackage(HashMap<Integer, Tile> tileHashMap, HashMap<Integer, Actor> actorHashMap) {
        this.actorHashMap = actorHashMap;
        this.tileHashMap = tileHashMap;
    }

    public HashMap<Integer, Actor> getActors() {
        return actorHashMap;
    }

    public ArrayList<Actor> getActorArray() {
        return new ArrayList<Actor>(actorHashMap.values());
    }

    public HashMap<Integer, Tile> getTiles() {
        return tileHashMap;
    }
}
