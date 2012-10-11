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

package world.tile;

import render.EnviroSprite;
import render.SpriteID;

public enum Tile {
    FLOOR_STONE(EnviroSprite.stone_floor_1, true, false),
    WALL_STONE(EnviroSprite.stone_wall, false, true);
    SpriteID spriteID;
    boolean passable;
    boolean opaque;

    Tile(SpriteID spriteID, boolean ispass, boolean isopaq) {
        this.spriteID = spriteID;
        passable = ispass;
        opaque = isopaq;
    }

    public SpriteID getSpriteID() {
        return spriteID;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public void setOpaque(boolean opaque) {
        this.opaque = opaque;
    }
}
