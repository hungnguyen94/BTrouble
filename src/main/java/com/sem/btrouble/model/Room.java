package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Room contains all objects within the room (except for the players), and draws
 * them on the screen.
 *
 */
@SuppressWarnings("serial")
public class Room implements Serializable, Drawable {
    private int spawnPositionX;
    private int spawnPositionY;

    private List<Wall> walls;
    private List<Floor> floors;
    private List<Bubble> bubbles;
    private Image background;

    private List<Collidable> moveableBorders;

    /**
     * Initializes the room with empty objects.
     */
    public Room() {
        walls = new ArrayList<Wall>();
        floors = new ArrayList<Floor>();
        bubbles = new ArrayList<Bubble>();
        moveableBorders = new ArrayList<Collidable>();
        spawnPositionX = 0;
        spawnPositionY = 0;
        try {
            setBackground(new Image("Sprites/background1280x720.png"));
        } catch (RuntimeException | SlickException e) {
            System.out.println("Images ignored.");
        }
    }

    /**
     * Initializes the room with given objects.
     * 
     * @param walls
     *            - list of walls
     * @param floors
     *            - list of floors
     * @param bubbles
     *            - list of bubbles
     * @param spawnX
     *            - spawn position on x-axis
     * @param spawnY
     *            - spawn position on y-axis
     */
    public Room(List<Wall> walls, List<Floor> floors, List<Bubble> bubbles, int spawnX,
        int spawnY, String background) {
        this.walls = walls;
        this.floors = floors;
        this.bubbles = bubbles;
        this.moveableBorders = new ArrayList<Collidable>();
        spawnPositionX = spawnX;
        spawnPositionY = spawnY;
        try {
            setBackground(new Image("Sprites/"+background));
        } catch (RuntimeException | SlickException e) {
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
            return (this.bubbles.equals(that.bubbles) && this.spawnPositionX == that.spawnPositionX
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
    public Collection<Collidable> getCollidables() {
        ArrayList<Collidable> allCollidables = new ArrayList<Collidable>();
        allCollidables.addAll(walls);
        allCollidables.addAll(floors);
        // allCollidables.addAll(bubbles);
        // allCollidables.addAll(moveableBorders);
        return allCollidables;
    }

    /**
     * Return all collidable objects in a room.
     *
     * @return - all collidable objects in a room
     */
    public Collection<Collection<? extends Collidable>> getCollidablesLists() {
        Collection<Collection<? extends Collidable>> allCollidables = new ArrayList<>();
        allCollidables.add(walls);
        allCollidables.add(floors);
        // allCollidables.addAll(bubbles);
        // allCollidables.addAll(moveableBorders);
        return allCollidables;
    }

    /**
     * Returns the collection of bubbles within this Room.
     * 
     * @return returns the collection of bubbles within this Room.
     */
    public Collection<Bubble> getBubbles() {
        return bubbles;
    }

    /**
     * Add a bubble object to the room.
     * 
     * @param b
     *            - bubble to be added
     */
    public void addBubble(Bubble b) {
        bubbles.add(b);
    }

    /**
     * Remove a bubble object from the room.
     * 
     * @param b
     *            - bubble to be removed
     */
    public void removeBubble(Bubble b) {
        bubbles.remove(b);
    }

    /**
     * Check is the room has bubbles.
     * 
     * @return - true if the value has bubbles
     */
    public boolean hasBubbles() {
        return !bubbles.isEmpty();
    }

    /**
     * Move all the bubbles in the room.
     */
    public void moveBubbles() {
        for (Bubble b : bubbles) {
            b.move();
        }
        if (!moveableBorders.isEmpty()) {
            for (Collidable f : moveableBorders) {
                if (f instanceof Floor) {
                    Floor that = (Floor) f;
                    that.grow(0f, 0.1f);
                    that.setY(630f - that.getHeight());
                }
            }
        }
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
        for (Wall w : walls) {
            w.draw(graphics);
        }
        for (Floor f : floors) {
            f.draw(graphics);
        }
         for (Bubble b: bubbles) {
         b.draw(graphics);
         }
    }

    public void addMovables(ArrayList<Floor> movableFloors) {
        moveableBorders.addAll(movableFloors);
    }

}
