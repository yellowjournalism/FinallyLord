package actor;

import render.ActorSprite;
import render.SpriteID;

public enum ActorType {
    chicken(ActorSprite.chicken),
    goblin(ActorSprite.goblin);
    private SpriteID spriteID;

    ActorType(SpriteID sprite) {
        spriteID = sprite;
    }

    public SpriteID getSpriteID() {
        return spriteID;
    }
}
