/*
 * This file is part of Finally Lord.
 *
 *     Finally Lord is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Finally Lord is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Finally Lord.  If not, see <http://www.gnu.org/licenses/>.
 */

package render;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Hank
 * Date: 4/2/12
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
//Its simple, holds the ID of a specific sprite to lookup with SpriteMap
//Might just replace with an all-encompassing "Point" class later, idk.
public class SpriteID implements Serializable {
    public int x, y;

    public SpriteID(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
