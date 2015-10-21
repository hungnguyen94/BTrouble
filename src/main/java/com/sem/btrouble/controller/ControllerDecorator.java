package com.sem.btrouble.controller;

import java.util.Collection;

/**
 * @author Hung
 */
abstract class ControllerDecorator implements Controller {

    private Controller controller;

    /**
     * Constructor for the controller decorator.
     * @param controller controller to be decorated.
     */
    ControllerDecorator(Controller controller) {
        this.controller = controller;
    }

    /**
     * Set the controller that needs to be decorated.
     * @param controller Controller that needs to be decorated.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    /**
     * Adds a reference to a list.
     * @param collidableCollection Collection of collidables.
     */
    public void addListReference(Collection<? extends Collidable> collidableCollection) {
        this.controller.addListReference(collidableCollection);
    }

    /**
     * Update method of the controller.
     */
    public abstract void update();
}
