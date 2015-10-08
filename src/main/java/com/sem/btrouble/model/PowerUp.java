package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.HashMap;
import java.util.Map;

public abstract class PowerUp extends Rectangle implements Collidable {
	
	private boolean falling;
	private float vy;
	private float ay;
	
	public PowerUp() {
		super(-50, -50, 50, 100);
	}
	
	public PowerUp(float xpos, float ypos) {
		super(xpos, ypos, 50, 100);
		this.falling = true;
		this.vy = 2;
		this.ay = .3f;
	}

    public abstract void activate();
    
    public abstract void reset();
    
    public abstract void draw() throws SlickException;
    
    public boolean isFalling() {
        return falling;
    }
    
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    public void fall() {
    	float y = getY();
        float changeY = y += vy;
        setY(changeY);
        vy += ay;
    }
    
    public void move() {
        if (isFalling())
            fall();
        else
            vy = 0;
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
        collisionActionMap.put(Floor.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                PowerUp.this.setFalling(false);
                PowerUp.this.setY(collider.getY() - getHeight());
            }
        });

        // Method called on collision with Player.
        collisionActionMap.put(Player.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                Player player = (Player) collider;
                if(!(PowerUp.this instanceof LifePowerUp) && !(PowerUp.this instanceof TimePowerUp)
                        && player.getLives() < 5) {
                    PowerUp.this.activate();
                }
                if(PowerUp.this instanceof TimePowerUp) {
                    TimePowerUp timePower = (TimePowerUp) PowerUp.this;
                    timePower.activateShort();
                }
                Model.deleteShortPower(PowerUp.this);
                System.out.println(Model.getShortPower());
            }
        });

        return collisionActionMap;
    }

    /**
     * Checks for intersection with another Collidable.
     *
     * @param collidable Check if this collidable intersects with that collidable.
     * @return True if this object intersects with collidable.
     */
    @Override
    public boolean intersectsCollidable(Collidable collidable) {
        return false;
    }
}
