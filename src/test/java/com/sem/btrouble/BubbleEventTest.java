package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.model.Bubble;

@RunWith(MockitoJUnitRunner.class)
public class BubbleEventTest extends GameEventTest{
	
	@Mock private Bubble bubble;
	private BubbleEvent event;

	@Before
	public void setUp() {
		event = new BubbleEvent(bubble, 1, "Test");
		setEvent(event);
		setObject(bubble);
		setId(1);
		setString("Test");
	}
	
	@Test
	public void toStringTest() {
		assertEquals("<BubbleEvent: Test>", event.toString());
	}
	

}
