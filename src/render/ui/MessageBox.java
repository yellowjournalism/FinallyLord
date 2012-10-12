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


import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SpriteSheetFont;
import utility.Log;

import java.util.ArrayList;

public class MessageBox {
    ArrayList<String> log;
    SpriteSheet sheet;
    SpriteSheetFont font;
    float scale;

    public MessageBox(float scale) {
        log = new ArrayList<String>();
        this.scale = scale;
        try {
            sheet = new SpriteSheet("data/img/lofi_font_big_ascii_color.png", 8, 8);
            font = new SpriteSheetFont(sheet, ' ');
        } catch (SlickException e) {
            e.printStackTrace();
        }
        addText("This is a really long string that maybe one day will be automatically split but maybe not who knows." +
                "It turns out this string does get split which is pretty awesome considering the circumstances.");
        Log.setBox(this);
    }

    public void render() {
        //font.drawString(100,600,"lol\nwut");
        int start = log.size() - 22;
        if (start < 0) {
            start = 0;
        }
        int renderpos = 0;
        for (int c = start; c < log.size(); c++) {
            drawText(log.get(c), 0, 540 + renderpos * font.getLineHeight());
            renderpos++;
        }
    }

    public void drawText(String s, int x, int y) {
        font.drawString(x, y, s);

    }

    public void addText(String s) {
        String[] p = s.split("\n");
        for (int c = 0; c < p.length; c++) {
            String z = p[c];
            if (z.length() < 96) {//This string doesn't need to be automatically split
                log.add(z);
            } else {//This one does
                String s1 = z.substring(0, 96);
                String s2 = z.substring(96);
                for (int x = 95; x >= 0; x--) {
                    if (s1.charAt(x) == ' ') {
                        addText(s1.substring(0, x));
                        addText(s1.substring(x + 1) + s2);
                        break;
                    }
                }
            }
        }
    }

}
