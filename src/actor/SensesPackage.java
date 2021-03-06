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

import entity.Entity;
import utility.Point;
import world.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

//Used to return a single
public class SensesPackage {
    private HashMap<Integer, Actor> actorHashMap;
    private HashMap<Integer, Tile> tileHashMap;
    private HashMap<Integer, ArrayList<Entity>> entities;
    private boolean playervisible;
    private Point playerlocation;
    int sizex;

    public SensesPackage(HashMap<Integer, Actor> actorHashMap, HashMap<Integer, Tile> tileHashMap, int sizex) {
        this.actorHashMap = actorHashMap;
        this.tileHashMap = tileHashMap;
        this.sizex = sizex;
    }

    public SensesPackage(int sizex) {
        actorHashMap = new HashMap<Integer, Actor>();
        tileHashMap = new HashMap<Integer, Tile>();
        entities = new HashMap<Integer, ArrayList<Entity>>();
        this.sizex = sizex;
        playervisible = false;
    }

    public HashMap<Integer, Actor> getActors() {
        return actorHashMap;
    }

    public HashMap<Integer, Tile> getTiles() {
        return tileHashMap;
    }

    public HashMap<Integer, ArrayList<Entity>> getEntities() {
        return entities;
    }

    public void putActor(int key, Actor a) {
        actorHashMap.put(key, a);
    }

    public void putTile(int key, Tile t) {
        tileHashMap.put(key, t);
    }

    public void putEntities(int key, ArrayList<Entity> ents) {
        entities.put(key, ents);
    }

    public int genKey(int x, int y) {
        return x * sizex + y;
    }

    public int genKey(Point p) {
        return p.getX() * sizex + p.getY();
    }

    public void playerLoc(Point p) {
        playervisible = true;
        playerlocation = p.copy();
    }

    public boolean playerVisible() {
        return playervisible;
    }

    public Point getPlayerLocation() {
        return playerlocation;
    }


    public boolean free(Point p) {//This does not count the player (yet) TODO make this count the player REMEMBER THE SIMILAR CODE IN THE PATHFINDING
        int key = genKey(p);
        boolean tilefree = false;
        boolean actorfree = true;
        if (tileHashMap.containsKey(key)) {
            tilefree = tileHashMap.get(key).isPassable();
        }
        if (actorHashMap.containsKey(key)) {
            actorfree = false;
        }


        return tilefree && actorfree;
    }
}
