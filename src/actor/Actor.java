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

package actor;

import render.SpriteID;
import utility.Point;

public class Actor {
    Point position;
    SpriteID spriteID;
    private boolean hasmoved;

    public Actor(Point pos, ActorType type) {
        spriteID = type.getSpriteID();
        position = pos;
    }

    public void update() {

    }

    public void runTurn(SensesPackage senses) {

    }

//    public void runTurn(SensesPackage senses) {
//        HashMap<Integer,Tile> tiles=senses.getTiles();
//        HashMap<Integer,Actor> actors=senses.getActors();
//        if (Rand.oneIn(1)) {
//            for(int c=0;c<50;c++){
//                int x,y;
//                float chance=Rand.nextFloat();
//                if(chance<(1f/3f)){
//                    x=-1;
//                }
//                else if(chance<2f/3f){
//                    x=0;
//                }
//                else{
//                    x=1;
//                }
//                chance=Rand.nextFloat();
//                if(chance<(1f/3f)){
//                    y=-1;
//                }
//                else if(chance<2f/3f){
//                    y=0;
//                }
//                else{
//                    y=1;
//                }
//                int key=senses.genKey(position.getX()+x,position.getY()+y);
//                if(tiles.containsKey(key)){
//                    if(tiles.get(key).isPassable()&&!actors.containsKey(key)){
//                        hasmoved = true;
//                        position = position.add(new Point(x, y));
//                        break;
//                    }
//                }
//
//            }
//        }
//    }

    public boolean hasMoved() {
        return hasmoved;
    }

    public void moveHandled() {
        hasmoved = false;
    }

    public SpriteID getSpriteID() {
        return spriteID;
    }

    public Point getPos() {
        return position;
    }
}
