package com.sem.btrouble.controller;

import com.sem.btrouble.model.PlayerPowerUp;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.GamePowerUp;
import com.sem.btrouble.model.PowerUpFactory;
import org.newdawn.slick.Graphics;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Hung
 * Controller to control the powerUps.
 */
public class PowerUpController extends ControllerDecorator implements Controller {

    private List<PlayerPowerUp> playerPowerUpList;
    private List<GamePowerUp> gamePowerUpList;
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
        this.playerPowerUpList = new CopyOnWriteArrayList<>();
        this.gamePowerUpList = new CopyOnWriteArrayList<>();
        this.controller.addListReference(this.playerPowerUpList);
        this.controller.addListReference(this.gamePowerUpList);
        this.bubbleList = controller.getBubbleList();
    }


    /**
     * Drop a powerup in the level.
     * @param powerUp Add this powerup.
     */
    public void addPowerUp(PlayerPowerUp powerUp) {
        playerPowerUpList.add(powerUp);
    }

    /**
     * Drop a powerup in the level.
     * @param powerUp Add this powerup.
     */
    public void removePowerUp(PlayerPowerUp powerUp) {
        playerPowerUpList.remove(powerUp);
    }

    /**
     * Drop the powerUps on bubbleCollision.
     * Move the powerUps.
     */
    public void update() {
        for(Bubble bubble : bubbleList) {
            if(bubble.getCollidedStatus()) {
                PlayerPowerUp playerPowerUp = PowerUpFactory.generate(bubble.getX(), 
                    bubble.getY(), Math.random());
                if(playerPowerUp != null) {
                    addPowerUp(playerPowerUp);
                }
            }
        }
        for(PlayerPowerUp powerUp : playerPowerUpList) {
            powerUp.move();
        }
        for(GamePowerUp powerUp : gamePowerUpList) {
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
        for(PlayerPowerUp powerUp : playerPowerUpList) {
            powerUp.draw(graphics);
        }
        controller.draw(graphics);
    }
}
