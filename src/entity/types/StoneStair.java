package entity.types;

import entity.Entity;
import entity.EntityFlag;
import entity.controllers.StairController;
import logic.Command;
import render.EnviroSprite;
import utility.Log;
import utility.Point;

public class StoneStair extends Entity {
    boolean up;

    public StoneStair(Point p, boolean up) {
        super(p);
        this.up = up;
        if (up) {
            flagCommand(Command.UP);
            addFlag(EntityFlag.POINT_MOVE_UP);
            controller = new StairController(this, up, EnviroSprite.stone_stair_up);
        } else {
            flagCommand(Command.DOWN);
            addFlag(EntityFlag.POINT_MOVE_DOWN);
            controller = new StairController(this, up, EnviroSprite.stone_stair_down);
        }


    }

    @Override
    public void interact(Command c) {
        Log.print("received Command");
        controller.command(c);
    }
}
