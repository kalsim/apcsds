package HeapsLab;

import java.util.*;
/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * leftmost, rightmost, maxDepth, createRandom, coundNodes, countLeaves, preOrder, inOrder,
 * and postOrder traversals, fillList, saveTree, buildTree, loadTree, copy, sameShape,
 * insertMorse, decodeMorse, eval, and createDecodingTree
 *
 * @author Montek Kalsi
 *
 * @version 1/10/18
 */
public class TreeUtil
{
	/**
	 * Instance variable used to prompt for command line input
	 */
	private static Scanner in = new Scanner(System.in);

	/**
	 * Instance variable for debugging
	 */
	private static final boolean DEBUG = false;
	/**
	 * Outputs the leftmost TreeNode in t
	 * @param t the TreeNode whose leftmost node is found
	 * @return the leftmost TreeNode in t
	 */
	public static Object leftmost(TreeNode t)
	{
		if (t == null)
			return t;
		TreeNode temp = t;
		while (temp.getLeft() != null)
			temp = temp.getLeft();
		return temp;
	}
	/**
	 * Outputs the rightmost TreeNode in t
	 * @param t the TreeNode whose rightmost node is found
	 * @return the rightmost TreeNode in t
	 */
	public static Object rightmost(TreeNode t)
	{
		if (t == null)
			return t;
		TreeNode temp = t;
		while (temp.getRight() != null)
			temp = temp.getRight();
		return temp;
	}
	/**
	 * Finds the maximum height in TreeNode t
	 * @param t the TreeNode whose maximum depth is found
	 * @return the maximum height of the tree t
	 */
	public static int maxDepth(TreeNode t)
	{
		if (t == null)
			return 0;
		return 1+Math.max(maxDepth(t.getLeft()), maxDepth(t.getRight()));
	}

	/**
	 * Creates a random tree of the specified depth.  No attempt to balance the tree
	 * is provided.
	 * @param depth of the tree
	 * @return TreeNode object that points to the generated tree
	 */
	public static TreeNode createRandom(int depth)
	{
		if (Math.random() * Math.pow(2, depth) < 1)
			return null;
		return new TreeNode(((int)(Math.random() * 10)),
				createRandom(depth - 1),
				createRandom(depth - 1));
	}
	/**
	 * Counts the nodes in the tree t
	 * @param t the TreeNode whose nodes are counted
	 * @return the number of nodes in the tree t
	 */
	public static int countNodes(TreeNode t)
	{
		if (t == null)
			return 0;
		return 1+(countNodes(t.getLeft()) + countNodes(t.getRight()));
	}
	/**
	 * Counts the leaves in the tree t
	 * @param t the tree whose leaves are counted
	 * @return the number of leaves in tree t
	 */
	public static int countLeaves(TreeNode t)
	{
		if (t==null)
			return 0;
		else if (t.getLeft() == null && t.getRight() == null)
			return 1;
		return countLeaves(t.getLeft()) + countLeaves(t.getRight());
	}
	/**
	 * Traverses and visits the nodes in tree t using a preorder traversal
	 * @param t the tree being traversed
	 * @param display the display used to visit the tree
	 */
	public static void preOrder(TreeNode t, TreeDisplay display)
	{
		if (t!=null)
		{
			display.visit(t);
			preOrder(t.getLeft(), display);
			preOrder(t.getRight(), display);
		}
	}
	/**
	 * Traverses and visits the nodes in tree t using an inorder traversal
	 * @param t the tree being traversed
	 * @param display the display used to visit the tree
	 */
	public static void inOrder(TreeNode t, TreeDisplay display)
	{
		if (t!=null)
		{

			inOrder(t.getLeft(), display);
			display.visit(t);
			inOrder(t.getRight(), display);
		}
	}
	/**
	 * Traverses and visits the nodes in tree t using a postorder traversal
	 * @param t the tree being traversed
	 * @param display the display used to visit the tree
	 */
	public static void postOrder(TreeNode t, TreeDisplay display)
	{
		if (t!=null)
		{

			postOrder(t.getLeft(), display);
			postOrder(t.getRight(), display);
			display.visit(t);
		}
	}
	/**
	 * Generates a list of characters corresponding to the values and shape of the tree t
	 * @param t the tree being converted into a list
	 * @param list the list being created based upon the tree
	 */
	public static void fillList(TreeNode t, List<String> list)
	{
		if(t == null)
		{
			list.add("$");
			return;
		}
		else
		{
			list.add(t.getValue().toString());
			fillList(t.getLeft(), list);
			fillList(t.getRight(), list);
		}
	}
	/**
	 * saveTree uses the FileUtil utility class to save the tree rooted at t
	 * as a file with the given file name
	 * @param fileName is the name of the file to create which will hold the data
	 *        values in the tree
	 * @param t is the root of the tree to save
	 */
	public static void saveTree(String fileName, TreeNode t)
	{
		ArrayList<String> file = new ArrayList<String>();
		fillList(t, file);
		Iterator <String> it = file.iterator();
		FileUtil.saveFile(fileName, it);
	}
	/**
	 * buildTree takes in an iterator which will iterate through a valid description of
	 * a binary tree with String values.  Null nodes are indicated by "$" markers
	 * @param it the iterator which will iterate over the tree description
	 * @return a pointer to the root of the tree built by the iteration
	 */
	public static TreeNode buildTree(Iterator<String> it)
	{
		if (!it.hasNext())
			return null;
		Object temp = it.next();
		if (temp.equals("$"))
		{
			return null;
		}
		else
		{
			return new TreeNode(temp, buildTree(it), buildTree(it));
		}
	}
	/**
	 * read a file description of a tree and then build the tree
	 * @param fileName is a valid file name for a file that describes a binary tree
	 * @return a pointer to the root of the tree
	 */
	public static TreeNode loadTree(String fileName)
	{
		Iterator it = FileUtil.loadFile(fileName);
		return buildTree(it);
	}
	/**
	 * utility method that waits for a user to type text into Std Input and then press enter
	 * @return the string entered by the user
	 */
	private static String getUserInput()
	{
		return in.nextLine();
	}
	/**
	 * plays a single round of 20 questions
	 * postcondition:  plays a round of twenty questions, asking the user questions as it
	 *                 walks down the given knowledge tree, lighting up the display as it goes;
	 *                 modifies the tree to include information learned.
	 * @param t a pointer to the root of the game tree
	 * @param display which will show the progress of the game
	 */
	private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
	{
		throw new RuntimeException("Write ME!");
	}
	/**
	 * plays a game of 20 questions
	 * Begins by reading in a starting file and then plays multiple rounds
	 * until the user enters "quit".  Then the final tree is saved
	 */
	public static void twentyQuestions()
	{
		throw new RuntimeException("Write ME!");
	}
	/**
	 * copy a binary tree
	 * @param t the root of the tree to copy
	 * @return a new tree, which is a complete copy
	 *         of t with all new TreeNode objects
	 *         pointing to the same values as t (in the same order, shape, etc)
	 */
	public static TreeNode copy(TreeNode t)
	{
		if (t == null)
			return null;
		else
		{
			TreeNode temp = new TreeNode(t.getValue(), copy(t.getLeft()), copy(t.getRight()));
			return temp;
		}
	}

	/**
	 * tests to see if two trees have the same shape, but not necessarily the
	 * same values.  Two trees have the same shape if they have TreeNode objects
	 * in the same locations relative to the root
	 * @param t1 pointer to the root of the first tree
	 * @param t2 pointer to the root of the second tree
	 * @return true if t1 and t2 describe trees having the same shape, false otherwise
	 */
	public static boolean sameShape(TreeNode t1, TreeNode t2)
	{
		if (t1 == null && t2 == null)
			return true;
		else if (t1 == null || t2 == null)
			return false;
		return sameShape(t1.getLeft(), t2.getLeft()) && sameShape(t1.getRight(), t2.getRight());
	}
	/**
	 * Generate a tree for decoding Morse code
	 * @param display the display that will show the decoding tree
	 * @return the decoding tree
	 */
	public static TreeNode createDecodingTree(TreeDisplay display)
	{
		TreeNode tree = new TreeNode("Morse Tree");
		display.displayTree(tree);
		insertMorse(tree, "a", ".-", display);
		insertMorse(tree, "b", "-...", display);
		insertMorse(tree, "c", "-.-.", display);
		insertMorse(tree, "d", "-..", display);
		insertMorse(tree, "e", ".", display);
		insertMorse(tree, "f", "..-.", display);
		insertMorse(tree, "g", "--.", display);
		insertMorse(tree, "h", "....", display);
		insertMorse(tree, "i", "..", display);
		insertMorse(tree, "j", ".---", display);
		insertMorse(tree, "k", "-.-", display);
		insertMorse(tree, "l", ".-..", display);
		insertMorse(tree, "m", "--", display);
		insertMorse(tree, "n", "-.", display);
		insertMorse(tree, "o", "---", display);
		insertMorse(tree, "p", ".--.", display);
		insertMorse(tree, "q", "--.-", display);
		insertMorse(tree, "r", ".-.", display);
		insertMorse(tree, "s", "...", display);
		insertMorse(tree, "t", "-", display);
		insertMorse(tree, "u", "..-", display);
		insertMorse(tree, "v", "...-", display);
		insertMorse(tree, "w", ".--", display);
		insertMorse(tree, "x", "-..-", display);
		insertMorse(tree, "y", "-.--", display);
		insertMorse(tree, "z", "--..", display);
		return tree;
	}
	/**
	 * helper method for building a Morse code decoding tree.
	 * postcondition:  inserts the given letter into the decodingTree,
	 *                 in the appropriate position, as determined by
	 *                 the given Morse code sequence; lights up the display
	 *                 as it walks down the tree
	 * @param decodingTree is the partial decoding tree
	 * @param letter is the letter to add
	 * @param code is the Morse code for letter
	 * @param display is the display that will show progress as the method walks
	 *        down the tree
	 */
	private static void insertMorse(TreeNode decodingTree, String letter,
									String code, TreeDisplay display)
	{
		int c = code.length();
		for(int i = 0; i<c; i++)
		{
			if(code.substring(i,i+1).equals("-"))
			{
				if(decodingTree.getRight()==null)
					decodingTree.setRight(new TreeNode(null));
				decodingTree = decodingTree.getRight();
				display.visit(decodingTree);
			}
			else if(code.substring(i,i+1).equals("."))
			{
				if(decodingTree.getLeft()==null)
					decodingTree.setLeft(new TreeNode(null));
				decodingTree = decodingTree.getLeft();
				display.visit(decodingTree);
			}
		}
		decodingTree.setValue(letter);
	}
	/**
	 * decodes Morse code by walking the decoding tree according to the input code
	 * @param decodingTree is the Morse code decoding tree
	 * @param cipherText is Morse code consisting of dots, dashes, and spaces
	 * @param display is the display object that will show the decoding progress
	 * @return the string represented by cipherText
	 */
	public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
	{
		int c = cipherText.length();
		String str = "";
		TreeNode temp = decodingTree;
		for(int i = 0; i<c; i++)
		{
			if(cipherText.substring(i,i+1).equals("-"))
			{
				temp = temp.getRight();
				//display.visit(temp);
			}
			if(cipherText.substring(i,i+1).equals("."))
			{
				temp = temp.getLeft();
				//display.visit(temp);
			}
			if(cipherText.substring(i,i+1).equals(" "))
			{
				display.visit(temp);
				str+=temp.getValue().toString();
				temp = decodingTree;
			}
		}
		display.visit(temp);
		str+=temp.getValue().toString();
		temp = decodingTree;
		return str;
	}

	/**
	 * Evaluates the expression tree expTree
	 * @param expTree the tree being evaluated
	 * @return the value of the expression tree
	 */
	public static int eval(TreeNode expTree)
	{
		if (expTree.getValue().equals("+"))
			return eval(expTree.getLeft()) + eval(expTree.getRight());
		else if (expTree.getValue().equals("-"))
			return eval(expTree.getLeft()) - eval(expTree.getRight());
		else if (expTree.getValue().equals("*"))
			return eval(expTree.getLeft()) * eval(expTree.getRight());
		else if (expTree.getValue().equals("/"))
			return eval(expTree.getLeft()) / eval(expTree.getRight());
		return Integer.parseInt((String) expTree.getValue());
	}
	/**
	 * optional work - NOT WRITTEN
	 */
	public static TreeNode createExpressionTree(String exp)
	{
		throw new RuntimeException("Write ME!");
	}

	/**
	 * debug printout
	 * postcondition: out is printed to System.out
	 * @param out the string to send to System.out
	 */

	private static void debugPrint(String out)
	{
		if(DEBUG) System.out.println("debug: " + out);
	}
}