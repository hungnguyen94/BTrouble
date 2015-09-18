package com.sem.btrouble.view;

import com.sem.btrouble.controller.Controller;
import com.sem.btrouble.event.ExceptionEvent;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Timers;
import com.sem.btrouble.tools.GameObserver;
import com.sem.btrouble.tools.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.io.InputStream;

/**
 * Application running the game.
 *
 */
public class SlickApp extends BasicGame {
  private Image background;
  private Timers timers;
  private Rectangle timerBar;
  private TrueTypeFont font;
  private static Controller controller;
  private GameObserver observer;

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

    controller.addObserver(observer);

    timers = controller.getTimers();
    timerBar = new Rectangle(200, gc.getHeight() - 114, gc.getWidth() - 400, 25);
    timers.restartTimer();

    // load font from a .ttf file
    try {
      InputStream inputStream = ResourceLoader.getResourceAsStream("Sprites/IndieFlower.ttf");

      Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
      awtFont = awtFont.deriveFont(24f); // set font size
      font = new TrueTypeFont(awtFont, false);

    } catch (Exception e) {
      e.printStackTrace();
    }
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
      controller.update();
      for (Player player : Model.getPlayers()) {
        player.move(gc, delta);
      }
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
    graphics.setFont(font);
    background = new Image("Sprites/background.jpg");
    background.draw(0, 0);

    drawCountDown(gc, graphics);
    drawPlayers(graphics);
    drawBubbles(graphics);
    drawTimer(graphics);

    drawLives(graphics);
    drawScore(graphics);
  }

  /**
   * Draw the count down timer on screen.
   * 
   * @param gc
   *          should be the GameContainer containing the game
   * @param graphics
   *          should be the graphics handler of the game
   */
  private void drawCountDown(GameContainer gc, Graphics graphics) {
    if (timers.getCountdownRunning()) {
      graphics.drawString("Game starts in " + (timers.getCountdownTimeLeft() / 1000) + " seconds",
          gc.getWidth() / 2 - 200, gc.getHeight() / 2 - 100);
    }
  }

  /**
   * Draw players on screen.
   * 
   * @param graphics
   *          should be the graphics handler of the game
   */
  private void drawPlayers(Graphics graphics) throws SlickException {
    for (Player player : Model.getPlayers()) {
      player.draw();
    }
  }

  /**
   * Draw bubbles on screen.
   * 
   * @param graphics
   *          should be the graphics handler of the game
   */
  private void drawBubbles(Graphics graphics) {
    for (Bubble bubble : Model.getBubbles()) {
      graphics.setAntiAlias(true);
      graphics.setColor(Color.black);
      graphics.fill(bubble);
      graphics.draw(bubble);
    }
  }

  /**
   * Draw timer progress bar.
   * 
   * @param graphics
   *          should be the graphics handler of the game
   */
  private void drawTimer(Graphics graphics) {
    graphics.setColor(Color.darkGray);
    graphics.fillRect(timerBar.getX(), timerBar.getY(),
        (int) (timerBar.getWidth() * timers.getLevelTimeLeft() / timers.getLevelMaxDuration()),
        timerBar.getHeight());
    graphics.setColor(Color.lightGray);
    graphics.draw(timerBar);

  }

  /**
   * Draw lives on screen.
   * 
   * @param graphics
   *          should be the graphics handler of the game
   * @throws SlickException
   *           when the lives could not be drawn.
   */
  private void drawLives(Graphics graphics) throws SlickException {
    SpriteSheet livesImage = new SpriteSheet("Sprites/lives_spritesheet.jpg", 381, 171);
    int lives = Model.getPlayers().get(0).getLives();
    if (lives >= 0) {
      livesImage.getSprite(lives, 0).draw(225, 851, (float) 0.286);
    }
  }

  /**
   * Draw score on screen.
   * 
   * @param graphics
   *          should be the graphics handler of the game
   * @throws SlickException
   *           when the score could not be drawn.
   */
  private void drawScore(Graphics graphics) throws SlickException {
    graphics.setColor(Color.white);
    String score = "" + Model.getPlayers().get(0).getScore();
    graphics.drawString(score, 891 - font.getWidth(score), 851);
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