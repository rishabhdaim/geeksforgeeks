/**
 * 
 */
package stack;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class CircularDeque<E> implements Deque<E> {

	private int front, rear, size;
	private E[] elements;

	@SuppressWarnings("unchecked")
	public CircularDeque() {
		elements = (E[]) new Object[5];
		front = rear = size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see stacks.Queue#offer(java.lang.Object)
	 */
	@Override
	public boolean offer(E e) {
		elements[rear] = e;
		rear = ++rear % elements.length;
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
		E e = elements[front];
		elements[front] = null;
		front = ++front % elements.length;
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
		return elements[front];
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
	 * @see stacks.Deque#addFirst(java.lang.Object)
	 */
	@Override
	public boolean addFirst(E e) {
		elements[front] = e;
		if (front == 0)
			front = elements.length;
		else
			front = --front % elements.length;
		size++;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see stacks.Deque#pollLast()
	 */
	@Override
	public E pollLast() {
		E e = elements[rear];
		elements[rear] = null;
		if (rear == 0)
			rear = elements.length;
		else
			rear = --rear % elements.length;
		size--;
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see stacks.Deque#peekLast()
	 */
	@Override
	public E peekLast() {
		return elements[rear];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CircularDeque [front=" + front + ", rear=" + rear + ", size="
				+ size + ", elements=" + Arrays.toString(elements) + "]";
	}
}