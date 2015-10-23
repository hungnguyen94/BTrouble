//package com.sem.btrouble;
//
//import com.sem.btrouble.game.AbstractGame;
//import com.sem.btrouble.game.MultiPlayerSurvivalGame;
//import com.sem.btrouble.model.Model;
//import com.sem.btrouble.model.Player;
//import com.sem.btrouble.model.Room;
//import com.sem.btrouble.observering.Direction;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
///**
// * This is a end to end test
// */
//public class MultiplayerSurvivalTest {
//    AbstractGame game;
//    Player player1;
//    Player player2;
//
//    @Before
//    public void setUp() {
//        Model.init(100, 100);
//        Room room = new Room();
//        room = Model.getDataLoader().loadRoom(Model.getRoom_current());
//        player1 = new Player(0f, 0f);
//        player2 = new Player(1f, 1f);
//        game = new MultiPlayerSurvivalGame(room);
//        game.addPlayer(player1);
//        game.addPlayer(player2);
//        assertFalse(game.isLevelRunning());
//        game.startGame();
//        try {
//            Thread.sleep(3000);
//        } catch(InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void multiPlayerSurvivalTest() {
//        game.updateGame();
//        assertTrue(game.isLevelRunning());
//        for(int i = 0; i < 10; i++) {
//            float oldCenterX = player1.getCenterX();
//            game.movePlayer(player1, Direction.LEFT, 10);
//            assertTrue(player1.getCenterX() < oldCenterX);
//            game.fireRope(player1);
//            game.updateGame();
//        }
//        for(int i = 0; i < 10; i++) {
//            float oldCenterX = player2.getCenterX();
//            game.movePlayer(player2, Direction.RIGHT, 10);
//            assertTrue(player2.getCenterX() > oldCenterX);
//            game.fireRope(player2);
//            game.updateGame();
//        }
//
//        // Lose the game by getting hit by a bubble
//        assertTrue(game.isLevelRunning());
//        while(game.isLevelRunning()) {
//            game.updateGame();
//        }
//        assertFalse(game.isLevelRunning());
//
//    }
//}
