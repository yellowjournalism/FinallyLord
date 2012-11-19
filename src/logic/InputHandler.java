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

package logic;

import org.newdawn.slick.Input;
import utility.Point;

//You can guess what this does
public class InputHandler {
    private boolean hasdirect;
    private Point direction;
    private boolean hascmd;
    private Command command;
    private Input input;

    public void update(Input input) {
        this.input = input;
        hasdirect = false;
        hascmd = false;
        pollMovement();


    }

    private boolean checkKeys(int a, int b) {
        if (input.isKeyPressed(a) || input.isKeyPressed(b)) {
            return true;
        }
        return false;
    }

    private boolean checkKey(int a) {
        return input.isKeyPressed(a);
    }

    public void pollMovement() {
        if (checkKeys(Input.KEY_NUMPAD8, Input.KEY_K)) {
            hasdirect = true;
            direction = new Point(0, -1);
        }
        if (checkKeys(Input.KEY_NUMPAD9, Input.KEY_U)) {
            hasdirect = true;
            direction = new Point(1, -1);
        }
        if (checkKeys(Input.KEY_NUMPAD6, Input.KEY_L)) {
            hasdirect = true;
            direction = new Point(1, 0);
        }
        if (checkKeys(Input.KEY_NUMPAD3, Input.KEY_N)) {
            hasdirect = true;
            direction = new Point(1, 1);
        }
        if (checkKeys(Input.KEY_NUMPAD2, Input.KEY_J)) {
            hasdirect = true;
            direction = new Point(0, 1);
        }
        if (checkKeys(Input.KEY_NUMPAD1, Input.KEY_B)) {
            hasdirect = true;
            direction = new Point(-1, 1);
        }
        if (checkKeys(Input.KEY_NUMPAD4, Input.KEY_H)) {
            hasdirect = true;
            direction = new Point(-1, 0);
        }
        if (checkKeys(Input.KEY_NUMPAD7, Input.KEY_Y)) {
            hasdirect = true;
            direction = new Point(-1, -1);
        }
        if (checkKeys(Input.KEY_SPACE, Input.KEY_A)) {
            hascmd = true;
            command = Command.OPEN;
        }
        if (checkKey(Input.KEY_PERIOD)) {
            hascmd = true;
            command = Command.DOWN;
        }
        if (checkKey(Input.KEY_COMMA)) {
            hascmd = true;
            command = Command.UP;
        }
    }

    public boolean hasDirect() {
        return hasdirect;
    }

    public Point getDirection() {
        return direction;
    }

    public boolean hasCommand() {
        return hascmd;
    }

    public Command getCommand() {
        return command;
    }

}
