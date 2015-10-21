package com.sem.btrouble.controller;

import com.sem.btrouble.model.Drawable;
import com.sem.btrouble.model.Rope;
import org.newdawn.slick.Graphics;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Hung
 */
public class RopeController extends ControllerDecorator implements Drawable {

    private List<Rope> ropesList;
    private Controller controller;

    /**
     * Constructor for the controller decorator.
     *
     * @param controller controller to be decorated.
     */
    public RopeController(Controller controller) {
        super(controller);
        this.controller = controller;
        this.ropesList = new CopyOnWriteArrayList<>();
        controller.addListReference(this.ropesList);
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
        controller.update();
    }

    @Override
    public void draw(Graphics graphics) {
        for(Rope rope : ropesList) {
            rope.draw(graphics);
        }
        controller.draw(graphics);
    }
}
