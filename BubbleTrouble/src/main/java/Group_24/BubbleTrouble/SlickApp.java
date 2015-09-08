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
        for(Player player: Model.getPlayers()) {
          player.move(gc, delta);
      }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        for(Player player: Model.getPlayers()) {
            player.draw();
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