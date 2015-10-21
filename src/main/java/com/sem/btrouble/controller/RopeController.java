package com.sem.btrouble.controller;

import com.sem.btrouble.model.Drawable;
import com.sem.btrouble.model.Rope;
import org.newdawn.slick.Graphics;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Hung
 */
public class RopeController extends MainControllerDecorator implements Drawable {

    private List<Rope> ropesList;

    /**
     * Constructor for the control decorator.
     *
     * @param control control to be decorated.
     */
    public RopeController(MainController control) {
        super(control);
        this.ropesList = new CopyOnWriteArrayList<>();
        control.addListReference(this.ropesList);
    }

    /**
     * Adds a rope to the list.
     * @param rope Rope to be added.
     */
    public void addRope(Rope rope) {
        ropesList.add(rope);
    }

    /**
     * Moves the bubbles and checks if bubbles are split.
     * If they are, add the splitted bubbles
     * to the list.
     */
    public void update() {
        for(Rope rope : ropesList) {
            rope.move();
        }
        control.update();
    }

    @Override
    public void draw(Graphics graphics) {
        for(Rope rope : ropesList) {
            rope.draw(graphics);
        }
        control.draw(graphics);
    }
}
