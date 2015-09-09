package Group_24.BubbleTrouble;

import java.awt.image.BufferedImage;
import java.lang.Object;

public class Animation {

  private BufferedImage[] frames;
  private int currentFrame;
  
  private long startTime;
  private long delay;
  
  public Animation() {}
  
  public boolean equals(Object other) {
	  boolean res = false;
	  if(other instanceof Animation) {
		  Animation that = (Animation) other;
		  boolean r = false;
		  int size = 0;
		  if(this.frames.length == that.frames.length) {
			  for(int i = 0; i < this.frames.length; i++) {
				  if(this.frames[i].equals(that.frames[i])) size++;
			  }	  
			  if(size == this.frames.length) r = true;
		  }
		  if(this.currentFrame == that.currentFrame && this.startTime == that.startTime && this.delay == that.delay && r) {
			  res = true;
		  }
	  }
	  return res;
  }
  
  public void setFrames(BufferedImage[] images) {
    frames = images;
    if (currentFrame >= frames.length) currentFrame = 0;
  }
  
  public void setDelay(long d) {
    delay = d;
  }
  
  public void update() {
    
    if (delay == -1) return;
    
    long elapsed = (System.nanoTime() -startTime) / 1000000;
    if (elapsed > delay) {
      currentFrame++;
      startTime = System.nanoTime();
    }
    
    if (currentFrame == frames.length) {
      currentFrame = 0;
    }
  }
  
  public BufferedImage getImage() {
    return frames[currentFrame];
  }
  
}
