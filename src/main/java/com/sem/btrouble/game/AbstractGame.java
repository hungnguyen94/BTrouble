package com.sem.btrouble.game;

import com.sem.btrouble.controller.Level;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Rope;
import com.sem.btrouble.model.RopeFactory;
import com.sem.btrouble.observering.Direction;
import com.sem.btrouble.observering.LevelObserver;
import com.sem.btrouble.view.GameState;
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
    private GameState view;

    /**
     * Constructor for a game.
     */
    public AbstractGame(Room room) {
        loadLevel(room);
    }

    /**
     * Constructor for a game.
     * @param room Room.
     * @param view View connected to this controller.
     */
    public AbstractGame(Room room, GameState view) {
        this.view = view;
        loadLevel(room);
    }

    /**
     * Returns the level.
     * @return Returns the level.
     */
    protected Level getLevel() {
        return level;
    }

    /**
     * Loads a new level with the given room.
     * @param room Room of the level that should be loaded.
     */
    public void loadLevel(Room room) {
        room.loadRoom();
        this.level = new Level(room);
        level.registerObserver(this);
        level.registerObserver(view);
    }

    /**
     * Start the level in a separate thread.
     * AbstractGame is independent from the view, and can be run headless.
     */
    public void startLevel() {
        Timer t1 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level.start();
                System.out.println("LEVEL STARTED");
            }
        });
        t1.setRepeats(false);
        t1.setInitialDelay(2000);
        t1.start();
        spawnBubbles();
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

    // PlayerController?
    public void fireRope(Player player) {
        if(player.canFireRope() && player.isAlive()) {
            Rope rope = RopeFactory.makeRope(player);
            level.addRope(rope);
        }
    }

    /**
     * Spawns bubbles in the level.
     */
    public void spawnBubbles() {
//        bubbleController.addBubble(new Bubble(3, 500, 200).split());
        List<Bubble> bubbleList = new ArrayList<>();
        bubbleList.add(new Bubble(3, 100, 250));
        bubbleList.add(new Bubble(3, 300, 190));
        bubbleList.add(new Bubble(3, 700, 150));
        level.addBubble(bubbleList);
    }

    /**
     * Check if the level is running.
     * @return True if level is running.
     */
    public boolean isLevelRunning() {
        return level.isLevelRunning();
    }

    public void draw(Graphics graphics) {
        level.draw(graphics);
    }
}
