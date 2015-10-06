package com.sem.btrouble.event;

import com.sem.btrouble.model.Player;

/**
 * PlayerEvent, holds different kinds of player events (e.g. life lost) which
 * can be used to write events to log files.
 */
public class PlayerEvent extends GameEvent {

    public static final int COLLISION_BUBBLE = 0;
    public static final int COLLISION_LEFTWALL = 1;
    public static final int COLLISION_RIGHTWALL = 2;
    public static final int POPBUBBLE = 3;
    public static final int LIFE_LOST = 4;
    public static final int LIFE_GAINED = 5;
    public static final int SHOOT = 6; // TODO: Has to be fired.

    /**
     * Constructs a new PlayerEvent, representing an event in the game.
     * 
     * @param subject
     *            should be the object which triggered the event.
     * @param id
     *            should be an integer, representing the id of the event type.
     * @param message
     *            should be a String representing the message to be written to
     *            the log file.
     */
    public PlayerEvent(Object subject, int id, String message) {
	super(subject, id, message);
    }

    @Override
    public Player getSubject() {
	return (Player) super.getSubject();
    }

    @Override
    public String toString() {
	return "<PlayerEvent: " + this.getMessage() + ">";
    }
}
