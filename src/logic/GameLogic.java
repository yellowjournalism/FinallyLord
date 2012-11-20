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

import actor.Player;
import org.newdawn.slick.GameContainer;
import render.Render;
import render.ui.GUI;
import utility.Log;
import utility.Point;
import world.Dungeon;
import world.Map;

public class GameLogic {
    InputHandler inputHandler;
    Player player;
    Render render;
    Dungeon dungeon;
    Map activeMap;
    GameContainer gameContainer;
    GUI gui;


    public GameLogic(GameContainer gc) {
        inputHandler = new InputHandler();
        player = new Player(new Point(1, 1));
        render = new Render(player.getPos());
        dungeon = new Dungeon(player);
        activeMap = dungeon;
        ActionHandler.init(player);
        ActionHandler.setActiveMap(activeMap);
        gameContainer = gc;
        gui = new GUI(gameContainer);

    }

    public void update(GameContainer gc) {
        gui.update();
        inputHandler.update(gc.getInput());
        boolean flagsenses = false;
        if (inputHandler.hasDirect()) {
            if (ActionHandler.attemptPlayerMove(inputHandler.getDirection())) {
                flagsenses = true;
                //Movement successful, run actor turns turns;
                activeMap.runTurns();
                Log.print("Player HP" + player.getCharacterSheet().getHP());

            }
        }
        //TODO find a way to work commands into turns
        if (inputHandler.hasCommand()) {
            //TODO There needs to be a system for directional/aimed commands
            flagsenses = true;
            activeMap.sendPlayerCommand(inputHandler.getCommand());
        }
        activeMap.update();
        if (flagsenses) {
            render.flagSenses();
        }


    }

    public void render() {
        //TODO handle player state to switch between maps
        //REMEMBER TO INCLUDE UPDATING THE ACTIONHANDLER!!!!
        render.render(activeMap);
        gui.render();
    }

}
