package utility;

public class Point {
    int x;
    int y;
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Point add(Point b){
        return new Point(x+b.getX(),y+b.getY());
    }
    public void push(Point dir){
        x+=dir.x;
        y+=dir.y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
