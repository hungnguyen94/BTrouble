package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.HashMap;
import java.util.Map;
/**
 * Superclass for all power ups.
 * @author Martin
 *
 */
@SuppressWarnings("serial")
public abstract class PowerUp extends Rectangle implements Drawable, Collidable {
	
        private boolean falling;
        private float vy;
        private float ay;

    /**
     * Construct power up bought in the store.
     */
    public PowerUp() {
        super(-50, -50, 50, 100);
    }

    /**
     * Construct power up received in the game.
     * @param xpos x position
     * @param ypos y position
     */
    public PowerUp(float xpos, float ypos) {
        super(xpos, ypos, 50, 100);
        this.falling = true;
        this.vy = 2;
        this.ay = .3f;
    }
    
    /**
     * Get the vertical speed.
     * @return the vertical speed
     */
    public float getVY() {
        return vy;
    }
    
    /**
     * Get the vertical acceleration.
     * @return vertical acceleration
     */
    public float getAY() {
        return ay;
    }

    /**
     * Activate the power up.
    */
    public abstract void activate();
    
    /**
     * Reset the power up.
     */
    public abstract void reset();
    
    /**
     * Draw the power up.
     */
    public abstract void draw(Graphics graphics);
    
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
        float changeY = y + vy;
        setY(changeY);
        vy += ay;
    }
    
    /**
     * Let the power up move.
     */
    public void move() {
        if (isFalling()) {
            fall();
        }
        else {
            vy = 0;
        }    
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
            PowerUp.this.setFalling(false);
            PowerUp.this.setY(collider.getY() - getHeight());
        }
    }

    /**
     * Class to call method on collision with Player.
     */
    private class PlayerCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable collider) {
            PowerUp.this.activate();
            Model.deleteShortPower(PowerUp.this);
            System.out.println(Model.getShortPower());
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
