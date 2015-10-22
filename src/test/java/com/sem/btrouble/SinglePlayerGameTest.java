package com.sem.btrouble;

import com.sem.btrouble.game.AbstractGame;
import com.sem.btrouble.game.SinglePlayerGame;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.observering.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a end to end test
 */
public class SinglePlayerGameTest {
    AbstractGame game;
    Player player;

    @Before
    public void setUp() {
        Room room = new Room();
        room.loadRoom();
        player = new Player(0f, 0f);
        game = new SinglePlayerGame(room);
        game.addPlayer(player);
        assertFalse(game.isLevelRunning());
        game.startLevel();
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void singlePlayerEndToEndTest() {
        game.updateGame();
        assertTrue(game.isLevelRunning());
        for(int i = 0; i < 10; i++) {
            float oldCenterX = player.getCenterX();
            game.movePlayer(player, Direction.LEFT, 10);
            assertTrue(player.getCenterX() < oldCenterX);
            game.updateGame();
        }
        for(int i = 0; i < 10; i++) {
            float oldCenterX = player.getCenterX();
            game.movePlayer(player, Direction.RIGHT, 10);
            assertTrue(player.getCenterX() > oldCenterX);
            game.updateGame();
        }
        assertTrue(player.canFireRope());
        game.fireRope(player);
        assertFalse(player.canFireRope());
        // Lose the game by getting hit by a bubble
        assertTrue(game.isLevelRunning());
        while(game.isLevelRunning()) {
            game.updateGame();
        }
        assertFalse(game.isLevelRunning());

    }
}
