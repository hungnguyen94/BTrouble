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
 * Class representing a wall.
 */
@SuppressWarnings("serial")
public class Wall extends Rectangle implements Drawable, Collidable, Movable {

    private float speed;
    private Direction direction;
    private static final float DEFAULT_SPEED = 0f;

    /**
     * Constructor for wall class.
     *
     * @param x X position of the wall
     * @param y Y position of the wall
     * @param width width of the wall
     * @param height height of the wall
     */
    public Wall(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.speed = DEFAULT_SPEED;
        this.direction = Direction.NONE;
    }

    /**
     * Constructor for wall class.
     *
     * @param x X position of the wall
     * @param y Y position of the wall
     * @param width width of the wall
     * @param height height of the wall
     * @param speed speed of movement
     * @param direction Direction of the movement
     */
    public Wall(float x, float y, float width, float height, float speed, Direction direction) {
        super(x, y, width, height);
        this.speed = speed;
        this.direction = direction;
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
     * Move the wall to the right.
     */
    public void moveRight() {
        grow(speed, 0);
        setCenterX(getCenterX() + speed);
    }

    /**
     * Move the wall to the left.
     */
    public void moveLeft() {
        setCenterX(getCenterX() - speed);
        grow(speed, 0);
    }

    /**
     * Move the wall up.
     */
    public void moveUp() {
        setCenterY(getCenterY() - speed);
    }

    /**
     * Move the wall down.
     */
    public void moveDown() {
        setCenterY(getCenterY() + speed);
    }

    /**
     * Get the speed of the wall.
     * @return the speed
     */
    public float getSpeed() {
    	return speed;
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
     * Checks for intersection with another Collidable.
     * @param collidable Check if this collidable intersects with that collidable.
     * @return True if this object intersects with collidable.
     */
    @Override
    public boolean intersectsCollidable(Collidable collidable) {
        return intersects((Shape) collidable);
    }
}
