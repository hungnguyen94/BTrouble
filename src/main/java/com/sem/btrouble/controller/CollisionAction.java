package com.sem.btrouble.controller;

import org.newdawn.slick.geom.Shape;

/**
 * Interface for a collision action.
 */
public interface CollisionAction {

    /**
     * Actions that should be performed during
     * collision between two shapes.
     * @param collider Collided object.
     */
    void onCollision(Collidable collider);
}
