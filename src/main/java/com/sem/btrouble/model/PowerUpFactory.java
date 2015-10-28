package com.sem.btrouble.model;

import java.util.List;

/**
 * Class which generates a random power up.
 * @author Martin
 *
 */
public abstract class PowerUpFactory {

    /**
     * Generate a random power up.
     * @param x x position
     * @param y y position
     * @param random the random number
     * @return the power up
     */
	public static PlayerPowerUp generate(float x, float y, double random) {
	      random -= 0.1f;
	      if(random < 0) {
            return new StayRopePowerUp(x, y, 5000);
        }
        if(random < 0) {
            return new LifePowerUp(x, y);
        }
        return null;
	}
	
	/**
	 * Get the types that are already in the game.
	 * @return array with types
	 */
	public static int[] getTypes() {
		List<PlayerPowerUp> powers = Model.getShortPower();
		int[] types = {0, 0, 0};
		for(PlayerPowerUp power : powers) {
//			if(power instanceof TimePowerUp) {
//				types[0] = 1;
//			}
			if(power instanceof LifePowerUp) {
				types[2] = 1;
			}
//			if(power instanceof SlowPowerUp) {
//				types[1] = 1;
//			}
		}
		return types;
	}

}
