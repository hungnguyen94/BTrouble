package com.sem.btrouble;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sem.btrouble.model.Floor;

/**
 * Test the floor class.
 * 
 * @author Martin
 *
 */
public class FloorTest {

    private Floor floor = new Floor(1, 1, 1, 1);

    /**
     * Test the moveRight method.
     */
    @Test
    public void moveRightTest() {
	floor.moveRight();
	assertEquals(1.1, floor.getX(), 0.01);
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
	assertEquals(0.9, floor.getY(), 0.01);
    }

    /**
     * Test the moveDown method.
     */
    @Test
    public void moveDownTest() {
	floor.moveDown();
	assertEquals(1.1, floor.getY(), 0.01);
    }

}
