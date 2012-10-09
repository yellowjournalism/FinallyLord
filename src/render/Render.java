package render;

import actor.Actor;
import org.newdawn.slick.Image;
import render.ui.MessageBox;
import utility.Point;
import world.Map;
import world.tile.Tile;

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

    public Render(Point player_pos) {
        playerpos = player_pos;
        tilesx = 26;//This is the screen size modifier, in number of tiles on either side of the player
        tilesy = 18;
        environment = new SpriteMap("data/img/lofi_environment.png", 8, 8);
        actor_sprites = new SpriteMap("data/img/lofi_char.png", 8, 8);
        tilesize = tilesy * 2 + 1;//Each side plus the middle for the player
        ppt = 540f / (float) tilesize;
        scale = ppt / 8f;
        messageBox = new MessageBox(scale);


    }

    public void render(Map map) {

        HashMap<Integer, Tile> tileHashMap = map.getTileMap();
        HashMap<Integer, Actor> actorHashMap = map.getActorMap();

        Point top_left = new Point(playerpos.getX() - tilesx, playerpos.getY() - tilesy);

        //image.draw((float) ((x - top_left.x) * ppt), (float) ((y - top_left.y) * ppt), scale)
        for (int x = top_left.getX(); x <= playerpos.getX() + tilesx; x++) {
            for (int y = top_left.getY(); y <= playerpos.getY() + tilesy; y++) {
                //Tiles
                Tile tile = tileHashMap.get(map.genKey(x, y));
                if (tile != null) {
                    Image image = environment.getSprite(tile.getSpriteID());
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
        Image player = actor_sprites.getSprite(ActorSprite.player);

        player.draw((float) ((playerpos.getX() - top_left.getX()) * ppt), (float) ((playerpos.getY() - top_left.getY()) * ppt), scale);
        messageBox.render();
    }
}
