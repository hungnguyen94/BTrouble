package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import java.util.ArrayList;

import Group_24.BubbleTrouble.Model;
import static org.mockito.Mockito.when;
import java.lang.Object;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.isA;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ModelTest {

	@Mock Player player;
	@Mock Bubble bubble;
	@Mock Room room;
	
	@Before
	public void setUp() {
		Model.init();
	}
	
	@Test
	public void getRoomHeightTest() {
		assertEquals(Model.getRoomHeight(), 500);
	}
	
	@Test
	public void getRoomWidthTest() {
		assertEquals(Model.getRoomWidth(), 800);
	}
	
	@Test
	public void addPlayerTest() {
		Model.addPlayer(player);
		ArrayList<Player> playerlist = new ArrayList<Player>();
		playerlist.add(player);
		assertEquals(Model.getPlayers(), playerlist);
	}
	
	/*
	@Test
	public void getBubblesTest() {
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(bubble);
		when(any(ArrayList.class).get(anyInt())).thenReturn(room);
		when(room.getBubbles()).thenReturn(bubbles);
		Model.getBubbles();
		assertEquals(Model.getBubbles(), bubbles);
	}
	
	@Test
	public void restartRoomTest() {
		when(Model.getCurrentRoom()).thenReturn(room);
		Model.restartRoom();
		verify(room).reload();
	}*/


}
