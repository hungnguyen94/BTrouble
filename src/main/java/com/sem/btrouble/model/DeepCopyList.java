package com.sem.btrouble.model;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a list that is able to create deep copies of object.
 * The deep copies can be created by the deepCopy static method.
 * @param <E> class to be held in the list.
 */
public class DeepCopyList<E> {

    private List<E> list;
    private E objectCopy;

    /**
     * Constructor which initializes an empty list.
     */
    public DeepCopyList() {
        this.list = new ArrayList<E>();
    }

    /**
     * Add a room to the list.
     * @param obj room to be added
     */
    public void add(E obj) {
        list.add(obj);
    }

    /**
     * This method is a getter for the a list.
     * Instead of getting the object, it returns a deep copy of the object instead.
     * @param index Index the object is located in the list
     * @return Deep copy of the selected object.
     */
    @SuppressWarnings("unchecked")
    public E getCopy(int index) {
        objectCopy = (E) deepCopy(list.get(index));
        return objectCopy;
    }

    /**
     * This method will serialize and deserialize
     * an object, resulting in a deep copy.
     * @param obj Object to be serialized
     * @return A deep copy of the object
     */
    public static Object deepCopy(Object obj) {
        try {
            ByteArrayOutputStream baOutput = new ByteArrayOutputStream();
            ObjectOutputStream oOutput = new ObjectOutputStream(baOutput);
            oOutput.writeObject(obj);

            ByteArrayInputStream baInput = new ByteArrayInputStream(baOutput.toByteArray());
            ObjectInputStream oInput = new ObjectInputStream(baInput);
            return oInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
