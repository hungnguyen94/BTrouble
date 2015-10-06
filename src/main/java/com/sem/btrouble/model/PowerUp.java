package com.sem.btrouble.model;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public abstract class PowerUp extends Rectangle {
	
	private boolean falling;
	private float vy;
	private float ay;
	
	public PowerUp() {
		super(-50, -50, 50, 100);
	}
	
	public PowerUp(float xpos, float ypos) {
		super(xpos, ypos, 50, 100);
		this.falling = true;
		this.vy = 2;
		this.ay = .3f;
	}

    public abstract void activate();
    
    public abstract void reset();
    
    public abstract void draw() throws SlickException;
    
    public boolean isFalling() {
        return falling;
    }
    
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    public void fall() {
    	float y = getY();
        float changeY = y += vy;
        setY(changeY);
        vy += ay;
    }
    
    public void move() {
        if (isFalling())
            fall();
        else
            vy = 0;
    }
    
}
