package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sem.btrouble.model.Wallet;

public class WalletTest {

	private Wallet wallet = new Wallet();
	
	@Test
	public void increaseValueTest() {
		wallet.increaseValue(10);
		assertEquals(10, wallet.getValue());
	}
	
	@Test
	public void decreaseValueTest() {
		wallet.decreaseValue(10);
		assertEquals(-10, wallet.getValue());
	}

}
