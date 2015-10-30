package com.sem.btrouble;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Wall;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RoomTest {

	private Room room;
	@Mock private Wall wall;
	@Mock private Floor floor;
	private List<Wall> walls = new ArrayList<Wall>();
	private List<Floor> floors = new ArrayList<Floor>();
	
	@Before
	public void setUp() {
		walls.add(wall);
		floors.add(floor);
		room =  new Room(walls, floors, 1, 1, "Test");
	}
	
	@Test
	public void getFloorsTest() {
		List<Floor> floors = new ArrayList<Floor>();
		floors.add(floor);
		assertEquals(floors, room.getFloors());
	}
	
	@Test
	public void getWallsTest() {
		List<Wall> walls = new ArrayList<Wall>();
		walls.add(wall);
		assertEquals(walls, room.getWalls());
	}
	
	@Test
	public void setSpawnPositionXTest() {
		room.setSpawnPositionX(2);
		assertEquals(2, room.getSpawnPositionX());
	}
	
	@Test
	public void setSpawnPositionYTest() {
		room.setSpawnPositionY(2);
		assertEquals(2, room.getSpawnPositionY());
	}
	
	@Test
	public void equalsTrueTest() {
		assertTrue(room.equals(room));
	}
	
	@Test
	public void equalsFalseXTest() {
		Room compare = new Room(walls, floors, 2, 1, "test");
		assertFalse(room.equals(compare));
	}
	
	@Test
	public void equalsFalseYTest() {
		Room compare = new Room(walls, floors, 1, 2, "test");
		assertFalse(room.equals(compare));
	}
	
	@Test
	public void equalsOtherTest() {
		assertFalse(room.equals(new String("Test")));
	}
	
	@Test
	public void getCollidablesListTest() {
		Collection<Collidable> list = new ArrayList<>();
		list.addAll(walls);
		list.addAll(floors);
		assertEquals(list, room.getCollidablesList());
	}

	@Test
	public void addMovableFloorTest() {
		ArrayList<Floor> floors = new ArrayList<Floor>();
		floors.add(floor);
		room.addMovableFloors(floors);
		assertEquals(floors, room.getMoveableFloors());
	}

	@Test
	public void addMovableWallTest() {
		ArrayList<Wall> walls = new ArrayList<>();
		walls.add(wall);
		room.addMovableWalls(walls);
		assertEquals(walls, room.getMoveableWalls());
	}
}
