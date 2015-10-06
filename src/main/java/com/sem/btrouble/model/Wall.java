package com.sem.btrouble.model;

import org.newdawn.slick.geom.Rectangle;

/**
 * Class representing a wall
 */
@SuppressWarnings("serial")
public class Wall extends Rectangle {

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
}
