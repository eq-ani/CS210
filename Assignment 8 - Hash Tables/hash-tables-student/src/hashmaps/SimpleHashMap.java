/*
 * Copyright 2023 Marc Liberatore.
 */
package hashmaps;

import java.util.Set;
import java.util.HashSet;

import hashtables.ChainingHashTable;
import hashtables.ChainingHashTableIterator;    



/**
 * An implementation of a SimpleMap, built using the ChainingHashTable and 
 * SimpleMapEntry classes. This class should behave similarly to the built-in
 * java.util.HashMap, though it is much simpler!
 */
public class SimpleHashMap<K, V> implements SimpleMap<K, V> 
{
    ChainingHashTable<SimpleMapEntry<K,V>>table;

    public SimpleHashMap() 
    {
        table = new ChainingHashTable<>();
    }

    @Override
    public int size() 
    {
        return table.size();
    }

    @Override
    public void put(K k, V v) 
    {
        SimpleMapEntry<K,V> entry = new SimpleMapEntry<K,V>(k, v);
        table.add(entry);
    }    
    @Override
    public V get(K k) 
    {
        SimpleMapEntry<K,V> dummy = new SimpleMapEntry<K,V>(k, null);
        if (table.get(dummy) == null)
        {
            return null;
        }
        return table.get(dummy).v;
    }

    @Override
    public V getOrDefault(K k, V defaultValue) 
    {
        if (this.get(k) != null)
        {
            return this.get(k);
        }
        return defaultValue;
    }

    @Override
    public V remove(K k) 
    {
        SimpleMapEntry<K,V> dummy = new SimpleMapEntry<>(k, null);
        V v;
        if (table.get(dummy) == null)
        {
            v = null;
        }
        else
        {
            v = table.get(dummy).v;
        }
        table.remove(dummy);
        return v;
    }

    @Override
    public Set<K> keys() 
    {
        Set<K> keys = new HashSet<>(); 
        for (SimpleMapEntry<K,V> entry : table)
        {
            keys.add(entry.k);
        }
        return keys;
    }    
}
