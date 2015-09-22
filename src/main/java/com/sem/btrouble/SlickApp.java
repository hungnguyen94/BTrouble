package com.sem.btrouble;

import java.io.IOException;

import com.sem.btrouble.controller.Controller;
import com.sem.btrouble.event.ExceptionEvent;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Timers;
import com.sem.btrouble.tools.GameObserver;
import com.sem.btrouble.tools.Logger;
import com.sem.btrouble.tools.SoundObserver;
import com.sem.btrouble.view.View;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Application running the game.
 *
 */
public class SlickApp extends BasicGame {
  private Timers timers;
  private static Controller controller;
  private static View view;
  private GameObserver observer;
  private SoundObserver soundObserver;
  private Audio wavEffect;

  public SlickApp(String gamename) {
    super(gamename);
  }

  /**
   * Initialize method of the slick2d library.
   * 
   * @param gc
   *          should be the GameContainer containing the game.
   * @throws SlickException
   *           when the game could not be initialized.
   */
  public void init(GameContainer gc) throws SlickException {
    controller = new Controller(gc);
    observer = new GameObserver(true);
    soundObserver = new SoundObserver();

    controller.addObserver(soundObserver);
    controller.addObserver(observer);

    timers = controller.getTimers();
    timers.restartTimer();

    view = new View(gc, timers);
    
    try {
	    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("Bubble_Trouble_Theme.wav"));
        } catch (IOException e) {
	    e.printStackTrace();
	}
	wavEffect.playAsSoundEffect(1.0f, 1.0f, true);
	SoundStore.get().poll(0);
  }

  /**
   * Update method of the slick2d library.
   * 
   * @param gc
   *          should be the GameContainer containing the game
   * @param delta
   *          should be an integer representing the speed of the player
   * @throws SlickException
   *           when the controller could not be updated
   */
  public void update(GameContainer gc, int delta) throws SlickException {
    if (!timers.getCountdownRunning()) {
      controller.update(delta);
    }
  }

  /**
   * Render method of the slick2d library.
   * 
   * @param gc
   *          should be the GameContainer containing the game
   * @param graphics
   *          should be the graphics handler of the game
   * @throws SlickException
   *           when an item could not be drawn.
   */
  public void render(GameContainer gc, Graphics graphics) throws SlickException {
    view.draw(graphics);
  }

  /**
   * Main class of the SlickApp.
   * 
   * @param args
   *          should be empty.
   */
  public static void main(String[] args) {
    try {

      AppGameContainer appgc;
      appgc = new AppGameContainer(new SlickApp("Bubble Trouble"));
      appgc.setDisplayMode(1123, 921, false);
      appgc.setShowFPS(false);
      appgc.setVSync(true);
      appgc.setTargetFrameRate(60);
      appgc.setAlwaysRender(true);
      appgc.start();

    } catch (SlickException ex) {
      Logger.log(new ExceptionEvent(ex, "initialisation of the game failed."));
    }
  }

  public static Controller getController() {
    return controller;
  }
}