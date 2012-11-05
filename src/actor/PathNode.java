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
 * Date: 11/1/12
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class PathNode {
    private Point location;
    private PathNode parent;
    private int gscore;

    public PathNode(Point location, PathNode parent, int addg) {
        this.location = location;
        this.parent = parent;
        if (parent != null) {
            gscore = addg + parent.getGscore();
        }
    }

    public int getGscore() {
        return gscore;
    }

    public PathNode getParent() {
        return parent;
    }

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }

    public Point getLocation() {
        return location;
    }

    public void addParents(ArrayList<Point> points) {
        points.add(location);
        if (parent == null) {
            return;

        }

        parent.addParents(points);
    }
}
