package HeapsLab;

/**
 * This HeapUtils class will implement the methods heapify,
 * swap, buildHeap, remove, and insert.
 * @version 1/20/18
 * @author Shray Alag
 * @author Montek Kalsi
 */
public class HeapUtils
{
	/**
	 * modifies heap to make it a max heap
	 * Big O: O(N) where N is the size of the heap
	 * @param heap the array representation of the heap
	 * @param index the index of the root of the tree being heapified
	 * @param heapSize the size of the heap
	 */
	public static void heapify(Comparable[] heap, int index, int heapSize)
	{
		int k = index*2;
		int newindex = index;
		Comparable newobj = null;
		if((k<=heapSize) && (heap[index].compareTo(heap[k])<0 ||
				heap[index].compareTo(heap[k+1])<0))
		{
			if((k+1<=heapSize) && heap[k].compareTo(heap[k+1])>0)
			{
				newindex = k;
				newobj = heap[k];
			}
			else if(k+1<=heapSize)
			{
				newindex = k+1;
				newobj = heap[k+1];
			}
			if(newindex != index)
			{
				heap[newindex] = heap[index];
				heap[index] = newobj;
				heapify(heap, newindex, heapSize);
			}
		}
	}


	/**
	 * Switches the values of two array elements
	 * @param index1 the index of the first element
	 * @param index the index of the second element
	 */
	private static Comparable[] swap(Comparable[] heap, int index, int index1)
	{
		Comparable x = heap[index];
		heap[index] = heap[index1];
		heap[index1] = x;
		return heap;
	}

	/**
	 * Builds the heap starting from the bottom right node
	 * Big O - O(NlogN) where N is the size of the heap
	 * @param heap the input array representing the heap being built
	 * @param heapSize the size of the heap
	 */
	public static void buildHeap(Comparable[] heap, int heapSize)
	{
		int midIndex = heapSize/2;
		for (int i = midIndex; i>0; i--)
		{
			heapify(heap, i, heapSize);
		}
	}

	/**
	 * Removes the root value from a max heap while following the rules of a heap
	 * @param heap an array representing a complete binary tree with
	 *             heapSize nodes
	 * @param heapSize the size of the heap
	 * @return the root value of the original heap
	 */
	public static Comparable remove(Comparable[] heap, int heapSize)
	{
		Comparable max = heap[1];
		Comparable rep = heap[heapSize];
		heap[heapSize] = max;
		heap[1] = rep;
		heapSize--;
		return heap[heapSize];
	}

	/**
	 * Inserts an item into a heap while maintaining the rules of heaps
	 * @param heap an array representing a complete binary tree with
	 *             heapSize nodes
	 * @param item the object to be inserted into the heap
	 * @param heapSize the size of the heap
	 * @return the resulting heap after inserting item
	 */
	public static Comparable[] insert(Comparable[] heap, Comparable item, int heapSize)
	{

		Comparable[] heaptem = new Comparable[heap.length+1];
		for(int i = 1; i<=heapSize; i++)
		{
			heaptem[i] = heap[i];
		}
		heapSize++;
		heaptem[heapSize] = item;
		heap = heaptem;
		buildHeap(heap, heapSize);
		return heap;
	}

	/**
	 * Sorts an array of Comparable objects into ascending order
	 * @param heap is an array
	 * @param heapSize is the size of the array
	 * @return a comparable array of that is sorted
	 */
	public static Comparable[] heapSort (Comparable [] heap, int heapSize)
	{
		for(int k = 11; k>0; k--)
		{
			buildHeap(heap,k);
			remove(heap, k);
		}
		heapify(heap, 1, 2);
		return heap;
	}
}