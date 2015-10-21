package com.sem.btrouble.controller;

import java.util.Collection;

/**
 * @author Hung
 */
abstract class ControlDecorator implements Control {

    protected Control control;

    /**
     * Constructor for the control decorator.
     * @param control control to be decorated.
     */
    public ControlDecorator(Control control) {
        this.control = control;
    }

    /**
     * Adds a collidable.
     * @param collidable Collidable that is added.
     */
    public void addCollidable(Collidable collidable) {
        this.control.addCollidable(collidable);
    }

    /**
     * Adds a reference to a list.
     * @param collidableCollection Collection of collidables.
     */
    public void addListReference(Collection<? extends Collidable> collidableCollection) {
        this.control.addListReference(collidableCollection);
    }

    /**
     * Update method of the control.
     */
    public void update() {
        this.control.update();
    }
}
