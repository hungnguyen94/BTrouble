package com.sem.btrouble.model;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

/**
 * 
 * @author Christian This class handles the settings for the graphics
 *
 */

public class GraphicSettings {
    // Initialize all the variables for the graphics settings
    private boolean fullscreen = true;
    private boolean antialiasing = true;

    private ArrayList<Resolution> resolutions = new ArrayList<Resolution>();

    private int currentResolution = 1;

    /**
     * 
     * @param fullscreen
     *            should be a boolean representing whether the game should be in
     *            full screen mode.
     * @param antialiasing
     *            should be a boolean representing whether the game should be in
     *            anti aliasing mode.
     * @param currentResolution
     *            should be an integer representing the type of resolution
     * @throws SlickException
     *             when the initialization of the resolutions failed.
     */
    public GraphicSettings(boolean fullscreen, boolean antialiasing, int currentResolution)
            throws SlickException {
        this.fullscreen = fullscreen;
        this.antialiasing = antialiasing;
        this.currentResolution = currentResolution;
        initializeResolutions();
    }

    private final int[] resSet1 = { 1280, 720 };
    private final int[] resSet2 = { 1366, 768 };
    private final int[] resSet3 = { 1600, 900 };
    private final int[] resSet4 = { 1920, 1080 };

    /**
     * Initializes the resolutions, should be called at the creation of a new
     * GraphicSettings.
     * 
     * @throws SlickException
     *             when the initialization of the resolutions failed.
     */
    public void initializeResolutions() throws SlickException {
        Resolution res1 = new Resolution(resSet1[0], resSet1[1], "Sprites/background1280x720.png");
        Resolution res2 = new Resolution(resSet2[0], resSet2[1], null);
        Resolution res3 = new Resolution(resSet3[0], resSet3[1], null);
        Resolution res4 = new Resolution(resSet4[0], resSet4[1], null);

        resolutions.add(res1);
        resolutions.add(res2);
        resolutions.add(res3);
        resolutions.add(res4);
    }

    /**
     * Returns whether the game is in full screen mode.
     * 
     * @return returns a boolean representing whether the game is in full screen
     *         mode.
     */
    public boolean isFullscreen() {
        return fullscreen;
    }

    /**
     * Sets the full screen mode of the game.
     * 
     * @param fullscreen
     *            should be a boolean representing whether the game should be in
     *            full screen mode.
     */
    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    /**
     * Returns whether the game is in anti-aliasing mode.
     * 
     * @return returns a boolean representing whether the game is in
     *         anti-aliasing mode.
     */
    public boolean isAntialiasing() {
        return antialiasing;
    }

    /**
     * Sets the anti-aliasing mode of the game.
     * 
     * @param antialiasing
     *            should be a boolean representing whether the game should be in
     *            anti-aliasing mode.
     */
    public void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
    }

    /**
     * Returns the current resolution of the game.
     * 
     * @return returns an integer representing the type of resolution of the
     *         game.
     */
    public int getCurrentResolution() {
        return currentResolution;
    }

    /**
     * Sets the current resolution of the game.
     * 
     * @param currentResolution
     *            should be an integer representing the type of resolution of
     *            the game.
     */
    public void setCurrentResolution(int currentResolution) {
        this.currentResolution = currentResolution;
    }

    /**
     * Returns the types of resolutions ins the game.
     * 
     * @return returns an ArrayList representing the types of resolutions ins
     *         the game.
     */
    public ArrayList<Resolution> getResolutions() {
        return resolutions;
    }
}