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

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by IntelliJ IDEA.
 * User: Hank
 * Date: 4/2/12
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */

//This Class will help with more optimal rendering
//Instead of ArrayLists with and a static ID list, can replace needless copying with this.
public class SpriteMap {
    private SpriteSheet spriteSheet;
    private int x, y;

    public SpriteMap(String path, int x, int y) {
        this.x = x;
        this.y = y;
        loadImage(path);

    }

    private void loadImage(String path) {
        try {
            Image img = new Image(path);
            img.setFilter(Image.FILTER_NEAREST);
            spriteSheet = new SpriteSheet(img, x, y);

        } catch (SlickException e) {
            System.out.println("Cannot find Image for SpriteMap!...or maybe there's a problem with the filter, idk.");
            System.exit(0);
        }
    }

    public Image getSprite(SpriteID spriteID) {

        Image img = spriteSheet.getSprite(spriteID.x, spriteID.y);

        return img;
    }
}
