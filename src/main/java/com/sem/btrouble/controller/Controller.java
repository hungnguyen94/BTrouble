package com.sem.btrouble.controller;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.sem.btrouble.SlickApp;
import com.sem.btrouble.event.ControllerEvent;
import com.sem.btrouble.event.Event;
import com.sem.btrouble.event.LevelEvent;
import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.Rope;
import com.sem.btrouble.model.Timers;
import com.sem.btrouble.observering.Observer;
import com.sem.btrouble.observering.Subject;

/**
 * Controller, recalculates the Model, on request of the view.
 */
public class Controller implements Subject {

    private GameContainer gc;
    private CollisionHandler collisionHandler;
    private int timeLeft;
    private List<Observer> observers;

    /**
     * Starts a new game by loading data into the room and adding the players.
     * 
     * @param container
     *            GameContainer from Slick2D
     * @param sbg
     *            State of the game
     * @throws SlickException
     *             Throws exception on error
     */
    public Controller(GameContainer container, StateBasedGame sbg) throws SlickException {
        this.gc = container;
        this.observers = new ArrayList<Observer>();

        collisionHandler = new CollisionHandler();

        Model.init(SlickApp.SCREEN_WIDTH, SlickApp.SCREEN_HEIGHT);
        Player p = new Player(0, 0);
        Model.addPlayer(p);
        collisionHandler.addCollidable(p);
        restartRoom();
    }

    /**
     * Returns the timers.
     * 
     * @return timer
     */
    public Timers getTimers() {
        return Model.getTimers();
    }

    /**
     * Updates the model, should be done on request of the view.
     * 
     * @param delta
     *            milliseconds between frames
     * @throws SlickException
     *             throw error on exception
     */
    public void update(int delta) throws SlickException {
        collisionHandler.checkCollision(Model.getCurrentRoom().getBubbles());

        for (Player player : Model.getPlayers()) {
            if (!collisionHandler.checkCollision(player)) {
                player.setFalling(true);
            }

            if (!player.isAlive()) {
                // Update observers.
                fireEvent(LevelEvent.LEVELLOST);
                loseLife(player);
            }
            collisionHandler.checkCollision(player.getRopes());
        }

        // Check if timer has run out.
        if (this.getTimers().getLevelTimeLeft() <= 0) {
            fireEvent(ControllerEvent.OUTOFTIME);
            // Update observers.
            fireEvent(LevelEvent.LEVELLOST);
            for (Player p : Model.getPlayers()) {
                loseLife(p);
            }
        }

        ArrayList<PowerUp> powers = Model.getShortPower();
        if (powers.size() > 0) {
            for (PowerUp power : powers) {
                if (collisionHandler.checkCollision(power)) {

                    if (timeLeft >= getTimers().getLevelTimeLeft() + 30000) {
                        Model.deleteShortPower(power);
                    }
                }
                power.move();
                if (!collisionHandler.checkCollision(power)) {
                    timeLeft = getTimers().getLevelTimeLeft();
                }
            }
        }

        for (Player p : Model.getPlayers()) {
            p.move();
        }

        processInput(delta);
        // calculate movements
        updateRopes();
        updateBubble();
    }

    private static final double ROPE_OFFSET = 0.9;

    /**
     * Move the player on key presses.
     *
     * @param delta
     *            milliseconds between frames
     */
    public void processInput(int delta) {
        Input input = gc.getInput();
        Player p1 = Model.getPlayers().get(0);

        if (input.isKeyDown(Input.KEY_LEFT)) {
            p1.moveLeft(delta);
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            p1.moveRight(delta);
        }

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            Rope r = new Rope(p1.getX() + (int) (p1.getWidth() / 2),
                    (float) (p1.getY() + p1.getHeight() * ROPE_OFFSET));
            if (p1.fire(r)) {
                collisionHandler.addCollidable(r);
                fireEvent(PlayerEvent.SHOOT);
            }
        }
    }

    /**
     * Lets the player lose a life.
     *
     * @param player
     *            should be the player who lost a life
     */
    public void loseLife(Player player) {
        fireEvent(PlayerEvent.LIFE_LOST);
        player.loseLife();
        if (!player.hasLives()) {
            endGame("Game over...");
        } else {
            collisionHandler.removeCollidable(Model.getCurrentRoom().getCollidables());
            // Hardcoded player 1
            collisionHandler.removeCollidable(Model.getPlayers().get(0).getRopes());
            restartRoom();
            player.setAlive(true);
        }
    }

    private void restartRoom() {
        fireEvent(ControllerEvent.RESTARTROOM);
        Model.restartRoom();
        ArrayList<PowerUp> powers = Model.getPowerUps();
        for (PowerUp power : powers) {
            power.reset();
        }
        collisionHandler.addCollidable(Model.getCurrentRoom().getCollidables());
    }

    /**
     * Updates the bubble, checks whether bubbles should be removed or not, and
     * calculates the new position of each bubble.
     */
    private void updateBubble() {
        if (!Model.getCurrentRoom().hasBubbles()) {
            fireEvent(LevelEvent.LEVELWON);
            collisionHandler.removeCollidable(Model.getCurrentRoom().getCollidables());
            Model.getNextRoom();
            restartRoom();
            // sbg.enterState(2, new FadeOutTransition(), new
            // FadeInTransition());
        }
        Model.getCurrentRoom().moveBubbles();
    }

    /**
     * Adds a bubble to the game.
     *
     * @param bubble
     *            Bubble to add to the room
     */
    public void addBubble(Bubble bubble) {
        Model.getCurrentRoom().addBubble(bubble);
        collisionHandler.addCollidable(bubble);
    }

    /**
     * Removes a bubble from the game.
     *
     * @param bubble
     *            the bubble to remove
     */
    public void removeBubble(Bubble bubble) {
        Model.getCurrentRoom().removeBubble(bubble);
        collisionHandler.removeCollidable(bubble);
    }

    /**
     * Updates the ropes, calculates the new position of the rope and removes
     * the rope if it hits the ceiling.
     */
    private void updateRopes() throws SlickException {
        for (Player player : Model.getPlayers()) {
            player.moveRopes();
            collisionHandler.removeCollidable(player.removeCollidedRopes());
        }
    }

    /**
     * Ends the game by stopping the view, shows a message.
     *
     * @param message
     *            should be a String containing the message which is shown.
     */
    public void endGame(String message) {
        fireEvent(ControllerEvent.GAMEOVER);
        gc.exit();
    }

    @Override
    public void fireEvent(Event gameEvent) {
        for (Observer observer : observers) {
            observer.update(gameEvent);
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
