/**
 * 
 */
package trie;

import hashing.HashMap;

/**
 * @author aa49442
 * 
 */
public class Trie<E> {

	private Node<E> root;
	private int size;

	public Trie() {
		root = new Node<E>(null);
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Trie [root=" + root + ", size=" + size + "]";
	}

	// Method to insert a new word to Trie
	public void insert(E[] word) {
		// Find length of the given word
		int length = word.length;
		Node<E> crawl = root;

		// Traverse through all characters of given word
		for (int i = 0; i < length; i++) {
			HashMap<E, Node<E>> child = crawl.getChildren();
			E c = word[i];

			// If there is already a child for current character of given word
			if (child.containsKey(c))
				crawl = child.get(c);
			else {
				Node<E> temp = new Node<E>(c);
				child.put(c, temp);
				size++;
				crawl = temp;
			}
		}
		// Set bIsEnd true for last character
		crawl.setbIsEnd(true);
	}

	public String longestString(E[] word) {
		String result = "";
		int length = word.length;
		Node<E> crawl = root;
		for (int i = 0; i < length; i++) {
			E c = word[i];
			HashMap<E, Node<E>> child = crawl.getChildren();

			if (child.containsKey(c)) {
				result += c;
				crawl = child.get(c);
			} else
				break;
		}
		return result;
	}

	private static class Node<E> {
		private E value;
		private boolean bIsEnd;
		private final HashMap<E, Node<E>> children;

		/**
		 * @param e
		 */
		public Node(E e) {
			super();
			this.value = e;
			children = new HashMap<E, Node<E>>();
			bIsEnd = false;
		}

		/**
		 * @return the bIsEnd
		 */
		@SuppressWarnings("unused")
		public boolean isbIsEnd() {
			return bIsEnd;
		}

		/**
		 * @param bIsEnd
		 *            the bIsEnd to set
		 */
		public void setbIsEnd(boolean bIsEnd) {
			this.bIsEnd = bIsEnd;
		}

		/**
		 * @return the e
		 */
		@SuppressWarnings("unused")
		public E getValue() {
			return value;
		}

		/**
		 * @return the children
		 */
		public HashMap<E, Node<E>> getChildren() {
			return children;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Node [value=" + value + ", bIsEnd=" + bIsEnd
					+ ", children=" + children + "]";
		}
	}
}