package hashtables;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * An implementation of HashTable.
 * 
 * This implementation uses chaining to resolve collisions. Chaining means 
 * the underlying array stores references to growable structures (like 
 * LinkedLists) that we expect to remain small in size. When there is a 
 * collision, the element is added to the end of the growable structure. It
 * must search the entire growable structure whenever checking membership
 * or removing elements.
 * 
 * This implementation maintains a capacity equal to 2^n - 1 for some positive
 * integer n. When the load factor exceeds 0.75, the next add() triggers a
 * resize by incrementing n (by one). For example, when n=3, then capacity=7.
 * When size=6, then load factor ~=0.86. The addition of the seventh item would
 * trigger a resize, increasing the capacity of the array to 15.
 */
public class ChainingHashTable<E> implements HashTable<E> {
    
    /**
     * Instantiate a new hash table. The initial capacity should be 7.
     */
    LinkedList<E>[] arr;
    int n;
    int size;
    public ChainingHashTable() 
    {
        size = 0;
        n = 3;
        arr = new LinkedList[7];
    }

    /**
     * Instantiate a new hash table. The initial capacity should be 
     * at least sufficient to hold n elements, but must be one less
     * than a power of two.
     */
    public ChainingHashTable(int n) 
    {
        size = 0;
        this.n = 1;
        while (n > 1)
        {
            n = n / 2;
            this.n += 1;
        }
        arr = new LinkedList[(int) Math.pow(2, this.n) - 1];
    }

    @Override
    public int capacity() 
    {
        return arr.length;
    }

    @Override
    public int size() 
    {
        return size;
    }

    @Override
    public double loadFactor() 
    {
        return ((double)size()/capacity());
    }

    @Override
    public boolean add(E e) 
    {
        int hash = Math.abs(e.hashCode());
        if (loadFactor() > .75)
        {
            ChainingHashTable<E> newarr;
            this.n += 1;
            newarr = new ChainingHashTable<>((int) Math.pow(2, this.n) - 1);
            for (E e2: this)
            {
                newarr.add(e2);
            }
            this.arr = newarr.arr;
        }
        if (arr[hash % capacity()] == null)
        {
            arr[hash % capacity()] = new LinkedList<E>();
        }
        if (this.contains(e))
        {
            arr[hash % capacity()].remove(e);
            arr[hash % capacity()].add(e);
            return false;
        }
        else
        {
            arr[hash % capacity()].add(e);
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(E e) 
    {
        int hash = Math.abs(e.hashCode());
        if (!this.contains(e))
        {
            return false;
        }
        arr[hash % capacity()].remove(e);
        size--;
        return true;
    }    
    @Override
    public boolean contains(E e) 
    {
        int hash = Math.abs(e.hashCode());
        if (arr[hash % capacity()] == null)
        {
            return false;
        }
        LinkedList<E> l = arr[hash % capacity()];
        for (E i : l)
        {
            if (i.equals(e))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(E e) {
        int hash = Math.abs(e.hashCode());
        if (arr[hash % capacity()] == null)
        {
            return null;
        }
        LinkedList<E> l = arr[hash % capacity()];
        for (E i : l)
        {
            if (i.equals(e))
            {
                return i;
            }
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new ChainingHashTableIterator<>(this);
    }
}
