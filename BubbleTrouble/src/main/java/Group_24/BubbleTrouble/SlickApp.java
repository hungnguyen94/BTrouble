package Group_24.BubbleTrouble;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;

/**
 * Application running the game.
 *
 */
@SuppressWarnings("serial")
public class SlickApp extends BasicGame
{

    private Image player;

	public SlickApp(String gamename) {
        super(gamename);
    }

    public void init(GameContainer gc) throws SlickException {
        player = new Image("sprites/Player.png");
    }

    public void update(GameContainer gc, int delta) throws SlickException {

    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawString("Test", 10, 30);
        player.draw(100, 300);
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
