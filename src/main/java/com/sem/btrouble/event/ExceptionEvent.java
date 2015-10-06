package com.sem.btrouble.event;

/**
 * ExceptionEvent, represents an exception as an Event.
 */
public class ExceptionEvent extends GameEvent {

    /**
     * Creates a new ExceptionEvent, containing the exception which triggers
     * this event, and a message which can be displayed or written to a log
     * file.
     * 
     * @param subject
     *            should be the exception that triggered the event
     * @param message
     *            should be a String containing a message, explaining the event.
     */
    public ExceptionEvent(Object subject, String message) {
	super(subject, 0, message);
    }

    /**
     * Returns a String which can be displayed or written to a log file.
     * 
     * @return returns a String which can be displayed or written to a log file.
     */
    @Override
    public String toString() {
	return "### <EXCEPTION: " + this.getSubject().getClass().getName() + "; "
		+ this.getMessage() + " >";
    }

}
