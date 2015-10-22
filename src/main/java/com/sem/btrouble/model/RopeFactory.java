package com.sem.btrouble.model;

/**
 * @author Hung
 */
public class RopeFactory {

    /**
     * Returns a rope object.
     * @param player
     * @return
     */
    public static Rope makeRope(Player player) {
        if(player.canFireRope() && player.isAlive()) {
            if(player.getWallet().containsPowerUp(StayRopePowerUp.class)) {
                return new StayRope(player.getCenterX(), player.getY() + player.getHeight() - 5, player);
            } else {
                return new Rope(player.getCenterX(), player.getY() + player.getHeight() - 5, player);
            }
        }
        return null;
    }
}