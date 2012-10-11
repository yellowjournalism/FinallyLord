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

package render.ui;


import org.newdawn.slick.*;

public enum FontType {
    STD;

    public static SpriteSheetFont getFont(FontType type, float scale) {
        switch (type) {
            case STD:
                SpriteSheet spriteSheet = getSpriteSheet("data/img/lofi_font_big_ascii_color.png", scale);
                return new SpriteSheetFont(spriteSheet, ' ');


        }
        return null;
    }

    private static SpriteSheet getSpriteSheet(String path, float scale) {

        try {
            SpriteSheet spriteSheet;
            Color col = new Color(0, 0, 255);
            Image img = new Image(path);
            if (scale != 1) {
                img = img.getScaledCopy(scale);
            }
            img.setFilter(Image.FILTER_NEAREST);
            spriteSheet = new SpriteSheet(img, 8 * (int) scale, 8 * (int) scale);
            return spriteSheet;

        } catch (SlickException e) {
            System.out.println("Couldn't find font file...");
            System.exit(0);
        }
        return null;
    }
}
