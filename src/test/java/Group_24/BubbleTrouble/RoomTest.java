package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.newdawn.slick.geom.Rectangle;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoomTest {
	
	@Mock private RoomData data;
	@Mock private RoomData data2;
	@Mock private Player player;
	@Mock private Bubble bubble;
	private Room room;
	private ArrayList<Bubble> bubbles;
	
	@Before
	public void setUp() {
		Model.init();
		Model.addPlayer(player);
		bubbles = new ArrayList<Bubble>();
		bubbles.add(bubble);
		when(data.getStartingPositionX()).thenReturn(0);
		when(data.getStartingPositionY()).thenReturn(0);
		when(data.getBubbles()).thenReturn(bubbles);
		room = new Room(data);
	}

	@Test
	public void reloadTest() {
		verify(player).moveTo(0, 0);
	}
	
	@Test
	public void equalsTrueTest() {
		assertTrue(room.equals(room));
	}
	
	@Test
	public void equalsFalseDataTest() {
		assertFalse(room.equals(new Room(data2)));
	}
	
	@Test
	public void equalsFalseBubblesTest() {
		when(data.getBubbles()).thenReturn(new ArrayList<Bubble>());
		assertFalse(room.equals(new Room(data)));
	}
	
	@Test
	public void equalsFalseXTest() {
		when(data.getStartingPositionX()).thenReturn(1);
		assertFalse(room.equals(new Room(data)));
	}
	
	@Test
	public void equalsFalseYTest() {
		when(data.getStartingPositionY()).thenReturn(1);
		assertFalse(room.equals(new Room(data)));
	}
	
	@Test 
	public void equalsOtherTest() {
		String string = new String("Room");
		assertFalse(room.equals(string));
	}
	
	@Test
	public void getBubblesTest() {
		assertEquals(room.getBubbles(), bubbles);
	}
	
	/*@Test
	public void getWallsTest() {
		ArrayList<Rectangle> walls = new ArrayList<Rectangle>();
		walls.add(new Rectangle(0, 0, 1, 800));
		walls.add(new Rectangle(1123, 0, 1, 800));
		assertEquals(walls, room.getWalls());
		
	}*/

}
