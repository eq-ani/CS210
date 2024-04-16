package hashtables;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChainingHashTableIterator<E> implements Iterator<E> 
{   
    protected int ind;
    protected int llind;
    protected int size;
    protected ChainingHashTable<E> table;
    ChainingHashTableIterator(ChainingHashTable<E> table)
    {
        this.table = table;
        this.ind = 0;
        this.llind = 0;
        this.size = 0;
    }
    @Override
    public boolean hasNext() 
    {
        return size < table.size();
    }

    @Override
    public E next() 
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        }
        if (table.arr[ind] != null && llind < table.arr[ind].size())
        {
            size++;
            llind++;
            return table.arr[ind].get(llind - 1);
        }
        llind = 0;
        ind++;
        while (table.arr[ind] == null || table.arr[ind].size() == 0)
        {
            ind++;
        }
        size++;
        llind++;
        return table.arr[ind].get(llind - 1);
    }
}
