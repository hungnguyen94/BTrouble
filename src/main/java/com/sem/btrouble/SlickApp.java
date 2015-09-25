package com.sem.btrouble;

import com.sem.btrouble.event.ExceptionEvent;
import com.sem.btrouble.model.GraphicSettings;
import com.sem.btrouble.tools.Logger;
import com.sem.btrouble.view.GameView;
import com.sem.btrouble.view.MenuView;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Application running the game.
 *
 */
public class SlickApp extends StateBasedGame {

	private static GraphicSettings graphics;

	/**
	 * Init the Slickapp.
	 * @param gamename is the name of the game
	 */
    public SlickApp(String gamename) {
        super(gamename);
    }

    /**
     * Main class of the SlickApp.
     *
     * @param args should be empty.
     */
    public static void main(String[] args) {
        try {
        	graphics = new GraphicSettings(true, true, 0);
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SlickApp("Bubble Trouble"));
            appgc.setDisplayMode(graphics.getResolutions().get(graphics.getCurrentResolution()).
            		getScreenWidth(), graphics.getResolutions().get(graphics.getCurrentResolution()).
            		getScreenHeight(), graphics.isFullscreen());
            appgc.setShowFPS(false);
            appgc.setVSync(true);
            appgc.setTargetFrameRate(60);
            appgc.setAlwaysRender(true);
            appgc.start();
        } catch (SlickException ex) {
            Logger.log(new ExceptionEvent(ex,
            		"initialisation of the game failed."));
        }
    }

    /**
     * Add states to the stateslist.
     * @param gc GameContainer reference
     * @throws SlickException when sprite is incorrect
     */
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MenuView());
        this.addState(new GameView());
    }
    
    /**
     * Returns the graphics settings.
     * @return the graphics
     */
    public static GraphicSettings returnGraphics() {
  	  return graphics;
    }
}
