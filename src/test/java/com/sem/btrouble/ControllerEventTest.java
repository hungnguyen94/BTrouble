package com.sem.btrouble;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sem.btrouble.controller.Controller;
import com.sem.btrouble.event.ControllerEvent;

/**
 * Class which tests the ControllerEvent class.
 * 
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerEventTest extends GameEventTest {

    @Mock
    private Controller controller;
    private ControllerEvent event;

    /**
     * Set up the objects to use the tests of GameEventTest.
     */
    @Before
    public void setUp() {
	event = new ControllerEvent(controller, 1, "Test");
	setEvent(event);
	setObject(controller);
	setId(1);
	setString("Test");
    }

    /**
     * Test the toString method.
     */
    @Test
    public void toStringTest() {
	assertEquals("<ControllerEvent: Test>", event.toString());
    }

}
