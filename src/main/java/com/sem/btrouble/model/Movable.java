package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;

/**
 * @author Hung
 * This is an interface that all movable objects should implement.
 */
public interface Movable extends Collidable {

    /**
     * This method should move the object.
     */
    void move();
}
