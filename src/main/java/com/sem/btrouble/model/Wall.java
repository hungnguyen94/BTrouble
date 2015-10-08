package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a wall.
 */
@SuppressWarnings("serial")
public class Wall extends Rectangle implements Drawable, Collidable {

    private int speed;

    /**
     * Constructor for wall class.
     * 
     * @param x
     *            - X position of the wall
     * @param y
     *            - Y position of the wall
     * @param width
     *            - width of the wall
     * @param height
     *            - height of the wall
     */
    public Wall(float x, float y, float width, float height) {
        super(x, y, width, height);
        speed = 1;
    }

    /**
     * Get the x position.
     * @return the x position
     */
    public float getX() {
        return x;
    }

    /**
     * Get the y position.
     * @return the y position
     */
    public float getY() {
        return y;
    }

    /**
     * Move the wall X.
     */
    public void moveX() {
        x += speed;
    }

    /**
     * Move the floor to the right.
     */
    public void moveRight() {
        x += speed;
    }

    /**
     * Move the floor to the left.
     */
    public void moveLeft() {
        x -= speed;
    }

    /**
     * Move the wall up.
     */
    public void moveUp() {
        y += speed;
    }

    /**
     * Move the wall down.
     */
    public void moveDown() {
        y -= speed;
    }

    /**
     * Change the direction of a wall.
     */
    public void changeDirection() {
        speed = -speed;
    }

    /**
     * Draw the object.
     *
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setLineWidth(2);
        graphics.draw(this);
        graphics.setLineWidth(1);
    }

    /**
     * Every collidable should return a Map with all CollisionActions
     * that collidable should process. To prevent class checking, simply
     * use the class as the key, and a CollisionAction instance as value.
     * @return A map of all actions this collidable can do on a collision.
     */
    @Override
    public Map<Class<? extends Collidable>, CollisionAction> getCollideActions() {
        return new HashMap<Class<? extends Collidable>, CollisionAction>();
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
