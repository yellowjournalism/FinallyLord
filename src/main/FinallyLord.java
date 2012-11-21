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

package main;


import de.matthiasmann.twl.Button;
import logic.GameLogic;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import render.ui.BasicTWLGameState;
import render.ui.RootPane;
import utility.Log;


public class FinallyLord extends BasicTWLGameState {
    GameLogic gameLogic;
    private Button btn;


    public FinallyLord() {

    }


    @Override
    protected RootPane createRootPane() {
        RootPane rp = super.createRootPane();
        rp.setTheme("mainMenu");

        btn = new Button("Hello World");
        btn.addCallback(new Runnable() {
            public void run() {
                Log.print("It works!");
            }
        });

        rp.add(btn);
        return rp;
    }

    @Override
    protected void layoutRootPane() {
        btn.adjustSize();
        btn.setPosition(100, 100);
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameLogic = new GameLogic(gameContainer);
        gameContainer.getGraphics().setBackground(new Color(0.1f, 0.1f, 0.1f));
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        gameLogic.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        gameLogic.update(gameContainer);
    }
}
