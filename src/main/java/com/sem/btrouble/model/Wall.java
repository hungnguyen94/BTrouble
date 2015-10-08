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
 * Class representing a wall
 */
@SuppressWarnings("serial")
public class Wall extends Rectangle implements Drawable, Collidable {

    private int speed;

    /**
     * Constructor for wall class
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * Move the wall X
     */
    public void moveX() {
        x += speed;
    }

    /**
     * Move the floor to the right
     */
    public void moveRight() {
        x += speed;
    }

    /**
     * Move the floor to the left
     */
    public void moveLeft() {
        x -= speed;
    }

    /**
     * Move the wall up
     */
    public void moveUp() {
        y += speed;
    }

    /**
     * Move the wall down
     */
    public void moveDown() {
        y -= speed;
    }

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
     * Get a map of the actions on collisions.
     *
     * @return A map of all actions this collidable can do on a collision.
     */
    @Override
    public Map<Class<? extends Collidable>, CollisionAction> getCollideActions() {
        return new HashMap<Class<? extends Collidable>, CollisionAction>();
    }

    /**
     * Checks for intersection with a shape.
     * @param collidable Check if this collidable intersectsCollidable with that collidable.
     */
    @Override
    public boolean intersectsCollidable(Collidable collidable) {
        return intersects((Shape) collidable);
    }
}
