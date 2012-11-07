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

import render.ActorSprite;
import render.SpriteID;

public enum ActorType {
    chicken(ActorSprite.chicken, 1, 1, 1, 1, 1, 1),
    goblin(ActorSprite.goblin, 4, 14, 6, 4, 3, 4);
    private SpriteID spriteID;
    int[] stats;

    ActorType(SpriteID sprite, int STR, int DEX, int CON, int INT, int WIS, int CHA) {
        spriteID = sprite;
        stats = new int[]{STR, DEX, CON, INT, WIS, CHA};//Make sure STAT gets updated if this does
    }

    public SpriteID getSpriteID() {
        return spriteID;
    }

    public int[] getStats() {
        return stats;
    }

}
