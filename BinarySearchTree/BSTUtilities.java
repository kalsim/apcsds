package BinarySearchTree;

import HeapsLab.TreeDisplay;
import HeapsLab.TreeNode;
import HeapsLab.TreeUtil;
/**
 * BSTUtilities contains a collection of static methods for operating on binary search trees such as
 * insert, contains, deleteNode, and delete
 *
 * @author Montek Kalsi
 *
 * @version 1/10/18
 */
public abstract class BSTUtilities
{
	/**
	 * Checks whether the binary search tree t contains a node with value x
	 * @precondition:  t is a binary search tree in ascending order
	 * @postcondition: returns true if t contains the value x; otherwise, returns false
	 * @return true if t contains a node with value x
	 */
	public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
	{
		if (t == null)
		{
			return false;
		}
		if (x.compareTo(t.getValue()) == 0)
			return true;
		if (x.compareTo(t.getValue()) <0)
			return contains(t.getLeft(), x, display);
		return contains(t.getRight(), x, display);
	}

	/**
	 * Inserts a new TreeNode with value x into the binary search tree t
	 * following the traditional rules of a binary search tree. If x is a duplicate,
	 * it is ignored.
	 * @precondition:  t is a binary search tree in ascending order
	 * @postcondition: if t is empty, returns a new tree containing x;
	 *                otherwise, returns t, with x having been inserted
	 *                at the appropriate position to maintain the binary
	 *                search tree property; x is ignored if it is a
	 *                duplicate of an element already in t; only one new
	 *                TreeNode is created in the course of the traversal
	 * @return a pointer to the newly modified tree
	 */
	public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
	{
		if (t == null)
		{
			return new TreeNode(x);
		}
		if (x.compareTo(t.getValue()) == 0)
			return t;
		if (x.compareTo(t.getValue()) <0)
		{
			if (t.getLeft()==null)
			{
				t.setLeft(new TreeNode(x));
				return t;
			}
			else
			{
				t.setLeft(insert(t.getLeft(),x,display));
				return t;
			}
		}
		else
		{
			if (t.getRight()==null)
			{
				t.setRight(new TreeNode(x));
				return t;
			}
			else
			{
				t.setRight(insert(t.getRight(),x,display));
				return t;
			}
		}
	}

	/**
	 * Deletes the node t from the binary search tree and outputs a pointer to the
	 * new modified tree
	 * @precondition:  t is a binary search tree in ascending order
	 * @postcondition: returns a pointer to a binary search tree,
	 *                 in which the value at node t has been deleted
	 *                 (and no new TreeNodes have been created)
	 * @return a pointer to the new tree without TreeNode t
	 */
	private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
	{
		TreeNode focusNode = t;
		if (focusNode.getLeft() == null && focusNode.getRight() == null) // deleted node was a leaf
			return null;
		if (focusNode.getLeft() == null)                // deleted node has only one child
			return focusNode.getRight();
		else if (focusNode.getRight() == null)
			return focusNode.getLeft();
		else                            // has sub trees on both sides
		{
			Comparable x = (Comparable)focusNode.getValue();
			Object value =  TreeUtil.leftmost(focusNode.getRight());
			focusNode.setValue(value);              // change the value to make replicate
			Comparable d = (Comparable)value;           // moving the node
			focusNode.setRight(delete(focusNode.getRight(), d, display));
			return focusNode;                   // do we need to return this ?
		}
	}

	/**
	 * Outputs a pointer to the modified tree t after removing any TreeNode
	 * with the value x from the binary search tree t
	 * @precondition:  t is a binary search tree in ascending order
	 * @postcondition: returns a pointer to a binary search tree,
	 *                 in which the value x has been deleted (if present)
	 *                 (and no new TreeNodes have been created)
	 * @return a pointer to the modified binary search tree t
	 */
	public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
	{
		if (contains(t,x,display))
		{

			TreeNode focusNode = t;
			if (x.compareTo(focusNode.getValue()) > 0 )         // go right if greater in value
				focusNode.setRight(delete(focusNode.getRight(), x, display));
			else if (x.compareTo(focusNode.getValue()) < 0)         // go left if lesser in value
				focusNode.setLeft(delete(focusNode.getLeft(), x , display));
			else             // found the node so call delete
			{
				return deleteNode(focusNode, display);          // once found call deleteNode
			}
		}
		return t;
	}
}
