/**
 * 
 */
package linkedlists;

import java.util.NoSuchElementException;

/**
 * @author aa49442
 * 
 */
public class ChildLinkedList<E extends Comparable<? super E>> {

	private Node<E> head;
	private Node<E> tail;
	private int size;

	public ChildLinkedList() {
		head = tail = null;
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

	private Node<E> successor(Node<E> p) {
		if (p == null)
			return null;
		else if (p.next != null) {
			Node<E> t = p.next;
			while (t.child != null)
				t = t.child;
			return t;
		} else {
			Node<E> parent = p.parent;
			Node<E> curr = p;
			while (parent != null && curr == parent.next) {
				curr = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node<E> t = head;
		while (t.child != null)
			t = t.child;
		while (t != null) {
			builder.append(t.e + " ");
			t = successor(t);
		}
		return "ChildLinkedList [head=" + head + ", tail=" + tail + ", size="
				+ size + ", elements=" + builder.toString() + "]";
	}

	/**
	 * @param pE
	 *            parent element..
	 * @param cE
	 *            child Element..
	 */
	public void addChild(E pE, E cE) {
		Node<E> p = null;
		p = getNode(pE, head);
		if (p == null)
			throw new NoSuchElementException();

		addChild(p, cE);
	}

	private void addChild(Node<E> p, E cE) {
		final Node<E> c = p.child;
		final Node<E> childNode = new Node<E>(cE, c, null, p);
		p.child = childNode;
		size++;
	}

	private Node<E> getNode(E pE, Node<E> head) {
		Node<E> t = head;
		if (t == null)
			return null;
		while (t.child != null)
			t = t.child;

		while (t != null) {
			if (t.e.equals(pE))
				return t;
			else
				t = successor(t);
		}
		return null;
	}

	private void linkFirst(E e) {
		final Node<E> f = head; // save first node
		final Node<E> newNode = new Node<E>(e, f, null, null);

		head = newNode;

		if (tail == null)
			tail = newNode;
		size++;
	}

	private void linkLast(E e) {
		final Node<E> l = tail; // save last node
		final Node<E> newNode = new Node<E>(e, null, null, null);
		tail = newNode;
		if (head == null)
			head = newNode;
		else
			l.next = newNode;
		size++;
	}

	private static class Node<E> {
		E e;
		Node<E> next;
		Node<E> child;
		Node<E> parent;

		/**
		 * @param e
		 * @param next
		 * @param child
		 */
		public Node(E e, Node<E> next, Node<E> child, Node<E> parent) {
			super();
			this.e = e;
			this.next = next;
			this.child = child;
			this.parent = parent;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E nextE = next == null ? null : next.e;
			E childE = child == null ? null : child.e;
			return "Node [e=" + e + ", next=" + nextE + ", child=" + childE
					+ "]";
		}
	}

	public void flattenList() {
		if (this.head == null)
			return;

		Node<E> temp = null;
		Node<E> curr = this.head;

		while (curr != tail) {
			if (curr.child != null) {
				tail.next = curr.child;
				temp = curr.child;
				temp.parent.child = null; // nulling reference from parent to
											// child, needed to traverse the
											// list..
				temp.parent = null; // null reference to parent as this node is
									// moving on top list..
				while (temp.next != null)
					temp = temp.next;
				tail = temp;
			}
			curr = curr.next;
		}
	}
}