package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a rope.
 * @author Martin
 *
 */
@SuppressWarnings("serial")
public class Rope extends Rectangle implements Drawable, Collidable {
    private int dy;
    private static final int INITIAL_ROPESPEED = 5;
    private Image sprite;
    private boolean collided;

    /**
     * Constructs a new rope at the given position.
     * 
     * @param xpos
     *            should be a float representing the horizontal position of the
     *            rope.
     * @param ypos
     *            should be a float representing the vertical position of the
     *            rope.
     */
    public Rope(float xpos, float ypos) {
        super(xpos, ypos, 2f, 2f);
        this.dy = INITIAL_ROPESPEED;
        collided = false;
        // sprite = new Image("Sprites/rope.png");
    }

    /**
     * Checks whether the provided Object is the same as this Rope.
     * 
     * @param other
     *            should be the Object to be checked for equality.
     * @return returns a boolean representing whether the provided Object is the
     *         same as this Rope.
     */
    public boolean equals(Object other) {
        if (other instanceof Rope) {
            Rope that = (Rope) other;
            return Math.abs(this.x - that.x) == 0 
                    && Math.abs(this.y - that.y) == 0 
                    && Math.abs(this.dy - that.dy) == 0;
        }
        return false;
    }
    
    /**
     * HashCode because of implemented equals method.
     * @return hashCode
     */
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }

    /**
     * Get the y distance.
     * @return dy
     */
    public int getDy() {
        return dy;
    }

    /**
     * Return if the rope is collided.
     * @return boolean
     */
    public boolean isCollided() {
        return collided;
    }

    /**
     * Set collided.
     * @param collided boolean
     */
    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    /**
     * Draws the Rope on the screen.
     * 
     * @throws SlickException
     *             when the Rope could not be drawn.
     */
    @Override
    public void draw(Graphics graphics) {
        try {
            sprite = new Image("Sprites/rope.png");
        } catch(SlickException e) {
            e.printStackTrace();
        }
        sprite.draw(x - (int) (sprite.getWidth() / 2), y);
    }

    /**
     * Calculates the next position of the Rope.
     */
    public void move() {
        if (collided) {
            return;
        }
        grow(0, (float) (1.5 * dy));
        y -= 1.5 * dy;
        if (getY() <= 0) {
            dy = 0;
            setCollided(true);
        }
    }

    /**
     * Every collidable should return a Map with all CollisionActions
     * that collidable should process. To prevent class checking, simply
     * use the class as the key, and a CollisionAction instance as value.
     * @return A map of all actions this collidable can do on a collision.
     */
    @Override
    public Map<Class<? extends Collidable>, CollisionAction> getCollideActions() {
        Map<Class<? extends Collidable>, CollisionAction> collisionActionMap = new HashMap<Class<? extends Collidable>, CollisionAction>();

        // Method called on Floor collision.
        collisionActionMap.put(Floor.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                setCollided(true);
            }
        });

        // Method called on Wall collision.
        collisionActionMap.put(Wall.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                setCollided(true);
            }
        });

        // Method called on Wall collision.
        collisionActionMap.put(Wall.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                setCollided(true);
            }
        });

        return collisionActionMap;
    }

    /**
     * Checks for intersection with another Collidable.
     * @param collidable Check if this collidable intersects with that collidable.
     * @return True if this object intersects with collidable.
     */
    @Override
    public boolean intersectsCollidable(Collidable collidable) {
        return intersects((Shape) collidable);
    }
}
