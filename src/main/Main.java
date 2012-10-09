package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new FinallyLord());
        app.setDisplayMode(1280, 720, true);
        app.start();

    }
}
