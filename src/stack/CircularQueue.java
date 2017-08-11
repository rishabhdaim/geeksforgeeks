/**
 * 
 */
package stack;

import java.util.Arrays;

/**
 * @author apple
 * 
 * 
 */
public class CircularQueue<E> implements Queue<E> {

	private int front, rear, size;
	private E[] elements;

	@SuppressWarnings("unchecked")
	public CircularQueue() {
		elements = (E[]) new Object[4];
		front = rear = size = 0;
	}

	@Override
	public boolean offer(E e) {
		if (size >= elements.length)
			resize();
		elements[rear] = e;
		rear = ++rear % elements.length;
		size++;
		return true;
	}

	private void resize() {
		@SuppressWarnings("unchecked")
		E[] newE = (E[]) new Object[elements.length * 2];
		if (front < rear)
			for (int i = front, j = 0; i < rear; i++)
				newE[j++] = elements[i];
		else {
			int j = 0;
			for (int i = front; i < elements.length; i++)
				newE[j++] = elements[i];
			for (int i = 0; i < rear; i++)
				newE[j++] = elements[i];
		}
		front = 0;
		rear = size;
		elements = newE;
	}

	@Override
	public E poll() {
		E e = elements[front];
		elements[front] = null;
		front = ++front % elements.length;
		size--;
		return e;
	}

	@Override
	public E peek() {
		return elements[front];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

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
		return "CircularQueue [front=" + front + ", rear=" + rear + ", size="
				+ size + ", elements=" + Arrays.toString(elements) + "]";
	}
}