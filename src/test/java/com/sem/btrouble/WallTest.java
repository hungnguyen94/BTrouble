package com.sem.btrouble;

import com.sem.btrouble.model.Wall;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for the wall class
 */
public class WallTest {

    private Wall wall;

    @Before
    public void setUp() {
        wall = new Wall(0, 0, 10, 11);
    }

    @Test
    public void moveTest() {

    }

}
