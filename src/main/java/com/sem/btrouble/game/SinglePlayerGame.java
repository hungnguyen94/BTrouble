package com.sem.btrouble.game;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.observering.LevelObserver;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractGame class.
 */
public class SinglePlayerGame extends AbstractGame {
    private Player player;

    /**
     * Constructor for a game.
     * @param room Room
     */
    public SinglePlayerGame(Room room) {
        super(room);

    }

    /**
     * Constructor for a game.
     * @param room Room.
     * @param view View connected to this controller.
     */
    public SinglePlayerGame(Room room, LevelObserver view) {
        super(room, view);
    }

    /**
     * Code in this method is run in the game loop.
     */
    @Override
    public void runGameLoop() {

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


    /**
     * Spawns bubbles in the level.
     */
    @Override
    public void spawnBubbles() {
//        bubbleController.addBubble(new Bubble(3, 500, 200).split());
        List<Bubble> bubbleList = new ArrayList<>();
        bubbleList.add(new Bubble(3, 100, 250));
        bubbleList.add(new Bubble(3, 300, 190));
        bubbleList.add(new Bubble(3, 700, 150));
        getLevel().addBubble(bubbleList);
    }

    /**
     * This method is called when a level is won.
     */
    @Override
    public void levelWon() {
        getLevel().stop();
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
