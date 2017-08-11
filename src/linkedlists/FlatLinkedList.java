/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class FlatLinkedList<E extends Comparable<? super E>> {

	private Node<E> head;
	private int size;

	public FlatLinkedList() {
		head = null;
		size = 0;
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
		Node<E> temp = null;
		while (t != null) {
			builder.append(t.e + " ");
			if (t.down != null) {
				temp = t.down;
				while (temp != null) {
					builder.append(temp.e + " ");
					temp = temp.down;
				}
			}
			t = t.next;
		}

		return "FlatLinkedList [head=" + head + ", size=" + size
				+ ", elements= " + builder.toString() + "]";
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void clear() {
		head = null;
		size = 0;
	}

	public void add(E e) {
		if (isEmpty())
			linkFirst(e);
		else if (getSucc(e) != null)
			linkBefore(e, getSucc(e));
		else
			linkLast(e);
		size++;
	}

	public void addChild(E e, E cE) {
		if (isEmpty())
			addChild(linkFirst(e), cE);
		else {
			Node<E> t = getNode(e);
			if (t == null) {
				add(e);
				t = getNode(e);
			}
			addChild(t, cE);
		}
	}

	private void addChild(Node<E> pNode, E cE) {
		Node<E> t = pNode.down;
		Node<E> prev = null;
		while (t != null && cE.compareTo(t.e) > 0) {
			prev = t;
			t = t.down;
		}

		final Node<E> newNode = new Node<E>(cE, null, t);
		if (prev == null)
			pNode.down = newNode;
		else
			prev.down = newNode;
		size++;
	}

	private Node<E> linkLast(E e) {
		Node<E> t = head;
		while (t.next != null)
			t = t.next; // now t is last element..
		final Node<E> newNode = new Node<E>(e, null, null);
		t.next = newNode;
		return newNode;
	}

	private Node<E> linkFirst(E e) {
		final Node<E> f = head;
		final Node<E> newNode = new Node<E>(e, f, null);
		head = newNode;
		return newNode;
	}

	/**
	 * @param e
	 * @param succ
	 * @return
	 */
	private Node<E> linkBefore(E e, Node<E> succ) {
		final Node<E> s = succ;
		final Node<E> newNode = new Node<E>(e, s, null);

		Node<E> t = head;
		while (t != null && t.next != s)
			t = t.next;
		if (t == null)
			head = newNode;
		else
			t.next = newNode;

		return newNode;
	}

	private Node<E> getSucc(E e) {
		Node<E> t = head;

		while (t != null) {
			if (t.e.compareTo(e) > 0)
				return t;
			else
				t = t.next;
		}
		return null;
	}

	private Node<E> getNode(E e) {
		Node<E> t = head;

		while (t != null) {
			if (t.e.compareTo(e) == 0)
				return t;
			else
				t = t.next;
		}
		return null;
	}

	private static class Node<E> {
		E e;
		Node<E> next;
		Node<E> down;

		/**
		 * @param e
		 * @param next
		 * @param down
		 */
		public Node(E e, Node<E> next, Node<E> down) {
			super();
			this.e = e;
			this.next = next;
			this.down = down;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((e == null) ? 0 : e.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node<?> other = (Node<?>) obj;
			if (e == null) {
				if (other.e != null)
					return false;
			} else if (!e.equals(other.e))
				return false;
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E nE = next == null ? null : next.e;
			E dE = down == null ? null : down.e;
			return "Node [e=" + e + ", next=" + nE + ", down=" + dE + "]";
		}
	}

	public void flatten() {
		Node<E> root = this.head;
		Node<E> result = flatten(root);

		while (result.next != null) {
			System.out.print(result.e + " ");
			result = result.next;
		}
		System.out.println();
	}

	private Node<E> flatten(Node<E> root) {
		if (root == null || root.next == null)
			return root;
		return merge(root, flatten(root.next));
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	private Node<E> merge(Node<E> a, Node<E> b) {
		if (a == null)
			return b;
		if (b == null)
			return a;

		Node<E> result = null;

		if (a.e.compareTo(b.e) < 0) {
			result = a;
			Node<E> next = a.down;
			a.down = null;
			result.next = merge(next, b);
		} else {
			result = b;
			Node<E> next = b.down;
			b.down = null;
			result.next = merge(a, next);
		}
		return result;
	}
}