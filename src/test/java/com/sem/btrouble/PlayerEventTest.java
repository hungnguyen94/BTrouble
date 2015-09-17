package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.model.Player;

@RunWith(MockitoJUnitRunner.class)
public class PlayerEventTest extends GameEventTest {
	
	@Mock private Player player;
	private PlayerEvent event;

	@Before
	public void setUp() {
		event = new PlayerEvent(player, 1, "Test");
		setEvent(event);
		setObject(player);
		setId(1);
		setString("Test");
	}
	
	@Test
	public void toStringTest() {
		assertEquals("<PlayerEvent: Test>", event.toString());
	}
	
	@Test
	public void getSubjectTest() {
		assertEquals(player, event.getSubject());
	}

}
