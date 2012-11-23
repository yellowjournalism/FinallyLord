package logic;

import main.Game;

public class GameStateHandler {
    static int currentstate = 1;
    static int MAINGAME = 0;
    static int MAINMENU = 1;
    static Game game;

    public static void init(Game g) {
        game = g;
    }

    public synchronized static void goToGame() {
        game.enterState(MAINGAME);
    }

    public synchronized static void goToMenu() {
        game.enterState(MAINMENU);
    }


}
