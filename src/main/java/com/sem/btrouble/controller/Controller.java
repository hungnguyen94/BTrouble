package com.sem.btrouble.controller;

import com.sem.btrouble.model.Drawable;

import java.util.Collection;

/**
 * @author Hung
 */
public interface Controller extends Drawable {

    /**
     * Update method for the Controller.
     */
    void update();

    /**
     * Adds a reference to a list to the collisionHandler..
     * @param collidableCollection Collection of collidables.
     */
    void addListReference(Collection<? extends Collidable> collidableCollection);
}
