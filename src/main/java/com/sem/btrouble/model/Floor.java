package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
import com.sem.btrouble.observering.Direction;
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
public class Floor extends Rectangle implements Drawable, Collidable, Movable {

    private float speed;
    private Direction direction;
    private static final float DEFAULT_SPEED = 0f;

    /**
     * Constructor for floor class.
     *
     * @param x X position of the floor
     * @param y Y position of the floor
     * @param width width of the floor
     * @param height height of the floor
     *
     */
    public Floor(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.speed = DEFAULT_SPEED;
        this.direction = Direction.NONE;
    }

    /**
     * Constructor for floor class with speed and direction.
     *
     * @param x X position of the floor
     * @param y Y position of the floor
     * @param width width of the floor
     * @param height height of the floor
     * @param speed speed of movement
     * @param direction Direction of the movement
     */
    public Floor(float x, float y, float width, float height, float speed, Direction direction) {
        super(x, y, width, height);
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * Move the floor to the right.
     */
    public void moveRight() {
        setCenterX(getCenterX() + speed);
    }

    /**
     * Move the floor to the left.
     */
    public void moveLeft() {
        setCenterX(getCenterX() - speed);
    }

    /**
     * Move the floor up.
     */
    public void moveUp() {
        setCenterY(getCenterY() - speed);
        grow(0, speed);
    }

    /**
     * Move the floor down.
     */
    public void moveDown() {
        setCenterY(getCenterY() + speed);
        grow(0, speed);
    }


    /**
     * This method should move the object.
     */
    @Override
    public void move() {
        switch(direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }

    /**
     * Draw the object.
     *
     * @param graphics The graphics
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
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
     * This method is to check if a collidable
     * should be removed from the level. If this method
     * returns true, it will be removed.
     *
     * @return True if object should be removed.
     */
    @Override
    public boolean getCollidedStatus() {
        return false;
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
