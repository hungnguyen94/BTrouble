package com.sem.btrouble.view;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

import com.sem.btrouble.controller.Controller;
import com.sem.btrouble.event.ExceptionEvent;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Timers;
import com.sem.btrouble.tools.GameObserver;
import com.sem.btrouble.tools.Logger;


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
   * Initialize method of the slick2d library
   * 
   * @param gc
   *          - gamecontainer
   * @throws SlickException
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
   * Update method of the slick2d library
   * 
   * @param gc
   *          - gamecontainer
   * @param delta
   *          - speed of the player
   * @throws SlickException
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
   *          - gamecontainer
   * @param g
   *          - graphics handler
   * @throws SlickException
   */
  public void render(GameContainer gc, Graphics g) throws SlickException {
    g.setFont(font);
    background = new Image("Sprites/background.jpg");
    background.draw(0, 0);

    drawCountDown(gc, g);
    drawPlayers(g);
    drawBubbles(g);
    drawTimer(g);

    drawLives(g);
    drawScore(g);
  }

  /**
   * Draw the countdown on screen.
   * 
   * @param gc
   *          - gamecontainer
   * @param g
   *          - graphics handler
   */
  private void drawCountDown(GameContainer gc, Graphics g) {
    if (timers.getCountdownRunning()) {
      g.drawString("Game starts in " + (timers.getCountdownTimeLeft() / 1000) + " seconds",
          gc.getWidth() / 2 - 200, gc.getHeight() / 2 - 100);
    }
  }

  /**
   * Draw players on screen.
   * 
   * @param g
   *          - graphics handler
   */
  private void drawPlayers(Graphics g) throws SlickException {
    for (Player player : Model.getPlayers()) {
      player.draw();
    }
  }

  /**
   * Draw bubbles on screen.
   * 
   * @param g
   *          - graphics handler
   */
  private void drawBubbles(Graphics g) {
    for (Bubble bubble : Model.getBubbles()) {
      g.setAntiAlias(true);
      g.setColor(Color.black);
      g.fill(bubble);
      g.draw(bubble);
    }
  }

  /**
   * Draw timer progress bar.
   * 
   * @param g
   *          - Graphics handler
   */
  private void drawTimer(Graphics g) {
    g.setColor(Color.darkGray);
    g.fillRect(timerBar.getX(), timerBar.getY(),
        (int) (timerBar.getWidth() * timers.getLevelTimeLeft() / timers.getLevelMaxDuration()),
        timerBar.getHeight());
    g.setColor(Color.lightGray);
    g.draw(timerBar);

  }

  /**
   * Draw lives on screen
   * 
   * @param g
   *          - graphics handler
   * @throws SlickException
   */
  private void drawLives(Graphics g) throws SlickException {
//    SpriteSheet livesImage = new SpriteSheet("Sprites/lives_spritesheet.png", 381, 171);
//    int lives = Model.getPlayers().get(0).getLives();
//    if (lives >= 0) {
//      g.drawImage(livesImage.getSprite(lives, 0).getScaledCopy(109, 49), 225, 851);
//    }
  }

  /**
   * Draw score on screen.
   * 
   * @param g
   *          - graphics handler
   * @throws SlickException
   */
  private void drawScore(Graphics g) throws SlickException {
    g.setColor(Color.white);
    String score = "" + Model.getPlayers().get(0).getScore();
    g.drawString(score, 891 - font.getWidth(score), 851);
  }

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