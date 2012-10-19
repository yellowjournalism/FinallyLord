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
        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int y = cy - radius; y <= cy + radius; y++) {
                if (distance(x, cx, y, cy) <= radius) {
                    int dx = Math.abs(x2 - x1);
                    int dy = Math.abs(y2 - y1);

                    int sx = (x1 < x2) ? 1 : -1;
                    int sy = (y1 < y2) ? 1 : -1;

                    int err = dx - dy;

                    while (true) {
                        framebuffer.setPixel(x1, y1, Vec3.one);

                        if (x1 == x2 && y1 == y2) {
                            break;
                        }

                        int e2 = 2 * err;

                        if (e2 > -dy) {
                            err = err - dy;
                            x1 = x1 + sx;
                        }

                        if (e2 < dx) {
                            err = err + dx;
                            y1 = y1 + sy;
                        }
                    }
                }
            }
        }

    }


}
