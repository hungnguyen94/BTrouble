package com.sem.btrouble.event;

import javax.security.auth.Subject;

public class ExceptionEvent extends GameEvent {

  public ExceptionEvent(Object subject, String message) {
    super(subject, 0, message);
  }

  @Override
  public String toString() {
    return "### <EXCEPTION: " + this.getSubject().getClass().getName() + "; " + this.getMessage() + " >";
  }

}
