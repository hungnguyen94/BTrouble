package com.sem.btrouble.model;

/**
 * Class which takes care of the resolution.
 * @author Christian This class handles the different resolutions
 *
 */

public class Resolution {

    private int screenWidth;
    private int screenHeight;
    private String background;

    /**
     * Constructs a resolution.
     * @param screenWidth width of the screen
     * @param screenHeight height of the screen
     * @param background background
     */
    public Resolution(int screenWidth, int screenHeight, String background) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.background = background;
    }

    /**
     * Are two resolutions equal.
     * @param other object to compare with
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other instanceof Resolution) {
            Resolution that = (Resolution) other;
            return (this.screenHeight == that.screenHeight && this.screenWidth == that.screenWidth
                    && this.background.equals(that.background));
        }
        return false;
    }
    
    /**
     * HashCode because of implemented equals method.
     */
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }

    /**
     * This method returns the background for a certain resolution.
     * 
     * @return The background
     */
    public String getBackground() {
        return background;
    }

    /**
     * This method returns the width of the resolution.
     * 
     * @return Width of the screen
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * This method changes the width of the resolution.
     * 
     * @param screenWidth width of the screen
     */
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    /**
     * This method returns the height of the resolution.
     * 
     * @return Height of the screen
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * This method changes the height of the resolution.
     * 
     * @param screenHeight height of the screen
     */
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

}