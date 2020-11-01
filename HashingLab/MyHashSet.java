package HashingLab;


import java.util.*;
/**
 * Implementation for my Hash Set which has multiple methods such as contains,
 * toBucketIndex, add, an Iterator, and more
 *
 * @author Montek Kalsi
 * @version 4/25/2018
 *
 * @param <E> the type of values contained in the MyHashSet
 */

public class MyHashSet<E>
{
    /**
     * Stores the number of buckets as a final variable
     */
    private static final int NUM_BUCKETS = 5;

    /**
     * Creates an array of linked lists which contains the values in the
     * MyHashSet
     */
    private LinkedList<E>[] buckets;

    /**
     * Stores the size of the MyHashSet
     */
    private int size;

    /**
     * Constructs a MyHashSet object while initializing the buckets and
     * their contents, and sets the size to 0
     */
    public MyHashSet()
    {
        buckets = new LinkedList[NUM_BUCKETS];
        size = 0;
    }

    /**
     * Hash function which determines the proper index in buckets where
     * obj is stored
     * @param obj the object being stored in the MyHashSet
     * @return the index where the object should be hashed to
     */
    private int toBucketIndex(Object obj)
    {
        return Math.abs(obj.hashCode()) % buckets.length;
    }

    /**
     * Outputs the size of the MyHashSet
     * @return the size of the MyHashSet
     */
    public int size()
    {
        return size;
    }

    /**
     * Determines whether object obj is in the MyHashSet
     * @param obj the object being searched for
     * @return true if an instance of obj is in MyHashSet
     */
    public boolean contains(Object obj)
    {
        int ind = toBucketIndex(obj);
        if (buckets[ind] == null)
        {
            return false;
        }
        Iterator it = buckets[ind].listIterator();
        while (it.hasNext())
        {
            if (it.next().equals(obj))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the object obj into the MyHashSet
     * @param obj the object added into the MyHashSet
     * @return true if it's added and false if it's already contained
     * in the MyHashSet
     */
    public boolean add(E obj)
    {
        int i = toBucketIndex(obj);
        if (buckets[i] == null)
        {
            buckets[i] = new LinkedList<E>();
        }
        if (!contains(obj))
        {
            buckets[i].add(obj);
            size++;
            return true;
        }
        return false;
    }

    /**
     * If obj is present in this set, removes obj and
     * returns true; otherwise returns false
     * @param obj the object being removed from the MyHashSet
     * @return true if obj is removed
     */
    public boolean remove(Object obj)
    {
        if (contains(obj))
        {
            int ind = toBucketIndex(obj);
            Iterator it = buckets[ind].listIterator();
            while (it.hasNext())
            {
                if (it.next().equals(obj))
                {
                    it.remove();
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Outputs the contents of the MyHashSet as a string
     * @return the string equivalent of the values in the MyHashSet
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < buckets.length; i++)
            if (buckets[i] != null)
                s += i + ":" + buckets[i] + " ";
        return s;
    }


    /**
     * Constructs a new MyHashSetIterator
     * @return an iterator for the MyHashSet
     */
    public Iterator<E> iterator()
    {
        return new MyHashSetIterator();
    }

    /**
     * Implementation for my My Hash Set Iterator which has multiple
     * methods for traversing and modifying a My Hash Set
     */
    private class MyHashSetIterator implements Iterator<E>
    {
        /**
         * Keeps track of the current bucket index
         */
        private int index;
        /**
         * Iterator which helps traverse the current Linked List at
         * the location index in buckets
         */
        private ListIterator it;

        /**
         * Constructs a MyHashSetIterator and the current index to be 0
         * while the iterator is null
         */
        public MyHashSetIterator()
        {
            for (int i=0; i<buckets.length; i++)
            {
                if (buckets[i]!=null)
                {
                    index = i;
                    it = buckets[i].listIterator();
                    break;
                }
            }
        }

        /**
         * Checks to see if there is a next value in the MyHashSet
         * @return true if there is a next value in the MyHashSet
         */
        public boolean hasNext()
        {
            if (it.hasNext())
                return true;
            boolean f = false;
            for (int i=index+1; i<buckets.length; i++)
            {
                if (buckets[i]!=null)
                {
                    f=true;
                    break;
                }
            }
            return f;
        }

        /**
         * Outputs the next value in the MyHashSet
         * @return the next value in the MyHashSet
         */
        public E next()
        {
            if (it.hasNext())
            {
                return (E) it.next();
            }
            index++;
            while (buckets[index]==null)
            {
                index++;
            }
            it = buckets[index].listIterator();
            return (E) it.next();
        }

        /**
         * Removes the last element returned by next in the MyHashSet
         * postcondition: removes the last element that was returned by next
         */
        public void remove()
        {
            it.remove();
        }
    }
}