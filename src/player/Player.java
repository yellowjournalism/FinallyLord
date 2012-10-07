package player;

import utility.Point;

public class Player {
    Point pos;
    public Player(Point p){
        pos=p;
    }
    public Point getPos(){
        return pos;
    }
    public void move(Point dir){
        pos.push(dir);
    }
}
