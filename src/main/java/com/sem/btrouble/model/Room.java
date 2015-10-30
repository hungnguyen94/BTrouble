package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Room contains all objects within the room (except for the players), and draws
 * them on the screen.
 *
 */
@SuppressWarnings("serial")
public class Room implements Drawable {
    private int spawnPositionX;
    private int spawnPositionY;

    private List<Wall> walls;
    private List<Floor> floors;
    private Image background;

    private List<Wall> moveableWalls;
    private List<Floor> moveableFloors;

    /**
     * Initializes the room with empty objects.
     */
    public Room() {
        walls = new ArrayList<>();
        floors = new ArrayList<>();
        moveableWalls = new ArrayList<>();
        moveableFloors = new ArrayList<>();
        spawnPositionX = 0;
        spawnPositionY = 0;
        try {
            setBackground(new Image("Sprites/background1280x720.png"));
        } catch (RuntimeException | SlickException | UnsatisfiedLinkError e) {
            System.out.println("Images ignored.");
        }
    }

    /**
     * Initializes the room with given objects.
     * 
     * @param walls list of walls
     * @param floors list of floors
     * @param spawnX spawn position on x-axis
     * @param spawnY spawn position on y-axis
     * @param background background image file location
     */
    public Room(List<Wall> walls, List<Floor> floors, int spawnX, int spawnY, String background) {
        this.walls = walls;
        this.floors = floors;
        this.moveableWalls = new ArrayList<>();
        this.moveableFloors = new ArrayList<>();
        this.spawnPositionX = spawnX;
        this.spawnPositionY = spawnY;
        try {
            setBackground(new Image("Sprites/"+background));
        } catch (RuntimeException | SlickException | UnsatisfiedLinkError e) {
            System.out.println("Images ignored.");
        }
    }

    /**
     * Returns the background of the room.
     * 
     * @return returns the background of the room.
     */
    public Image getBackground() {
        return background;
    }

    /**
     * Returns the walls in the room.
     * 
     * @return returns the walls in the room
     */
    public List<Wall> getWalls() {
        return walls;
    }

    /**
     * Returns the floors in the room.
     * 
     * @return returns the floors in the room
     */
    public List<Floor> getFloors() {
        return floors;
    }

    /**
     * Get the borders which are moveable.
     * @return the moveable borders
     */
    public List<Wall> getMoveableWalls() {
        return moveableWalls;
    }

    /**
     * Adds movable walls to the room.
     * @param movableWalls movable walls
     */
    public void addMovableWalls(Collection<Wall> movableWalls) {
        moveableWalls.addAll(movableWalls);
    }

    /**
     * Get the borders which are moveable.
     * @return the moveable floors
     */
    public List<Floor> getMoveableFloors() {
        return moveableFloors;
    }

    /**
     * Adds movable Floors to the room.
     * @param movableFloors movable floors
     */
    public void addMovableFloors(Collection<Floor> movableFloors) {
        moveableFloors.addAll(movableFloors);
    }

    /**
     * Sets the background of the room.
     * 
     * @param background
     *            should be the background the room should have.
     */
    public void setBackground(Image background) {
        this.background = background;
    }

    /**
     * Checks whether the provided Object is the same as this Room.
     * 
     * @param other
     *            should be the Object to be checked for equality.
     * @return returns a boolean representing whether the provided Object is the
     *         same as this Room.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Room) {
            Room that = (Room) other;
            return (this.spawnPositionX == that.spawnPositionX
                    && this.spawnPositionY == that.spawnPositionY);
        }
        return false;
    }

    /**
     * HashCode because of implemented equals method.
     * 
     * @return hashCode
     */
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }

    /**
     * Return all collidable objects in a room.
     *
     * @return - all collidable objects in a room
     */
    public Collection<Collidable> getCollidablesList() {
        Collection<Collidable> allCollidables = new ArrayList<>();
        allCollidables.addAll(walls);
        allCollidables.addAll(floors);
        return allCollidables;
    }

    /**
     * Return the x coordinate of the spawn position.
     * 
     * @return - x coordinate of spawn position
     */
    public int getSpawnPositionX() {
        return spawnPositionX;
    }

    /**
     * Return the y coordinate of the spawn position.
     * 
     * @return - y coordinate of spawn position
     */
    public int getSpawnPositionY() {
        return spawnPositionY;
    }

    /**
     * Set the position x.
     * 
     * @param x
     *            position x
     */
    public void setSpawnPositionX(int x) {
        this.spawnPositionX = x;
    }

    /**
     * Set the position y.
     * 
     * @param y
     *            position y
     */
    public void setSpawnPositionY(int y) {
        this.spawnPositionY = y;
    }

    /**
     * Draw the walls and floors.
     *
     * @param graphics
     *            The graphics
     */
    @Override
    public void draw(Graphics graphics) {
        if(background != null){
            background.draw(0f, 0f);
        }
        graphics.setColor(Color.blue);
        for (Floor f : floors) {
            f.draw(graphics);
        }
    }
}
