package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import model.Bubble;
import model.Model;
import model.Player;
import model.Room;

@RunWith(MockitoJUnitRunner.class)
public class RoomTest {

	@Mock private Player player;
	@Mock private Bubble bubble;
	private Room room;
	private ArrayList<Bubble> bubbles;
	
	@Before
	public void setUp() {
        room = new Room();
		Model.init();
		Model.addPlayer(player);
        Model.addRoom(room);
	}

    /**
     * Test the getter of spawn position X
     */
    @Test
    public void testGetSpawnPositionX() {
        assertTrue(room.getSpawnPositionX() == 0);
        Model.restartRoom();
        assertFalse(room.getSpawnPositionX() == 0);
    }

    /**
     * Test the getter of spawn position Y
     */
    @Test
    public void testGetSpawnPositionY() {
        assertTrue(room.getSpawnPositionY() == 0);
        Model.restartRoom();
        assertFalse(room.getSpawnPositionY() == 0);
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

    /**
     * Test the getter of the list of walls
     */
    @Test
    public void testGetWalls() {
        assertTrue(room.getWalls().isEmpty());
        room.loadRoom();
        assertFalse(room.getWalls().isEmpty());
    }

    /**
     * Test the getter of the list of floors
     */
    @Test
    public void testGetFloors() {
        assertTrue(room.getFloors().isEmpty());
        room.loadRoom();
        assertFalse(room.getFloors().isEmpty());
    }

    /**
     * Test the reload method
     */
	@Test
	public void reloadTest() {
        Room myRoom = new Room();
        assertEquals(myRoom, room);
        room.reload();
        assertNotEquals(myRoom, room);
        myRoom.reload();
        assertEquals(myRoom, room);
    }
}
