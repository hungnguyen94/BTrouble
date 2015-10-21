package com.sem.btrouble.controller;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.PowerUpGenerator;
import org.newdawn.slick.Graphics;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Hung
 * Controller to controller the powerUps.
 */
public class PowerUpController extends ControllerDecorator implements Controller {

    private List<PowerUp> powerUpList;
    private List<Bubble> bubbleList;
    private BubbleController controller;

    /**
     * Constructor for the powerupController. The
     * powerUpController can only decorate the bubbleController.
     * @param controller bubble controller that will be decorated.
     */
    public PowerUpController(BubbleController controller) {
        super(controller);
        this.controller = controller;
        this.powerUpList = new CopyOnWriteArrayList<>();
        this.controller.addListReference(this.powerUpList);
        this.bubbleList = controller.getBubbleList();
    }


    /**
     * Drop a powerup in the level.
     * @param powerUp Add this powerup.
     */
    public void addPowerUp(PowerUp powerUp) {
        powerUpList.add(powerUp);
    }

    /**
     * Drop a powerup in the level.
     * @param powerUp Add this powerup.
     */
    public void removePowerUp(PowerUp powerUp) {
        powerUpList.remove(powerUp);
    }

    /**
     * Drop the powerUps on bubbleCollision.
     * Move the powerUps.
     */
    public void update() {
        for(Bubble bubble : bubbleList) {
            if(bubble.getCollidedStatus()) {
                addPowerUp(PowerUpGenerator.generate(bubble.getX(), bubble.getY(), Math.random()));
            }
        }
        for(PowerUp powerUp : powerUpList) {
            powerUp.move();
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
        for(PowerUp powerUp : powerUpList) {
            powerUp.draw(graphics);
        }
        controller.draw(graphics);
    }
}
