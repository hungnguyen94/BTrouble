package Group_24.BubbleTrouble;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;

import javax.swing.*;

/**
 * Application running the game.
 *
 */
@SuppressWarnings("serial")
public class SlickApp extends BasicGame
{

	public SlickApp(String gamename) {
        super(gamename);
    }

    public void init(GameContainer gc) throws SlickException {
        Controller.startNewGame(gc);
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        Controller.update();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        for(Player player: Model.getPlayers()) {
            player.draw();
            g.setColor(Color.white);
            g.drawString("[Score " + player.getScore() + ", Lives " + player.getLives() + "]", 10, 30);
        }
        for(Bubble bubble: Model.getBubbles()) {
            g.setColor(Color.white);
            g.fill(bubble.getSprite());
            g.draw(bubble.getSprite());
        }
        for(Wall wall: Model.getCurrentRoom().getWalls()) {
            g.setColor(Color.green);
            g.fill(wall.getSprite());
            g.draw(wall.getSprite());
        }
        for(Floor floor: Model.getCurrentRoom().getFloors()) {
            g.setColor(Color.blue);
            g.fill(floor.getSprite());
            g.draw(floor.getSprite());
        }
    }

    public static void main(String[] args) {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SlickApp("Bubble Trouble"));
            appgc.setDisplayMode(800, 500, false);
            //appgc.setShowFPS(false);
            appgc.setAlwaysRender(true);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SlickApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}