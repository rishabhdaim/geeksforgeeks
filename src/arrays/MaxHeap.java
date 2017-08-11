/**
 * 
 */
package arrays;

import java.util.Objects;

/**
 * @author aa49442
 * 
 */
public class MaxHeap<E extends Comparable<? super E>> {

	private E[] elements;
	private int size;
	private static final int CAPACITY = 16;

	public MaxHeap() {
		this(CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public MaxHeap(int capacity) {
		elements = (E[]) new Comparable[capacity];
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		for (int i = 0; i < size; i++)
			elements[i] = null;
	}

	public boolean offer(E e) {
		add(e);
		return true;
	}

	public void push(E e) {
		add(e);
	}

	public void buildArr(final E e) {
		if (size >= elements.length)
			growUp();
		elements[size++] = e;
	}

	public void add(E e) {
		int i = size;

		if (size == elements.length)
			growUp();

		size = i + 1;

		if (i == 0)
			elements[0] = e;
		else
			siftUp(i, e);
	}

	public E poll() {
		if (size == 0)
			return null;
		return elements[0];
	}

	public E pop() {
		if (size == 0)
			return null;
		int s = --size;
		final E e = elements[s]; // get last element of heap
		elements[s] = null; // to help GC
		final E result = elements[0];
		if (s != 0) // heap is not empty
			siftDown(0, e);
		return result;
	}

	public boolean remove(final E e) {
		int i = indexOf(e);
		if (i == -1)
			return false;
		else
			return removeAt(i);
	}

	private boolean removeAt(final int i) {
		int s = --size;
		if (s == i)
			elements[i] = null; // simply remove last element
		else {
			final E last = elements[s];
			elements[s] = null; // help GC
			siftDown(i, last); // move last element to its correct position
			if (elements[i] == last)
				siftUp(i, last);
		}
		return true;
	}

	/**
	 * Get index of given element
	 * 
	 * @param e element whose index we need to find
	 * @return index of element e if exists else -1
	 */
	private int indexOf(final E e) {
		for (int i = 0; i < size; i++)
			if (Objects.equals(e, elements[i]))
				return i;
		return -1;
	}

	public E removeFirst() {
		return poll();
	}

	/**
	 * create heap from given elements. Every element is greater than its
	 * children. This method only touch first half of elements because leaves
	 * are already at correct position because they have no children.
	 */
	public void heapify() {
		for (int i = size >>> 1 - 1; i >= 0; i--)
			siftDown(i, elements[i]);
	}

	/**
	 * Move elements down where it is greater than both of its children
	 * 
	 * @param i index of element
	 * @param e value of element
	 */
	private void siftDown(int i, final E e) {
		final int half = (size - 1) >>> 1;
		while (i < half) {
			int child = i << 1 + 1;
			int right = child + 1;
			E c = elements[child]; // assuming left child is greater
			if (right < size && c.compareTo(elements[right]) < 0)
				c = elements[child = right]; // get bigger of children
			if (c.compareTo(e) < 0)
				break; // if element is greater than its children
			elements[i] = c; // add bigger child to its parent's index
			i = child;
		}
		elements[i] = e;
	}
	
	/**
	 * Move element up from its current position to where it is smaller than its parent
	 * 
	 * @param i initial index of element
	 * @param e newly added element
	 */
	private void siftUp(int i, final E e) {
		while (i > 0) {
			int parent = (i - i) >>> 1;
			if (e.compareTo(elements[parent]) <= 0)
				break;
			elements[i] = elements[parent];
			i = parent;
		}
		elements[i] = e;
	}

	private void growUp() {
		@SuppressWarnings("unchecked")
		final E[] newArr = (E[]) new Comparable[size * 2];
		for (int i = 0; i < elements.length; i++) {
			newArr[i] = elements[i];
		}

		elements = newArr;
	}
}