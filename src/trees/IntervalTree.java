/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 * @param <E>
 */
public class IntervalTree<E extends Comparable<? super E>> {

	private Node<E> root;
	private int size;

	public IntervalTree() {
		root = null;
		size = 0;
	}

	/**
	 * @return root of this tree..
	 */
	public Node<E> root() {
		return root;
	}

	/**
	 * @return the size
	 */
	public int size() {
		return size;
	}

	public void add(E low, E high) {
		Interval<E> i = new Interval<E>(low, high);

		if (root == null) {
			root = new Node<E>(i, i.high, null);
			size++;
			return;
		}
		Node<E> n = root;
		int cmp = 0;
		Node<E> parent = null;
		while (n != null) {
			if (n.max.compareTo(high) < 0)
				n.max = high;
			parent = n;
			cmp = n.interval.low.compareTo(low);

			if (cmp > 0)
				n = n.left;
			else if (cmp < 0)
				n = n.right;
			else
				// both are equal..
				return;
		}

		final Node<E> newNode = new Node<E>(i, high, parent);

		if (cmp > 0)
			parent.left = newNode;
		else
			parent.right = newNode;
		size++;
	}

	public void inOrderTraversal(Node<E> root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.interval + " " + root.max + " -- ");
		inOrderTraversal(root.right);
	}

	/**
	 * @author aa49442
	 * 
	 * @param <E>
	 */
	private static class Node<E extends Comparable<? super E>> {
		Interval<E> interval;
		E max;
		Node<E> left;
		Node<E> right;
		@SuppressWarnings("unused")
		Node<E> parent;

		/**
		 * @param interval
		 * @param max
		 * @param parent
		 */
		public Node(Interval<E> interval, E max, Node<E> parent) {
			super();
			this.interval = interval;
			this.max = max;
			this.parent = parent;
		}

		/**
		 * @param interval
		 * @param max
		 * @param left
		 * @param right
		 * @param parent
		 */
		@SuppressWarnings("unused")
		public Node(Interval<E> interval, E max, Node<E> left, Node<E> right,
				Node<E> parent) {
			super();
			this.interval = interval;
			this.max = max;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Node [interval=" + interval.toString() + ", max=" + max
					+ "]";
		}
	}

	/**
	 * @author aa49442
	 * 
	 * @param <E>
	 */
	private static class Interval<E extends Comparable<? super E>> implements
			Comparable<Interval<E>> {
		E low;
		E high;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Interval [low=" + low + ", high=" + high + "]";
		}

		/**
		 * @param low
		 * @param high
		 */
		public Interval(E low, E high) {
			super();
			this.low = low;
			this.high = high;
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
			result = prime * result + ((high == null) ? 0 : high.hashCode());
			result = prime * result + ((low == null) ? 0 : low.hashCode());
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
			Interval<?> other = (Interval<?>) obj;
			if (high == null) {
				if (other.high != null)
					return false;
			} else if (!high.equals(other.high))
				return false;
			if (low == null) {
				if (other.low != null)
					return false;
			} else if (!low.equals(other.low))
				return false;
			return true;
		}

		@Override
		public int compareTo(Interval<E> o) {
			int cmp;
			return (cmp = this.low.compareTo(o.low)) != 0 ? cmp : this.high
					.compareTo(o.high);
		}
	}

	public Node<E> searchInterval(E low, E high) {
		if (root == null)
			return null;

		Interval<E> i = new Interval<E>(low, high);

		Node<E> n = root;
		int cmp;
		while (n != null) {
			cmp = n.interval.compareTo(i);
			if (cmp == 0)
				return n;
			else if (cmp > 0)
				n = n.left;
			else
				n = n.right;
		}
		return null;
	}
}