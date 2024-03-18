/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    E[] array;
    int size;

    public ArrayList() {
        size = 0;
        array = (E[]) new Object[10];
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = prime * result + array[i].hashCode();
        }
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof List))
            return false;
        List other = (List) obj;
        if (size != other.size())
            return false;
        for (int i = 0; i < size; i++) // interate through the list, returning false if any element isn't equal to the
                                       // other
        {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
        // TODO before returning true, make sure each element of the lists are equal!
    }

    @Override
    public int size() { // size will be updated through the remove and add functions
        return size;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) // checks for index out of bounds exception
        {
            throw new IndexOutOfBoundsException();
        }
        return array[index]; // returns the object at index
    }

    @Override
    public void add(E e) {
        if (size == array.length) {
            E[] big = (E[]) new Object[array.length * 2]; // if the size is equal to the array length, create a new
                                                          // array with more space to store more elements
            for (int i = 0; i < array.length; i++) {
                big[i] = array[i];
            }
            array = big;
        }
        array[size] = e; // adds an element to the end of the array, with [size] and increases the size
                         // afterwards
        size++;
    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) // checks for indx out of bounds exceptions
        {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) // increases the array size if neccesary
        {
            E[] big = (E[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                big[i] = array[i];
            }
            array = big;
        }
        for (int i = size; i > index; i--) // starts at the end of the array and moves every element to the right until
                                           // the desired index is reached, setting the array[index] to the given object
        {
            array[i] = array[i - 1];
        }
        array[index] = e;
        size++;

    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) // checks for index out of bounds exception
        {
            throw new IndexOutOfBoundsException();
        }
        E val = array[index]; // temporarily stores the data within the deleted index
        for (int i = index; i < size - 1; i++) // moves all data to the left over the deleted index, removing it from
                                               // the list
        {
            array[i] = array[i + 1];
        }
        size--;
        return val;
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) // checks for index out of bounds exception
        {
            throw new IndexOutOfBoundsException();
        }
        array[index] = e; // sets the data at the given index to the given data.
        return array[index];
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) // interates through the array until a match is found, if not found then return -1
        {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public Iterator<E> iterator() {        
        return new ArrayListIterator(array);
    }
}