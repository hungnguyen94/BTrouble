package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sem.btrouble.controller.Controller;
import com.sem.btrouble.event.ControllerEvent;

@RunWith(MockitoJUnitRunner.class)
public class ControllerEventTest extends GameEventTest {

	@Mock private Controller controller;
	private ControllerEvent event;
	
	@Before
	public void setUp() {
		event = new ControllerEvent(controller, 1, "Test");
		setEvent(event);
		setObject(controller);
		setId(1);
		setString("Test");
	}
	
	@Test
	public void toStringTest() {
		assertEquals("<ControllerEvent: Test>", event.toString());
	}

}
