package listlab;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Implementation for my Array List which has multiple methods such as add,
 * remove, get, and an Iterator
 *
 * @author Montek Kalsi
 * @version 11/9/2017
 *
 * @param <E> the type of values contained in the MyLinkedList
 */
public class MyArrayList<E>
{
    /**
     * Instance variable that keeps track of the size
     */
    private int size;
    /**
     * Instance variable that keeps track of the objects in the arraylist in an array
     */
    private Object[] values;  //(Java doesn't let us make an array of type E)

    /**
     * Constructs a MyArrayList object with a size of 0 and an array of values with size 1;
     */
    public MyArrayList()
    {
        size = 0;
        values = new Object[1];
    }

    /**
     * Outputs all of the Objects in the array values as a string
     *
     * @return all of the Objects as a String
     */
    public String toString()
    {
        if (size == 0)
            return "[]";

        String s = "[";
        for (int i = 0; i < size - 1; i++)
            s += values[i] + ", ";
        return s + values[size - 1] + "]";
    }

    /**
     * Doubles the size of the array
     * postcondition: replaces the array with one that is
     *                twice as long, and copies all of the
     *              old elements into it
     */
    private void doubleCapacity()
    {
        Object[] temp = new Object[2 * values.length];
        for (int i = 0; i < values.length; i++)
        {
            temp[i] = values[i];
        }
        values = temp;
    }

    /**
     * Returns the length of the array
     * postcondition: returns the length of the array
     * @return the length of the array
     */
    public int getCapacity()
    {
        return values.length;
    }

    /**
     * Outputs the size of the MyArrayList object
     *
     * @return the size of the MyArrayList object
     */
    public int size()
    {
        return size;
    }

    /**
     * Outputs the value stored at index index of the MyArrayList object
     *
     * @param index the index whose value is returned
     * @return the size of the MyArrayList object
     */
    public E get(int index)
    {
        return (E) values[index];
    }

    /**
     * Sets the value at index index to obj
     * @param index the index whose value is set to obj
     * @param obj the new value stored at index index
     * postcondition: replaces the element at position index with obj
     *                returns the element formerly at the specified position
     * @return the previous value stored at index
     */
    public E set(int index, E obj)
    {
        Object temp = values[index];
        values[index] = obj;
        return (E) temp;
    }

    /**
     * Adds a new obj to the end of the list
     * @param obj the new object added to the end
     * postcondition: appends obj to end of list; returns true
     * @return true after adding the object
     */
    public boolean add(E obj)
    {
        if (size == values.length)
        {
            doubleCapacity();
        }
        values[size] = obj;
        size++;
        return true;
    }

    /**
     * Removes the object at index from the list
     * @param index the index where the object is removed
     * postcondition: removes element from position index, moving elements
     *                at position index + 1 and higher to the left
     *                (subtracts 1 from their indices) and adjusts size
     *                returns the element formerly at the specified position
     * @return the value of the object removed
     */
    public E remove(int index)
    {
        Object temp = values[index];
        for (int i = index + 1; i < values.length; i++)
        {
            values[i - 1] = values[i];
        }
        size--;
        return (E) temp;
    }

    /**
     * Initializes a new MyArrayListIterator object
     * @return a new MyArrayListIterator object
     */
    public Iterator<E> iterator()
    {
        return new MyArrayListIterator();
    }


    /**
     * Adds a new object into the list at index
     * @param index the index where the object is added
     * @param obj the object that is added
     * precondition:  0 <= index <= size
     * postcondition: inserts obj at position index,
     * moving elements at position index and higher
     * to the right (adds 1 to their indices) and adjusts size
     */
    public void add(int index, E obj)
    {
        if (size == values.length)
        {
            doubleCapacity();
        }
        for (int i = size; i > index; i--)
        {
            values[i] = values[i - 1];
        }
        values[index] = obj;
        size++;
    }

    /**
     * Implementation for a MyArrayListIterator which provides methods useful for iterating through
     * and modifying a MyArrayList such as remove
     */
    private class MyArrayListIterator implements Iterator<E>
    {
        /**
         * the index of the value that will be returned by next()
         */
        private int nextIndex;

        /**
         * Initializes a new MyArrayListIterator object with nextIndex set to 0
         */
        public MyArrayListIterator()
        {
            nextIndex = 0;
        }

        /**
         * Checks to see whether there is a next element in the list
         *
         * @return true if there is a next element
         */
        public boolean hasNext()
        {
            return nextIndex < size - 1;
        }

        /**
         * Moves to the next element by adding 1 to nextIndex and returning
         * the value which just preceded
         *
         * @return the value which nextIndex used to be
         */
        public E next()
        {
            nextIndex++;
            return (E) values[nextIndex - 1];
        }

        /**
         * Removes the last element returned by next from the list
         * postcondition: removes the last element that was returned by next
         */
        public void remove()
        {
            MyArrayList.this.remove(nextIndex - 1);
        }
    }

    /**
     * Implementation for a MyArrayListListIterator which helps to create
     * a MyArrayListListIterator which can traverse through a MyArrayList
     * while modifying it in ways such as adding, removing, and setting
     */
    private class MyArrayListListIterator extends MyArrayListIterator implements ListIterator<E>
    {
        /**
         * stores the next index in the MyArrayList
         */
        private int nextIndex;
        /**
         * Index of element that will be returned by the next call of next()
         */
        private int previousIndex;
        /**
         * direction of traversal
         */
        private boolean forward;

        /**
         * Constructs a new MyArrayListListIterator
         */
        public MyArrayListListIterator()
        {
            nextIndex = 0;
            previousIndex = -1;
            forward = true;
        }

        /**
         * Returns next element and moves pointer forward
         * @return next Object in MyArrayList
         */
        public E next()
        {
            nextIndex++;
            previousIndex++;
            forward = true;
            return (E) values[nextIndex -1];
        }

        /**
         * Adds element before element that would be returned by next
         * @param obj element to add
         */
        public void add(E obj)
        {
            MyArrayList.this.add(nextIndex -1, obj);
            nextIndex++;
            previousIndex++;
        }

        /**
         * Determines whether there is another element in MyArrayList
         * while traversing in the backward direction
         * @return true if there is another element, false otherwise
         */
        public boolean hasPrevious()
        {
            return previousIndex>=0;
        }

        /**
         * Returns previous element and moves pointer forward
         * @return previous Object in MyArrayList
         */
        public E previous()
        {
            previousIndex--;
            nextIndex--;
            forward = false;
            return (E) values[previousIndex+1];
        }

        /**
         * Returns index of the next element
         * @return index of element that would be
         *         returned by a call to next()
         */
        public int nextIndex()
        {
            return nextIndex;
        }

        /**
         * Outputs the previous index
         * @return the previous index
         */
        public int previousIndex()
        {
            return previousIndex;
        }

        /**
         * Removes element that was returned by next() pr previous()
         * USE direction FOR THIS
         */
        public void remove()
        {
            if (forward)
            {
                MyArrayList.this.remove(nextIndex - 1);
                nextIndex--;
                previousIndex--;
            }
            else
            {
                MyArrayList.this.remove(previousIndex+1);
                nextIndex--;
                previousIndex--;
            }
        }

        /**
         * Changes the value at the current index in the list to be obj
         * @param obj the new value to be stored at the current index (nextIndex-1)
         */
        public void set(E obj)
        {
            values[nextIndex-1] = obj;
        }
    }

}