package com.sem.btrouble;

import static org.junit.Assert.*;

import com.sem.btrouble.model.Timers;
import org.junit.Test;

public class TimersTest {
	
	private Timers timer = new Timers(1);

	@Test
	public void getLevelTimeLeftTest() {
		assertEquals(timer.getLevelTimeLeft(), 500*100);
	}
	
	@Test
	public void getLevelMaxDuration() {
		assertEquals(timer.getLevelMaxDuration(), 500*100);
	}
	
	@Test
	public void getCountdownTimeLeft() {
		assertEquals(timer.getCountdownTimeLeft(), 100*30);
	}
	
	@Test
	public void getCountdownMaxDuration() {
		assertEquals(timer.getCountdownMaxDuration(), 100*30);
	}
	
	

}
