package com.sem.btrouble.controller;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.view.GameState;
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
     */
    public MultiPlayerSurvivalGame(Room room) {
        super(room);

    }

    /**
     * Constructor for a game.
     * @param room Room.
     * @param view View connected to this controller.
     */
    public MultiPlayerSurvivalGame(Room room, GameState view) {
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
            spawnBubbles();
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

    private void spawnRandomBubbles() {
        List<Bubble> bubbleList = new ArrayList<>();
        for(int i = 0; i < Math.random()*5; i++) {
            bubbleList.add(new Bubble((int)(Math.random()*3), (float)(Math.random()*720), 250));
        }
        getLevel().addBubble(bubbleList);
    }


    /**
     * Spawns bubbles in the level.
     */
    @Override
    public void spawnBubbles() {
//        bubbleController.addBubble(new Bubble(3, 500, 200).split());
        List<Bubble> bubbleList = new ArrayList<>();
        bubbleList.add(new Bubble(2, 100, 250));
        bubbleList.add(new Bubble(1, 300, 190));
        bubbleList.add(new Bubble(1, 700, 150));
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
     * This method is called when a level is won.
     */
    @Override
    public void levelWon() {
//        getLevel().stop();
        spawnBubbles();
        System.out.println("Level won");
    }

    /**
     * This method is called when a level is lost.
     */
    @Override
    public void levelLost() {
        getLevel().stop();
        if(anyPlayerHasLife()) {
            System.out.println("Level restart");
            //startLevel();
        } else {
            // End game.
            System.out.println("Game has ended");
        }
    }

    public void draw(Graphics graphics) {
        getLevel().draw(graphics);
    }
}
