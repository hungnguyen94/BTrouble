package com.sem.btrouble.controller;

import org.newdawn.slick.geom.Shape;

/**
 * Interface for a collision action.
 */
public interface CollisionAction<C extends Shape> {

    /**
     * Actions that should be performed during
     * collision between two shapes.
     */
    void onCollision();
}
