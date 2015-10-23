package com.sem.btrouble.game;

import com.sem.btrouble.controller.Level;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Rope;
import com.sem.btrouble.model.RopeFactory;
import com.sem.btrouble.observering.Direction;
import com.sem.btrouble.observering.LevelObserver;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractGame class.
 */
public abstract class AbstractGame implements LevelObserver {
    private Level level;
    private LevelObserver view;
    private Timer levelTimer;

    // Max time in seconds.
    public static final int MAX_GAME_DURATION = 3000;
    private int currentTime;

    /**
     * Constructor for a game.
     * @param room Room
     */
    public AbstractGame(Room room) {
        loadLevel(room);
        currentTime = 0;
    }

    /**
     * Constructor for a game.
     * @param room Room.
     * @param view View connected to this controller.
     */
    public AbstractGame(Room room, LevelObserver view) {
        this.view = view;
        loadLevel(room);
        currentTime = 0;
    }

    /**
     * Returns the level.
     * @return Returns the level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Loads a new level with the given room.
     * @param room Room of the level that should be loaded.
     */
    public void loadLevel(Room room) {
        this.level = new Level(room);
        level.registerObserver(this);
        level.registerObserver(view);
    }

    /**
     * Start the game.
     */
    public void startGame() {
        levelTimer = new Timer(100, new LevelTimerActionListener());
        Timer t1 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startLevel();
                System.out.println("LEVEL STARTED");
            }
        });
        t1.setRepeats(false);
        t1.setInitialDelay(2000);
        t1.start();
    }

    /**
     * Starts the level.
     */
    public void startLevel() {
        level.start();
    }

    /**
     * Does the same as startlevel, except running in a separate thread.
     */
    public void updateGame() {
        if(isLevelRunning()) {
            level.moveObjects();
            runGameLoop();
        }
    }

    /**
     * Code in this method is run in the game loop.
     */
    public abstract void runGameLoop();

    /**
     * This method adds a player to the game.
     * @param player This player will be added.
     */
    public abstract void addPlayer(Player player);

    /**
     * Move the player in the specified direction.
     * @param player Player to be moved.
     * @param direction Direction of the move.
     * @param delta delta
     */
    public void movePlayer(Player player, Direction direction, int delta) {
        if(player.isAlive() && isLevelRunning()) {
            switch(direction) {
                case LEFT:
                    player.moveLeft(delta);
                    break;
                case RIGHT:
                    player.moveRight(delta);
                    break;
            }
        }
    }

    /**
     * This fires a rope.
     * @param player Owner of the rope.
     */
    public void fireRope(Player player) {
        if(player.canFireRope() && player.isAlive()) {
            Rope rope = RopeFactory.makeRope(player);
            level.addRope(rope);
        }
    }

    /**
     * Check if the level is running.
     * @return True if level is running.
     */
    public boolean isLevelRunning() {
        return level.isLevelRunning();
    }

    /**
     * Getter for leveltimer.
     * @return leveltimer.
     */
    public Timer getLevelTimer() {
        return levelTimer;
    }

    /**
     * This draws the level.
     * @param graphics graphics handler.
     */
    public void draw(Graphics graphics) {
        level.draw(graphics);
        drawTimer(graphics);
    }

    /**
     * Draw timer progress bar.
     *
     * @param graphics
     *            Graphics object from Slick2D
     */
    private void drawTimer(Graphics graphics) {
        if(levelTimer.isRunning()) {
            graphics.setColor(Color.darkGray);
            graphics.fillRect(200, 634,
                    (int) (880 * (MAX_GAME_DURATION - currentTime) / MAX_GAME_DURATION),
                    20);
        }
    }

    /**
     * ActionListener for the levelTimer timer. Specifies what actions should be
     * executed every timer cycle.
     */
    class LevelTimerActionListener implements ActionListener {
        /**
         * Perform action.
         * @param event the event
         */
        public void actionPerformed(ActionEvent event) {
            if(currentTime < MAX_GAME_DURATION) {
                currentTime++;
            } else {
                level.loseLevel();
                levelTimer.stop();
            }
        }
    }
}
