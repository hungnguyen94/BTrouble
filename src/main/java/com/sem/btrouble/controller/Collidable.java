package com.sem.btrouble.controller;

import java.util.Map;

/**
 * All classes that are collidable
 * must implement this class.
 */
public interface Collidable {

    /**
     * Every collidable should return a Map with all CollisionActions
     * that collidable should process. To prevent class checking, simply
     * use the class as the key, and a CollisionAction instance as value.
     * @return A map of all actions this collidable can do on a collision.
     */
    Map<Class<? extends Collidable>, CollisionAction> getCollideActions();

    /**
     * This method should return the
     * center X position of the collidable.
     * @return Center of X position.
     */
    float getCenterX();

    /**
     * This method should return the
     * center Y position of the collidable.
     * @return Center of Y position.
     */
    float getCenterY();

    /**
     * Get the x location of the left side of this collidable.
     * @return x location of the left side.
     */
    float getX();

    /**
     * Get the y position of the top of this shape.
     * @return y position of the top.
     */
    float getY();

    /**
     * Get the width of this shape.
     * @return the width.
     */
    float getWidth();

    /**
     * Get the height of this shape.
     * @return the height.
     */
    float getHeight();

    /**
     * Checks for intersection with another Collidable.
     * @param collidable Check if this collidable intersects with that collidable.
     * @return True if this object intersects with collidable.
     */
    boolean intersectsCollidable(Collidable collidable);

}
