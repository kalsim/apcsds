
package listlab;

import java.util.Iterator;
import java.util.ListIterator;
/**
 * Implementation for my Linked List which has multiple methods such as add,
 * remove, get, and an Iterator
 *
 * @author Montek Kalsi
 * @version 11/9/2017
 *
 * @param <E> the type of values contained in the MyLinkedList
 */
public class MyLinkedList<E>
{
	/**
	 * Keeps track of the first DoubleNode in the MyLinkedList
	 */
	private DoubleNode first;
	/**
	 * Keeps track of the last DoubleNode in the MyLinkedList
	 */
	private DoubleNode last;
	/**
	 * Keeps track of the size of the MyLinkedList
	 */
	private int size;

	/**
	 * Constructs a MyLinkedList object with first and last set to null and size being 0
	 */
	public MyLinkedList()
	{
		first = null;
		last = null;
		size = 0;
	}

	/**
	 * Outputs all of the Objects in the list as a string
	 *
	 * @return all of the Objects as a String
	 */
	public String toString()
	{
		DoubleNode node = first;
		if (node == null)
			return "[]";
		String s = "[";
		while (node.getNext() != null)
		{
			s += node.getValue() + ", ";
			node = node.getNext();
		}
		return s + node.getValue() + "]";
	}

	/**
	 * Returns the node at index and starts traversing from first
	 * @param index the index where the node is returned
	 * precondition:  0 <= index <= size / 2
	 * postcondition: starting from first, returns the node
	 *                with given index (where index 0
	 *                returns first)
	 * @return the DoubleNode at index
	 */
	private DoubleNode getNodeFromFirst(int index)
	{
		DoubleNode i = first;
		int x = index;
		while (x>0)
		{
			i = i.getNext();
			x--;
		}
		return i;
	}

	/**
	 * Returns the node at index and starts traversing from last going backwards
	 * @param index the index where the node is returned
	 * precondition:  size / 2 <= index < size
	 * postcondition: starting from last, returns the node
	 * with given index (where index size-1
	 * returns last)
	 * @return the DoubleNode at index
	 */
	private DoubleNode getNodeFromLast(int index)
	{
		DoubleNode i = last;
		int x = size-1-index;
		while (x>0)
		{
			i = i.getPrevious();
			x--;
		}
		return i;
	}

	/**
	 *
	 *  Returns the node at position index
	 *  @param index the index where the node is returned
	 * precondition:  0 <= index < size
	 * postcondition: starting from first or last (whichever
	 * is closer), returns the node with given
	 * index
	 * @return the DoubleNode at index
	 */

	private DoubleNode getNode(int index)
	{
		if (index<=size/2)
		{
			return getNodeFromFirst(index);
		}
		return getNodeFromLast(index);
	}

	/**
	 * Outputs the size of the MyLinkedList
	 * @return the size of the list
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Returns the value of the DoubleNode at position index in the list
	 * @param index the index where the DoubleNode is
	 * @return the value of the DoubleNode at index
	 */
	public E get(int index)
	{

		return (E) getNode(index).getValue();
	}

	/**
	 * Changes the value of the DoubleNode at index to obj, and outputs the previous value
	 * @param index the index where the DoubleNode is located
	 * @param obj the new value of the DoubleNode
	 * postcondition: replaces the element at position index with obj
	 *               returns the element formerly at the specified position
	 * @return the element previously at position index
	 */
	public E set(int index, E obj)
	{
		Object temp = getNode(index).getValue();
		getNode(index).setValue(obj);
		return (E) temp;
	}

	/**
	 * Appends a new DoubleNode to the end of the list with value obj
	 * @param obj the value of the new DoubleNode being added
	 * postcondition: appends obj to end of list; returns true
	 * @return true when the new DoubleNode is added
	 */
	public boolean add(E obj)
	{
		if (last == null)
		{
			DoubleNode n = new DoubleNode(obj);
			first = n;
			last = n;
			size++;
			return true;
		}
		else
		{
			DoubleNode n = new DoubleNode(obj);
			n.setPrevious(last);
			last.setNext(n);
			last = n;
			size++;
			return true;
		}
	}

	/**
	 * Removes the DoubleNode at position index of the list and returns its value
	 * @param index the index where the DoubleNode is removed
	 * postcondition: removes element from position index, moving elements
	 *                at position index + 1 and higher to the left
	 *                (subtracts 1 from their indices) and adjusts size
	 *                returns the element formerly at the specified position
	 * @return the previous value stored in the removed DoubleNode
	 */
	public E remove(int index)
	{
		if (index == 0)
		{
			return removeFirst();
		}
		else if (index == size-1)
		{
			return removeLast();
		}
		DoubleNode node = getNode(index);
		Object prev = node.getValue();
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		size--;
		return (E) prev;

	}

	/**
	 * Adds a new DoubleNode with value obj at position index in the list
	 * @param index the index where the new DoubleNode is added
	 * @param obj the value of hte new DoubleNode being added
	 * precondition:  0 <= index <= size
	 * postcondition: inserts obj at position index,
	 *                moving elements at position index and higher
	 *                to the right (adds 1 to their indices) and adjusts size
	 */
	public void add(int index, E obj)
	{
		if (index==0)
			addFirst(obj);
		else if (index==size)
			addLast(obj);
		else
		{
			DoubleNode temp = (DoubleNode) getNode(index);
			DoubleNode insert = new DoubleNode(obj);
			insert.setPrevious(temp.getPrevious());
			insert.setNext(temp);
			temp.getPrevious().setNext(insert);
			temp.setPrevious(insert);
			size++;
		}
	}

	/**
	 * Adds a new DoubleNode with value obj to the front of the list
	 * @param obj the value of the new DoubleNode being added
	 */
	public void addFirst(E obj)
	{
		DoubleNode node = new DoubleNode(obj);
		if (first==null)
		{
			first = node;
			last = node;
		}
		else
		{
			node.setNext(first);
			first.setPrevious(node);
			first = node;
		}
		size++;
	}

	/**
	 * Adds a new DoubleNode with value obj to the end of the list
	 * @param obj the value of the new DoubleNode being added
	 */
	public void addLast(E obj)
	{
		add(obj);
	}

	/**
	 * Returns the first DoubleNode in the list
	 * @return the first DoubleNode in the list
	 */
	public E getFirst()
	{
		return (E) first;
	}

	/**
	 * Returns the last DoubleNode in the list
	 * @return the last DoubleNode in the list
	 */
	public E getLast()
	{
		return (E) last;
	}

	/**
	 * Removes the first DoubleNode in the list
	 * @return the value previously stored in the removed DoubleNode
	 */
	public E removeFirst()
	{
		if (size<=1)
		{
			Object temp = first.getValue();
			first = null;
			last = null;
			size = 0;
			return (E) temp;
		}
		Object temp = first.getValue();
		first = first.getNext();
		first.setPrevious(null);
		size--;
		return (E) temp;
	}

	/**
	 * Removes the last DoubleNode in the list
	 * @return the value previously stored in the removed DoubleNode
	 */
	public E removeLast()
	{
		if (size<=1)
		{
			Object temp = first.getValue();
			first = null;
			last = null;
			size = 0;
			return (E) temp;
		}
		Object temp = last.getValue();
		last = last.getPrevious();
		last.setNext(null);
		size--;
		return (E) temp;
	}

	/**
	 * Constructs and returns a new MyLinkedListIterator
	 * @return a new MyLinkedListIterator
	 */
	public Iterator<E> iterator()
	{
		return new MyLinkedListIterator();
	}

	/**
	 * Implementation for my My Linked List Iterator which has multiple
	 * methods for traversing and modifying a My Linked List
	 */
	private class MyLinkedListIterator implements Iterator<E>
	{
		/**
		 * Keeps track of the next node in the list
		 */
		private DoubleNode nextNode;

		/**
		 * Constructs a MyLinkedListIterator and sets nextNode to be equal to first
		 */
		public MyLinkedListIterator()
		{
			nextNode = first;
		}

		/**
		 * Checks to see if there is a next DoubleNode in the list
		 * @return true if the next node is not null (i.e. there is a next node in the list)
		 */
		public boolean hasNext()
		{
			return nextNode != null;
		}

		/**
		 * Outputs the value of the next DoubleNode in the MyLinkedList
		 * @return the value of the next DoubleNode
		 */
		public E next()
		{
			DoubleNode temp = nextNode;
			nextNode = nextNode.getNext();
			return (E) temp.getValue();
		}

		/**
		 * Removes the last element returned by next in the list
		 * postcondition: removes the last element that was returned by next
		 */
		public void remove()
		{
			int i = 0;
			DoubleNode temp = first;
			while (temp != nextNode.getPrevious())
			{
				temp = temp.getNext();
				i++;
			}
			MyLinkedList.this.remove(i);
		}
	}
}