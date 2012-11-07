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

import logic.ActionHandler;
import utility.Point;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: hankbrobeck
 * Date: 11/3/12
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class AI {
    Actor parent;
    boolean pathing;
    ArrayList<Point> path;
    int pathindex;

    public AI(Actor parent) {
        this.parent = parent;
    }

    public void run(SensesPackage senses) { //TODO this code is potentially buggy because it doesn't check collision always.
        if (senses.playerVisible()) { //Check if the player is visible and update the path as necessary

            path = Pathfinding.pathTo(parent.getPos(), senses.getPlayerLocation(), senses);
            pathing = true;
            pathindex = 1;

        }
        boolean moved = followPath();//Follow the path (if there is one)
        //Can it attack?
        if (!moved && senses.playerVisible()) {
            if (parent.getPos().adjacentTo(senses.getPlayerLocation())) {
                ActionHandler.attackPlayer(parent);
            }
        }


    }

    private boolean followPath() {

        if (pathing) {
            if (path.size() == 0) {
                return false;
            }
            if (pathindex == path.size() - 1) {//we finished the path
                pathing = false;
                return false;
            }
            parent.moveTo(path.get(pathindex));
            pathindex++;
            return true;
        }
        return false;
    }

}
