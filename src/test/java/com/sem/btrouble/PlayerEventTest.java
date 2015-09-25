package com.sem.btrouble;

import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.model.Player;

/**
 * Class which test the PlayerEvent class.
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerEventTest extends GameEventTest {
	
	@Mock private Player player;
	private PlayerEvent event;

	/**
	 * Set up the objects to use the GameEventTest.
	 */
	@Before
	public void setUp() {
		event = new PlayerEvent(player, 1, "Test");
		setEvent(event);
		setObject(player);
		setId(1);
		setString("Test");
	}
	
	/**
	 * Test the toString method.
	 */
	@Test
	public void toStringTest() {
		assertEquals("<PlayerEvent: Test>", event.toString());
	}
	
	/**
	 * Test the getSubject method.
	 */
	@Test
	public void getSubjectTest() {
		assertEquals(player, event.getSubject());
	}

}
