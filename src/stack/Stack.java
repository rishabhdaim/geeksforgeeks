/**
 * 
 */
package stack;

import java.util.Iterator;

import linkedlists.DoubleLinkedList;

/**
 * @author rishabh.daim
 *
 */
public class Stack<E extends Comparable<? super E>> implements Iterable<E> {
	
	/**
	 * internal list used for storing elements
	 */
	private DoubleLinkedList<E> list;
	
	public Stack() {
		list = new DoubleLinkedList<>();
	}
	
	public E pop() {
		return list.removeFirst();
	}
	
	public E peek() {
		return list.peekFirst();
	}
	
	public void push(E e) {
		list.addFirst(e);
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}
}
