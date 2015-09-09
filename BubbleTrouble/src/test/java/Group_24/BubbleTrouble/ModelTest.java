package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import java.util.ArrayList;

import Group_24.BubbleTrouble.Model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ModelTest {

	@Mock Player player;
	private ArrayList<Room> rooms;
	
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


}
