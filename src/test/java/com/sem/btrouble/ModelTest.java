package com.sem.btrouble;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class ModelTest extends Model {

	@Mock private Room room;
	@Mock private Player player;
	
	@Before
	public void setUp() {
		Model.init(1280, 720);
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
	
	@Test
	public void restartRoomTest() {
		Model.addRoom(room);
		Model.restartRoom();
		verify(room).reload();
	}


}
