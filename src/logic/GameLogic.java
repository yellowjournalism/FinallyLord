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

package logic;

import actor.ActionHandler;
import actor.Player;
import org.newdawn.slick.GameContainer;
import render.Render;
import utility.Point;
import world.Dungeon;
import world.Map;

public class GameLogic {
    InputHandler inputHandler;
    Player player;
    Render render;
    Dungeon dungeon;
    Map activeMap;
    ActionHandler actionHandler;


    public GameLogic() {
        inputHandler = new InputHandler();
        player = new Player(new Point(0, 0));
        render = new Render(player.getPos());
        dungeon = new Dungeon(player);
        activeMap = dungeon;
        actionHandler = new ActionHandler(player);
        actionHandler.setActiveMap(activeMap);
    }

    public void update(GameContainer gc) {
        inputHandler.update(gc.getInput());
        activeMap.update();
        if (inputHandler.hasMoved()) {
            if (actionHandler.attemptPlayerMove(inputHandler.getMovement())) {
                render.flagSenses();
                //Movement successful, run actor turns turns;
                activeMap.runTurns();
            }
        }
    }

    public void render() {
        //TODO handle player state to switch between maps
        //REMEMBER TO INCLUDE UPDATING THE ACTIONHANDLER!!!!
        render.render(activeMap);
    }

}
