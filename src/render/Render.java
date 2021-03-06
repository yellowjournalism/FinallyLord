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

package render;

import actor.Actor;
import actor.SensesPackage;
import entity.Entity;
import org.newdawn.slick.Image;
import render.ui.MessageBox;
import utility.Log;
import utility.Point;
import world.Map;
import world.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class Render {
    Point playerpos;
    int tilesx, tilesy, tilesize;
    float ppt;
    float scale;
    SpriteMap environment;
    SpriteMap actor_sprites;
    SpriteMap obj_sprites;
    MessageBox messageBox;
    SensesPackage playerSenses;
    boolean updateSenses;

    public Render(Point player_pos) {
        playerpos = player_pos;
        tilesx = 26;//This is the screen size modifier, in number of tiles on either side of the player
        tilesy = 18;
        environment = new SpriteMap("data/img/lofi_environment.png", 8, 8);
        actor_sprites = new SpriteMap("data/img/lofi_char.png", 8, 8);
        tilesize = tilesy * 2 + 1;//Each side plus the middle for the player
        ppt = 540f / (float) tilesize;
        scale = ppt / 8f;
        updateSenses = true;
        messageBox = new MessageBox(scale);


    }

    public void flagSenses() {
        updateSenses = true;
    }

    public void render(Map map) {

        if (updateSenses) {
            long startTime = System.currentTimeMillis();
            playerSenses = map.getPlayerSenses();
            long endTime = System.currentTimeMillis();
            Log.print("Shadowcasting time: " + (endTime - startTime));
            updateSenses = false;
        }
//        HashMap<Integer, Tile> tileHashMap = map.getTileMap();
//        ArrayList<Actor> actors = map.getActors();
//        HashMap<Integer, Actor> actorHashMap = map.getActorHash();
        HashMap<Integer, Tile> tileHashMap = playerSenses.getTiles();
        HashMap<Integer, Actor> actorHashMap = playerSenses.getActors();
        HashMap<Integer, ArrayList<Entity>> entities = playerSenses.getEntities();


        Point top_left = new Point(playerpos.getX() - tilesx, playerpos.getY() - tilesy);

        //image.draw((float) ((x - top_left.x) * ppt), (float) ((y - top_left.y) * ppt), scale)
        for (int x = top_left.getX(); x <= playerpos.getX() + tilesx; x++) {
            for (int y = top_left.getY(); y <= playerpos.getY() + tilesy; y++) {
                if (map.inBounds(x, y)) {  //TODO Make a better system for drawing in bounds only
                    //Tiles
                    Tile tile = tileHashMap.get(map.genKey(x, y));
                    if (tile != null) {
                        Image image = environment.getSprite(tile.getSpriteID());
                        image.draw((float) ((x - top_left.getX()) * ppt), (float) ((y - top_left.getY()) * ppt), scale);
                    }
                    //Entities
                    ArrayList<Entity> ents = entities.get(map.genKey(x, y));
                    if (ents != null && ents.size() > 0) {
                        Entity entity = ents.get(0);
                        Image image = environment.getSprite(entity.getSpriteID());
                        image.draw((float) ((x - top_left.getX()) * ppt), (float) ((y - top_left.getY()) * ppt), scale);

                    }
                    //Actors
                    Actor actor = actorHashMap.get(map.genKey(x, y));
                    if (actor != null) {
                        Image image = actor_sprites.getSprite(actor.getSpriteID());
                        image.draw((float) ((x - top_left.getX()) * ppt), (float) ((y - top_left.getY()) * ppt), scale);
                    }
                }

            }
        }

        Image player = actor_sprites.getSprite(ActorSprite.player);

        player.draw((float) ((playerpos.getX() - top_left.getX()) * ppt), (float) ((playerpos.getY() - top_left.getY()) * ppt), scale);
        messageBox.render();
    }

    private boolean inBounds(int x1, int y1, int x2, int y2, int x, int y) {
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            return true;
        }
        return false;
    }
}
