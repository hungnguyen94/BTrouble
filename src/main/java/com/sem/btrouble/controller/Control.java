package com.sem.btrouble.controller;

import com.sem.btrouble.model.Drawable;

import java.util.Collection;

/**
 * @author Hung
 */
public interface Control extends Drawable {

    /**
     * Update method for the Control.
     */
    void update();

    /**
     * Adds a collidable.
     * @param collidable Collidable that is added.
     */
    void addCollidable(Collidable collidable);

    /**
     * remove a collidable.
     * @param collidable Collidable that is removed.
     */
    void removeCollidable(Collidable collidable);

    /**
     * Adds a reference to a list.
     * @param collidableCollection Collection of collidables.
     */
    void addListReference(Collection<? extends Collidable> collidableCollection);
}
