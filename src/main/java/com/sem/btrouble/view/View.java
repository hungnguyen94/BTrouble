package com.sem.btrouble.view;

import com.sem.btrouble.SlickApp;
import com.sem.btrouble.model.Timers;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.Player;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by rubenwiersma on 18-09-15.
 */
public class View {
    private TrueTypeFont font;
    private GameContainer gc;
    private Timers timers;
    private Rectangle timerBar;

    /**
     * View constructor, sets up initial attributes and loads the font.
     * 
     * @param gc
     *            GameContainer given by Slick2D
     * @param timers
     *            Timers object with the timers from the main app
     */
    public View(GameContainer gc, Timers timers) {
        this.gc = gc;
        this.timers = timers;
        timerBar = new Rectangle(200, gc.getHeight() - gc.getHeight() / 100 * 12,
                gc.getWidth() - 400, 25);

        // load font from a .ttf file
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Sprites/IndieFlower.ttf");

            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    inputStream);
            awtFont = awtFont.deriveFont(24f); // set font size
            font = new TrueTypeFont(awtFont, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws all the elements to the screen.
     * 
     * @param graphics
     *            Graphics object from Slick2D
     * @throws SlickException occurs when graphics are invalid
     */
    public void draw(Graphics graphics) throws SlickException {
        drawBackground(graphics);
        drawCountDown(graphics);
        drawPlayers(graphics);
        drawPowers(graphics);
        drawTimer(graphics);
        Model.getCurrentRoom().draw(graphics);

        drawLives(graphics);
        drawScore(graphics);
    }

    /**
     * Draws the background on the screen.
     * 
     * @param graphics
     *            Graphics object from Slick2D
     * @throws SlickException occurs when graphics are invalid
     */
    private void drawBackground(Graphics graphics) throws SlickException {
        graphics.setFont(font);
        Model.getCurrentRoom().getBackground().draw(0, 0);
    } 

    /**
     * Draw the count down timer on screen.
     * 
     * @param graphics
     *            Graphics object from Slick2D
     */
    private void drawCountDown(Graphics graphics) {
        if (timers.getCountdownRunning()) {
            graphics.drawString(
                    "Game starts in " + (timers.getCountdownTimeLeft() / 1000 + 1) + " seconds",
                    gc.getWidth() / 2 - 200, gc.getHeight() / 2 - 100);
        }
    }

    /**
     * Draw players on screen.
     * @param graphics the graphics
     * @throws SlickException occurs when graphics are invalid
     */
    private void drawPlayers(Graphics graphics) throws SlickException {
        for (Player player : Model.getPlayers()) {
            if(player.isAlive()) {
                player.draw(graphics);
            }
        }
    }

    /**
     * draw powers on screen.
     * @param graphics the graphics
     */
    private void drawPowers(Graphics graphics) {
    	for (PowerUp power : Model.getShortPower()) {
    		power.draw(graphics);
    	}
    }

    /**
     * Draw timer progress bar.
     * 
     * @param graphics
     *            Graphics object from Slick2D
     */
    private void drawTimer(Graphics graphics) {
        graphics.setColor(Color.darkGray);
        graphics.fillRect(
                timerBar.getX(), timerBar.getY(), (int) (timerBar.getWidth()
                        * timers.getLevelTimeLeft() / timers.getLevelMaxDuration()),
                timerBar.getHeight());
        graphics.setColor(Color.lightGray);
        graphics.draw(timerBar);

    }

    /**
     * Draw lives on screen.
     *
     * @throws SlickException
     *             when the lives could not be drawn.
     */
    private void drawLives(Graphics graphics) throws SlickException {
        SpriteSheet livesImage = new SpriteSheet("Sprites/lives_spritesheet.jpg", 381, 171);
        int lives = Model.getPlayers().get(0).getLives();
        if (lives >= 0) {
            if(!SlickApp.multiplayer()) {
                livesImage.getSprite(lives, 0).draw(190, 670, (float) 0.286);
            }
            else if(SlickApp.multiplayer()) {
                graphics.setColor(Color.white);
                graphics.drawString("Player 1: ", 190, 670);
                livesImage.getSprite(lives, 0).draw(310, 670, (float) 0.286);
                graphics.drawString("Player 2: ", 400, 670);
                livesImage.getSprite(Model.getPlayers().get(1).getLives(), 0).
                    draw(520, 670, (float) 0.286);
            }
        }
    }

    /**
     * Draw score on screen.
     *
     * @throws SlickException
     *             when the score could not be drawn.
     * @param graphics
     *            Graphics object from Slick2D
     */
    private void drawScore(Graphics graphics) throws SlickException {
        ArrayList<Player> players = Model.getPlayers();
        int sum = 0;
        graphics.setColor(Color.white);
        for (int i = 0; i < players.size(); i++) {
            String value = "Player " + (i + 1) + ": " 
                   + Model.getWallet(Model.getPlayers().get(i)).getValue();
            if (SlickApp.multiplayer() && SlickApp.versus()) {
                graphics.drawString(value, 900 + i * 190 - font.getWidth(value), 670);
            }
            sum += players.get(i).getWallet().getValue();
        }
        if (!SlickApp.versus() || !SlickApp.multiplayer()) {
            String score = "" + sum;
            graphics.drawString(score, 1090 - font.getWidth(score), 670);
        }
        
        //String score = "" + Model.getWallet(Model.getPlayers().get(0)).getValue();
        //graphics.drawString(score, 1090 - font.getWidth(score), 670);
    }

}