package com.sem.btrouble.controller;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.PowerUpGenerator;
import org.newdawn.slick.Graphics;

import java.util.List;

/**
 * @author Hung
 * Controller to control the powerUps.
 */
public class PowerUpController extends ControlDecorator implements Control {

    private List<PowerUp> powerUpList;

    /**
     * Constructor for the powerupController.
     * @param control control that will be decorated.
     */
    public PowerUpController(Control control, List<PowerUp> powerUpList) {
        super(control);
        this.powerUpList = powerUpList;
        control.addListReference(this.powerUpList);
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

    @Override
    public void removeCollidable(Collidable collidable) {
        if(collidable instanceof Bubble) {
            powerUpList.add(PowerUpGenerator.generate(collidable.getX(), collidable.getY(), Math.random()));
        }
        control.removeCollidable(collidable);
    }

    /**
     * Check if powerups need to be removed.
     */
    public void update() {
        for(PowerUp powerUp : powerUpList) {
            if(powerUp.getCollidedStatus()) {
                removePowerUp(powerUp);
            }
        }
        control.update();
    }

    /**
     * Draw the object.
     *
     * @param graphics The graphics
     */
    @Override
    public void draw(Graphics graphics) {
        control.draw(graphics);
    }
}
