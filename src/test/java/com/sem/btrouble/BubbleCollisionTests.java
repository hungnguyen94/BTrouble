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
 * @Author Hung
 */
public class BubbleCollisionTests {

//    @Mock
    Bubble bubble;
    @Spy
    Floor floor;
    @Spy
    Wall wall;

    private static final float X = 0;
    private static final float Y = 0;
    private static final float CENTER_X = 9f;
    private static final float CENTER_Y = 11f;
    private static final float HEIGHT = 14f;
    private static final float WIDTH = 15f;

    @Before
    public void setUp() {
        bubble = new Bubble(10, CENTER_X, CENTER_Y);
    }

    @Test
    public void bubbleWallCollisionRight() {
        wall = Mockito.spy(new Wall(100, 100, WIDTH, HEIGHT));
        bubble.getCollideActions().get(Wall.class).onCollision(wall);
        assertTrue(bubble.getVelocityX() < 0);
    }

    @Test
    public void bubbleWallCollisionLeft() {
        wall = Mockito.spy(new Wall(-100, -100, WIDTH, HEIGHT));
        bubble.getCollideActions().get(Wall.class).onCollision(wall);
        assertTrue(bubble.getVelocityX() > 0);
    }


    @Test
    public void bubbleFloorCollision() {
        floor = Mockito.spy(new Floor(0, 1000, WIDTH, HEIGHT));
        bubble.move();
        assertTrue(bubble.getVelocityY() > 0);
        bubble.getCollideActions().get(Floor.class).onCollision(floor);
        bubble.move();
        assertTrue(bubble.getVelocityY() < 0);
    }

    @Test
    public void bubbleCeilingCollision() {
        floor = Mockito.spy(new Floor(0, -1000, HEIGHT, WIDTH));
        bubble.move();
        bubble.bounceYFloor();
        bubble.move();
        assertTrue(bubble.getVelocityY() < 0);
        bubble.getCollideActions().get(Floor.class).onCollision(floor);
        assertTrue(bubble.getVelocityY() > 0);
    }

    @Test
    public void bubbleBubbleLeftCollision() {
        Bubble otherBubble = new Bubble(10, CENTER_X + 10, CENTER_Y);
        bubble.getCollideActions().get(Bubble.class).onCollision(otherBubble);
        otherBubble.getCollideActions().get(Bubble.class).onCollision(bubble);
        assertTrue(bubble.getVelocityX() < 0);
        assertTrue(otherBubble.getVelocityX() > 0);
    }

    @Test
    public void bubbleBubbleRightCollision() {
        Bubble otherBubble = new Bubble(10, CENTER_X - 10, CENTER_Y);
        bubble.getCollideActions().get(Bubble.class).onCollision(otherBubble);
        otherBubble.getCollideActions().get(Bubble.class).onCollision(bubble);
        assertTrue(bubble.getVelocityX() > 0);
        assertTrue(otherBubble.getVelocityX() < 0);
    }

    @Test
    public void bubbleBubbleTopCollision() {
        Bubble otherBubble = new Bubble(10, CENTER_X, CENTER_Y - 10);
        bubble.move();
        otherBubble.move();
        bubble.getCollideActions().get(Bubble.class).onCollision(otherBubble);
        otherBubble.getCollideActions().get(Bubble.class).onCollision(bubble);
        assertTrue(bubble.getVelocityY() > 0);
        assertTrue(otherBubble.getVelocityY() < 0);
    }

    @Test
    public void bubbleBubbleBottomCollision() {
        Bubble otherBubble = new Bubble(10, CENTER_X, CENTER_Y + 10);
        bubble.move();
        otherBubble.move();
        bubble.getCollideActions().get(Bubble.class).onCollision(otherBubble);
        otherBubble.getCollideActions().get(Bubble.class).onCollision(bubble);
        assertTrue(bubble.getVelocityY() < 0);
        assertTrue(otherBubble.getVelocityY() > 0);
    }


}
