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
            if (senses.free(senses.getPlayerlocation())) {
                path = Pathfinding.pathTo(parent.getPos(), senses.getPlayerlocation(), senses);
                pathing = true;
                pathindex = 0;
            }
        }
        followPath();//Follow the path (if there is one)


    }

    private void followPath() {

        if (pathing) {
            if (pathindex == path.size()) {//we finished the path
                pathing = false;
                return;
            }
            parent.moveTo(path.get(pathindex));
            pathindex++;
        }
    }

}
