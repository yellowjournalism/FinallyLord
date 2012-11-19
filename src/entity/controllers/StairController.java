package entity.controllers;

import effect.Effect;
import effect.EffectPackage;
import entity.Entity;
import logic.Command;
import render.SpriteID;

public class StairController extends Controller {
    boolean up;

    public StairController(Entity e, boolean up, SpriteID spriteID) {
        super(e);
        this.up = up; //what doesn't go up, must go down (not up means it moves down)
        this.spriteID = spriteID;
    }

    @Override
    public void update() {

    }

    @Override
    public void command(Command command) {
        if (up) {
            if (command == Command.UP) {
                parent.addEffectPackage(new EffectPackage(Effect.MOVE_UP, true, null));
            }
        } else {
            if (command == Command.DOWN) {
                parent.addEffectPackage(new EffectPackage(Effect.MOVE_DOWN, true, null));
            }
        }
    }


}
