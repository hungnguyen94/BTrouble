package com.sem.btrouble.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Room iterator
 */
public class RoomIterator implements Iterator {

    private List<Room> roomsList;
    private int counter;

    public RoomIterator() {
        this.roomsList = new ArrayList<Room>();
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
     * Get a copy of the current room
     */
    public Room roomRestart() {
        return roomsList.get(counter).copyRoom();
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    public boolean hasNext() {
        if((roomsList.size()-1) > counter) {
            return true;
        }
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    public Room next() throws NoSuchElementException {
        counter++;
        return roomsList.get(counter).copyRoom();
    }
}
