/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayListIterator<E> implements Iterator<E> {
    protected int currind;
    protected E[] arr;
    protected int prevind;
    ArrayListIterator(E[] array)
    {
        arr = array;
        currind = 0;
    }
    @Override
    public boolean hasNext() {
        if (arr[currind] != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public E next() throws NoSuchElementException{
        if (arr[currind] == null)
        {
            throw new NoSuchElementException();
        }
        prevind = currind;
        currind++;
        return arr[prevind];
    }
}
