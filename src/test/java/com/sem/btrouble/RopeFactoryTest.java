package com.sem.btrouble;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Rope;
import com.sem.btrouble.model.RopeFactory;
import com.sem.btrouble.model.StayRope;
import com.sem.btrouble.model.StayRopePowerUp;
import com.sem.btrouble.model.Wallet;

@RunWith(MockitoJUnitRunner.class)
public class RopeFactoryTest {

	@Mock private Player player;
	@Mock private Wallet wallet;
	@Mock private StayRopePowerUp power;
	
	@Test
	public void allTrueTest() {
		when(player.canFireRope()).thenReturn(true);
		when(player.isAlive()).thenReturn(true);
		when(player.getWallet()).thenReturn(wallet);
		when(wallet.containsPowerUp(StayRopePowerUp.class)).thenReturn(power);
		when(player.getCenterX()).thenReturn(1f);
		when(player.getY()).thenReturn(1f);
		when(player.getHeight()).thenReturn(1f);
		Rope rope = RopeFactory.makeRope(player);
		assertEquals(rope, new StayRope(player.getCenterX(),
				player.getY() + player.getHeight() - 5, player));
	}
	
	@Test
	public void canFireRopeFalseTest() {
		when(player.canFireRope()).thenReturn(false);
		assertEquals(null, RopeFactory.makeRope(player));
	}
	
	@Test
	public void isAliveFalseTest() {
		when(player.isAlive()).thenReturn(false);
		assertEquals(null, RopeFactory.makeRope(player));
	}
	
	@Test
	public void walletFalseTest() {
		when(player.isAlive()).thenReturn(true);
		when(player.canFireRope()).thenReturn(true);
		when(player.getWallet()).thenReturn(wallet);
		when(wallet.containsPowerUp(StayRopePowerUp.class)).thenReturn(null);
		when(player.getCenterX()).thenReturn(1f);
		when(player.getY()).thenReturn(1f);
		when(player.getHeight()).thenReturn(1f);
		Rope rope = RopeFactory.makeRope(player);
		assertEquals(rope, new Rope(player.getCenterX(),
				player.getY() + player.getHeight() - 5, player));
	}

}
