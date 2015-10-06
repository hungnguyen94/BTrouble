package com.sem.btrouble.model;

public class PowerUpGenerator {
	
	public static PowerUp generate(float x, float y) {
		PowerUp power;
		double random = Math.random();
		if (random < 0.075) {
			power = new SlowPowerUp(x, y);
		}
		else if (random < 0.15 && random > 0.075) {
			power = new TimePowerUp(x, y);
		}
		else if (random > 0.15 && random < 0.2) {
			power = new LifePowerUp(x, y);
		}
		else {
			power = null;
		}
		return power;
	}

}
