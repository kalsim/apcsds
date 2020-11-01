package BinarySearchTree;

import HeapsLab.TreeDisplay;
import HeapsLab.TreeNode;

/**
 * MyTreeSet provides implementation for storing a Set in a Binary Search Tree
 * @author Montek Kalsi
 * @version 4/25/2018
 */
public class MyTreeSet<E>
{
	/**
	 * Stores the root of the MyTreeSet
	 */
	private TreeNode root;
	/**
	 * Stores the size of the MyTreeSet
	 */
	private int size;
	/**
	 * Helps display the MyTreeSet
	 */
	private TreeDisplay display;

	/**
	 * Constructs a MyTreeSet object with a null root, size 0, and a TreeDisplay
	 * with a 1 ms delay
	 */
	public MyTreeSet()
	{
		root = null;
		size = 0;
		display = new TreeDisplay();

		//wait 1 millisecond when visiting a node
		display.setDelay(1);
	}

	/**
	 * Outputs the size of the MyTreeSet
	 * @return the size of the MyTreeSet
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Checks whether obj is stored within the MyTreeSet
	 * @param obj the object whose presence is checked
	 * @return true if obj is present in the MyTreeSet
	 */
	public boolean contains(Object obj)
	{
		return BSTUtilities.contains(root, (Comparable)obj, display);
	}

	/**
	 * Adds obj to the MyTreeSet; if it already exists, nothing happens and it
	 * returns false
	 * @param obj the object being added
	 * @return true if the object was added
	 */
	public boolean add(E obj)
	{
		if (contains(obj))
			return false;
		root = BSTUtilities.insert(root, (Comparable)obj, display);
		size++;
		return true;
	}

	/**
	 * if obj is present in this set, removes obj and
	 * returns true; otherwise returns false
	 * @param obj the object being removed
	 * @return true if obj is removed
	 */
	public boolean remove(Object obj)
	{
		if (!contains(obj))
			return false;
		root = BSTUtilities.delete(root, (Comparable)obj, display);
		size--;
		return true;
	}

	/**
	 * Outputs a string representation of the MyTreeSet
	 * @return a string representation of the contents in the MyTreeSet
	 */
	public String toString()
	{
		return toString(root);
	}

	/**
	 * Helper method which returns a string representation of the MyTreeSet
	 * @param t the TreeNode being converted to a string
	 * @return the string representation of t
	 */
	private String toString(TreeNode t)
	{
		if (t == null)
			return " ";
		return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
	}
}