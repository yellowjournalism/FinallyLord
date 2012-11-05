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

import utility.Log;
import utility.Point;
import world.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: hankbrobeck
 * Date: 11/1/12
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class Pathfinding {
    private static Point _end;
    private static Point _start;

    public static ArrayList<Point> pathTo(Point start, Point end, SensesPackage senses) {
        HashMap<Integer, Tile> tiles = senses.getTiles();
        HashMap<Integer, Actor> actors = senses.getActors();
        HashMap<Integer, PathNode> openlist = new HashMap<Integer, PathNode>();
        HashMap<Integer, PathNode> closedlist = new HashMap<Integer, PathNode>();
        ArrayList<PathNode> surroundings = new ArrayList<PathNode>();
        PathNode nodestart = new PathNode(start, null, 0);
        openlist.put(senses.genKey(nodestart.getLocation()), nodestart);
        _start = start;
        _end = end;


        int maxattempts = 10000;
        boolean success = false;
        for (int a = 0; a < maxattempts; a++) {
            //check and see if the openlist has somehow been emptied
            //this means there is no path
            if (openlist.size() == 0) {
                Log.print("Pathfinding failure");
                break;
            }
            //find the node in the openlist that has the lowest "f" score
            ArrayList<PathNode> opennodes = new ArrayList<PathNode>(openlist.values());
            PathNode curlowest = opennodes.get(0);
            int lowestf = curlowest.getGscore();// + getHCost(_end, curlowest);
            for (int c = 0; c < opennodes.size(); c++) {

                PathNode check = opennodes.get(c);

                int checkf = check.getGscore() + getHCost(end, check);
                if (checkf < lowestf) {
                    curlowest = check;
                    lowestf = checkf;
                }
            }

            //Move the lowest f score node to the closed list
            openlist.remove(senses.genKey(curlowest.getLocation()));
            closedlist.put(senses.genKey(curlowest.getLocation()), curlowest);
            if (curlowest.getLocation().equals(end)) {//We made it!
                success = true;
                break;
            }
            //Add adjacent 8 squares
            surroundings.clear();
            surroundings.add(new PathNode(new Point(curlowest.getX() - 1, curlowest.getY()), curlowest, 10));
            surroundings.add(new PathNode(new Point(curlowest.getX() - 1, curlowest.getY() - 1), curlowest, 14));
            surroundings.add(new PathNode(new Point(curlowest.getX(), curlowest.getY() - 1), curlowest, 10));
            surroundings.add(new PathNode(new Point(curlowest.getX() + 1, curlowest.getY() - 1), curlowest, 14));
            surroundings.add(new PathNode(new Point(curlowest.getX() + 1, curlowest.getY()), curlowest, 10));
            surroundings.add(new PathNode(new Point(curlowest.getX() + 1, curlowest.getY() + 1), curlowest, 14));
            surroundings.add(new PathNode(new Point(curlowest.getX(), curlowest.getY() + 1), curlowest, 10));
            surroundings.add(new PathNode(new Point(curlowest.getX() - 1, curlowest.getY() + 1), curlowest, 14));

            for (int c = 0; c < surroundings.size(); c++) {
                PathNode node = surroundings.get(c);
                int nodeKey = senses.genKey(node.getLocation());
                boolean free = isEnd(node.getLocation());//is the point at the _end? If it is we need to add it to the
                // closed list, even if it normally shouldn't be
                if (!free) {
                    if (senses.free(node.getLocation())) {
                        if (senses.playerVisible()) {
                            if (!senses.getPlayerLocation().equals(node.getLocation())) {
                                free = true;
                            }
                        }
                    }
                }
                boolean inclosed = closedlist.containsKey(nodeKey);//is it in the closed list already?
                boolean inopen = openlist.containsKey(nodeKey);//is it in the open list?where is it?
                if (free & !inclosed & !inopen) {//The node is free and new, add it to the open list
                    openlist.put(nodeKey, node);
                } else if (inopen) {//this node is in the open list, is there a faster path through curlowest ?
                    PathNode compnode = openlist.get(nodeKey);
                    if (node.getGscore() < compnode.getGscore()) {//The path is faster, switch it out;
                        openlist.remove(nodeKey);
                        openlist.put(nodeKey, node);

                    }

                }


            }


        }
        ArrayList<Point> path = new ArrayList<Point>();
        if (success) {
            closedlist.get(senses.genKey(end)).addParents(path);
            path = reverseArray(path);

        }
        return path;

    }

    public static ArrayList<Point> reverseArray(ArrayList<Point> array) {
        ArrayList<Point> newarray = new ArrayList<Point>();
        for (int c = array.size() - 1; c >= 0; c--) {
            newarray.add(array.get(c));
        }
        return newarray;

    }

    public static int pointInArray(Point p, ArrayList<PathNode> array) {
        for (int c = 0; c < array.size(); c++) {
            if (p.equals(array.get(c).getLocation())) {
                return c;
            }
        }
        return -1;

    }

    public static boolean isEnd(Point p) {
        if (p.equals(_end)) {
            return true;
        }
        return false;
    }

    //    public int getHCost(Point _end, PathNode node) {
//        int h;
//        Point nodepos = node.getLocation();
//        int xDistance = (int) Math.abs(nodepos.x - _end.x);
//        int yDistance = (int) Math.abs(nodepos.y - _end.y);
//        if (xDistance > yDistance) {
//            h = 14 * yDistance + 10 * (xDistance - yDistance);
//        } else {
//            h = 14 * xDistance + 10 * (yDistance - xDistance);
//        }
//        return h;
//    }
    public static int getHCost(Point end, PathNode node) {
        int h;
        int x = node.getX();
        int y = node.getY();
        h = (10 * (Math.abs(x - end.getX()) + Math.abs(y - end.getY())));
        return h;
    }

}
