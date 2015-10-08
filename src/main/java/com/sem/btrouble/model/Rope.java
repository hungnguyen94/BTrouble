package com.sem.btrouble.model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Represents a rope.
 * @author Martin
 *
 */
@SuppressWarnings("serial")
public class Rope extends Rectangle {
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
            return (Math.abs(this.x - that.x) == 0 
                    && Math.abs(this.y - that.y) == 0 
                    && Math.abs(this.dy - that.dy) == 0);
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
    public void draw() throws SlickException {
        sprite = new Image("Sprites/rope.png");
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
}
