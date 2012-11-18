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

package entity;

import logic.Command;
import render.SpriteID;

/**
 * Created with IntelliJ IDEA.
 * User: hankbrobeck
 * Date: 11/8/12
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Controller {
    private Entity parent;
    private SpriteID spriteID;
    boolean passable, opaque;

    public Controller(Entity e) {
        parent = e;
    }

    public abstract void update();

    public abstract void command(Command command);

    public boolean isPassable() {
        return passable;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public abstract SpriteID getSpriteID();


}
