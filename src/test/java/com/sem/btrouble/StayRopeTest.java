package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.StayRope;

@RunWith(MockitoJUnitRunner.class)
public class StayRopeTest {

	@Mock private Player player;
	@Mock private Player otherPlayer;
	private StayRope rope;
	private StayRope compare;
	
	@Before
	public void setUp() {
		rope = new StayRope(1, 1, player);
		compare = new StayRope(1, 1, player);
	}
	
	@Test
	public void equalsTrueTest() {
		assertTrue(rope.equals(compare));
	}
	
	@Test
	public void equalsFalseXTest() {
		compare.setX(2);
		assertFalse(rope.equals(compare));
	}
	
	@Test
	public void equalsFalseYTest() {
		compare.setY(2);
		assertFalse(rope.equals(compare));
	}
	
	@Test
	public void equalsFalsePlayerTest() {
		StayRope rope2 = new StayRope(1, 1, otherPlayer);
		assertFalse(rope.equals(rope2));
	}
	
	@Test
	public void equalsOtherTest() {
		assertFalse(rope.equals(new String("Test")));
	}
	
	@Test
	public void moveFalseTest() {
		rope.setMoveFlag(false);
		rope.move();
		assertTrue(rope.equals(compare));
	}
	
	@Test
	public void moveTrueTest() {
		rope.move();
		compare.grow(0, (float) (1.5 * compare.getSpeedY()));
		compare.setCenterY(compare.getCenterY() - 1.5f * compare.getSpeedY());
		assertTrue(rope.equals(compare));
	}

}
