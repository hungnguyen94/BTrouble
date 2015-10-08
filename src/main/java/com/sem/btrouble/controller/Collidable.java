package com.sem.btrouble.controller;

import org.newdawn.slick.geom.Shape;

import java.util.Map;

/**
 * All classes that are collidable
 * must implement this class.
 */
public interface Collidable {

    /**
     * Get a map of the actions on collisions.
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
     * Checks for intersection with a shape.
     * @param collidable Check if this collidable intersectsCollidable with that collidable.
     */
    boolean intersectsCollidable(Collidable collidable);

}
