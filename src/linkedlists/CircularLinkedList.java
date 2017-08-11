/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class CircularLinkedList<E extends Comparable<? super E>> {

	/**
	 * head of circular linked list..
	 */
	private Node<E> head = new Node<E>(null, null);
	private int size;

	public CircularLinkedList() {
		head.next = head;
		size = 0;
	}

	public void add(E e) {
		linkLast(e);
	}

	public void addFirst(E e) {
		linkFirst(e);
	}

	public void addLast(E e) {
		linkLast(e);
	}

	private Node<E> linkLast(E e) {
		Node<E> t = head;
		while (t.next != head)
			t = t.next;

		final Node<E> newNode = new Node<E>(e, head);
		t.next = newNode;
		size++;
		return newNode;
	}

	private Node<E> linkFirst(E e) {
		final Node<E> f = head.next;
		final Node<E> newNode = new Node<E>(e, f);
		head.next = newNode;
		size++;
		return newNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Node<E> curr = head.next; curr != head; curr = curr.next)
			builder.append(curr.e + " ");
		return "CircularLinkedList [elements=" + builder.toString() + ", size="
				+ size + "]";
	}

	private static class Node<E> {
		E e;
		Node<E> next;

		/**
		 * @param e
		 * @param next
		 */
		public Node(E e, Node<E> next) {
			super();
			this.e = e;
			this.next = next;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E nextE = next == null ? null : next.e;
			return "Node [e=" + e + ", next=" + nextE + "]";
		}
	}

}
