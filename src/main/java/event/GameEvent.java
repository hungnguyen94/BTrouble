package event;

public abstract class GameEvent {
  
  private int type;
  
  public GameEvent(int type) {
    this.type = type;
  }
  
  public int getType(){
    return this.type;
  }
  
  public abstract String toString();
  
}
