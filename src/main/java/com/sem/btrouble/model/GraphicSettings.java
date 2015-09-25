package com.sem.btrouble.model;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author Christian
 * This class handles the settings for the graphics
 *
 */

public class GraphicSettings {
    // Initialize all the variables for the graphics settings
    private boolean fullscreen = true;
    private boolean antialiasing = true;
    
    private ArrayList<Resolution> resolutions = new ArrayList<Resolution>();

    
    private int currentResolution = 1;
    
    public GraphicSettings (boolean fullscreen, boolean antialiasing, int currentResolution) throws SlickException {
        this.fullscreen = fullscreen;
        this.antialiasing = antialiasing;
        this.currentResolution = currentResolution;
        initializeResolutions();
    }
    
    public void initializeResolutions() throws SlickException {
        Resolution res1 = new Resolution(1280, 720, "Sprites/background1280x720.png");
        Resolution res2 = new Resolution(1366, 768, null);
        Resolution res3 = new Resolution(1600, 900, null);
        Resolution res4 = new Resolution(1920, 1080, null);
        
        resolutions.add(res1);
        resolutions.add(res2);
        resolutions.add(res3);
        resolutions.add(res4);
    }
    
    public boolean isFullscreen() {
        return fullscreen;
    }
    
    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }
    
    public boolean isAntialiasing() {
        return antialiasing;
    }
    
    public void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
    }
    
    public int getCurrentResolution() {
        return currentResolution;
    }
    
    public void setCurrentResolution(int currentResolution) {
        this.currentResolution = currentResolution;
    }  
    
    public ArrayList<Resolution> getResolutions() {
        return resolutions;
    }
}