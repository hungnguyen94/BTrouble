package com.sem.btrouble;

import com.sem.btrouble.model.Floor;
import com.sem.btrouble.observering.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test the floor class.
 * 
 * @author Martin
 *
 */
public class FloorTest {

    private Floor floor;

    @Before
    public void setUp() {
        floor = new Floor(1, 1, 1, 1, 0.1f, Direction.NONE);
    }

    /**
     * Test the moveRight method.
     */
    @Test
    public void moveRightTest() {
        floor.moveRight();
        assertEquals(1.1, floor.getX(), 0.01);
    }
    
    @Test
    public void getCollidedStatusTest() {
    	assertFalse(floor.getCollidedStatus());
    }

    /**
     * Test the moveLeft method.
     */
    @Test
    public void moveLeftTest() {
        floor.moveLeft();
        assertEquals(0.9, floor.getX(), 0.01);
    }

    /**
     * Test the moveUp method.
     */
    @Test
    public void moveUpTest() {
        floor.moveUp();
        assertEquals(0.8f, floor.getY(), 0.01);
    }

    /**
     * Test the moveDown method.
     */
    @Test
    public void moveDownTest() {
        floor.moveDown();
        assertEquals(1.0f, floor.getY(), 0.01);
    }

}
