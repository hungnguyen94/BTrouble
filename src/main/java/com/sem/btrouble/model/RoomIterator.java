package com.sem.btrouble.model;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Provides a way to iterate through a list of rooms.
 */
public class RoomIterator implements Iterator {

    private List<Room> roomsList;
    private Room currentRoom;
    private int counter;

    /**
     * Constructor which initializes an empty list.
     */
    public RoomIterator() {
        this.roomsList = new LinkedList<Room>();
        this.counter = 0;
    }

    /**
     * Constructor with a list containing the rooms.
     * @param rList list of rooms
     */
    public RoomIterator(List<Room> rList) {
        this.roomsList = rList;
        this.counter = 0;
    }

    /**
     * Add a room to the list.
     * @param r room to be added
     */
    public void add(Room r) {
        roomsList.add(r);
    }

    /**
     * Getter for the current loaded room.
     * @return The current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Reload the room object.
     * @return Returns the (copy of) current room
     */
    public Room roomRestart() {
        if(roomsList.isEmpty()) {
            return null;
        }
        currentRoom = roomsList.get(counter).copyRoom();
        return currentRoom;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    public boolean hasNext() {
        return (roomsList.size() - 1) > counter;
    }

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this iterator
     * @throws IllegalStateException         if the {@code next} method has not
     *                                       yet been called, or the {@code remove}
     *                                       method has already been called after
     *                                       the last call to the {@code next}
     *                                       method
     */
    @Override
    public void remove() {
        roomsList.remove(counter--);
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    public Room next() {
        if(hasNext()) {
            counter++;
            return roomsList.get(counter).copyRoom();
        } else {
            throw new NoSuchElementException("No more rooms available");
        }
    }
}
