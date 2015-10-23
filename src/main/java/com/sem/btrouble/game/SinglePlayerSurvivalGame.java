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
public class SinglePlayerSurvivalGame extends AbstractGame {
    private static final int TIME = 500;
    private Player player;
    private int waveTime;

    /**
     * Constructor for a game.
     */
    public SinglePlayerSurvivalGame(Room room) {
        super(room);

    }

    /**
     * Constructor for a game.
     * @param room Room.
     * @param view View connected to this controller.
     */
    public SinglePlayerSurvivalGame(Room room, LevelObserver view) {
        super(room, view);
        waveTime = TIME;
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
        this.player = player;
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
     * This method is called when a level is won.
     */
    @Override
    public void levelWon() {
        System.out.println("Level won");
    }

    /**
     * This method is called when a level is lost.
     */
    @Override
    public void levelLost() {
        getLevel().stop();
        if(player.hasLives()) {
            System.out.println("Level restart");
            //startGame();
        } else {
            // End game.
            System.out.println("Game has ended");
        }
    }

    public void draw(Graphics graphics) {
        getLevel().draw(graphics);
    }
}
