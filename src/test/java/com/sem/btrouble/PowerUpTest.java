package com.sem.btrouble;

import static org.mockito.Mockito.verify;
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
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.tools.DataLoader;

@RunWith(MockitoJUnitRunner.class)
public class PowerUpTest {

	private PowerUp power;
	@Mock private Room room;
	@Mock private Player player;
	@Mock private Bubble bubble;
	
	@Before
	public void setUp() {
	  DataLoader.init();
		Model.init(DataLoader.getRooms());
		power = new PowerUp(0);
	}
	
	@Test
	public void givePower0Test() {
		Model.addPlayer(player);
		power.givePower();
		verify(player).addLife();
	}
	
	@Test
	public void givePower1Test() {
		power.setType(1);
		Model.addRoom(room);
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(bubble);
		when(room.getBubbles()).thenReturn(bubbles);
		power.givePower();
	}

}
