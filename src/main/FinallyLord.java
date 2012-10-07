package main;

import logic.GameLogic;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class FinallyLord extends BasicGame {
    GameLogic gameLogic;
    public FinallyLord() {
        super("Finally Lord");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameLogic=new GameLogic();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        gameLogic.update(gameContainer);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        gameLogic.render();
    }
}
