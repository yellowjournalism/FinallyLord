package world;


import actor.Actor;
import world.tile.Tile;

import java.util.HashMap;

public abstract class Map {
    public abstract HashMap<Integer, Tile> getTileMap();

    public abstract HashMap<Integer, Actor> getActorMap();

    public abstract int genKey(int x, int y);

}
