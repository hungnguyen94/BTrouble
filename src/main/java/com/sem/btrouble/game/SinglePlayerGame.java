package com.sem.btrouble.game;

import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.observering.LevelObserver;

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
     * Start the level.
     */
    @Override
    public void startLevel() {
        getLevelTimer().start();
        getLevel().start();
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
            getLevelTimer().stop();
            System.out.println("Level restart");
            //startGame();
        } else {
            // End game.
            System.out.println("Game has ended");
        }
    }
}
