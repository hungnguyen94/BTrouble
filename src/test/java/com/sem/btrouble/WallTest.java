package com.sem.btrouble;

import com.sem.btrouble.model.Wall;

import com.sem.btrouble.observering.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test for the wall class.
 */
public class WallTest {

    private Wall wall;

    /**
     * Set up the wall object.
     */
    @Before
    public void setUp() {
        wall = new Wall(0, 0, 10, 11, 1f, Direction.NONE);
    }

    /**
     * Test the moveRight method.
     */
    @Test
    public void moveRightTest() {
        wall.moveRight();
        assertEquals(0f, wall.getX(), 0);
    }

    /**
     * Test the moveLeft method.
     */
    @Test
    public void moveLeftTest() {
        wall.moveLeft();
        assertEquals(-2f, wall.getX(), 0);
    }

    /**
     * Test the moveUp method.
     */
    @Test
    public void moveUpTest() {
        wall.moveUp();
        assertEquals(-1f, wall.getY(), 0);
    }

    /**
     * Test the moveDown method.
     */
    @Test
    public void moveDownTest() {
        wall.moveDown();
        assertEquals(1f, wall.getY(), 0);
    }
    
    @Test
    public void getCollidedStatusTest() {
    	assertFalse(wall.getCollidedStatus());
    }
}
