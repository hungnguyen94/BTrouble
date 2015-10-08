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
 * Class representing a floor.
 */
@SuppressWarnings("serial")
public class Floor extends Rectangle implements Drawable, Collidable {

    private float speed;
    private final float defaultspeed = 0.1f;

    /**
     * Constructor for floor class.
     * 
     * @param x
     *            - X position of the floor
     * @param y
     *            - Y position of the floor
     * @param width
     *            - width of the floor
     * @param height
     *            - height of the floor
     */
    public Floor(float x, float y, float width, float height) {
        super(x, y, width, height);
        speed = defaultspeed;
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
     * Move the floor up.
     */
    public void moveUp() {
        y -= speed;
    }

    /**
     * Move the floor down.
     */
    public void moveDown() {
        y += speed;
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
}
