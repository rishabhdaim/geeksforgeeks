/**
 * 
 */
package trie;

/**
 * @author aa49442
 * 
 */
public class TrieTree<E extends Comparable<? super E>> {

	private Node<E> root;
	private int size;

	public TrieTree() {
		root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	public void insert(E[] word) {
		int length = word.length;
		if (root == null) {
			root = new Node<E>(word[0]);
			appendAfter(word, length, root, 1);
			return;
		}

		Node<E> t = root;
		Node<E> parent = null;
		int cmp = 0;
		int i;
		for (i = 0; i < length && t != null;) {
			parent = t;
			cmp = word[i].compareTo(t.data);

			if (cmp == 0) {
				t = t.eql;
				i++;
			} else if (cmp < 0)
				t = t.left;
			else
				t = t.right;
		}
		if (!(i < length))
			return;
		if (parent.isEnd && cmp == 0) {
			appendAfter(word, length, parent, i);
			return;
		}
		if (cmp < 0) {
			parent.left = new Node<E>(word[i]);
			appendAfter(word, length, parent.left, ++i);
		} else {
			parent.right = new Node<E>(word[i]);
			appendAfter(word, length, parent.right, ++i);
		}
	}

	/**
	 * @param word
	 * @param length
	 */
	private void appendAfter(E[] word, int length, Node<E> parent, int start) {
		Node<E> t = parent;
		for (int i = start; i < length; i++) {
			t.eql = new Node<E>(word[i]);
			t = t.eql;
			t.setEnd(false); // now this is not end..
		}
		t.setEnd(true);
	}

	private static class Node<E> {
		E data;
		boolean isEnd;
		Node<E> left, eql, right;

		/**
		 * @param data
		 * @param isEnd
		 * @param left
		 * @param eql
		 * @param right
		 */
		public Node(E data) {
			super();
			this.data = data;
			left = null;
			eql = null;
			right = null;
			isEnd = false;
		}

		/**
		 * @param isEnd
		 *            the isEnd to set
		 */
		public void setEnd(boolean isEnd) {
			this.isEnd = isEnd;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E eqlE = eql == null ? null : eql.data;
			E rightE = right == null ? null : right.data;
			E leftE = left == null ? null : left.data;
			return "Node [data=" + data + ", isEnd=" + isEnd + ", left="
					+ leftE + ", eql=" + eqlE + ", right=" + rightE + "]";
		}
	}
}