/*
 * This file is part of Finally Lord.
 *
 *      Finally Lord is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      Finally Lord is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with Finally Lord.  If not, see <http://www.gnu.org/licenses/>.
 */

package entity;

import effect.EffectPackage;
import entity.controllers.Controller;
import logic.Command;
import render.SpriteID;
import utility.Point;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: hankbrobeck
 * Date: 10/24/12
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity {
    protected Point position;
    protected Controller controller;
    protected ArrayList<Command> availableCommands;
    protected ArrayList<EntityFlag> flags;
    protected ArrayList<EffectPackage> effectPackages;


    protected Entity(Point p) {
        position = p;
        availableCommands = new ArrayList<Command>();
        flags = new ArrayList<EntityFlag>();
        effectPackages = new ArrayList<EffectPackage>();

    }

    public void turnUpdate() {

    }

    public void update() {
        effectPackages.clear();
        controller.update();
    }

    public void addEffectPackage(EffectPackage effectPackage) {
        effectPackages.add(effectPackage);
    }

    public ArrayList<EffectPackage> getEffectPackages() {
        return effectPackages;
    }

    protected void flagCommand(Command c) {
        availableCommands.add(c);
    }

    public ArrayList<Command> getAvailableCommands() {
        return availableCommands;
    }

    public void addFlag(EntityFlag flag) {
        flags.add(flag);
    }

    public boolean hasFlag(EntityFlag flag) {
        for (int x = 0; x < flags.size(); x++) {
            if (flag == flags.get(x)) {
                return true;
            }
        }
        return false;
    }

    public SpriteID getSpriteID() {
        return controller.getSpriteID();
    }

    public abstract void interact(Command c);

    public Point getPosition() {
        return position;
    }

    public boolean isPassable() {
        return controller.isPassable();
    }

    public boolean isOpaque() {
        return controller.isOpaque();
    }


}
