package com.sem.btrouble.event;

import com.sem.btrouble.model.Bubble;

/**
 * BubbleEvent, holds different kinds of bubble events which can be used to
 * write events to log files. Should be used by the model to communicate with
 * the controller.
 */
public class BubbleEvent extends GameEvent {

    public static final int COLLISION_FLOOR = 0;
    public static final int COLLISION_WALL = 1;
    public static final int COLLISION_ROPE = 2;
    public static final int COLLISION_CEILING = 3;

    /**
     * Creates a new BubbleEvent, containing a bubble which triggers this event,
     * an id which represents the type of collision, and a message which can be
     * displayed or written to a log file.
     * 
     * @param subject
     *            should be the bubble that triggered the event
     * @param id
     *            should be an integer representing the type of event
     * @param message
     *            should be a String containing a message, explaining the event.
     */
    public BubbleEvent(Bubble subject, int id, String message) {
        super(subject, id, message);
    }

    @Override
    public Bubble getSubject() {
        return (Bubble) super.getSubject();
    }

    /**
     * Returns a String which can be displayed or written to a log file.
     * 
     * @return returns a String which can be displayed or written to a log file.
     */
    public String toString() {
        return "<BubbleEvent: " + this.getMessage() + ">";
    }
}
