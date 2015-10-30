package com.sem.btrouble.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
/**
 * Superclass for all power ups.
 *
 */
public abstract class BubblePowerUp extends PowerUp implements Drawable, Movable {
	
    private static final long serialVersionUID = 1L;
    protected CopyOnWriteArrayList<Bubble> bubbles;
    
    /**
     * Construct power up bought in the store.
     */
    public BubblePowerUp() {
        super(-50, -50);
        
    }

    /**
     * Construct power up received in the game.
     * @param xpos x position
     * @param ypos y position
     */
    public BubblePowerUp(float xpos, float ypos, CopyOnWriteArrayList<Bubble> bubbleList) {
        super(xpos, ypos);
        this.bubbles = bubbleList;
    }

    /**
     * Returns the expired status of the PowerUp.
     * @return True if this powerup is expired.
     */
    public boolean isExpired() {
        return expired;
    }
    
    /**
     * Apply the powerUp to the Player.
     * Starts the expiration timer to remove the
     * powerup when it runs out.
     * @param player Player to apply the powerUp to.
     */
    public abstract void activate(final Player player, CopyOnWriteArrayList<Bubble> bubbles);
    
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
            BubblePowerUp.this.activate((Player) collider, bubbles);
            BubblePowerUp.this.collided = true;
        }
    }

}
