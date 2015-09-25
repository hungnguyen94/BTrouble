package com.sem.btrouble;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.Room;

@RunWith(MockitoJUnitRunner.class)
public class PowerUpTest {

	private PowerUp power;
	@Mock private Room room;
	@Mock private Player player;
	@Mock private Bubble bubble;
	
	@Before
	public void setUp() {
		Model.init();
		power = new PowerUp(0);
	}
	
	@Test
	public void givePower0Test() {
		Model.addPlayer(player);
		power.givePower();
		ArrayList<Player> players = Model.getPlayers();
		verify(player).addLife();
	}
	
/*	@Test
	public void givePower1Test() {
		power.setType(1);
		Model.addRoom(room);
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(bubble);
		when(room.getBubbles()).thenReturn(bubbles);
		power.givePower();
		ArrayList<Bubble> bubbles2 = Model.getBubbles();
		verify(bubble).setAY(.3f);
	}*/

}
