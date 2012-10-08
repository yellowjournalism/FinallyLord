package actor;

import render.SpriteID;
import utility.Point;

public class Actor {
    Point position;
    SpriteID spriteID;

    public Actor(Point pos, ActorType type) {
        spriteID = type.getSpriteID();
        position = pos;
    }

    public void update() {

    }

    public SpriteID getSpriteID() {
        return spriteID;
    }
}
