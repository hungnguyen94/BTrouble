package event;

public abstract class GameEvent {

  private Object subject;
  private int id;
  private String message;

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
