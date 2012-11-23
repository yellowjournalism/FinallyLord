package main;

import de.matthiasmann.twl.Button;
import logic.GameStateHandler;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import render.ui.BasicTWLGameState;
import render.ui.RootPane;

public class MainMenu extends BasicTWLGameState {
    Button btn;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    protected RootPane createRootPane() {
        RootPane rp = super.createRootPane();


        btn = new Button("Finally Lord");
        btn.addCallback(new Runnable() {
            public void run() {
//                Log.print("It works!");
                GameStateHandler.goToGame();

            }
        });

        rp.add(btn);
        return rp;
    }

    @Override
    protected void layoutRootPane() {
        btn.adjustSize();
        btn.setPosition(100, 100);
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
