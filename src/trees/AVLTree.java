/**
 * 
 */
package trees;

/**
 * Implementation of AVL Tree. AVL is an self balancing binary search tree with guarantee log(n) operation for insert, search & delete.
 * 
 * It uses height to balance left and right nodes
 * 
 * @author Rishabh.Daim
 * 
 */
public class AVLTree<E extends Comparable<E>> {

	private Node<E> root = null;
	
	public AVLTree() {
		this(null);
	}
	
	public AVLTree(final E e) {
		this.root = new Node<>(e, null);
	}
	
	
	public boolean add(final E e) {
		
		if (root == null) {
			this.root = new Node<>(e, null);
			return true;
		}
		
		Node<E> parent = null;
		Node<E> t = root;
		int cmp = 0;
		while (t != null) {
			cmp = t.e.compareTo(e);
			parent = t;
			if (cmp > 1) {
				t = t.right;
			} else if (cmp < 1) {
				t = t.left;
			} else {
				System.err.println("Node already exists!!");
				return false;
			}
		}
		
		final Node<E> newNode = new Node<>(e, parent);
		
		if (cmp > 1) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		
		fixAfterInsertion(newNode);
		return true;
	}
	
	
	/**
	 * We start fixing from Node's parent, because newNode is always balanced.
	 * 
	 * @param node fix tree after inserting new node.
	 */
	private void fixAfterInsertion(Node<E> node) {
		
		while (node != null && node != root) {
			
			if (isBalanced(parentOf(node))) {
				node.parent.height++; // increase height since we have added one node
			} else {
				// node is not balanced
				// we have 4 cases here, LL, LR, RR, RL, lets check for them one by one
				if (parentOf(node) == leftOf(parentOf(parentOf(node)))) {
					// node's parent is left node of its parent
					int leftHeight = heightOf(node.left);
					int rightHeight = heightOf(node.right);
					
					if (leftHeight > rightHeight) {
						// now we have LL case, so we will rotateRight from node
						rightRotate(parentOf(node));
					} else {
						// now it is LR case, so first leftRotate, then right rotate from parentOf(parentOf(node))
						leftRotate(node);
						node = parentOf(parentOf(node));
						rightRotate(node);
					}
				} else {
					// node's parent is left node of its parent
					int leftHeight = heightOf(node.left);
					int rightHeight = heightOf(node.right);
					
					if (rightHeight > leftHeight) {
						// now we have RR case, so we will rotateLeft from node
						leftRotate(parentOf(node));
					} else {
						// now it is RL case, so first rightRotate, then left rotate from parentOf(parentOf(node))
						rightRotate(node);
						node = parentOf(parentOf(node));
						leftRotate(node);
					}
				}
			}
			node = node.parent; // move upwards
		}
	}
	
	private Node<E> leftOf(final Node<E> node) {
		return node != null ? node.left : null;
	}
	
	private Node<E> rightOf(final Node<E> node) {
		return node != null ? node.right : null;
	}

	private Node<E> parentOf(final Node<E> node) {
		return node != null ? node.parent : null;
	}

	// rotations to balance tree
	/**
	 * right rotation of tree
	 * 
	 * @param node
	 */
	private void rightRotate(final Node<E> node) {
		
		if (node == null) return;
		
		final Node<E> left = node.left;
		final Node<E> right = left.right;
		
		// add right of left child to left of root node
		node.left = right;
		if (right != null) right.parent = node;
		
		final Node<E> parent = node.parent; // parent of root node
		left.parent = parent;
		
		if (parent == null) 
			root = left; // since parent of p is null, make root point to l
		else if (parent.left == node) parent.left = left;
		else parent.right = left;
		
		// add root as right child of left & make it parent of root
		left.right = node;
		node.parent = left;
		
		// now modify heights
		left.height = setHeight(left.left, left.right);
		if (right != null) setHeight(right.left, right.right);
	}
	
	/**
	 * left rotation of tree
	 * 
	 * @param node
	 */
	private void leftRotate(final Node<E> node) {
		
		if (node == null) return;
		
		final Node<E> right = node.right;
		final Node<E> left = right.left;
		
		// add right of left child to left of root node
		node.right = left;
		if (left != null) left.parent = node;
		
		final Node<E> parent = node.parent; // parent of root node
		left.parent = parent;
		
		if (parent == null) 
			root = right; // since parent of p is null, make root point to l
		else if (parent.right == node) parent.right = right;
		else parent.left = right;
		
		// add root as right child of left & make it parent of root
		right.left = node;
		node.parent = right;
		
		// now modify heights
		right.height = setHeight(right.left, right.right);
		if (left != null) setHeight(left.left, left.right);
	}
	
	
	/**
	 * @param node
	 * @return true if node is balanced
	 */
	private boolean isBalanced(final Node<E> node) {
		return Math.abs(heightOf(leftOf(node)) - heightOf(rightOf(node))) > 1;
	}
	
	/**
	 * @param node
	 * @return height of tree
	 */
	private int heightOf(final Node<E> node) {
		return Math.abs(node != null ? node.height : 0);
	}

	/**
	 * @param left
	 * @param right
	 * @return sum of 
	 */
	private int setHeight(final Node<E> left, final Node<E> right) {
		return Math.max((left != null ? left.height : 0), (right != null ? right.height : 0)) + 1;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}


	/**
	 * @author Rishabh.Daim
	 *
	 * @param <E>
	 */
	private static class Node<E extends Comparable<E>> {
		
		E e;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		int height;
		
		private Node(final E e, final Node<E> parent) {
			this.e = e;
			this.parent = parent;
			this.height = 1; // height of new node is always 1
		}
	}
}
