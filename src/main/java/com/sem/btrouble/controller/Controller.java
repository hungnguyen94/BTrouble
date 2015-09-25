package com.sem.btrouble.controller;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.event.ControllerEvent;
import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Rope;
import com.sem.btrouble.model.Timers;
import com.sem.btrouble.tools.DataLoader;
import com.sem.btrouble.tools.GameObservable;
import com.sem.btrouble.view.GameView;

/**
 * Controller, recalculates the Model, on request of the view.
 */
public class Controller extends GameObservable {

  private GameContainer gc;
  private CollisionHandler collisionHandler;
  private static Timers timers;

  /**
   * Starts a new game by loading data into the room and adding the players.
   */
  public Controller(GameContainer container) throws SlickException {
    timers = new Timers(100);
    this.gc = container;

    collisionHandler = new CollisionHandler();

    collisionHandler.addObserver(new Observer() {

      public void update(Observable o, Object arg) {
        if (arg instanceof GameEvent) {
          GameView.getController().fireEvent((GameEvent) arg);
        }
      }
    });

    Model.init();
    Player p = new Player(0, 0);
    Model.addPlayer(p);
    Model.addRoom(DataLoader.getRoom(0));
    Model.addRoom(DataLoader.getRoom(1));
    refreshCollisions();
  }

  private void refreshCollisions() {
    Model.restartRoom();
    collisionHandler.addCollidable(Model.getPlayers());
    collisionHandler.addCollidable(Model.getCurrentRoom().getCollidables());
  }

  public Timers getTimers() {
    return timers;
  }

  public void drawCollidables(Graphics g) {
    collisionHandler.hitboxDraw(g);
  }

  /**
   * Updates the model, should be done on request of the view.
   */
  public void update(int delta) throws SlickException {
    collisionHandler.checkCollision(Model.getCurrentRoom().getBubbles());

    for (Player player : Model.getPlayers()) {
      if (!collisionHandler.checkCollision(player)) {
        player.setFalling(true);
      }

      if (!player.isAlive()) {
        loseLife(player);
      }
      collisionHandler.checkCollision(player.getRopes());
      if (player.getRopes().size() > 0)
        fireEvent(new PlayerEvent(player, PlayerEvent.SHOOT, "Shot a rope"));
    }

    // Check if timer has run out.
    if (this.getTimers().getLevelTimeLeft() <= 0) {
      fireEvent(new ControllerEvent(this, ControllerEvent.OUTOFTIME, "Out of time"));
      for (Player p : Model.getPlayers())
        loseLife(p);
    }

    for (Player p : Model.getPlayers()) {
      p.move();
    }
    processInput(delta);
    // calculate movements
    updateRopes();
    updateBubble();
  }

  /**
   * Move the player on key presses
   * 
   * @param delta
   *          - milliseconds between frames
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
          (float) (p1.getY() + p1.getHeight() * 0.9));
      if (p1.fire(r))
        collisionHandler.addCollidable(r);
    }
  }

  /**
   * Lets the player lose a life.
   * 
   * @param player
   *          should be the player who lost a life
   */
  public void loseLife(Player player) {
    fireEvent(new PlayerEvent(player, PlayerEvent.LIFE_LOST, "Lost a life"));
    player.loseLife();
    if (!player.hasLives()) {
      endGame("Game over...");
    } else {
      collisionHandler.removeCollidable(Model.getCurrentRoom().getCollidables());
      collisionHandler.removeCollidable(Model.getPlayers().get(0).getRopes());
      restartRoom();
      player.setAlive(true);
    }
  }

  private void restartRoom() {
    fireEvent(new ControllerEvent(this, ControllerEvent.RESTARTROOM, "Room restarted"));
    Model.restartRoom();
    getTimers().restartTimer();
    collisionHandler.addCollidable(Model.getCurrentRoom().getCollidables());
  }

  /**
   * Updates the bubble, checks whether bubbles should be removed or not, and
   * calculates the new position of each bubble.
   */
  private void updateBubble() {
    if (!Model.getCurrentRoom().hasBubbles()) {
      noBubblesLeft();
    } else {
      Model.getCurrentRoom().moveBubbles();
    }
  }

  private void noBubblesLeft() {
    if (Model.hasNextRoom()) {
      fireEvent(new ControllerEvent(this, ControllerEvent.NEXTROOM, "Moved to the next room."));
      Model.loadNextRoom();
      refreshCollisions();
    } else {
      fireEvent(new ControllerEvent(this, ControllerEvent.GAMEWON, "You won the game!"));
      endGame("You won the game!");
    }
  }

  /**
   * Adds a bubble to the game.
   * 
   * @param bubble
   *          Bubble to add to the room
   */
  public void addBubble(Bubble bubble) {
    Model.getCurrentRoom().addBubble(bubble);
    collisionHandler.addCollidable(bubble);
  }

  /**
   * Removes a bubble from the game.
   * 
   * @param bubble
   *          the bubble to remove
   */
  public void removeBubble(Bubble bubble) {
    Model.getCurrentRoom().removeBubble(bubble);
    collisionHandler.removeCollidable(bubble);
  }

  /**
   * Updates the ropes, calculates the new position of the rope and removes the
   * rope if it hits the ceiling.
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
   *          should be a String containing the message which is shown.
   */
  public void endGame(String message) {
    fireEvent(new ControllerEvent(this, ControllerEvent.GAMEOVER, "Game over"));
    gc.exit();
  }

}
