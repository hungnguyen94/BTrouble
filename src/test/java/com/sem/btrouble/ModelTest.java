package com.sem.btrouble;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.tools.DataLoader;

@RunWith(MockitoJUnitRunner.class)
public class ModelTest extends Model {

	@Mock private Room room;
	@Mock private Player player;
	
	@Before
	public void setUp() {
	  DataLoader.init();
		Model.init(new ArrayList<Room>());
	}
	
	@Test
	public void getRoomHeightTest() {
		assertEquals(Model.getRoomHeight(), 720);
	}
	
	@Test
	public void getRoomWidthTest() {
		assertEquals(Model.getRoomWidth(), 1280);
	}
	
	@Test
	public void addRoomTest() {
		Model.addRoom(room);
		assertEquals(Model.getCurrentRoom(), room);
	}
	
	@Test
	public void getBubblesTest() {
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		Model.addRoom(room);
		when(room.getBubbles()).thenReturn(bubbles);
		assertEquals(Model.getBubbles(), bubbles);
	}
	
	@Test
	public void addPlayerTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		Model.addPlayer(player);
		assertEquals(players, Model.getPlayers());
	}


}
