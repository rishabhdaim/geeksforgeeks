/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class MinHeap<E extends Comparable<? super E>> {

	private E[] elements;
	private int size;
	private static final int CAPACITY = 16;

	public MinHeap() {
		this(CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public MinHeap(final int capacity) {
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
}