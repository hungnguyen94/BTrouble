package com.sem.btrouble.model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

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
public class Room implements Serializable {
    private int spawnPositionX;
    private int spawnPositionY;

    private List<Wall> walls;
    private List<Floor> floors;
    private List<Bubble> bubbles;

    private List<Shape> moveableBorders;

    /**
     * Initializes the room with empty objects.
     */
    public Room() {
        walls = new ArrayList<Wall>();
        floors = new ArrayList<Floor>();
        bubbles = new ArrayList<Bubble>();
        moveableBorders = new ArrayList<Shape>();
        spawnPositionX = 0;
        spawnPositionY = 0;
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
            int spawnY) {
        this.walls = walls;
        this.floors = floors;
        this.bubbles = bubbles;
        this.moveableBorders = new ArrayList<Shape>();
        spawnPositionX = spawnX;
        spawnPositionY = spawnY;
    }

    /**
     * Create an exact copy of the current room with the same parameters and
     * return it.
     * @return - Return a deep copy of the current room.
     */
    public Room copyRoom() {
        try {
            ByteArrayOutputStream baOutput = new ByteArrayOutputStream();
            ObjectOutputStream oOutput = new ObjectOutputStream(baOutput);
            oOutput.writeObject(this);

            ByteArrayInputStream baInput = new ByteArrayInputStream(baOutput.toByteArray());
            ObjectInputStream oInput = new ObjectInputStream(baInput);
            return (Room) oInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
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
    public Collection<Shape> getCollidables() {
        ArrayList<Shape> allCollidables = new ArrayList<Shape>();
        allCollidables.addAll(walls);
        allCollidables.addAll(floors);
        allCollidables.addAll(bubbles);
        allCollidables.addAll(moveableBorders);
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
            for (Shape f : moveableBorders) {
                if (f instanceof Floor) {
                    Floor that = (Floor) f;
                    that.moveUp();
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
     * Method to load a room with default hard coded data.
     */
    public void loadRoom() {
        spawnPositionX = Model.getRoomWidth() / 2;
        spawnPositionY = 400;
        walls.clear();
        walls.add(new Wall(0, 0, 20, Model.getRoomHeight()));
        walls.add(new Wall(Model.getRoomWidth(), 0, 20, Model.getRoomHeight()));
        floors.clear();
        floors.add(new Floor(0, Model.getRoomHeight() - ((Model.getRoomHeight() / 100) * 14),
                Model.getRoomWidth(), 50));
        floors.add(new Floor(0, 0, Model.getRoomWidth(), 50));
        bubbles.clear();
        bubbles.add(new Bubble(2, Model.getRoomWidth() / 2, 200));
    }

    /**
     * Method to load a room with default hard coded data. Temp for test
     */
    public void loadRoom2() {
        spawnPositionX = Model.getRoomWidth() / 4;
        spawnPositionY = 400;
        walls.clear();
        walls.add(new Wall(0, 0, 20, Model.getRoomHeight()));
        walls.add(new Wall(Model.getRoomWidth(), 0, 20, Model.getRoomHeight()));
        floors.clear();
        Floor fTemp = new Floor(0, Model.getRoomHeight() - ((Model.getRoomHeight() / 100) * 14),
                Model.getRoomWidth(), 50);
        floors.add(fTemp);
        moveableBorders.add(fTemp);
        floors.add(new Floor(0, 0, 1123, 50));
        bubbles.clear();
        bubbles.add(new Bubble(2, Model.getRoomWidth() / 5, 200));
        bubbles.add(new Bubble(2, Model.getRoomWidth() - 100, 250));
        bubbles.add(new Bubble(2, Model.getRoomWidth() - 200, 200));
        bubbles.add(new Bubble(2, Model.getRoomWidth() - 300, 100));
    }

    /**
     * Draw the walls and floors.
     * 
     * @param g
     *            - graphics handler from Slick2D
     */
    public void drawRoom(Graphics g) {
        for (Wall w : walls) {
            g.fillRect(w.getX(), w.getY(), 5, w.getHeight());
        }
        for (Floor f : floors) {
            g.fillRect(f.getX(), f.getY(), f.getWidth(), 5);
        }
    }
}
