package com.sem.btrouble.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.geom.Shape;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
/**
 * Superclass for all power ups.
 * @author Martin
 *
 */

public abstract class PlayerPowerUp extends PowerUp implements Drawable, Movable {
	
    private static final long serialVersionUID = 1L;
    
    /**
     * Construct power up bought in the store.
     */
    public PlayerPowerUp() {
        super(-50, -50);
        
    }

    /**
     * Construct power up with expiration bought in the store.
     * @param expirationTime the expiration time for the powerup
     */
    public PlayerPowerUp(int expirationTime) {
        super(-50, -50);
        this.expired = false;
    }

    /**
     * Construct power up received in the game.
     * @param xpos x position
     * @param ypos y position
     * @param expirationTime the expiration time for the powerup
     */
    public PlayerPowerUp(float xpos, float ypos) {
        super(xpos, ypos);
    }
    
    /**
     * Apply the powerUp to the Player.
     * Starts the expiration timer to remove the
     * powerup when it runs out.
     * @param player Player to apply the powerUp to.
     */
    public abstract void activate(final Player player);
    
    /**
     * Sets the collided status for this powerup.
     * @param collided True if the powerUp is collided.
     */
    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    /**
     * This method is to check if a collidable
     * should be removed from the level. If this method
     * returns true, it will be removed.
     *
     * @return True if object should be removed.
     */
    @Override
    public boolean getCollidedStatus() {
        return collided;
    }

    /**
     * Every collidable should return a Map with all CollisionActions
     * that collidable should process. To prevent class checking, simply
     * use the class as the key, and a CollisionAction instance as value.
     *
     * @return A map of all actions this collidable can do on a collision.
     */
    @Override
    public Map<Class<? extends Collidable>, CollisionAction> getCollideActions() {
        Map<Class<? extends Collidable>, CollisionAction> collisionActionMap =
                new HashMap<Class<? extends Collidable>, CollisionAction>();
        
        collisionActionMap.put(Floor.class, new FloorCollision());
        
        collisionActionMap.put(Player.class, new PlayerCollision());

        return collisionActionMap;
    }

    /**
     * Class to call method on collision with Player.
     */
    private class PlayerCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable collider) {
            PlayerPowerUp.this.activate((Player) collider);
            PlayerPowerUp.this.collided = true;
        }
    }

    /**
     * Checks for intersection with another Collidable.
     *
     * @param collidable Check if this collidable intersects with that collidable.
     * @return True if this object intersects with collidable.
     */
    @Override
    public boolean intersectsCollidable(Collidable collidable) {
        return intersects((Shape) collidable);
    }
}
