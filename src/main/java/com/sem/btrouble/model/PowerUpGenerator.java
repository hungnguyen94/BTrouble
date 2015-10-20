package com.sem.btrouble.model;

import java.util.List;

/**
 * Class which generates a random power up.
 * @author Martin
 *
 */
public final class PowerUpGenerator {
    /**
     * The constructor should not be called, as this is a utility class.
     */
	private PowerUpGenerator() {};

    /**
     * Generate a random power up.
     * @param x x position
     * @param y y position
     * @param random the random number
     * @return the power up
     */
	public static PowerUp generate(float x, float y, double random) {
		PowerUp power = null;
		int[] types = getTypes();
		if (random > 0 && random < 0.1) {
			if(types[0] == 0) {
				power = new TimePowerUp(x, y);
			}
		}
		else if (random >= 0.1 && random < 0.2) {
			if(types[1] == 0) {
				power = new SlowPowerUp(x, y);
			}
		}
		else if (random >= 0.2 && random <= 0.3 && types[2] == 0) {
		    power = new LifePowerUp(x, y);
		}
		return new LifePowerUp(x, y);
	}
	
	/**
	 * Get the types that are already in the game.
	 * @return array with types
	 */
	public static int[] getTypes() {
		List<PowerUp> powers = Model.getShortPower();
		int[] types = {0, 0, 0};
		for(PowerUp power : powers) {
			if(power instanceof TimePowerUp) {
				types[0] = 1;
			}
			if(power instanceof LifePowerUp) {
				types[2] = 1;
			}
			if(power instanceof SlowPowerUp) {
				types[1] = 1;
			}
		}
		return types;
	}

}
