package com.sem.btrouble.controller;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Level;
import com.sem.btrouble.model.Movable;
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.PowerUpGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Hung
 */
public class BubbleController {

    private List<Bubble> bubbleList;
    private List<PowerUp> powerUpList;
    private Level level;

    /**
     * Constructor for bubble controller.
     * @param level Level that the controller needs to handle.
     */
    public BubbleController(Level level) {
        this.level = level;
        this.bubbleList = new CopyOnWriteArrayList<>();
        this.powerUpList = new ArrayList<>();
    }

    /**
     * Adds a bubble to the BubbleController.
     * @param bubble This is the bubble that will be added.
     */
    public void addBubble(Bubble bubble) {
        bubbleList.add(bubble);
        addMovableLevel(bubble);
    }

    /**
     * Adds a movable to the level.
     * @param movable This is the movable.
     */
    private void addMovableLevel(Movable movable) {
        level.addMovable(movable);
    }

    /**
     * Adds bubbles to the BubbleController.
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
    public void removeBubble(Bubble bubble) {
        bubbleList.remove(bubble);
        level.removeMovable(bubble);
    }

    /**
     * Drop a powerup in the level.
     * @param powerUp Add this powerup.
     */
    public void addPowerUp(PowerUp powerUp) {
        powerUpList.add(powerUp);
        addMovableLevel(powerUp);
    }

    /**
     * Checks if bubbles are split, and adds the splitted bubbles
     * to the list.
     */
    public void checkBubbleSplit() {
        for(Bubble bubble : bubbleList) {
            // If the bubble has been split.
            if(bubble.getCollidedStatus()) {
                addBubble(bubble.split());
                // Add powerup.
                addPowerUp(PowerUpGenerator.generate(bubble.getX(), bubble.getY(), Math.random()));
                removeBubble(bubble);
            }
        }
        checkPowerUp();
    }

    /**
     * Check if powerups need to be removed.
     */
    public void checkPowerUp() {
//        for(PowerUp powerUp : powerUpList) {
//            if(powerUp.getCollidedStatus()) {
//                powerUpList.remove(powerUp);
//            }
//
//        }
    }

}
