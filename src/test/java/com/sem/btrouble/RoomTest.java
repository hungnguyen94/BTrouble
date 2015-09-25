package com.sem.btrouble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Wall;
import com.sem.btrouble.model.Floor;

@RunWith(MockitoJUnitRunner.class)
public class RoomTest {

	@Mock private Player player;
	@Mock private Bubble bubble;
	private Room room;
	
	@Before
	public void setUp() {
	      
        room = new Room(0, new ArrayList<Wall>(), new ArrayList<Floor>(), new ArrayList<Bubble>());
		Model.init();
		Model.addPlayer(player);
        Model.addRoom(room);
	}

//    /**
//     * Test the getter of spawn position X
//     */
//    @Test
//    public void testGetSpawnPositionX() {
//        assertTrue(room.getSpawnPositionX() == 0);
//        Model.restartRoom();
//        assertFalse(room.getSpawnPositionX() == 0);
//    }
//
//    /**
//     * Test the getter of spawn position Y
//     */
//    @Test
//    public void testGetSpawnPositionY() {
//        assertTrue(room.getSpawnPositionY() == 0);
//        Model.restartRoom();
//        assertFalse(room.getSpawnPositionY() == 0);
//    }

//    /**
//     * Test the getter of the list of bubbles
//     */
//    @Test
//    public void testGetBubbles() {
//        assertTrue(room.getBubbles().isEmpty());
//        room.loadRoom();
//        assertFalse(room.getBubbles().isEmpty());
//    }
//
//    /**
//     * Test the getter of the list of walls
//     */
//    @Test
//    public void testGetWalls() {
//        assertTrue(room.getWalls().isEmpty());
//        room.loadRoom();
//        assertFalse(room.getWalls().isEmpty());
//    }
//
//    /**
//     * Test the getter of the list of floors
//     */
//    @Test
//    public void testGetFloors() {
//        assertTrue(room.getFloors().isEmpty());
//        room.loadRoom();
//        assertFalse(room.getFloors().isEmpty());
//    }

	@Test
	public void hasBubblesFalseTest() {
		assertFalse(room.hasBubbles());
	}
	
	@Test
	public void hasBubblesTrueTest() {
		room.addBubble(bubble);
		assertTrue(room.hasBubbles());
	}
	
	@Test
	public void moveBubblesTest() {
		room.addBubble(bubble);
		room.moveBubbles();
		verify(bubble).move();
	}
	
	@Test
	public void removeBubbleTest() {
		room.addBubble(bubble);
		room.removeBubble(bubble);
		assertFalse(room.hasBubbles());
	}
	
	@Test
	public void equalsOtherTest() {
		assertFalse(room.equals(new String("test")));
	}
	
	@Test
	public void equalsBubbleTest() {
		room.addBubble(bubble);
		Room room2 = new Room(0, new ArrayList<Wall>(), new ArrayList<Floor>(), new ArrayList<Bubble>());
		assertFalse(room.equals(room2));
	}
	
	@Test
	public void equalsXTest() {
		room.setSpawnPositionX(2);
		Room room2 = new Room(0, new ArrayList<Wall>(), new ArrayList<Floor>(), new ArrayList<Bubble>());
		assertFalse(room.equals(room2));
	}
	
	@Test
	public void equalsYTest() {
		room.setSpawnPositionY(2);
		Room room2 = new Room(0, new ArrayList<Wall>(), new ArrayList<Floor>(), new ArrayList<Bubble>());
		assertFalse(room.equals(room2));
	}
	
}
