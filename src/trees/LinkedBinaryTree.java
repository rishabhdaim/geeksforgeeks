/**
 * 
 */
package trees;

import linkedlists.DoubleLinkedList;

/**
 * @author aa49442
 * 
 */
public class LinkedBinaryTree<E extends Comparable<? super E>> {

	private Node<E> root;
	private int size;

	public LinkedBinaryTree() {
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

	public void insert(E e) {
		System.out.print(e + " ");
		if (root == null) {
			root = new Node<E>(e, null, null);
			return;
		}

		Node<E> n = root;
		final DoubleLinkedList<Node<E>> queue = new DoubleLinkedList<Node<E>>();
		queue.offer(n);

		while (true) {
			Node<E> t = queue.poll();
			if (t.left == null) {
				t.left = new Node<E>(e, null, null);
				break;
			} else
				queue.offer(t.left);
			if (t.right == null) {
				t.right = new Node<E>(e, null, null);
				break;
			} else
				queue.offer(t.right);
		}
	}

	public void inOrderTraversal(Node<E> root) {
		if (root == null)
			return;

		final DoubleLinkedList<Node<E>> stack = new DoubleLinkedList<Node<E>>();
		Node<E> t = root;
		while (true) {
			while (t != null) {
				stack.addFirst(t);
				t = t.left;
			}

			if (!stack.isEmpty()) {
				t = stack.poll();
				System.out.print(t.e + " ");
				t = t.right;
			} else
				break;
		}
		System.out.println();
	}

	public void levelOrderTraversal() {
		int k = heightOf(root);
		System.out.println("height of tree is : " + k);
		int i = 0;
		while (i <= k) {
			printkdistanceNodeDown(root, i);
			System.out.println();
			i++;
		}
	}

	public int heightOf(Node<E> root) {
		if (root == null)
			return 0;
		int left = heightOf(root.left);
		int right = heightOf(root.right);
		return 1 + ((left > right) ? left : right);
	}

	private void printkdistanceNodeDown(Node<E> root, int k) {
		if (root == null || k < 0)
			return;
		if (k == 0)
			System.out.print(root.e + " ");

		printkdistanceNodeDown(root.left, k - 1);
		printkdistanceNodeDown(root.right, k - 1);
	}

	private static class Node<E extends Comparable<? super E>> implements
			Comparable<Node<E>> {
		E e;
		Node<E> left;
		Node<E> right;

		/**
		 * @param e
		 * @param left
		 * @param right
		 */
		public Node(E e, Node<E> left, Node<E> right) {
			super();
			this.e = e;
			this.left = left;
			this.right = right;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E leftE = left == null ? null : left.e;
			E rightE = right == null ? null : right.e;
			return "Node [e=" + e + ", left=" + leftE + ", right=" + rightE
					+ "]";
		}

		@Override
		public int compareTo(Node<E> o) {
			return this.e.compareTo(o.e);
		}
	}
}