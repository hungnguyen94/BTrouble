package com.sem.btrouble.model;

import java.util.ArrayList;

public class PowerUpGenerator {
	
	public static PowerUp generate(float x, float y) {
		PowerUp power = null;
		int[] types = getTypes();
		double random = Math.random();
		if (random < 0.075) {
			if(types[0] == 0)
				power = new TimePowerUp(x, y);
		}
		else if (random < 0.15 && random > 0.075) {
			if(types[1] == 0)
				power = new SlowPowerUp(x, y);
		}
		else if (random > 0.15 && random < 0.2) {
			if(types[2] == 0)
				power = new LifePowerUp(x, y);
		}
		return power;
	}
	
	public static int[] getTypes() {
		ArrayList<PowerUp> powers = Model.getShortPower();
		int[] types = {0, 0, 0};
		for(int i = 0; i < powers.size(); i++) {
			if(powers.get(i) instanceof TimePowerUp) {
				types[0] = 1;
			}
			if(powers.get(i) instanceof LifePowerUp) {
				types[2] = 1;
			}
			if(powers.get(i) instanceof SlowPowerUp) {
				types[1] = 1;
			}
		}
		return types;
	}

}