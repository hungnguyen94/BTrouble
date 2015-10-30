package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
/**
 * Superclass for all power ups.
 * @author Martin
 *
 */
public abstract class PlayerPowerUp extends Rectangle implements Drawable, Movable {
	
    private boolean falling;
    private boolean collided;

    private float velocityY;
    private float accelerationY;
    private boolean expired;
    protected int expirationTime;

    /**
     * Construct power up bought in the store.
     */
    public PlayerPowerUp() {
        super(-50, -50, 50, 100);
    }

    /**
     * Construct power up received in the game.
     * @param xpos x position
     * @param ypos y position
     */
    public PlayerPowerUp(float xpos, float ypos, int expirationTime) {
        super(xpos, ypos, 40, 100);
        this.falling = true;
        this.expired = false;
        this.velocityY = 2;
        this.accelerationY = 0.3f;
        this.collided = false;
        this.expirationTime = expirationTime;
    }

    /**
     * Starts the expiration timer.
     * If the actionListener is run, the powerup is expired.
     */
    protected void startExpirationTimer(ActionListener actionListener) {
        Timer timer = new Timer(0, actionListener);
        timer.setInitialDelay(expirationTime);
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Returns the expired status of the PowerUp.
     * @return True if this powerup is expired.
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * Get the vertical speed.
     * @return the vertical speed
     */
    public float getVelocityY() {
        return velocityY;
    }
    
    /**
     * Get the vertical acceleration.
     * @return vertical acceleration
     */
    public float getAccelerationY() {
        return accelerationY;
    }

    /**
     * Sets the vertical velocity of a powerup.
     * @param velocityY the vertical velocity
     */
    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Sets the vertical acceleration of a powerup.
     * @param accelerationY the vertical acceleration
     */
    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
    }

    /**
     * Apply the powerUp to the Player.
     * @param player Player to apply the powerUp to.
    */
    public abstract void activate(Player player);
    
    /**
     * Reset the power up.
     */
    public abstract void reset();

    /**
     * Return if the power up is falling.
     * @return boolean
     */
    public boolean isFalling() {
        return falling;
    }
    
    /**
     * Set the power up to falling.
     * @param falling boolean
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    /**
     * Let the power up fall.
     */
    public void fall() {
    	float y = getY();
        float changeY = y + velocityY;
        setY(changeY);
        velocityY += accelerationY;
    }
    
    /**
     * Let the power up move.
     */
    public void move() {
        if (isFalling()) {
            fall();
        }
        else {
            velocityY = 0;
        }    
    }


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

        // Method called on collision with Floor.
        collisionActionMap.put(Floor.class, new FloorCollision());

        // Method called on collision with Player.
        collisionActionMap.put(Player.class, new PlayerCollision());

        return collisionActionMap;
    }

    /**
     * Class to call method on collision with Floor.
     */
    private class FloorCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable collider) {
            PlayerPowerUp.this.setFalling(false);
            PlayerPowerUp.this.setY(collider.getY() - getHeight());
        }
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
