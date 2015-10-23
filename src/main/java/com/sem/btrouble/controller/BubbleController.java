package com.sem.btrouble.controller;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Drawable;
import com.sem.btrouble.model.Model;

import org.newdawn.slick.Graphics;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class handles all the logic for the bubbles.
 * @author Hung
 */
public class BubbleController extends ControllerDecorator implements Drawable {

    private List<Bubble> bubbleList;
    private Controller controller;

    /**
     * Constructor for bubble controller.
     * @param controller Controller that will be decorated.
     */
    public BubbleController(Controller controller) {
        super(controller);
        this.controller = controller;
        this.bubbleList = new CopyOnWriteArrayList<>();
        bubbleList.addAll(Model.getCurrentRoom().getBubbles());
        this.controller.addListReference(this.bubbleList);
    }

    /**
     * Returns the size of the bubbleList.
     * @return size of the bubbleList.
     */
    public int getSize() {
        return bubbleList.size();
    }

    /**
     * Return the reference of the list.
     * @return BubbleList
     */
    protected List<Bubble> getBubbleList() {
        return bubbleList;
    }

    /**
     * Adds a bubble to the BubbleController.
     * @param bubble This is the bubble that will be added.
     */
    public void addBubble(Bubble bubble) {
        bubbleList.add(bubble);
    }

    /**
     * Add a collection of bubbles to the BubbleController. This will
     * automatically be reflected in the CollisionHandler.
     * @param bubbles This is the collection of bubbles that will be added.
     */
    public void addBubble(Collection<Bubble> bubbles) {
        for(Bubble bubble : bubbles) {
            addBubble(bubble);
        }
    }

    /**
     * Removes a bubble from the BubbleController.
     * @param bubble This is the bubble that will be removed.
     */
    public void removeBubble(Collidable bubble) {
        bubbleList.remove(bubble);
    }

    /**
     * Moves the bubbles and checks if bubbles are split.
     * If they are, add the splitted bubbles
     * to the list.
     */
    public void update() {
        for(Bubble bubble : bubbleList) {
            bubble.move();
            if(bubble.getCollidedStatus()) {
                addBubble(bubble.split());
            }
        }
        controller.update();
    }


    /**
     * Draw the object.
     *
     * @param graphics The graphics
     */
    @Override
    public void draw(Graphics graphics) {
        for(Bubble bubble : bubbleList) {
            bubble.draw(graphics);
        }
        controller.draw(graphics);
    }
}
