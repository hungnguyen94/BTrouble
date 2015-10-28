package com.sem.btrouble;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Wall;

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
	public void getCollidablesTest() {
		Collection<Collidable> list = room.getCollidables();
		Collection<Collidable> compare = new ArrayList<Collidable>();
		compare.add(wall);
		compare.add(floor);
		assertTrue(compare.equals(list));
	}
	
	@Test
	public void getCollidablesListTest() {
		Collection<Collection<? extends Collidable>> list = new ArrayList<Collection<? extends Collidable>>();
		list.add(walls);
		list.add(floors);
		assertEquals(list, room.getCollidablesLists());
	}
	
	@Test
	public void addMovablesTest() {
		ArrayList<Floor> floors = new ArrayList<Floor>();
		floors.add(floor);
		room.addMovables(floors);
		assertEquals(floors, room.getMoveableBorders());
	}
	
	@Test
	public void moveFalseTest() {
		Room room2 = room;
		room.move();
		assertTrue(room.equals(room2));
	}
	
	@Test
	public void moveTrueTrueTest() {
		ArrayList<Floor> floors = new ArrayList<Floor>();
		floors.add(floor);
		room.addMovables(floors);
		room.move();
		verify(floor).grow(0f, 0.1f);
		verify(floor).setY(630f - floor.getHeight());
	}

}
