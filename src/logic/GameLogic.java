package logic;

import org.newdawn.slick.GameContainer;
import player.Player;
import render.Render;
import utility.Point;
import world.Dungeon;

public class GameLogic {
    InputHandler inputHandler;
    Player player;
    Render render;
    Dungeon dungeon;

    public GameLogic() {
        inputHandler = new InputHandler();
        player = new Player(new Point(0, 0));
        render = new Render(player.getPos());
        dungeon = new Dungeon(player.getPos());
    }

    public void update(GameContainer gc) {
        inputHandler.update(gc.getInput());
        if (inputHandler.hasMoved()) {
            player.move(inputHandler.getMovement());
            //Log.print("Player Movement: "+player.getPos().getX()+","+player.getPos().getY());
        }
    }

    public void render() {
        //TODO handle player state to switch between maps
        render.render(dungeon);
    }

}
