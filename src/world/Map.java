package world;


import utility.Point;
import world.tile.Tile;

import java.util.HashMap;

public abstract class Map {
    public abstract HashMap<Integer, Tile> getTileMap();
    public abstract int genKey(int x,int y);

}
