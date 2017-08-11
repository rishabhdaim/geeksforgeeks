/**
 * 
 */
package stack;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class ArrayPriorityQueue<E extends Comparable<? super E>> implements
		Queue<E> {

	private E[] elements;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayPriorityQueue() {
		elements = (E[]) new Comparable[10];
		size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see stacks.Queue#offer(java.lang.Object)
	 */
	@Override
	public boolean offer(E e) {
		int i = size - 1;
		int j;
		for (j = i; j >= 0 && elements[j].compareTo(e) < 0; j--)
			elements[j + 1] = elements[j];
		elements[j + 1] = e;
		size++;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see stacks.Queue#poll()
	 */
	@Override
	public E poll() {
		E e = elements[size - 1];
		elements[size - 1] = null;
		size--;
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see stacks.Queue#peek()
	 */
	@Override
	public E peek() {
		return elements[size - 1];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see stacks.Queue#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see stacks.Queue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see stacks.Queue#search(java.lang.Object)
	 */
	@Override
	public boolean search(E e) {
		for (E e1 : elements)
			if (e.equals(e1))
				return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArrayPriorityQueue [elements=" + Arrays.toString(elements)
				+ ", size=" + size + "]";
	}
}
