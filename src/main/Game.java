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

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import render.ui.TWLStateBasedGame;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: hankbrobeck
 * Date: 11/20/12
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends TWLStateBasedGame {
    public Game(String name) {
        super(name);
    }

    @Override
    protected URL getThemeURL() {
        File themeFile = new File("data/ui/chutzpah.xml");
        URL url = null;
        try {
            url = themeFile.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new FinallyLord());
        enterState(0);
    }
}
