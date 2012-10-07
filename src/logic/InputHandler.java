package logic;/*
 * This file is part of Finally Lord.
 *
 *     Finally Lord is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Finally Lord is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Finally Lord.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.newdawn.slick.Input;

import utility.Point;

//You can guess what this does
public class InputHandler {
    private boolean hasmoved;
    private Point movement;
    private boolean hascmd;
    private Command command;
    private Input input;

    public void update(Input input){
        this.input=input;
        hasmoved=false;
        hascmd=false;
        pollMovement();


    }
    public void pollMovement(){
        if(input.isKeyPressed(Input.KEY_NUMPAD8)){
            hasmoved=true;
            movement=new Point(0,-1);
        }
        if(input.isKeyPressed(Input.KEY_NUMPAD9)){
            hasmoved=true;
            movement=new Point(1,-1);
        }
        if(input.isKeyPressed(Input.KEY_NUMPAD6)){
            hasmoved=true;
            movement=new Point(1,0);
        }
        if(input.isKeyPressed(Input.KEY_NUMPAD3)){
            hasmoved=true;
            movement=new Point(1,1);
        }
        if(input.isKeyPressed(Input.KEY_NUMPAD2)){
            hasmoved=true;
            movement=new Point(0,1);
        }
        if(input.isKeyPressed(Input.KEY_NUMPAD1)){
            hasmoved=true;
            movement=new Point(-1,1);
        }
        if(input.isKeyPressed(Input.KEY_NUMPAD4)){
            hasmoved=true;
            movement=new Point(-1,0);
        }
        if(input.isKeyPressed(Input.KEY_NUMPAD7)){
            hasmoved=true;
            movement=new Point(-1,-1);
        }
    }
    public boolean hasMoved(){
        return hasmoved;
    }
    public Point getMovement(){
        return movement;
    }
    public boolean hasCommand(){
        return hascmd;
    }
    public Command getCommand(){
        return command;
    }

}
