package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.event.GameEvent;

public abstract class GameEventTest {

	private GameEvent event;
	private Object object;
	private int id;
	private String message;
	
	@Before
	public abstract void setUp();
	
	public void setEvent(GameEvent event) {
		this.event = event;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setString(String string) {
		this.message = string;
	}
	
	@Test
	public void getSubjectTest() {
		assertEquals(object, event.getSubject());
	}
	
	@Test
	public void getIdTest() {
		assertEquals(id, event.getId());
	}
	
	@Test
	public void getMessageTest() {
		assertEquals(message, event.getMessage());
	}
	
	@Test
	public abstract void toStringTest();


}
