package com.sem.btrouble.model;

/**
 * Class which generates a random power up.
 * @author Martin
 *
 */
public final class PowerUpFactory {

    /**
     * Generate a random power up.
     * @param x x position
     * @param y y position
     * @param random the random number
     * @return the power up
     */
	public static PlayerPowerUp generate(float x, float y, double random) {
        if((random -= 0.2f) < 0) {
            return new StayRopePowerUp(x, y, 5000);
        }
        if((random -= 0.2f) < 0) {
            return new LifePowerUp(x, y);
        }
        return null;
	}
}
