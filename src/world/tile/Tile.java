package world.tile;

import render.EnviroSprite;
import render.SpriteID;

public enum Tile {
    FLOOR_STONE(EnviroSprite.stone_floor_1,true,false),
    WALL_STONE(EnviroSprite.stone_wall,false,true);
    SpriteID spriteID;
    boolean passable;
    boolean opaque;
    Tile(SpriteID spriteID,boolean ispass,boolean isopaq){
        this.spriteID=spriteID;
        passable=ispass;
        opaque=isopaq;
    }
    public SpriteID getSpriteID(){
        return spriteID;
    }
    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public void setOpaque(boolean opaque) {
        this.opaque = opaque;
    }
}
