package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class RoomDataTest {
	
	ArrayList<Bubble> bubbles;
	RoomData data;

	@Before
	public void setUp() {
		bubbles = new ArrayList<Bubble>();
		Bubble bubble = new Bubble(1, 1, 1);
		bubbles.add(bubble);
		data = new RoomData(bubbles);
	}
	
	@Test
	public void getBubblesTest() {
		assertEquals(bubbles, data.getBubbles());
	}
	
	@Test
	public void getStartingPositionXTest() {
		assertEquals(50, data.getStartingPositionX());
	}
	
	@Test
	public void getStartingPositionYTest() {
		assertEquals(350, data.getStartingPositionY());
	}
	
	@Test
	public void equalsTrueTest() {
		assertTrue(data.equals(data));
	}
	
	@Test
	public void equalsFalseBubbleTest() {
		ArrayList<Bubble> bubbles2 = new ArrayList<Bubble>();
		assertFalse(data.equals(new RoomData(bubbles2)));
	}
	
	@Test
	public void equalsOtherTest() {
		String string = new String("RoomData");
		assertFalse(data.equals(string));
	}

}
