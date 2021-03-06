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

public class Senses {
    private HashMap<Integer, Tile> tileHashMap;
    private HashMap<Integer, Actor> actorHashMap;
    private HashMap<Integer, ArrayList<Entity>> entities;
    private SensesPackage currentSenses;
    private int sizex;
    Point playerloc;
    private int[][] mult = {
            {1, 0, 0, -1, -1, 0, 0, 1},
            {0, 1, -1, 0, 0, -1, 1, 0},
            {0, 1, 1, 0, 0, -1, -1, 0},
            {1, 0, 0, 1, -1, 0, 0, -1}
    };

    public Senses(Point playerloc, HashMap<Integer, Tile> tileHashMap, HashMap<Integer, Actor> actorHashMap, HashMap<Integer, ArrayList<Entity>> entities, int sizex) {
        this.tileHashMap = tileHashMap;
        this.actorHashMap = actorHashMap;
        this.entities = entities;
        this.sizex = sizex;
        this.playerloc = playerloc;
    }

    private int genKey(int x, int y) {
        return x * sizex + y;
    }

    private double distance(int x1, int y1, int x2, int y2) {
        double c = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
        return Math.sqrt(c);
    }

    private void light(int x, int y) {
        int key = genKey(x, y);
        if (actorHashMap.containsKey(key)) {
            currentSenses.putActor(key, actorHashMap.get(key));
        }
        if (tileHashMap.containsKey(key)) {
            currentSenses.putTile(key, tileHashMap.get(key));
        }
        if (playerloc.equals(new Point(x, y))) {
            currentSenses.playerLoc(playerloc);
        }
        if (entities.containsKey(key)) {
            currentSenses.putEntities(key, entities.get(key));
        }

    }

    private boolean isBlocked(int x, int y) {
        int key = genKey(x, y);
        boolean tileblocked = true;
        if (tileHashMap.containsKey(key)) {
            tileblocked = tileHashMap.get(key).isOpaque();
        }
        boolean entblocked = false;
        if (entities.containsKey(key)) {
            ArrayList<Entity> ents = entities.get(key);
            for (int c = 0; c < ents.size(); c++) {
                Entity ent = ents.get(c);
                if (!ent.isOpaque()) {
                    entblocked = true;
                }
            }
        }
        return entblocked && tileblocked;
    }

    private void castLight(int cx, int cy, int row, double start,
                           double end, int radius, int xx, int xy, int yx, int yy, int depth) {
        //Recursive lightcasting function
        if (start < end) {
            return;
        }
        int radius_squared = radius * radius;
        for (int j = row; j <= radius; j++) {
            int dx = -j - 1;
            int dy = -j;
            boolean blocked = false;
            double newStartSlope = 0.0;
            while (dx <= 0) {
                dx += 1;
                //Translate the dx,dy coordinates into map coordinates
                int x = cx + dx * xx + dy * xy;
                int y = cy + dx * yx + dy * yy;
                //lslope and rslope store the slopes of the left and right
                //extremites of the square were considering:
                double lslope = (dx - 0.5) / (dy + 0.5);
                double rslope = (dx + 0.5) / (dy - 0.5);
                if (start < rslope) {
                    continue;
                } else if (end > lslope) {
                    break;
                } else {
                    //our light beam is touching this square; light it:
                    if (dx * dx + dy * dy < radius_squared) {
                        light(x, y);
                    }
                    if (blocked) {
                        //We are scanning a row of blocked squares:
                        if (isBlocked(x, y)) {
                            newStartSlope = rslope;
                            continue;
                        } else {
                            blocked = false;
                            start = newStartSlope;
                        }
                    } else {
                        if (isBlocked(x, y) && j < radius) {
                            //this is a blocking square, start a child scan
                            blocked = true;
                            castLight(cx, cy, j + 1, start, lslope,
                                    radius, xx, xy, yx, yy, depth + 1);
                            newStartSlope = rslope;
                        }
                    }
                }
            }
            if (blocked) {
                break;
            }
        }
    }

    public SensesPackage shadowCasting(int cx, int cy, int radius) {
        //Calculate lit squares from the given location and radius
        currentSenses = new SensesPackage(sizex);
        for (int oct = 0; oct < 8; oct++) {
            castLight(cx, cy, 1, 1.0, 0.0, radius, mult[0][oct], mult[1][oct],
                    mult[2][oct], mult[3][oct], 0);
        }
        currentSenses.getTiles().put(genKey(cx, cy), tileHashMap.get(genKey(cx, cy)));//Add the players position
        return currentSenses;
    }


}
