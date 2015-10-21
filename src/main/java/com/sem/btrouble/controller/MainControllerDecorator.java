package com.sem.btrouble.controller;

import java.util.Collection;

/**
 * @author Hung
 */
abstract class MainControllerDecorator implements MainController {

    protected MainController control;

    /**
     * Constructor for the control decorator.
     * @param control control to be decorated.
     */
    public MainControllerDecorator(MainController control) {
        this.control = control;
    }

    /**
     * Set the control that needs to be decorated.
     * @param control MainController that needs to be decorated.
     */
    public void setControl(MainController control) {
        this.control = control;
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
