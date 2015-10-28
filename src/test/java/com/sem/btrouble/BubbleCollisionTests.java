package com.sem.btrouble;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Wall;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.Assert.assertTrue;

/**
 * @author Hung
 */
public class BubbleCollisionTests {

//    @Mock
    private Bubble bubble;
    @Spy
    private Floor floor;
    @Spy
    private Wall wall;

    private static final float CENTER_X = 9f;
    private static final float CENTER_Y = 11f;
    private static final float HEIGHT = 14f;
    private static final float WIDTH = 15f;

    /**
     * Setup a bubble at the specified location.
     */
    @Before
    public void setUp() {
        bubble = new Bubble(10, CENTER_X, CENTER_Y);
    }

    /**
     * Test if the bubble collides with the right wall.
     */
    @Test
    public void bubbleWallCollisionRight() {
        wall = Mockito.spy(new Wall(100, 100, WIDTH, HEIGHT));
        bubble.getCollideActions().get(Wall.class).onCollision(wall);
        assertTrue(bubble.getVelocityX() < 0);
    }

    /**
     * Test if the bubble collides with the left wall.
     */
    @Test
    public void bubbleWallCollisionLeft() {
        wall = Mockito.spy(new Wall(-100, -100, WIDTH, HEIGHT));
        bubble.getCollideActions().get(Wall.class).onCollision(wall);
        assertTrue(bubble.getVelocityX() > 0);
    }

    /**
     * Test if the bubble collides with the floor.
     */
    @Test
    public void bubbleFloorCollision() {
        floor = Mockito.spy(new Floor(0, -100, HEIGHT, WIDTH));
        bubble.move();
        assertTrue(bubble.getVelocityY() >= 0);
        bubble.getCollideActions().get(Floor.class).onCollision(floor);
        assertTrue(bubble.getVelocityY() < 0);
    }

    /**
     * Test if the bubble collides with the ceiling.
     */
    @Test
    public void bubbleCeilingCollision() {
        floor = Mockito.spy(new Floor(0, 100, HEIGHT, WIDTH));
        bubble.move();
        bubble.bounceYFloor();
        bubble.move();
        bubble.getCollideActions().get(Floor.class).onCollision(floor);
        assertTrue(bubble.getVelocityY() < 0);
    }

    /**
     * Test if the bubble collides with a bubble on the left.
     */
    @Test
    public void bubbleBubbleLeftCollision() {
        Bubble otherBubble = new Bubble(10, CENTER_X + 10, CENTER_Y);
        bubble.getCollideActions().get(Bubble.class).onCollision(otherBubble);
        assertTrue(bubble.getVelocityX() < 0);
        assertTrue(otherBubble.getVelocityX() > 0);
    }

    /**
     * Test if the bubble collides with a bubble on the right.
     */
    @Test
    public void bubbleBubbleRightCollision() {
        Bubble otherBubble = new Bubble(10, CENTER_X - 10, CENTER_Y);
        bubble.getCollideActions().get(Bubble.class).onCollision(otherBubble);
        assertTrue(bubble.getVelocityX() > 0);
        assertTrue(otherBubble.getVelocityX() < 0);
    }

    /**
     * Test if the bubble collides with a bubble on the top.
     */
    @Test
    public void bubbleBubbleTopCollision() {
        Bubble otherBubble = new Bubble(10, CENTER_X, CENTER_Y - 10);
        bubble.move();
        otherBubble.move();
        bubble.getCollideActions().get(Bubble.class).onCollision(otherBubble);
        assertTrue(bubble.getVelocityY() > 0);
        assertTrue(otherBubble.getVelocityY() < 0);
    }

    /**
     * Test if the bubble collidedes with a bubble on the bottom.
     */
    @Test
    public void bubbleBubbleBottomCollision() {
        Bubble otherBubble = new Bubble(10, CENTER_X, CENTER_Y + 10);
        bubble.move();
        otherBubble.move();
        bubble.getCollideActions().get(Bubble.class).onCollision(otherBubble);
        assertTrue(bubble.getVelocityY() < 0);
        assertTrue(otherBubble.getVelocityY() > 0);
    }


}
