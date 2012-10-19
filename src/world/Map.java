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
import world.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Map {
    public abstract HashMap<Integer, Tile> getTileMap();

    public abstract ArrayList<Actor> getActors();

    public abstract HashMap<Integer, Actor> getActorHash();

    public abstract int genKey(int x, int y);

    public abstract void update();

    public abstract void runTurns();

}
