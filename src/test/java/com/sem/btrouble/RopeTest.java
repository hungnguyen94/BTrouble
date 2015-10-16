package com.sem.btrouble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.sem.btrouble.model.Rope;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

/**
 * Class which test the Rope class.
 * 
 * @author Martin
 *
 */
public class RopeTest {

    private Rope rope;

    /**
     * Set up the rope object.
     * 
     * @throws SlickException
     *             occur when a invalid image is used
     */
    @Before
    public void setUp() throws SlickException {
        rope = new Rope(1, 1);
    }

    /**
     * Test the getDy method.
     */
    @Test
    public void getDyTest() {
        assertEquals(5, rope.getDy());
    }

    /**
     * Test the equals method with two equal objects.
     */
    @Test
    public void equalsTrueTest() {
        assertTrue(rope.equals(rope));
    }

    /**
     * Test the equals method with false x.
     */
    @Test
    public void equalsFalseXTest() {
        assertFalse(rope.equals(new Rope(2, 1)));
    }

    /**
     * Test the equals method with false y.
     */
    @Test
    public void equalsFalseYTest() {
        assertFalse(rope.equals(new Rope(1, 2)));
    }

    /**
     * Test the equals method with false dy.
     */
    @Test
    public void equalsFalseDYTest() {
        Rope rope2 = new Rope(1, 1);
        rope2.setDY(10);
        assertFalse(rope.equals(rope2));
    }

    /**
     * Test the equals method with another type.
     */
    @Test
    public void equalsOtherTest() {
        String string = new String("rope");
        assertFalse(rope.equals(string));
    }

    /**
     * Test the effect of the move method on y.
     */
    @Test
    public void moveYTest() {
        float y = rope.getY();
        rope.move();
        assertEquals((y - 15), rope.getY(), 0);
    }

    /**
     * Test the effect of the move method on dy.
     */
    @Test
    public void moveDYTest() {
        rope.move();
        assertEquals(0, rope.getDy());
    }

    /**
     * Test the effect of the move method with no dy.
     */
    @Test
    public void moveNoDYTest() {
        Rope rope2 = new Rope(20, 20);
        rope2.move();
        assertEquals(5, rope2.getDy(), 0);
    }

}
