package com.sem.btrouble.event;

import com.sem.btrouble.controller.Controller;

/**
 * ControllerEvent, holds different kinds of controller events (e.g. gameover)
 * which can be used to write events to log files.
 */
public class ControllerEvent extends GameEvent {

    public static final int GAMEOVER = 0;
    public static final int GAMESTART = 1;
    public static final int NEXTROOM = 2;
    public static final int RESTARTROOM = 3;
    public static final int OUTOFTIME = 4;
    public static final int GAMEWON = 5;
    public static final int GAMELOST = 6;

    /**
     * Creates a new ControllerEvent, containing the controller which triggers
     * this event, an id which represents the type of event, and a message which
     * can be displayed or written to a log file.
     * 
     * @param controller
     *            should be the controller that triggered the event
     * @param id
     *            should be an integer representing the type of event
     * @param message
     *            should be a String containing a message, explaining the event.
     */
    public ControllerEvent(Controller controller, int id, String message) {
        super(controller, id, message);
    }

    /**
     * Returns a String which can be displayed or written to a log file.
     * 
     * @return returns a String which can be displayed or written to a log file.
     */
    public String toString() {
        return "<ControllerEvent: " + this.getMessage() + ">";
    }
}
