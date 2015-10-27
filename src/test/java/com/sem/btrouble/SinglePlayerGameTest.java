package com.sem.btrouble;

import com.sem.btrouble.game.AbstractGame;
import com.sem.btrouble.game.SinglePlayerGame;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.observering.Direction;
import com.sem.btrouble.tools.DataLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        DataLoader dt = new DataLoader(DataLoader.STANDARD_LOCATION);
        Room room = dt.loadRoom(1);
        player = new Player(0f, 0f);
        game = new SinglePlayerGame(room);
        game.addPlayer(player);

        List<Bubble> bubbleList = new ArrayList<>();
        bubbleList.add(new Bubble(3, 500, 200));
        game.spawnBubbles(bubbleList);

        assertFalse(game.isLevelRunning());
        game.startGame();
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
        }
        for(int i = 0; i < 10; i++) {
            float oldCenterX = player.getCenterX();
            game.movePlayer(player, Direction.RIGHT, 10);
            assertTrue(player.getCenterX() > oldCenterX);
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
