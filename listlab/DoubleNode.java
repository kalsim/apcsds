package listlab;


/**
 * Implementation for a Double Node which has multiple methods such as
 * getValue, getPrevious, getNext, and similar setters.
 *
 * @author Montek Kalsi
 * @version 11/9/2017
 */
public class DoubleNode
{
	/**
	 * Instance variable keeping track of the current value
	 */
	private Object value;
	/**
	 * Instance variable keeping track of the previous node
	 */
	private DoubleNode previous;
	/**
	 * Instance variable keeping track of the next node
	 */
	private DoubleNode next;

	/**
	 * Constructs a DoubleNode object with value v
	 * @param v the value which the DoubleNode stores
	 */
	public DoubleNode(Object v)
	{
		value = v;
		previous = null;
		next = null;
	}

	/**
	 * Outputs the value of the DoubleNode
	 * @return the value of the DoubleNode
	 */
	public Object getValue()
	{
		return value;
	}

	/**
	 * Returns the previous DoubleNode
	 * @return the previous DoubleNode
	 */
	public DoubleNode getPrevious()
	{
		return previous;
	}

	/**
	 * Returns the next DoubleNode
	 * @return the next DoubleNode
	 */
	public DoubleNode getNext()
	{
		return next;
	}

	/**
	 * Changes the value stored within the DoubleNode to v
	 * @param v the new value being stored
	 */
	public void setValue(Object v)
	{
		value = v;
	}

	/**
	 * Changes the pointer to the previous DoubleNode to point to a new DoubleNode p
	 * @param p the new previous DoubleNode
	 */
	public void setPrevious(DoubleNode p)
	{
		previous = p;
	}

	/**
	 * Changes the pointer to the next DoubleNode to point to a new DoubleNode n
	 * @param n the new next DoubleNode
	 */
	public void setNext(DoubleNode n)
	{
		next = n;
	}
}