package com.sem.btrouble.event;

public class ExceptionEvent extends GameEvent {

  public ExceptionEvent(Object subject, String message) {
    super(subject, 0, message);
  }

  @Override
  public String toString() {
    return "### <EXCEPTION: " + this.getSubject().getClass().getName() + "; " + this.getMessage()
        + " >";
  }

}
