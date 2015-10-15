package com.sem.btrouble.model;

import org.newdawn.slick.Graphics;

/**
 * All drawable objects should implement this.
 */
public interface Drawable {

    /**
     * Draw the object.
     * @param graphics The graphics
     */
    void draw(Graphics graphics);
}
