package Group_24.BubbleTrouble;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import javax.swing.*;

/**
 * Application running the game.
 *
 */
@SuppressWarnings("serial")
public class SlickApp extends BasicGame
{
  
  private Image background;

	public SlickApp(String gamename) {
        super(gamename);
    }

    public void init(GameContainer gc) throws SlickException {
        Controller.startNewGame(gc);
        background = new Image("Sprites/background.png");
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        Controller.update();
        for(Player player: Model.getPlayers()) {
          player.move(gc, delta);
      }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        background.draw(0,0);
        
        for(Player player: Model.getPlayers()) {
            player.draw();
            g.setColor(Color.white);
            g.drawString("[Score " + player.getScore() + ", Lives " + player.getLives() + "]", 10, 30);
        }
        for(Bubble bubble: Model.getBubbles()) {
            g.setColor(Color.white);
            g.fill(bubble);
            g.draw(bubble);
        }
        for(Rectangle wall: Model.getCurrentRoom().getWalls()) {
            g.setColor(Color.green);
            g.fill(wall);
            g.draw(wall);
        }
        for(Rectangle floor: Model.getCurrentRoom().getFloors()) {
            g.setColor(Color.blue);
            g.fill(floor);
            g.draw(floor);
        }
    }

    public static void main(String[] args) {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SlickApp("Bubble Trouble"));
            appgc.setDisplayMode(843, 596, false);
            //appgc.setShowFPS(false);
            appgc.setVSync(true);
            appgc.setAlwaysRender(true);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SlickApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}