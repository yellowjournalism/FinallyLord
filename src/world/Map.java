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
import actor.Player;
import actor.SensesPackage;
import logic.Command;
import world.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Map {
    ArrayList<Level> levels;
    Level currentLevel;
    Player player;

    int sizex, sizey;

    public Map(Player p, int sizex, int sizey) {
        this.sizex = sizex;
        this.sizey = sizey;
        player = p;
        levels = new ArrayList<Level>();
        for (int x = 0; x < 10; x++) {
            levels.add(new Level(p, levels, x, sizex, sizey));
        }
        currentLevel = levels.get(0);

    }

    public HashMap<Integer, Tile> getTileMap() {
        return currentLevel.getTileHashMap();
    }


    public HashMap<Integer, Actor> getActorHash() {
        return currentLevel.getActorHashMap();
    }

    public int genKey(int x, int y) {
        return x * sizex + y;
    }

    public void update() {
        currentLevel.update();
        if (currentLevel.levelchange) {
            Level oldlevel = currentLevel;
            currentLevel = levels.get(oldlevel.changeto);
            oldlevel.levelChanged();

        }
    }

    public void runTurns() {
        currentLevel.runTurns();
    }

    public void sendPlayerCommand(Command command) {
        currentLevel.sendPlayerCommand(command);
    }

    public abstract SensesPackage getPlayerSenses();

    public abstract boolean inBounds(int x, int y);


}
