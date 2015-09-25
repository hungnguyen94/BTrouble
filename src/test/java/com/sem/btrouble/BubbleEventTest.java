package com.sem.btrouble;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.event.BubbleEvent;

/**
 * Class which tests the BubbleEvent Class.
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BubbleEventTest extends GameEventTest {

	@Mock private Bubble bubble;
	private BubbleEvent event;

	/**
	 * Set up the objects to use the tests of GameEventTest.
	 */
	@Before
	public void setUp() {
		event = new BubbleEvent(bubble, 1, "Test");
		setEvent(event);
		setObject(bubble);
		setId(1);
		setString("Test");
	}
	
	
	/**
	 * Tests the toString method.
	 */
	@Test
	public void toStringTest() {
		assertEquals("<BubbleEvent: Test>", event.toString());
	}
	

}
