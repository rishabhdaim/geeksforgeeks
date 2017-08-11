/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class SkipList<E extends Comparable<? super E>> {

	@SuppressWarnings("unused")
	private static class HeadIndex<E> extends Index<E> {
		final int level;

		public HeadIndex(Node<E> node, Index<E> next, Index<E> down, int level) {
			super(node, next, down);
			this.level = level;
		}
	}

	private static class Index<E> {
		Node<E> node;
		Index<E> next;
		Index<E> down;

		/**
		 * @param node
		 * @param next
		 * @param down
		 */
		public Index(Node<E> node, Index<E> next, Index<E> down) {
			super();
			this.node = node;
			this.next = next;
			this.down = down;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E nextE = next == null ? null : next.node.e;
			E downE = down == null ? null : down.node.e;
			return "Index [node=" + node.toString() + ", next=" + nextE
					+ ", down=" + downE + "]";
		}
	}

	private static class Node<E> {
		E e;
		Node<E> next;

		/**
		 * @param e
		 * @param next
		 */
		@SuppressWarnings("unused")
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
			E e = next == null ? null : next.e;
			return "Node [e=" + this.e + ", next=" + e + "]";
		}
	}
}