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

package actor;

import render.SpriteID;
import utility.Point;

public class Actor {
    Point position;
    SpriteID spriteID;

    public Actor(Point pos, ActorType type) {
        spriteID = type.getSpriteID();
        position = pos;
    }

    public void update() {

    }

    public SpriteID getSpriteID() {
        return spriteID;
    }
}
