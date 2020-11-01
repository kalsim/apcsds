package HashingLab;

import HashingLab.MapEntry;

import java.util.*;
/**
 * MyHashMap acts like a map.
 *
 * @author Montek Kalsi
 * @version 4/25/2018
 * @param <K>   the type of key
 * @param <V>   the type of value
 */
public class MyHashMap<K, V> implements Map<K, V>
{
    /**
     * Stores the number of buckets with a final variable
     */
    private static final int NUM_BUCKETS = 5;

    /**
     * An array of linked lists which contain map entries to simulate a map
     */
    private LinkedList<MapEntry<K, V>>[] buckets;

    /**
     * Stores the size of the MyHashMap
     */
    private int size;

    /**
     * a constructor
     */
    public MyHashMap()
    {
        buckets = new LinkedList[NUM_BUCKETS];
        size = 0;
    }

    /**
     * Hash function which determines the proper index in buckets where obj is stored
     * @param obj
     *            the object to find the bucket index for
     * @return the correct bucket index for that object
     */
    private int toBucketIndex(Object obj)
    {
        return Math.abs(obj.hashCode()) % NUM_BUCKETS;
    }


    /**
     * Outputs the size of the MyHashMap
     * @return the size of the MyHashMap
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks if the MyHashMap is empty
     * @return true if the MyHashMap is empty
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Checks whether the MyHashMap contains the key key
     * @param key the key whose presence is being checked
     * @return true if the MyHashMap contains the key
     */
    public boolean containsKey(Object key)
    {
        int i = toBucketIndex(key);
        if (buckets[i]!=null)
        {
            for (MapEntry x : buckets[i])
                if (x.getKey().equals(key))
                    return true;
        }
        return false;
    }


    /**
     * Checks to see if value is contained within the MyHashMap
     * @param value the value whose presence is checked
     * @return true if the MyHashMap contains value
     */
    public boolean containsValue(Object value)
    {
        for (int i=0; i<buckets.length; i++)
        {
            if (buckets[i]!=null)
            {
                for (MapEntry x : buckets[i])
                    if (x.getValue().equals(value))
                        return true;
            }
        }
        return false;
    }


    /**
     * Outputs the value associated with the input key
     * @param key the key being input
     * @return the value in the MyHashMap associated with the key
     */
    public V get(Object key)
    {
        int i= toBucketIndex(key);
        if (containsKey(key))
        {
            for (MapEntry x : buckets[i])
                if (x.getKey().equals(key))
                    return (V) x.getValue();
        }
        return null;
    }

    /**
     * Inputs a new MapEntry with the given key and value into
     * the MyHashMap in a typical map fashion. If they key already
     * exists, the value is replaced.
     * @param key the key of the new MapEntry being added
     * @param value the value of the new MapEntry being added
     * @return null if it adds a new MapEntry; otherwise, returns the former
     * value with that key
     */
    public V put(K key, V value)
    {
        int i=toBucketIndex(key);
        if (containsKey(key))
        {
            for (MapEntry x : buckets[i])
                if (x.getKey().equals(key))
                {
                    V t = (V) x.getValue();
                    x.setValue(value);
                    return t;
                }

        }
        else
        {
            if (buckets[i] == null)
            {
                buckets[i] = new LinkedList<>();
            }
            buckets[i].add(new MapEntry<>(key, value));
            size++;
        }
        return null;
    }

    /**
     * Removes the value associated with key from the MyHashMap
     * @param key the key whose entry is being removed
     * @return the previous value stored after removal
     */
    public V remove(Object key)
    {
        int i=toBucketIndex(key);
        if (containsKey(key))
        {
            Iterator it = buckets[i].iterator();
            while (it.hasNext())
            {
                MapEntry temp = (MapEntry) it.next();
                if (temp.getKey().equals(key))
                {
                    V t = (V) temp.getValue();
                    it.remove();
                    size--;
                    return t;
                }
            }

        }
        return null;
    }


    /**
     * putss all the values into the HashSet
     * @param m the map entries being added
     */
    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
        {
            put(key, m.get(key));
        }
    }

    /**
     * Clears all the values in the MyHashSet
     */
    public void clear()
    {
        for (int i = 0; i < NUM_BUCKETS; i++)
        {
            buckets[i] = null;
        }
    }

    /**
     * Outputs a set with all the keys contained in it
     * @return a set with all the keys contained in it
     */
    public Set<K> keySet()
    {
        Set<K> s = new HashSet<K>();
        for (int i=0; i<buckets.length; i++)
        {
            for (MapEntry x: buckets[i])
            {
                s.add((K) x.getKey());
            }
        }
        return s;
    }

    /**
     * Assembles and outputs a collection of all the values stored within the MyHashMap
     * @return a compilation of all the values in the MyHashMap
     */
    public Collection<V> values()
    {
        Set<V> s = new HashSet<V>();
        for (int i=0; i<buckets.length; i++)
        {
            for (MapEntry x: buckets[i])
            {
                s.add((V) x.getValue());
            }
        }
        return s;
    }

    /**
     * Outputs a set containing all the MapEntries in the MyHashMap
     * @return a set with all the MapEntries in the MyHashMap
     */
    public Set<Entry<K, V>> entrySet()
    {
        Set<Entry<K, V>> s = new HashSet<Entry<K, V>>();
        for (int i=0; i<buckets.length; i++)
        {
            for (MapEntry x: buckets[i])
            {
                s.add(x);
            }
        }
        return s;
    }

    @Override
    public String toString()
    {
        String s = "";
        for (int i=0; i<buckets.length; i++)
        {
            if (buckets[i] != null)
            {
                for (MapEntry e : buckets[i])
                {
                    s+= e.getKey() + " : " + e.getValue() + ", ";
                }
            }
        }
        return s;
    }
}
