package com.sem.btrouble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;

@RunWith(MockitoJUnitRunner.class)
public class RoomTest {

	@Mock private Player player;
	@Mock private Bubble bubble;
	private Room room;
	
	@Before
	public void setUp() {
        room = new Room();
		Model.init(1280, 720);
		Model.addPlayer(player);
        Model.addRoom(room);
        Model.restartRoom();
	}

    /**
     * Test the getter of spawn position X
     */
    @Test
    public void testGetSpawnPositionX() {
        assertTrue(room.getSpawnPositionX() == 0);
        Model.restartRoom();
        assertFalse(Model.getCurrentRoom().getSpawnPositionX() == 0);
    }

    /**
     * Test the getter of spawn position Y
     */
    @Test
    public void testGetSpawnPositionY() {
        assertTrue(room.getSpawnPositionY() == 0);
        Model.restartRoom();
        assertFalse(Model.getCurrentRoom().getSpawnPositionY() == 0);
    }

    /**
     * Test the getter of the list of bubbles
     */
    @Test
    public void testGetBubbles() {
        assertTrue(room.getBubbles().isEmpty());
        room.loadRoom();
        assertFalse(room.getBubbles().isEmpty());
    }
	
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
		Room room2 = new Room();
		assertFalse(room.equals(room2));
	}
	
}
