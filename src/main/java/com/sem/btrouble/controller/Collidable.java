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
     * @return
     */
    Map<Class<? extends Shape>, CollisionAction<?>> getCollideActions();

}
