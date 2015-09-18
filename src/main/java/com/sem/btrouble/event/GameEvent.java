package com.sem.btrouble.event;

/**
 * Abstract class GameEvent, represents an event in the game. GameEvents have
 * the purpose of notifying classes of events happening in the game. When a
 * GameEvent is fired, it is written to the log file.
 *
 */
public abstract class GameEvent {

  private Object subject;
  private int id;
  private String message;

  /**
   * Constructs a new GameEvent, representing an event in the game.
   * 
   * @param subject
   *          should be the source object of the event.
   * @param id
   *          should be an integer, representing the id of the event type.
   * @param message
   *          should be a String representing the message to be written to the
   *          log file.
   */
  public GameEvent(Object subject, int id, String message) {
    this.subject = subject;
    this.id = id;
    this.message = message;
  }

  public Object getSubject() {
    return subject;
  }

  public int getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public abstract String toString();

}
