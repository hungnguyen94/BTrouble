package com.sem.btrouble.model;

import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Timer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;

public abstract class PowerUp extends Rectangle implements Drawable, Movable {

    private static final long serialVersionUID = 1L;

    protected boolean falling;
    protected boolean collided;

    protected float velocityY;
    protected float accelerationY;

    protected boolean expired;

    public PowerUp() {
        super(-50, -50, 40, 100);
    }

    public PowerUp(float xpos, float ypos) {
        super(xpos, ypos, 40, 100);
        this.falling = true;
        this.expired = false;
        this.velocityY = 2;
        this.accelerationY = 0.3f;
        this.collided = false;
    }

    /**
     * Get the vertical speed.
     * 
     * @return the vertical speed
     */
    public float getVelocityY() {
        return velocityY;
    }

    /**
     * Get the vertical acceleration.
     * 
     * @return vertical acceleration
     */
    public float getAccelerationY() {
        return accelerationY;
    }

    /**
     * Sets the vertical velocity of a powerup.
     * 
     * @param velocityY
     *            the vertical velocity
     */
    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Sets the vertical acceleration of a powerup.
     * 
     * @param accelerationY
     *            the vertical acceleration
     */
    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
    }

    /**
     * Set the power up to falling.
     * 
     * @param falling
     *            boolean
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**
     * Reset the power up.
     */
    public abstract void reset();

    /**
     * Draw the power up.
     * 
     * @param graphics
     *            Graphics handler
     */
    public abstract void draw(Graphics graphics);

    /**
     * Return if the power up is falling.
     * 
     * @return boolean
     */
    public boolean isFalling() {
        return falling;
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
        } else {
            velocityY = 0;
        }
    }

    /**
     * Sets the collided status for this powerup.
     * 
     * @param collided
     *            True if the powerUp is collided.
     */
    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    /**
     * This method is to check if a collidable should be removed from the level.
     * If this method returns true, it will be removed.
     *
     * @return True if object should be removed.
     */
    @Override
    public boolean getCollidedStatus() {
        return collided;
    }

    /**
     * Every collidable should return a Map with all CollisionActions that
     * collidable should process. To prevent class checking, simply use the
     * class as the key, and a CollisionAction instance as value.
     *
     * @return A map of all actions this collidable can do on a collision.
     */
    @Override
    public abstract Map<Class<? extends Collidable>, CollisionAction> getCollideActions();
    

    /**
     * Returns the expired status of the PowerUp.
     * 
     * @return True if this powerup is expired.
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * Checks for intersection with another Collidable.
     *
     * @param collidable
     *            Check if this collidable intersects with that collidable.
     * @return True if this object intersects with collidable.
     */
    @Override
    public boolean intersectsCollidable(Collidable collidable) {
        return intersects((Shape) collidable);
    }

    /**
     * Class to call method on collision with Floor.
     */
    protected class FloorCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable collider) {
            PowerUp.this.setFalling(false);
            PowerUp.this.setY(collider.getY() - getHeight());
        }
    }
}
