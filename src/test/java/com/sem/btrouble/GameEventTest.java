package com.sem.btrouble;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.event.GameEvent;

/**
 * Class which test the GameEvent class.
 * @author Martin
 *
 */
public abstract class GameEventTest {

	private GameEvent event;
	private Object object;
	private int id;
	private String message;
	
	/**
	 * Abstract method to set up the objects.
	 */
	@Before
	public abstract void setUp();
	
	/**
	 * Set the GameEvent object.
	 * @param event to set
	 */
	public void setEvent(GameEvent event) {
		this.event = event;
	}
	
	/**
	 * Set the object.
	 * @param object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}
	
	/**
	 * Set the id.
	 * @param id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Set the string.
	 * @param string to set
	 */
	public void setString(String string) {
		this.message = string;
	}
	
	/**
	 * Test the getSubject method.
	 */
	@Test
	public void getSubjectTest() {
		assertEquals(object, event.getSubject());
	}
	
	/**
	 * Test the getId method.
	 */
	@Test
	public void getIdTest() {
		assertEquals(id, event.getId());
	}
	
	/**
	 * Test the getMessage method.
	 */
	@Test
	public void getMessageTest() {
		assertEquals(message, event.getMessage());
	}
	
	/**
	 * Abstract method to test the toString method.
	 */
	@Test
	public abstract void toStringTest();


}
