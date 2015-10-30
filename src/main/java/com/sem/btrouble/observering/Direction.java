package com.sem.btrouble.observering;

/**
 * Enumeration for directions.
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    NONE;

    /**
     * Returns the Direction enum from a string.
     * @param directionString String with the direction.
     * @return Direction enum corresponding to that string.
     */
    public static Direction stringToDirection(String directionString) {
        switch(directionString) {
            case "up":
                return UP;
            case "down":
                return DOWN;
            case "left":
                return LEFT;
            case "right":
                return RIGHT;
            default:
                return NONE;
        }
    }
}
