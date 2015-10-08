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
     * @return All actions this collidable can do on a collision.
     */
    Map<Class<? extends Shape>, CollisionAction> getCollideActions();

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

}
