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

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.slick2d.sound.SlickSoundDevice;
import de.lessvoid.nifty.slick2d.time.LWJGLTimeProvider;
import logic.GameLogic;
import org.newdawn.slick.*;


public class FinallyLord extends BasicGame {
    GameLogic gameLogic;
    Nifty nifty;

    public FinallyLord() {
        super("Finally Lord");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameLogic = new GameLogic();
        gameContainer.getGraphics().setBackground(new Color(0.1f, 0.1f, 0.1f));
        nifty = new Nifty(new SlickRenderDevice(gameContainer), new SlickSoundDevice(),
                new PlainSlickInputSystem(), new LWJGLTimeProvider());
        nifty.fromXml("data/UI/uitest.xml", "start");
//        new ScreenBuilder("HelloScreen") {
//            // This is a bit of Java magic.  We are defining the screen as an
//            // anonymous inner class, but we need to put some code inside the
//            // constructor of this anonymous class.  The use of {} without any
//            // declarations tells java that the code inside the block is to used
//            // as the constructor
//            {
//                // Each screen has one or more layers
//                layer(new LayerBuilder() {
//                    {
//                        // Layout the child elements in the middle
//                        childLayoutCenter();
//                        // Each layer can have one or more panel
//                        panel(new PanelBuilder() {
//                            {
//                                // We simply have an empty panel with a
//                                // translucent background
//                                alignCenter();
//                                valignCenter();
//                                childLayoutCenter();
//                                width(percentage(100));
//                                height(percentage(100));
//
//                                // color is expressed using the RGBA notation
//                                backgroundColor("#ffffff88");
//                            }
//                        });
//                    }
//                });
//            }
//            // Build this screen and let the nifty object manage it
//        }.build(nifty);
//        nifty.gotoScreen("HelloScreen");

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        gameLogic.update(gameContainer);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        gameLogic.render();
        nifty.render(false);

    }
}
