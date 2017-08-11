/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class LinkedDeque<E extends Comparable<? super E>> {

	private DoubleLinkedList<E> doubleLinkedList;

	private final int capacity;

	/**
	 * @param capacity
	 */
	public LinkedDeque(int capacity) {
		super();
		this.capacity = capacity;
		doubleLinkedList = new DoubleLinkedList<E>();
	}

	public void offer(E e) {
		doubleLinkedList.offer(e);
	}

	public E poll() {
		return doubleLinkedList.poll();
	}

	public E peek() {
		return doubleLinkedList.peek();
	}

	public void addLast(E e) {
		if (doubleLinkedList.size() >= capacity)
			poll();
		doubleLinkedList.addLast(e);
	}

	public void addFirst(E e) {
		if (doubleLinkedList.size() >= capacity)
			poll();
		doubleLinkedList.addFirst(e);
	}

	public E removeFirst() {
		return doubleLinkedList.poll();
	}

	public E removeLast() {
		return doubleLinkedList.removeLast();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkedDeque [doubleLinkedList=" + doubleLinkedList.toString()
				+ ", capacity=" + capacity + "]";
	}

}
