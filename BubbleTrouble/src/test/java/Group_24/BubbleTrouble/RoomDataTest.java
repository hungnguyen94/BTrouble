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
		Bubble bubble2 = new Bubble(2, 2, 2);
		bubbles.add(bubble);
		bubbles.add(bubble2);
		data = new RoomData(bubbles);
	}
	
	/*@Test
	public void getBubblesTest() {
		assertTrue(bubbles.equals(data.getBubbles()));
	}*/
	
	@Test
	public void getStartingPosition() {
		assertEquals(50, data.getStartingposition());
	}

}
