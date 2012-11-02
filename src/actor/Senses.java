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

import java.util.HashMap;

public class Senses {
    private HashMap<Integer, Tile> tileHashMap;
    private HashMap<Integer, Actor> actorHashMap;
    private int sizex;

    public Senses(HashMap<Integer, Tile> tileHashMap, HashMap<Integer, Actor> actorHashMap, int sizex) {
        this.tileHashMap = tileHashMap;
        this.actorHashMap = actorHashMap;
        this.sizex = sizex;
    }

    private int genKey(int x, int y) {
        return x * sizex + y;
    }

    private double distance(int x1, int y1, int x2, int y2) {
        double c = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
        return Math.sqrt(c);
    }

    public SensesPackage getFOV(int cx, int cy, int radius) { //TODO replace this with a proper FOV algorithm
        HashMap<Integer, Tile> tiles = new HashMap<Integer, Tile>();
        HashMap<Integer, Actor> actors = new HashMap<Integer, Actor>();
        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int y = cy - radius; y <= cy + radius; y++) {
                if (distance(x, cx, y, cy) <= radius) {
                    int x1 = x;
                    int x2 = cx;
                    int y1 = y;
                    int y2 = cy;
                    int w = x2 - x1;
                    int h = y2 - y1;
                    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
                    if (w < 0) dx1 = -1;
                    else if (w > 0) dx1 = 1;
                    if (h < 0) dy1 = -1;
                    else if (h > 0) dy1 = 1;
                    if (w < 0) dx2 = -1;
                    else if (w > 0) dx2 = 1;
                    int longest = Math.abs(w);
                    int shortest = Math.abs(h);
                    if (!(longest > shortest)) {
                        longest = Math.abs(h);
                        shortest = Math.abs(w);
                        if (h < 0) dy2 = -1;
                        else if (h > 0) dy2 = 1;
                        dx2 = 0;
                    }
                    boolean fail = false;
                    int numerator = longest >> 1;
                    for (int i = 0; i <= longest; i++) {
                        int key = genKey(x1, y1);
                        if (tileHashMap.containsKey(key)) {
                            if (tileHashMap.get(key).isOpaque()) {
                                fail = true;
                                break;
                            }
                        }
                        numerator += shortest;
                        if (!(numerator < longest)) {
                            numerator -= longest;
                            x1 += dx1;
                            y1 += dy1;
                        } else {
                            x1 += dx2;
                            y1 += dy2;
                        }
                    }
                    int key = genKey(x, y);
                    if (!fail) {
                        if (tileHashMap.containsKey(key)) {
                            tiles.put(key, tileHashMap.get(key));
                        }
                        if (actorHashMap.containsKey(key)) {
                            actors.put(key, actorHashMap.get(key));
                        }
                    }
                }
            }
        }

        return new SensesPackage(tiles, actors);

    }


}
