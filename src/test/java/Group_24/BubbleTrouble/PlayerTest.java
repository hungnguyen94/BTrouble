package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public class PlayerTest {
	
	private Player player;
	
	@Before
	public void setUp() throws SlickException {
		player = new Player(1, 1);
	}

	@Test
	public void equalsTrueTest() {
		assertTrue(player.equals(player));
	}
	
	@Test
	public void equalsFalseXTest() {
		assertFalse(player.equals(new Player(2, 1)));
	}
	
	@Test
	public void equalsFalseYTest() {
		assertFalse(player.equals(new Player(3, 1)));
	}
	
	@Test
	public void equalsFalseLivesTest() {
		Player player2 = new Player(1, 1);
		player2.addLife();
		assertFalse(player.equals(player2));
	}
	
	@Test
	public void addLifeTest() {
		player.addLife();
		assertEquals(6, player.getLives());
	}
	
	@Test
	public void loseLifeTest() {
		player.loseLife();
		assertEquals(4, player.getLives());
	}
	
	@Test
	public void hasLivesTrueTest() {
		assertTrue(player.hasLives());
	}
	
	@Test
	public void hasLivesFalseTest() {
		for(int i = 0; i < 6; i++) {
			player.loseLife();
		}
		assertFalse(player.hasLives());
	}
	
	@Test
	public void getScoreTest() {
		assertEquals(0, player.getScore());
	}
	
	@Test
	public void increaseScoreTest() {
		player.increaseScore(1);
		assertEquals(1, player.getScore());
	}
	
	@Test
	public void equalsFalseScoreTest() {
		Player player2 = new Player(1, 1);
		player2.increaseScore(1);
		assertFalse(player.equals(player2));
	}
	
	@Test
	public void equalsFalseRightTest() {
		Player player2 = new Player(1, 1);
		player2.setRightBlocked(true);
		assertFalse(player.equals(player2));
	}
	
	@Test
	public void equalsFalseLeftTest() {
		Player player2 = new Player(1, 1);
		player2.setLeftBlocked(true);
		assertFalse(player.equals(player2));
	}
	
	@Test
	public void equalsOtherTest() {
		String string = new String("Player");
		assertFalse(player.equals(string));
	}
	
	@Test
	public void getRopesTest() {
		assertEquals(player.getRopes(), new ArrayList<Rope>());
	}
	
	@Test
	public void resetRopesTest() {
		player.resetRope();
		assertEquals(player.getRopes(), new ArrayList<Rope>());
	}

}
