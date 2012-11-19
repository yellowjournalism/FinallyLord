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

package entity.controllers;

import entity.Entity;
import logic.Command;
import render.SpriteID;

/**
 * Created with IntelliJ IDEA.
 * User: hankbrobeck
 * Date: 11/8/12
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class DoorController extends Controller {
    boolean open;
    SpriteID opensprite, closedsprite;

    public DoorController(Entity e, SpriteID opensprite, SpriteID closedsprite) {
        super(e);
        open = false;
        passable = false;
        opaque = true;
        this.opensprite = opensprite;
        this.closedsprite = closedsprite;


    }

    @Override
    public void update() {

    }

    @Override
    public void command(Command command) {
        switch (command) {
            case OPEN:
                if (open) {
                    open = false;
                    passable = false;
                    opaque = true;
                    spriteID = closedsprite;
                } else {
                    open = true;
                    passable = true;
                    opaque = false;
                    spriteID = opensprite;
                }

        }
    }

}
