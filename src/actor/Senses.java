package actor;

import world.tile.Tile;

import java.util.HashMap;

public class Senses {
    private HashMap<Integer, Tile> tileHashMap;
    private HashMap<Integer, Actor> actorHashMap;

    public Senses(HashMap<Integer, Tile> tileHashMap, HashMap<Integer, Actor> actorHashMap) {
        this.tileHashMap = tileHashMap;
        this.actorHashMap = actorHashMap;
    }


}
