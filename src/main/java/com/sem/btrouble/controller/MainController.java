package com.sem.btrouble.controller;

import com.sem.btrouble.model.Drawable;

import java.util.Collection;

/**
 * @author Hung
 */
public interface MainController extends Drawable {

    /**
     * Update method for the MainController.
     */
    void update();

    /**
     * Adds a reference to a list.
     * @param collidableCollection Collection of collidables.
     */
    void addListReference(Collection<? extends Collidable> collidableCollection);
}
