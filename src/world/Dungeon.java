package world;

import utility.Point;
import world.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class Dungeon extends Map{
    HashMap<Integer,Tile> level; //TODO replace this with proper level class for multiple level dungeons
    Point player_pos;

    int sizex,sizey;
    public Dungeon(Point p){
        player_pos=p;
        level=new HashMap<Integer, Tile>();
        sizex=20;
        sizey=20;
        generate();
    }
    public int genKey(int x,int y){
        return x*sizex+y;
    }
    private void generate(){
        for(int x=0;x<sizex;x++){

            for(int y=0;y<sizey;y++){
                if(x==0||x==sizex-1){
                    level.put(genKey(x,y),Tile.WALL_STONE);
                }
                else if(y==0||y==sizey-1){
                    level.put(genKey(x,y),Tile.WALL_STONE);
                }
                else{
                    level.put(genKey(x,y),Tile.FLOOR_STONE);
                }

            }
        }
    }

    @Override
    public HashMap<Integer, Tile> getTileMap() {
        return level;
    }
}
