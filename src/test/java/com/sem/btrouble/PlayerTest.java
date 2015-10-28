package com.sem.btrouble;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Rope;
import com.sem.btrouble.model.Wallet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class which tests the Player class.
 * 
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    private Player player;
    @Mock private Rope rope;

    /**
     * Set up the Player.
     * 
     * @throws SlickException
     *             occur when an invalid image has used.
     */
    @Before
    public void setUp() throws SlickException {
        player = new Player(1, 1);
    }
    
    @Test
    public void resetTest() {
    	player.reset();
    	assertEquals(2, player.getVelocityY(), 0);
    	assertFalse(player.getRightBlocked());
    	assertFalse(player.getLeftBlocked());
    	assertTrue(player.isAlive());
    	assertTrue(player.isFalling());
    	assertEquals(0, player.getRopeCount());
    }
    
    @Test
    public void canFireRopeTrueTest() {
    	assertTrue(player.canFireRope());
    }
    
    @Test
    public void canFireRopeFalseTest() {
    	player.increaseRopeCount();
    	assertFalse(player.canFireRope());
    }
    
    @Test
    public void increaseRopeCountTest() {
    	player.increaseRopeCount();
    	assertEquals(1, player.getRopeCount());
    }
    
    @Test
    public void decreaseRopeCountTrueTest() {
    	player.increaseRopeCount();
    	player.decreaseRopeCount();
    	assertEquals(0, player.getRopeCount());
    }
    
    @Test
    public void decreaseRopeCountFalseTest() {
    	player.decreaseRopeCount();
    	assertEquals(0, player.getRopeCount());
    }
    
    @Test
    public void fireTrueTest() {
        assertTrue(player.fire(rope));
    }
    
    @Test
    public void fireFalseTest() {
        player.fire(rope);
        assertFalse(player.fire(rope));
    }
    
    @Test
    public void getWalletTest() {
        assertEquals(player.getWallet(), new Wallet());
    }
    
    @Test
    public void setRightBlockTest() {
        player.setRightBlock(false);
        assertFalse(player.getRightBlocked());
    }
    
    @Test
    public void setLeftBlockTest() {
        player.setLeftBlock(false);
        assertFalse(player.getLeftBlocked());
    }

    /**
     * Tests the equals method with two equal Players.
     */
    @Test
    public void equalsTrueTest() {
        assertTrue(player.equals(player));
    }
    
    @Test
    public void equalsFalseVelocityYTest() {
        Player player2 = new Player(1, 1);
        player2.setVelocityY(10);
        assertFalse(player.equals(player2));
    }
    
    @Test
    public void equalsFalseFacingTest() {
        Player player2 = new Player(1, 1);
        player2.setFacingLeft(false);
        assertFalse(player.equals(player2));
    }

    @Test
    public void equalsFalseIdleTest() {
        Player player2 = new Player(1, 1);
        player2.setIdle(false);
        assertFalse(player.equals(player2));
    }

    /**
     * Test the equals method with a false x.
     */
    @Test
    public void equalsFalseXTest() {
        assertFalse(player.equals(new Player(2, 1)));
    }
    
    @Test
    public void equalsFalseRopeTest() {
        Player player2 = new Player(1, 1);
        player2.fire(rope);
        assertFalse(player.equals(player2));
    }

    /**
     * Test the equals method with a false y.
     */
    @Test
    public void equalsFalseYTest() {
        assertFalse(player.equals(new Player(1, 2)));
    }

    /**
     * Test the addLife method.
     */
    @Test
    public void addLifeTrueTest() {
        player.loseLife();
        assertEquals(4, player.getLives());
        player.addLife();
        assertEquals(5, player.getLives());
    }
    
    @Test
    public void addLifeFalseTest() {
    	player.addLife();
    	assertEquals(5, player.getLives());
    }

    /**
     * Test the loseLife method.
     */
    @Test
    public void loseLifeTest() {
        player.loseLife();
        assertEquals(4, player.getLives());
    }
    
    @Test
    public void getCollidedStatusTrueTest() {
    	player.setAlive(false);
    	assertTrue(player.getCollidedStatus());
    }
    
    @Test
    public void getCollidedStatusFalseTest() {
    	player.setAlive(true);
    	assertFalse(player.getCollidedStatus());
    }

    /**
     * Test the hasLives method with outcome true.
     */
    @Test
    public void hasLivesTrueTest() {
        assertTrue(player.hasLives());
    }
    
    @Test
    public void removeCollidedRopeFalseTest() {
    	when(rope.isCollided()).thenReturn(false);
    	player.fire(rope);
    	Collection<Collidable> list = player.removeCollidedRopes();
    	assertTrue(list.isEmpty());
    }
    
    
    @Test
    public void removeCollidedRopeTrueTest() {
    	when(rope.isCollided()).thenReturn(true);
    	player.fire(rope);
    	Collection<Collidable> list = player.removeCollidedRopes();
    	assertTrue(list.contains(rope));
    }

    /**
     * Test the hasLives method with outcome false.
     */
    @Test
    public void hasLivesFalseTest() {
        for (int i = 0; i < 6; i++) {
            player.loseLife();
        }
        assertFalse(player.hasLives());
    }

    /**
     * Test the getScore method.
     */
    @Test
    public void getScoreTest() {
        assertEquals(0, player.getScore());
    }

    /**
     * Test the increaseScore method.
     */
    @Test
    public void increaseScoreTest() {
        player.increaseScore(1);
        assertEquals(1, player.getScore());
    }

    /**
     * Test the equals method with a false score.
     */
    @Test
    public void equalsFalseScoreTest() {
        Player player2 = new Player(1, 1);
        player2.increaseScore(1);
        assertFalse(player.equals(player2));
    }

    /**
     * Test the equals method with a false rightBlocked.
     */
    @Test
    public void equalsFalseRightTest() {
        Player player2 = new Player(1, 1);
        player2.setRightBlock(true);
        assertFalse(player.equals(player2));
    }

    /**
     * Test the equals method with a false leftBlocked.
     */
    @Test
    public void equalsFalseLeftTest() {
        Player player2 = new Player(1, 1);
        player2.setLeftBlock(true);
        assertFalse(player.equals(player2));
    }
    
    @Test
    public void equalsFalseLivesTest() {
    	Player player2 = new Player(1, 1);
    	player2.loseLife();
    	assertFalse(player.equals(player2));
    }

    /**
     * Test the equals method with an other type.
     */
    @Test
    public void equalsOtherTest() {
        String string = new String("Player");
        assertFalse(player.equals(string));
    }

    /**
     * Test the getRopes method.
     */
    @Test
    public void getRopesTest() {
        assertEquals(player.getRopes(), new ArrayList<Rope>());
    }

    /**
     * Test the resetRopes method.
     */
    @Test
    public void resetRopesTest() {
        player.resetRope();
        assertEquals(player.getRopes(), new ArrayList<Rope>());
    }

    /**
     * Test the setAlive method.
     */
    @Test
    public void setAliveTest() {
        player.setAlive(false);
        assertFalse(player.isAlive());
    }

    /**
     * Test the setFalling method.
     */
    @Test
    public void setFallingTest() {
        player.setFalling(false);
        assertFalse(player.isFalling());
    }

    /**
     * Test the effect of the fall method on y.
     */
    @Test
    public void fallYTest() {
        double y = player.getY();
        player.fall();
        assertEquals(y + 2, player.getY(), 0);
    }

    /**
     * Test the effect of the fall method on vy.
     */
    @Test
    public void fallVelocityYTest() {
        player.fall();
        assertEquals(2 + .3f, player.getVelocityY(), 0);
    }

    /**
     * Test the effect of the moveTo method on x.
     */
    @Test
    public void moveToXTest() {
        player.moveTo(5, 5);
        assertEquals(5, player.getCenterX(), 0);
    }

    /**
     * Test the effect of the moveTo method on y.
     */
    @Test
    public void moveToYTest() {
        player.moveTo(5, 5);
        assertEquals(5, player.getCenterY(), 0);
    }

    /**
     * Test the effect of the moveTo method on isFalling.
     */
    @Test
    public void moveToFallingTest() {
        player.moveTo(5, 5);
        assertTrue(player.isFalling());
    }

    /**
     * Test the moveRight method with rightBlocked true.
     */
    @Test
    public void moveRightTrueTest() {
        Player player1 = player;
        player.setRightBlock(true);
        player.moveRight(1);
        assertEquals(player1, player);
    }

    /**
     * Test the moveLeft method with leftBlocked true.
     */
    @Test
    public void moveLeftTrueTest() {
        Player player1 = player;
        player.setLeftBlock(true);
        player.moveLeft(1);
        assertEquals(player1, player);
    }
    
    @Test
    public void moveRopesTest() {
        player.fire(rope);
        player.moveRopes();
        verify(rope).move();
    }

    /**
     * Test the move method with isFalling false.
     */
    @Test
    public void moveFalseTest() {
        player.setFalling(false);
        player.move();
        assertEquals(0, player.getVelocityY(), 0);
    }

    /**
     * Test the move method with isFalling true.
     */
    @Test
    public void moveTrueTest() {
        player.setFalling(true);
        double y = player.getY();
        double vy = player.getVelocityY();
        player.move();
        assertEquals(y + vy, player.getY(), 0);
        assertEquals(vy + .3, player.getVelocityY(), 0.000001);
    }

}
