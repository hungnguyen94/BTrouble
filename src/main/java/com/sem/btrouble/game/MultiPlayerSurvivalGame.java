package com.sem.btrouble.game;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.observering.LevelObserver;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * SinglePlayer survival game class.
 */
public class MultiPlayerSurvivalGame extends AbstractGame {
    private static final int TIME = 500;
    private List<Player> players;
    private int waveTime;

    /**
     * Constructor for a game.
     * @param room The room to play
     */
    public MultiPlayerSurvivalGame(Room room) {
        super(room);
        players = new ArrayList<>();
    }

    /**
     * Constructor for a game.
     * @param room Room.
     * @param view View connected to this controller.
     */
    public MultiPlayerSurvivalGame(Room room, LevelObserver view) {
        super(room, view);
        waveTime = TIME;
        players = new ArrayList<>();
    }

    /**
     * Code in this method is run in the game loop.
     */
    @Override
    public void runGameLoop() {
        if(waveTime <= 0) {
            spawnRandomBubbles();
            waveTime = TIME;
        } else {
            --waveTime;
        }

    }

    /**
     * This method adds a player to the game.
     *
     * @param player This player will be added.
     */
    @Override
    public void addPlayer(Player player) {
        players.add(player);
        getLevel().addPlayer(player);
    }

    /**
     * Spawn random bubbles.
     */
    private void spawnRandomBubbles() {
        List<Bubble> bubbleList = new ArrayList<>();
        for(int i = 0; i < Math.random()*5; i++) {
            bubbleList.add(new Bubble((int) (Math.random()*3), (float) (Math.random()*720), 250));
        }
        getLevel().addBubble(bubbleList);
    }
    
    /**
     * Returns true if theres a player with
     * a life left.
     * @return True if a player has a life.
     */
    private boolean anyPlayerHasLife() {
        for(Player player : players) {
            if(player.isAlive()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method is called when a level is lost.
     */
    @Override
    public void levelLost() {
        getLevel().stop();
        if(anyPlayerHasLife()) {
            System.out.println("Level restart");
            getLevel().loseLevel();
        } else {
            // End game.
            System.out.println("Game has ended");
        }
    }

    /**
     * Draw the graphics.
     * @param graphics the graphics
     */
    public void draw(Graphics graphics) {
        getLevel().draw(graphics);
    }

    @Override
    public void levelWon() {
        System.out.println("Level won");
    }
}
