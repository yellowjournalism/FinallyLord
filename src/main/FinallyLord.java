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

import logic.GameLogic;
import org.newdawn.slick.*;


public class FinallyLord extends BasicGame {
    GameLogic gameLogic;

    public FinallyLord() {
        super("Finally Lord");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameLogic = new GameLogic();
        gameContainer.getGraphics().setBackground(new Color(0.1f, 0.1f, 0.1f));
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        gameLogic.update(gameContainer);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        gameLogic.render();
    }
}
