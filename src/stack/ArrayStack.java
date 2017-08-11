/**
 * 
 */
package stack;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class ArrayStack<E> implements IStack<E> {

	private int size;
	private E[] stackArray;

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		stackArray = (E[]) new Object[20];
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int length) {
		stackArray = (E[]) new Object[length];
		size = 0;
	}

	@Override
	public void push(E e) {
		stackArray[size++] = e;
	}

	@Override
	public E pop() {
		E e = stackArray[size - 1];
		stackArray[size--] = null;
		return e;
	}

	@Override
	public E peek() {
		return stackArray[size - 1];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean search(E e) {
		for (E e1 : stackArray)
			if (e1.equals(e))
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
		return String.format("ArrayStack [stackArray=%s]",
				Arrays.toString(stackArray));
	}
}