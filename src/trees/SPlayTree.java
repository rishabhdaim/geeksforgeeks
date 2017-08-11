/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class SPlayTree<E extends Comparable<? super E>> {

	private Node<E> root;
	private int size;

	public SPlayTree() {
		root = null;
		size = 0;
	}

	public Node<E> add(E e) {
		System.out.print(e + " ");
		if (root == null) {
			root = new Node<E>(e, null, null);
			size++;
			return root;
		}
		root = search(root, e);
		if (root.e.equals(e))
			return root;
		else {
			final Node<E> n = new Node<E>(e, null, null);
			if (e.compareTo(root.e) < 0) {
				n.right = root;
				n.left = root.left;
				root.left = null;
			} else {
				n.left = root;
				n.right = root.right;
				root.right = null;
			}
			root = n;
			return n;
		}
	}

	public int size() {
		return size;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Node<E> root() {
		return root;
	}

	public void inOrderTraversal(Node<E> root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.e + " ");
		inOrderTraversal(root.right);
	}

	static class Node<E> {
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
			return "Node [e=" + e + "]";
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
	}

	public Node<E> search(Node<E> root, E e) {
		if (root == null || root.e.equals(e))
			return root;
		if (e.compareTo(root.e) < 0) {
			// key in left subTree..
			if (root.left == null)
				return root;
			if (e.compareTo(root.left.e) < 0) {
				root.left.left = search(root.left.left, e);
				root = rotateRight(root);
			} else if (e.compareTo(root.left.e) > 0) {
				root.left.right = search(root.left.right, e);
				if (root.left.right != null)
					root.left = rotateLeft(root.left);
			}
			return root.left == null ? root : rotateRight(root);
		} else {
			if (root.right == null)
				return root;
			if (e.compareTo(root.right.e) < 0) {
				root.right.left = search(root.right.left, e);
				if (root.right.left != null)
					root.right = rotateRight(root.right);
			} else if (e.compareTo(root.right.e) > 0) {
				root.right.right = search(root.right.right, e);
				root = rotateLeft(root);
			}
			return root.right == null ? root : rotateLeft(root);
		}
	}

	private Node<E> rotateLeft(Node<E> root) {
		final Node<E> right = root.right;
		root.right = right.left;
		right.left = root;
		return right;
	}

	private Node<E> rotateRight(Node<E> root) {
		final Node<E> left = root.left;
		root.left = left.right;
		left.right = root;
		return left;
	}
}