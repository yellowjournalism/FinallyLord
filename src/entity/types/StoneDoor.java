package entity.types;

import entity.Entity;
import entity.controllers.DoorController;
import logic.Command;
import render.EnviroSprite;
import utility.Point;

public class StoneDoor extends Entity {
    public StoneDoor(Point p) {
        super(p);
        controller = new DoorController(this, EnviroSprite.door_stone_open, EnviroSprite.door_stone_closed);
        flagCommand(Command.OPEN);
    }

    @Override
    public void interact(Command c) {
        if (c == Command.OPEN) {
            controller.command(c);
        }

    }
}
