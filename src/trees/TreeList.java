/**
 * 
 */
package trees;

import hashing.HashMap;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

import stack.Stack;
import linkedlists.DoubleLinkedList;
import linkedlists.SingleLinkedList;

/**
 * @author aa49442
 * 
 */
public class TreeList<E extends Comparable<? super E>> {

	private Node<E> root;
	private int size;

	public TreeList() {
		root = null;
		size = 0;
	}

	public void add(E e) {
		System.out.print(e + " ");
		Node<E> t = root;
		if (t == null) {
			root = new Node<E>(e, null, null, null);
			size++;
			return;
		}
		int cmp = 0;
		Node<E> parent = null;
		while (t != null) {
			cmp = e.compareTo(t.e);
			parent = t;
			if (cmp < 0)
				t = t.left;
			else if (cmp > 0)
				t = t.right;
			else
				return;
		}

		final Node<E> newNode = new Node<E>(e, null, null, parent);
		if (cmp < 0)
			parent.left = newNode;
		else
			parent.right = newNode;
		size++;
		fixAfterInsertion(newNode);
	}

	private void fixAfterInsertion(Node<E> x) {
		x.color = RED;

		while (x != null && x != root && x.parent.color == RED) {
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				Node<E> y = rightOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == rightOf(parentOf(x))) {
						x = parentOf(x);
						rotateLeft(x);
					}

					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateRight(parentOf(parentOf(x)));
				}
			} else {
				Node<E> y = leftOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == leftOf(parentOf(x))) {
						x = parentOf(x);
						rotateRight(x);
					}

					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		root.color = BLACK;
	}

	private static <E extends Comparable<? super E>> boolean colorOf(Node<E> n) {
		return n == null ? BLACK : n.color;
	}

	private static <E extends Comparable<? super E>> Node<E> leftOf(Node<E> n) {
		return n == null ? null : n.left;
	}

	private static <E extends Comparable<? super E>> Node<E> rightOf(Node<E> n) {
		return n == null ? null : n.right;
	}

	private static <E extends Comparable<? super E>> Node<E> parentOf(Node<E> n) {
		return n == null ? null : n.parent;
	}

	private static <E extends Comparable<? super E>> void setColor(Node<E> p,
			boolean c) {
		if (p != null)
			p.color = c;
	}

	private void rotateRight(final Node<E> n) {
		if (n == null)
			return;
		Node<E> l = n.left;

		n.left = l.right;

		if (l.right != null)
			l.right.parent = n;

		l.parent = n.parent;

		if (n.parent == null)
			root = l;
		else if (n.parent.left == n)
			n.parent.left = l;
		else
			n.parent.right = l;
		l.right = n;
		n.parent = l;
	}

	private void rotateLeft(final Node<E> n) {
		if (n == null)
			return;

		Node<E> r = n.right;

		n.right = r.left;

		if (r.left != null)
			r.left.parent = n;

		r.parent = n.parent;

		if (n.parent == null)
			root = r;
		else if (n.parent.right == n)
			n.parent.right = r;
		else
			n.parent.left = r;

		r.left = n;
		n.parent = r;
	}

	private Node<E> successor(Node<E> t) {
		if (t == null)
			return null;
		else if (t.right != null) {
			Node<E> n = t.right;
			while (n.left != null)
				n = n.left;
			return n;
		} else {
			Node<E> p = t.parent;
			Node<E> curr = t;

			while (p != null && curr == p.right) {
				curr = p;
				p = p.parent;
			}
			return p;
		}
	}

	private Node<E> predecessor(Node<E> t) {
		if (t == null)
			return null;
		else if (t.left != null) {
			Node<E> n = t.left;
			while (n.right != null)
				n = n.right;
			return n;
		} else {
			Node<E> p = t.parent;
			Node<E> curr = t;
			while (p != null && curr == p.left) {
				curr = p;
				p = p.parent;
			}
			return p;
		}
	}

	private Node<E> leftMost(Node<E> node) {
		if (node == null)
			return null;
		Node<E> l = node;
		while (l.left != null)
			l = l.left;
		return l;
	}

	private Node<E> rightMost(Node<E> node) {
		if (node == null)
			return null;
		Node<E> r = node;
		while (r.right != null)
			r = r.right;
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node<E> l = this.leftMost(root);
		if (l != null) {
			builder.append(l.toString() + " ");
			while ((l = successor(l)) != null)
				builder.append(l.toString() + " ");
		}
		E rE = root == null ? null : root.e;
		return "TreeList [root=" + rE + ", size=" + size + ", elements = "
				+ builder.toString() + "]";
	}

	private static final boolean RED = false;
	private static final boolean BLACK = true;

	static class Node<E extends Comparable<? super E>> implements
			Comparable<Node<E>> {
		private E e;
		Node<E> left;
		Node<E> right;
		private Node<E> parent;
		private int liss;
		private Node<E> nextRight;
		private Node<E> nextSucc;
		private boolean color = BLACK;

		/**
		 * @param e
		 * @param left
		 * @param right
		 * @param parent
		 */
		public Node(E e, Node<E> left, Node<E> right, Node<E> parent) {
			super();
			this.e = e;
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.liss = 0;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E lE = left == null ? null : left.e;
			E rE = right == null ? null : right.e;
			E pE = parent == null ? null : parent.e;
			E nRE = nextRight == null ? null : nextRight.e;
			E nSE = nextSucc == null ? null : nextSucc.e;
			return "Node [e=" + e + ", left=" + lE + ", right=" + rE
					+ ", parent=" + pE + ", liss=" + liss + ", nextRight="
					+ nRE + ", nextSucc=" + nSE + ", color=" + color + "]";
		}

		@Override
		public int compareTo(Node<E> o) {
			return this.e.compareTo(o.e);
		}
	}

	public void treeToCLL() {
		Node<E> s = treeToCLL(root);
		Node<E> next = s.right;
		System.out.print(s.e + " ");
		while (next != s) {
			System.out.print(next.e + " ");
			next = next.right;
		}
		System.out.println();
	}

	private Node<E> treeToCLL(final Node<E> root) {
		
		if (root == null)
			return null;
		
		// Recursively do the subtrees (leap of faith!)
		Node<E> aList = treeToCLL(root.left);
		Node<E> bList = treeToCLL(root.right);

		// transform node to circular list, by making its left & right to point itself
		root.left = root;
		root.right = root;
		root.nextRight = root.nextSucc = root.parent = null;
		
		// At this point we have three lists, and it's just a matter of
		// appending them together in the right order (aList, root, bList)
		return appendNode(appendNode(aList, root), bList);
	}

	private Node<E> appendNode(final Node<E> a, final Node<E> b) {
		
		if (a == null)
			return b;
		if (b == null)
			return a;

		// find the last node in each using the .previous pointer and save them
		final Node<E> aLeft = a.left; // aLeft is last node in A List i.e. smallest element
		final Node<E> bLeft = b.left; // bLeft is last node in B List i.e. largest element
		
		// point the next higher in A to b's First
		aLeft.right = b;
		// point the next lower in B to a's Last
		b.left = aLeft;
		
		// complete circular list
		a.left = bLeft;
		bLeft.right = a;
		
		return a; // return smaller value list always
	}

	public void verticalOrderTraversal() {
		final HashMap<Integer, SingleLinkedList<E>> hashMap = new HashMap<>();
		int hd = 0;
		verticalOrderTraversal(root, hashMap, hd);
		System.out.println(hashMap);
	}

	private void verticalOrderTraversal(final Node<E> root, final HashMap<Integer, SingleLinkedList<E>> hashMap, int hd) {
		
		if (root == null)
			return;
		SingleLinkedList<E> list = hashMap.get(hd);
		if (list == null)
			list = new SingleLinkedList<E>();
		list.add(root.e);
		hashMap.put(hd, list);
		verticalOrderTraversal(root.left, hashMap, hd - 1);
		verticalOrderTraversal(root.right, hashMap, hd + 1);
	}

	private int maxLevel;

	public void rightViewTraversal() {
		maxLevel = 0;
		int level = 1;
		rightViewTraversal(root, level);
	}

	private void rightViewTraversal(Node<E> node, int level) {
		if (node == null)
			return;
		if (maxLevel < level) {
			System.out.print(node.e + " ");
			maxLevel = level;
		}
		rightViewTraversal(node.right, level + 1);
		rightViewTraversal(node.left, level + 1);
	}

	public void leftViewTraversal() {
		maxLevel = 0;
		int level = 1;
		leftViewTraversal(root, level);
	}

	private void leftViewTraversal(Node<E> node, int level) {
		if (node == null)
			return;
		if (maxLevel < level) {
			System.out.print(node.e + " ");
			maxLevel = level;
		}
		leftViewTraversal(node.left, level + 1);
		leftViewTraversal(node.right, level + 1);
	}

	public void buildTreeFromInLevelOrder(int[] inOrder, int[] levelOrder) {
		final TreeList<Integer> list = new TreeList<Integer>();
		final HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		for (int i = 0, l = inOrder.length; i < l; i++)
			hashMap.put(inOrder[i], i);
		System.out.println(hashMap);
		for (int i = 0, l = levelOrder.length; i < l; i++)
			list.insert(levelOrder[i], hashMap);

		list.inOrderTraversal(list.root);
	}

	private void insert(E e, HashMap<E, Integer> hashMap) {
		if (root == null) {
			root = new Node<E>(e, null, null, null);
			return;
		}
		Node<E> n = root;
		int index = hashMap.get(e);
		while (true) {
			int i = hashMap.get(n.e);
			if (index < i) {
				if (n.left != null)
					n = n.left;
				else {
					n.left = new Node<E>(e, null, null, n);
					return;
				}
			} else {
				if (n.right != null)
					n = n.right;
				else {
					n.right = new Node<E>(e, null, null, n);
					return;
				}
			}
		}
	}

	public void inOrderTraversal(Node<E> root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.e + " ");
		inOrderTraversal(root.right);
	}

	public void printAllNodesAtKDistance(int i) {
		int t = printKDistanceNode(root, 34, i);
		if (t == -1)
			System.out.println("node doesn't exists..");
	}

	private int printKDistanceNode(Node<E> root, int element, int k) {
		if (root == null)
			return -1;
		if (root.e.equals(element)) {
			printkdistanceNodeDown(root, k);
			return 0;
		}

		// recur for left subtree..
		int dl = printKDistanceNode(root.left, element, k);
		if (dl != -1) {
			// If root is at distance k from target, print root Note that dl is
			// Distance of root's left child from target

			if (dl + 1 == k)
				System.out.print(root.e + " ");
			// Else go to right subtree and print all k-dl-2 distant nodes Note
			// that the right child is 2 edges away from left child
			else
				printkdistanceNodeDown(root.right, k - dl - 2);

			// Add 1 to the distance and return value for parent calls
			return 1 + dl;

		}
		// mirror of above..
		int dr = printKDistanceNode(root.right, element, k);
		if (dr != -1) {
			if (dr + 1 == k)
				System.out.print(root.e + " ");
			else
				printkdistanceNodeDown(root.left, k - dr - 2);
			return 1 + dr;
		}
		return -1;
	}

	private void printkdistanceNodeDown(Node<E> root, int k) {
		if (root == null || k < 0)
			return;
		if (k == 0)
			System.out.print(root.e + " ");

		printkdistanceNodeDown(root.left, k - 1);
		printkdistanceNodeDown(root.right, k - 1);
	}

	private int min, max;

	public void verticalOrderTraversalWithOutHM() {
		min = 0;
		max = 0;
		int hd = 0;
		findMinMax(root, hd);
		System.out.println("min & max are :" + min + " " + max);

		for (int lineNo = min; lineNo <= max; lineNo++) {
			printVerticalLine(root, lineNo, 0);
			System.out.println();
		}
	}

	private void printVerticalLine(Node<E> root, int lineNo, int hd) {
		if (root == null)
			return;
		if (hd == lineNo)
			System.out.print(root.e + " ");
		printVerticalLine(root.left, lineNo, hd - 1);
		printVerticalLine(root.right, lineNo, hd + 1);
	}

	private void findMinMax(final Node<E> root, final int hd) {
		if (root == null)
			return;
		if (hd < min)
			min = hd;
		else if (hd > max)
			max = hd;
		findMinMax(root.left, hd - 1);
		findMinMax(root.right, hd + 1);
	}

	public Node<E> root() {
		return root;
	}

	public void setRoot(Node<E> root) {
		this.root = root;
		;
	}

	public boolean isBalacnedTree() {
		return isBalacnedTree(root, false);
	}

	/**
	 * if (root == null) { maxH = minH = 0; return true; } int minL = 0, maxL =
	 * 0; int minR = 0, maxR = 0; if (!isBalacnedTree(root.left)) return false;
	 * if (!isBalacnedTree(root.right)) return false; minH = Math.min(minL,
	 * minR) + 1; maxH = Math.min(maxL, maxR) + 1;
	 * 
	 * if (maxH <= 2 * minH) return true; return false;
	 */
	private int lh, rh;

	private boolean isBalacnedTree(Node<E> root, boolean isLeft) {
		boolean l, r;
		if (root == null) {
			if (isLeft)
				lh = 0;
			else
				rh = 0;
			return true;
		}

		l = isBalacnedTree(root.left, true);
		r = isBalacnedTree(root.right, false);

		if (isLeft)
			lh++;
		else
			rh++;
		if (lh - rh >= 2 || rh - lh >= 2)
			return false;
		else
			return l && r;
	}

	public void kDistanceFromLeafNodes(int k) {
		@SuppressWarnings("unchecked")
		E[] path = (E[]) new Comparable[size];
		boolean[] visited = new boolean[size];
		kDistanceFromLeafNodes(root, path, visited, 0, k);
	}

	private void kDistanceFromLeafNodes(Node<E> root, E[] path,
			boolean[] visited, int pathLen, int k) {
		if (root == null)
			return;

		path[pathLen] = root.e;
		visited[pathLen] = true;
		pathLen++;

		if (isLeafNode(root) && (pathLen - k - 1) >= 0
				&& visited[pathLen - k - 1]) {
			System.out.print(path[pathLen - k - 1] + " ");
			visited[pathLen - k - 1] = false;
			return;
		}
		kDistanceFromLeafNodes(root.left, path, visited, pathLen, k);
		kDistanceFromLeafNodes(root.right, path, visited, pathLen, k);
	}

	private int d1, d2, dist;

	public int distanceBetweenNodes(E a, E b) {
		d1 = d2 = -1;
		dist = 0;
		Node<E> lca = findLcaAndDistUtil(root, a, b, 0);
		System.out.println(d1 + " " + d2);

		System.out.println("lca is : " + lca);
		if (d1 != -1 && d2 != -1)
			return dist;

		if (d1 != -1)
			return findLevel(lca, b, 0);
		if (d2 != -1)
			return findLevel(lca, a, 0);
		return -1;
	}

	private int findLevel(Node<E> root, E b, int i) {
		if (root == null)
			return -1;
		if (root.e.equals(b))
			return i;
		int j = findLevel(root.left, b, i + 1);
		return j != -1 ? j : findLevel(root.right, b, i + 1);
	}

	private Node<E> findLcaAndDistUtil(Node<E> root, E a, E b, int level) {
		
		if (root == null) return null;

		if (root.e.equals(a)) {
			d1 = level;
			return root;
		}
		if (root.e.equals(b)) {
			d2 = level;
			return root;
		}
		
		final Node<E> na = findLcaAndDistUtil(root.left, a, b, level + 1);
		final Node<E> nb = findLcaAndDistUtil(root.right, a, b, level + 1);

		if (na != null && nb != null) {
			dist = d1 + d2 - (2 * level);
			return root;
		}
		return na == null ? nb : na;
	}

	public void printWithOutSiblings(Node<E> root) {
		if (root == null)
			return;
		if (root.left != null && root.right != null) {
			printWithOutSiblings(root.left);
			printWithOutSiblings(root.right);
		} else if (root.left != null) {
			System.out.print(root.left.e + " ");
			printWithOutSiblings(root.left);
		} else if (root.right != null) {
			System.out.print(root.right.e + " ");
			printWithOutSiblings(root.right);
		}
	}

	private Node<E> prev;
	private Node<E> head;

	public void toDll() {
		prev = null;
		convertTreeToDll(root);

		printList(head);
	}

	private void printList(Node<E> head) {
		while (head != null) {
			System.out.print(head.e + " ");
			head = head.right;
		}
	}

	private void convertTreeToDll(Node<E> root) {
		if (root == null)
			return;

		convertTreeToDll(root.left);

		// to make head point to first node in inOrder..
		if (prev == null)
			head = root;
		else {
			root.left = prev;
			prev.right = root;
		}
		prev = root;
		convertTreeToDll(root.right);
	}

	public int sumOfPaths(Node<Integer> root) {
		int sum = 0;
		return sumOfPaths(root, sum);
	}

	private int sumOfPaths(Node<Integer> root, int sum) {
		if (root == null)
			return 0;
		sum = sum * 10 + root.e;
		if (root.left == null && root.right == null)
			return sum;
		return sumOfPaths(root.left, sum) + sumOfPaths(root.right, sum);
	}

	private Node<E> deepestLeft;
	private Node<E> deepestRight;

	public void deepestLeftNode(Node<E> root) {
		maxLevel = 0;
		deepestLeftNode(root, 0, false);
		System.out.println(deepestLeft);
	}

	private void deepestLeftNode(Node<E> root, int level, boolean isLeft) {
		if (root == null)
			return;
		if (isLeft && root.left == null && root.right == null
				&& level > maxLevel) {
			deepestLeft = root;
			maxLevel = level;
			return;
		}

		deepestLeftNode(root.left, level + 1, true);
		deepestLeftNode(root.right, level + 1, false);
	}

	public void deepestRightNode(Node<E> root) {
		maxLevel = 0;
		deepestRightNode(root, 0, false);
		System.out.println(deepestRight);
	}

	private void deepestRightNode(Node<E> root, int level, boolean isRight) {
		if (root == null)
			return;
		if (isRight && root.left == null && root.right == null
				&& level > maxLevel) {
			maxLevel = level;
			deepestRight = root;
			return;
		}

		deepestRightNode(root.left, level + 1, false);
		deepestRightNode(root.right, level + 1, true);
	}

	public void generateLeafDll() {
		inOrderTraversal(root);
		System.out.println();
		head = null;
		generateLeafDll(root);

		printList(head);
		System.out.println();
		inOrderTraversal(root);
		System.out.println();
	}

	private Node<E> generateLeafDll(Node<E> root) {
		if (root == null)
			return null;

		if (isLeafNode(root)) {
			root.right = head;

			if (head != null)
				head.left = root;
			head = root;
			return null;
		}

		root.right = generateLeafDll(root.right);
		root.left = generateLeafDll(root.left);
		return root;
	}

	public Node<Integer> deleteLessSum(Node<Integer> root, int i) {
		return deleteLessSum(root, i, 0);
	}

	private Node<Integer> deleteLessSum(Node<Integer> root, int i, int s) {
		if (root == null)
			return null;

		s = s + root.e;

		root.left = deleteLessSum(root.left, i, s);
		root.right = deleteLessSum(root.right, i, s);

		if (s < i && root.left == null && root.right == null)
			root = null;
		return root;
	}

	private int sum = 0;

	public void revInOrderTraversal(Node<Integer> root) {
		if (root == null)
			return;
		revInOrderTraversal(root.right);
		sum = sum + root.e;
		root.e = sum;
		revInOrderTraversal(root.left);
	}

	private int leafLevel;

	public boolean allLeafNodesSameLevel(Node<Integer> root) {
		leafLevel = 0;
		return allLeafNodesSameLevel(root, 0);
	}

	private boolean allLeafNodesSameLevel(Node<Integer> root, int i) {
		if (root == null)
			return true;

		if (isLeaf(root)) {
			if (leafLevel == 0) {
				leafLevel = i;
				return true;
			}
			return (leafLevel == i);
		}
		return allLeafNodesSameLevel(root.left, i + 1)
				&& allLeafNodesSameLevel(root.right, i + 1);
	}

	public int depthOfDeepestOddNode(Node<E> root, int i) {
		if (root == null)
			return 0;

		if (isLeafNode(root) && ((i % 2) != 0))
			return i;
		return Math.max(depthOfDeepestOddNode(root.left, i + 1),
				depthOfDeepestOddNode(root.right, i + 1));
	}

	public int depthOfDeepestEvenNode(Node<E> root, int i) {
		if (root == null)
			return 0;

		if (isLeafNode(root) && ((i % 2) == 0))
			return i;
		return Math.max(depthOfDeepestEvenNode(root.left, i + 1), depthOfDeepestEvenNode(root.right, i + 1));
	}

	public void printPostFromPreAndInOrder(int[] in, int size, int[] pre,
			int index) {
		int root = search(in, pre[index], size);
		if (root != 0)
			printPostFromPreAndInOrder(in, size, pre, index + 1);
		if (root != (size - 1))
			printPostFromPreAndInOrder(in, size, pre, index + 1);
		System.out.print(in[index] + " ");
	}

	private int search(int[] in, int e, int size) {
		for (int i = 0; i < size; i++)
			if (in[i] == e)
				return i;
		return -1;
	}

	public int getLevelDiff(Node<Integer> root) {
		if (root == null)
			return 0;
		return root.e - getLevelDiff(root.left) - getLevelDiff(root.right);
	}

	public void printAncestors(E e) {
		Node<E> root = this.root;
		if (root == null) return;
		
		final DoubleLinkedList<Node<E>> stack = new DoubleLinkedList<>();
		while (true) {
			while (root != null && !root.e.equals(e)) {
				stack.addFirst(root);
				root = root.left;
			}

			if (root != null && root.e.equals(e))
				break;

			if (stack.peek().right == null) {
				root = stack.poll();
				// If the popped node is right child of top, then remove the top
				// as well.
				while (!stack.isEmpty() && stack.peek().right == root)
					root = stack.poll();
			}

			root = stack.isEmpty() ? null : stack.peek().right;
		}

		while (!stack.isEmpty())
			System.out.print(stack.poll().e + " ");
	}

	public boolean printAncestorsRecursively(Node<E> root, E e) {
		if (root == null)
			return false;
		if (root.e.equals(e))
			return true;

		if (printAncestorsRecursively(root.left, e) || printAncestorsRecursively(root.right, e)) {
			System.out.print(root.e + " ");
			return true;
		}
		return false;
	}

	public E leftNode(E e) {
		return prevLeftNode(root, e);
	}
	
	public E rightNode(E e) {
		return nextRightNode(root, e);
	}

	private E prevLeftNode(Node<E> root, E e) {
		
		final DoubleLinkedList<Node<E>> queueNode = new DoubleLinkedList<>();
		final DoubleLinkedList<Integer> queueLevel = new DoubleLinkedList<>();
		int level = 0;
		queueNode.offer(root);
		queueLevel.offer(level);

		while (!queueNode.isEmpty()) {
			Node<E> n = queueNode.poll();
			level = queueLevel.poll();

			if (Objects.equals(n.e, e)) {
				if (queueLevel.isEmpty() || queueLevel.peek() != level)
					return null;
				return queueNode.poll().e;
			}

			if (n.right != null) {
				queueNode.offer(n.right);
				queueLevel.offer(level + 1);
			}
			if (n.left != null) {
				queueNode.offer(n.left);
				queueLevel.offer(level + 1);
			}
		}
		return null;
	}

	private E nextRightNode(final Node<E> root, final E e) {
		if (root == null)
			return null;
		Queue<Node<E>> es = new ArrayDeque<>();
		Queue<Integer> integers = new ArrayDeque<>();
		int level = 0;
		es.offer(root);
		integers.offer(level);

		while (es.size() != 0) {
			Node<E> t = es.poll();
			level = integers.poll();

			if (t.e.equals(e)) {
				if (integers.size() == 0 || integers.peek() != level)
					return null;
				return es.poll().e;
			}

			if (t.left != null) {
				es.offer(t.left);
				integers.offer(level + 1);
			}
			if (t.right != null) {
				es.offer(t.right);
				integers.offer(level + 1);
			}
		}
		return null;
	}

	public boolean isIsomorphic(Node<E> n1, Node<E> n2) {
		if (n1 == null && n2 == null)
			return true;
		if (n1 == null || n2 == null)
			return false;
		if (!n1.e.equals(n2.e))
			return false;
		return ((isIsomorphic(n1.left, n2.left) && isIsomorphic(n1.right,
				n2.right)) || (isIsomorphic(n1.left, n2.right) && isIsomorphic(
				n1.right, n2.left)));
	}

	public Node<E> remove(E e) {
		Node<E> n = getNode(e);
		if (n == null)
			return null;
		else {
			deleteNode(n);
			return n;
		}
	}

	private void deleteNode(Node<E> n) {
		size--;
		
		// strictly internal node, find successor, copy its value to current node and point current to successor
		if (n.left != null && n.right != null) {
			Node<E> s = successor(n);
			n.e = s.e;
			n = s;
		}
		
		// now removing successor node..
		Node<E> replacementNode = n.left != null ? n.left : n.right;

		// replacement exists..
		if (replacementNode != null) {
			replacementNode.parent = n.parent;
			// n is root node..
			if (n.parent == null)
				root = replacementNode;
			else if (n == n.parent.left)
				n.parent.left = replacementNode;
			else
				n.parent.right = replacementNode;

			n.parent = n.left = n.right = null;
			n.e = null;
		}
		// no replacement & we are only one here..
		else if (n.parent == null)
			root = null;
		else {
			// no repalcement & we are not only one, remove current node
			if (n.parent != null) {
				if (n == n.parent.left)
					n.parent.left = null;
				else if (n == n.parent.right)
					n.parent.right = null;
				n.parent = null;
			}
		}
	}

	private Node<E> getNode(E e) {
		if (root == null)
			return null;
		Node<E> t = root;
		while (t != null) {
			int cmp = e.compareTo(t.e);
			if (cmp == 0)
				return t;
			else if (cmp < 0)
				t = t.left;
			else
				t = t.right;
		}
		return null;
	}

	public void removeOutsideRange(Node<E> root, E min, E max) {
		if (root == null)
			return;

		removeOutsideRange(root.left, min, max);
		removeOutsideRange(root.right, min, max);

		if (root.e.compareTo(min) < 0)
			deleteNode(root);
		else if (root.e.compareTo(max) > 0)
			deleteNode(root);
	}

	public Node<E> convertListToTree(
			linkedlists.SingleLinkedList.Node<E> first, Node<E> root) {
		if (first == null) {
			root = null;
			return root;
		}
		final DoubleLinkedList<Node<E>> queue = new DoubleLinkedList<Node<E>>();

		root = new Node<E>(first.getE(), null, null, null);

		queue.offer(root);

		first = first.getNext();

		while (first != null) {
			Node<E> parent = queue.poll();
			Node<E> left, right = null;
			left = new Node<E>(first.getE(), null, null, parent);
			queue.offer(left);
			first = first.getNext();

			if (first != null) {
				right = new Node<E>(first.getE(), null, null, parent);
				queue.offer(right);
				first = first.getNext();
			}
			parent.left = left;
			parent.right = right;
		}
		return root;
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

	public void levelOrderTraversalReverse() {
		int k = heightOf(root);
		System.out.println("height of tree is : " + k);
		int i = k;
		while (i >= 0) {
			printkdistanceNodeDown(root, i);
			System.out.println();
			i--;
		}
	}

	/**
	 * level order traversal and sum of odd/even levels
	 * 
	 * @param root
	 */
	public void levelOrderTraversalWithQueue(Node<Integer> root) {
		if (root == null)
			return;
		int evenSum = 0;
		int oddSum = 0;
		int level = 1;
		final DoubleLinkedList<Node<Integer>> queue = new DoubleLinkedList<>();
		final DoubleLinkedList<Integer> levels = new DoubleLinkedList<>();
		queue.offer(root);
		levels.offer(level);
		while (!queue.isEmpty()) {
			
			final Node<Integer> e = queue.poll();
			level = levels.poll();
			System.out.print(e.e + "-" + level + " ");

			if ((level % 2) == 0)
				evenSum += e.e;
			else
				oddSum += e.e;
			
			if (e.left != null) {
				queue.add(e.left);
				levels.offer(level + 1);
			}
			if (e.right != null) {
				queue.add(e.right);
				levels.offer(level + 1);
			}
		}
		System.out.println();
		System.out.println("diff is : " + (oddSum - evenSum));
	}

	public void levelOrderTraversalReverseWithQueue(final Node<E> root) {
		
		if (root == null)
			return;

		final DoubleLinkedList<Node<E>> queue = new DoubleLinkedList<>();
		final DoubleLinkedList<Node<E>> stack = new DoubleLinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			
			final Node<E> t = queue.poll();
			stack.addFirst(t);
			
			// right is added first, cause we get elements from stack in order to print
			if (t.right != null) queue.offer(t.right);
			if (t.left != null) queue.offer(t.left);
		}

		while (!stack.isEmpty())
			System.out.print(stack.poll().e + " ");
		System.out.println();
	}

	public boolean isSumEquals(Node<Integer> node, int i) {
		
		if (node == null) return false;

		final DoubleLinkedList<Node<Integer>> inOrder = new DoubleLinkedList<>();
		final DoubleLinkedList<Node<Integer>> inOrderRev = new DoubleLinkedList<>();

		boolean done1 = false, done2 = false;
		Node<Integer> curr1 = node, curr2 = node;
		int val1 = 0, val2 = 0;
		while (true) {

			while (!done1) {
				if (curr1 != null) {
					inOrder.addFirst(curr1);
					curr1 = curr1.left;
				} else {
					if (inOrder.isEmpty())
						done1 = true;
					else {
						curr1 = inOrder.poll();
						val1 = curr1.e;
						curr1 = curr1.right;
						done1 = true;
					}
				}
			}
			while (!done2) {
				if (curr2 != null) {
					inOrderRev.addFirst(curr2);
					curr2 = curr2.right;
				} else {
					if (inOrderRev.isEmpty())
						done2 = true;
					else {
						curr2 = inOrderRev.poll();
						val2 = curr2.e;
						curr2 = curr2.left;
						done2 = true;
					}
				}
			}

			if ((val1 != val2) && ((val1 + val2) == i)) {
				System.out.println("values are : " + val1 + " -- " + val2);
				return true;
			} else if ((val1 + val2) > i)
				done2 = false;
			else if ((val1 + val2) < i)
				done1 = false;
			if (val1 >= val2)
				return false;
		}
	}

	public boolean isTripletEquals(Node<E> root, int i) {
		if (root == null)
			return false;

		Node<E> leftMost = leftMost(root);
		while (leftMost != null) {
			int a = (Integer) leftMost.e;
			Node<E> s = successor(leftMost);
			Node<E> p = rightMost(root);
			while (s != null && p != null && !s.e.equals(p.e)) {
				int sum = (Integer) s.e + (Integer) p.e + a;
				if (sum == i) {
					System.out.println("elements are : " + s.e + " - " + p.e
							+ " - " + a);
					return true;
				}
				if (sum < i)
					s = successor(s);
				else
					p = predecessor(p);
			}
			leftMost = successor(leftMost);
		}
		return false;
	}

	public void preOrderTraversal(Node<E> root) {
		if (root == null)
			return;
		System.out.print(root.e + " ");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}

	public void preOrderIterative(Node<E> root) {
		
		if (root == null) return;
		final DoubleLinkedList<Node<E>> stack = new DoubleLinkedList<Node<E>>();

		stack.addFirst(root);
		Node<E> n;
		
		while (!stack.isEmpty()) {
			n = stack.poll();
			System.out.print(n.e + " ");

			if (n.right != null)
				stack.addFirst(n.right);
			if (n.left != null)
				stack.addFirst(n.left);
		}
		System.out.println();
	}
	
	/**
	 * Pre-Order traversal without recursion and stack. In this traversal, we
	 * first create links to In-order successor and print the data using these
	 * links, and finally revert the changes to restore original tree.
	 * 
	 * 1. Initialize current as root 
	 * 2. While current is not NULL
	 * If current does not have left child
	 * a) Print current’s data
	 * b) Go to the right, i.e., current = current->right
	 * Else
	 * a) Make current as right child of the rightmost node in current's left subtree
	 * b) Go to this left child, i.e., current = current->left
	 * 
	 * @param root root of tree
	 */
	public void preOrderMorris(final Node<E> root) {
		
		if (root == null) {
			System.err.println("Empty Tree!!!");
			return;
		}
		
		Node<E> curr = root;
		Node<E> pre = null;
		
		while (curr != null) {
			
			if (curr.left == null) {
				// now curr is left most node i.e. no left child
				System.out.print(curr.e + " ");
				curr = curr.right;
			} else {
				// curr's left is not null
				pre = curr.left;
				// go to rightmost node of left sub tree
				while (pre.right != null && pre.right != curr)
					pre = pre.right;
				
				// make curr as right child of rightmost node i.e. its inorder predecessor
				if (pre.right == null) {
					pre.right = curr;
					System.out.print(curr.e + " ");
					curr = curr.left;
				} else {
					// revert the changes, curr is already marked as child of its inorder predecessor
					pre.right = null;
					curr = curr.right; // to iterate in right subtree
				}
			}
		}
		System.out.println();
	}

	public void postOrderTraversal(Node<E> root) {
		if (root == null) return;
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.print(root.e + " ");
	}

	public void postOrderIterative(Node<E> root) {
		if (root == null) return;
		final DoubleLinkedList<Node<E>> stack1 = new DoubleLinkedList<Node<E>>();
		final DoubleLinkedList<Node<E>> stack2 = new DoubleLinkedList<Node<E>>();

		stack1.addFirst(root);
		Node<E> n;
		while (!stack1.isEmpty()) {
			n = stack1.poll();

			stack2.addFirst(n);

			if (n.left != null)
				stack1.addFirst(n.left);
			if (n.right != null)
				stack1.addFirst(n.right);
		}

		while (!stack2.isEmpty())
			System.out.print(stack2.poll().e + " ");
		System.out.println();
	}

	public void inOrderIterative(final Node<E> root) {
		
		if (root == null) {
			System.err.println("Empty Tree!!!");
			return;
		}
		final Stack<Node<E>> stack = new Stack<>();

		Node<E> n = root;
		while (true) {
			
			// goto last left node and adding all nodes in stack on the way
			while (n != null) {
				stack.push(n);
				n = n.left;
			}
			
			// pop out last node, print and check if it has any right child
			if (!stack.isEmpty()) {
				n = stack.pop();
				System.out.print(n.e + " ");
				n = n.right;
			} else
				break;
		}
		System.out.println();
	}
	
	/**
	 * In-Order traversal without recursion and stack. In this traversal, we
	 * first create links to Inorder successor and print the data using these
	 * links, and finally revert the changes to restore original tree.
	 * 
	 * 1. Initialize current as root 
	 * 2. While current is not NULL
	 * If current does not have left child
	 * a) Print current’s data
	 * b) Go to the right, i.e., current = current->right
	 * Else
	 * a) Make current as right child of the rightmost node in current's left subtree
	 * b) Go to this left child, i.e., current = current->left
	 * 
	 * @param root
	 */
	public void inOrderMorris(final Node<E> root) {
		
		if (root == null) {
			System.err.println("Empty Tree!!!");
			return;
		}
		
		Node<E> curr = root;
		Node<E> pre = null;
		
		while (curr != null) {
			
			if (curr.left == null) {
				// now curr is left most node i.e. no left child, so print it
				System.out.print(curr.e + " ");
				curr = curr.right;
			} else {
				// curr's left is not null
				pre = curr.left;
				// go to rightmost node of left sub tree and make curr its right child
				while (pre.right != null && pre.right != curr)
					pre = pre.right;
				
				// make curr as right child of rightmost node i.e. its inorder predecessor
				if (pre.right == null) {
					pre.right = curr;
					curr = curr.left;
				} else {
					// revert the changes, curr is already marked as child of its inorder predecessor
					pre.right = null;
					System.out.print(curr.e + " ");
					curr = curr.right; // to iterate in right subtree
				}
			}
		}
		System.out.println();
	}

	public void postOrderIterativeSingleStack(Node<E> root) {
		if (root == null)
			return;
		final DoubleLinkedList<Node<E>> stack = new DoubleLinkedList<Node<E>>();
		Node<E> n = root;
		do {
			while (n != null) {
				if (n.right != null)
					stack.addFirst(n.right);
				stack.addFirst(n);
				n = n.left;
			}
			n = stack.poll();

			if (n.right != null && stack.peek() == n.right) {
				Node<E> r = stack.poll();
				stack.addFirst(n);
				n = r;
			} else {
				System.out.print(n.e + " ");
				n = null;
			}
		} while (!stack.isEmpty());
	}

	public int liss(Node<E> root) {
		
		if (root == null) return 0;
		// return for dynamic programming, no further calculation is required
		if (root.liss != 0) return root.liss;

		if (isLeafNode(root)) return (root.liss = 1);

		// Caculate size excluding the current node
		int liss_excl = liss(root.left) + liss(root.right);

		// Calculate size including the current node
		int liss_incl = 1;

		if (root.left != null)
			liss_incl += liss(root.left.left) + liss(root.left.right);
		if (root.right != null)
			liss_incl += liss(root.right.left) + liss(root.right.right);

		root.liss = Math.max(liss_excl, liss_incl);
		return root.liss;
	}

	public int ceil(Node<Integer> root, int e) {
		if (root == null)
			return -1;
		if (root.e.equals(e))
			return root.e;
		// If root's key is smaller, ceil must be in right subtree
		if (root.e.compareTo(e) < 0)
			return ceil(root.right, e);
		// Else, either left subtree or root has the ceil value
		Integer ceil = ceil(root.left, e);
		return ceil.compareTo(e) >= 0 ? ceil : root.e;
	}

	private static final int INFINITY = Integer.MAX_VALUE;

	public int floor(Node<Integer> root, int i) {
		if (root == null)
			return INFINITY;
		if (root.e.equals(i))
			return root.e;
		if (root.e.compareTo(i) > 0)
			return floor(root.left, i);
		final Integer floor = floor(root.right, i);
		return floor.compareTo(i) <= 0 ? floor : root.e;
	}

	public boolean isBST(final Node<Integer> root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(Node<Integer> root, int minValue, int maxValue) {
		if (root == null)
			return true;
		if (root.e < minValue || root.e > maxValue)
			return false;
		return isBST(root.left, minValue, root.e - 1)
				&& isBST(root.right, root.e + 1, maxValue);
	}

	private int preIndex;

	public Node<E> convertTreeFromPreOrder(E[] pre) {
		preIndex = 0;
		return convertTreeFromPreOrder(pre, pre[0], Integer.MIN_VALUE, Integer.MAX_VALUE, pre.length);
	}

	private Node<E> convertTreeFromPreOrder(E[] pre, E e, int minValue, int maxValue, int size) {
		if (preIndex >= size)
			return null;
		Node<E> root = null;

		if ((Integer) e > minValue && (Integer) e < maxValue) {
			root = new Node<E>(e, null, null, null);
			preIndex += 1;

			if (preIndex < size) {
				root.left = convertTreeFromPreOrder(pre, pre[preIndex],
						minValue, (Integer) e, size);
				if (preIndex < size)
					root.right = convertTreeFromPreOrder(pre, pre[preIndex],
							(Integer) e, maxValue, size);
				if (root.left != null)
					root.left.parent = root;
				if (root.right != null)
					root.right.parent = root;
			}
		}
		return root;
	}

	public Node<E> convertTreeFromPreOrderWithStack(E[] pre) {
		final DoubleLinkedList<Node<E>> stack = new DoubleLinkedList<Node<E>>();
		Node<E> root = new Node<E>(pre[0], null, null, null);
		stack.addFirst(root);
		for (int i = 1; i < pre.length; i++) {
			Node<E> t = null;
			while (!stack.isEmpty() && pre[i].compareTo(stack.peek().e) > 0)
				t = stack.poll();
			Node<E> n = new Node<E>(pre[i], null, null, t);
			if (t != null) {
				t.right = n;
				stack.addFirst(n);
			} else {
				stack.peek().left = n;
				stack.addFirst(n);
			}
		}
		return root;
	}

	public Node<E> constructTreeFromPrePostOrder(E[] preOrder, E[] postOrder) {
		preIndex = 0;
		return constructTreeFromPrePostOrder(preOrder, postOrder, 0, preOrder.length - 1, preOrder.length);
	}

	private Node<E> constructTreeFromPrePostOrder(E[] preOrder, E[] postOrder, int min, int max, int size) {
		if (preIndex >= size || min > max)
			return null;
		Node<E> root = new Node<E>(preOrder[preIndex], null, null, null);
		++preIndex;

		if (min == max)
			return root;

		// Search the next element of pre[] in post[]

		int i = 0;
		if (preIndex < size)
			for (i = min; i <= max; ++i)
				if (preOrder[preIndex].equals(postOrder[i]))
					break;

		if (i <= max) {
			root.left = constructTreeFromPrePostOrder(preOrder, postOrder, min,
					i, size);
			root.right = constructTreeFromPrePostOrder(preOrder, postOrder,
					i + 1, max, size);
			if (root.left != null)
				root.left.parent = root;
			if (root.right != null)
				root.right.parent = root;
		}
		return root;
	}

	public void swapNodes() {
		swap(root.left, root.right);
	}

	private void swap(Node<E> left, Node<E> right) {
		E temp = left.e;
		left.e = right.e;
		right.e = temp;
	}

	private Node<E> first, middle, last;

	public void undoSwap() {
		first = middle = last = prev = null;
		undoSwap(root);
		if (first != null && last != null)
			swap(first, last);
		else
			swap(first, middle);
		prev = null; // this used elsewhere also..
		System.out.println();
	}

	private void undoSwap(Node<E> root) {
		if (root == null)
			return;
		undoSwap(root.left);

		if (prev != null && root.e.compareTo(prev.e) < 0) {
			if (first == null) {
				first = prev;
				middle = root;
			} else
				last = root;
		}
		prev = root;
		undoSwap(root.right);
	}

	public void boundryTraversal() {
		if (root == null)
			return;
		System.out.print(root.e + " ");
		printLeftBoundry(root.left);
		printLeaves(root.left);
		printLeaves(root.right);
		printRightBoundry(root.right);
	}

	private void printLeaves(final Node<E> root) {
		if (root == null)
			return;
		printLeaves(root.left);
		if (isLeafNode(root))
			System.out.print(root.e + " ");
		printLeaves(root.right);
	}

	private void printLeftBoundry(final Node<E> root) {
		if (root == null)
			return;
		if (root.left != null) {
			System.out.print(root.e + " ");
			printLeftBoundry(root.left);
		} else if (root.right != null) {
			System.out.print(root.e + " ");
			printLeftBoundry(root.right);
		}
	}

	private void printRightBoundry(final Node<E> root) {
		if (root == null)
			return;
		if (root.right != null) {
			printRightBoundry(root.right);
			System.out.print(root.e + " ");
		} else if (root.left != null) {
			printRightBoundry(root.left);
			System.out.print(root.e + " ");
		}
	}

	Node<E> constructSpecialTreeFromInOrder(final E[] in, final int min, final int max) {
		
		if (min > max) return null;
		
		final int i = max(in, min, max);
		final Node<E> root = new Node<E>(in[i], null, null, null);
		
		// only element in ARRAY
		if (min == max) return root;
		root.left = constructSpecialTreeFromInOrder(in, min, i - 1);
		root.right = constructSpecialTreeFromInOrder(in, i + 1, max);
		
		// set parent node
		if (root.left != null) root.left.parent = root;
		if (root.right != null) root.right.parent = root;
		return root;
	}

	private int max(E[] in, int min, int max) {
		E minE = in[min];
		int minIndex = min;
		for (int i = min + 1; i <= max; i++) {
			if (minE.compareTo(in[i]) > 0) {
				minE = in[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	private int index = 0;

	public Node<E> constructSpecialTreeFromPrePreLNOrder(E[] preOrder,
			Character[] preLN, int size) {
		int i = index;
		if (i == size)
			return null;
		Node<E> root = new Node<E>(preOrder[index], null, null, null);
		index++;
		if (preLN[i] == 'N') {
			root.left = constructSpecialTreeFromPrePreLNOrder(preOrder, preLN,
					size);
			root.right = constructSpecialTreeFromPrePreLNOrder(preOrder, preLN,
					size);
			if (root.left != null)
				root.left.parent = root;
			if (root.right != null)
				root.right.parent = root;
		}
		return root;
	}

	public void mergeTree(Node<E> root, Node<E> otherRoot) {
		if (root == null) {
			inOrderTraversal(otherRoot);
			return;
		}
		if (otherRoot == null) {
			inOrderTraversal(root);
			return;
		}

		final DoubleLinkedList<Node<E>> stack = new DoubleLinkedList<Node<E>>();
		final DoubleLinkedList<Node<E>> otherStack = new DoubleLinkedList<Node<E>>();
		Node<E> n = root;
		Node<E> oN = otherRoot;
		while (true) {
			while (n != null) {
				stack.addFirst(n);
				n = n.left;
			}
			while (oN != null) {
				otherStack.addFirst(oN);
				oN = oN.left;
			}

			if (!stack.isEmpty() && !otherStack.isEmpty()) {
				n = stack.poll();
				oN = otherStack.poll();

				if (n.compareTo(oN) < 0) {
					System.out.print(n.e + " ");
					otherStack.addFirst(oN);
					oN = null;
					n = n.right;
				} else {
					System.out.print(oN.e + " ");
					stack.addFirst(n);
					n = null;
					oN = oN.right;
				}
			} else if (!otherStack.isEmpty()) {
				oN = otherStack.poll();
				oN.left = null;
				inOrderTraversal(oN);
				return;
			} else if (!stack.isEmpty()) {
				n = stack.poll();
				n.left = null;
				inOrderTraversal(n);
				return;
			}
		}
	}

	private Node<Integer> targetNode;

	public void nodeOnLargestSum(Node<Integer> root) {
		targetNode = null;
		sum = 0;
		nodeOnLargestSum(root, 0);

		printLargestSumNode(root);
	}

	private boolean printLargestSumNode(Node<Integer> root) {
		if (root == null)
			return false;

		if (root == targetNode || printLargestSumNode(root.left)
				|| printLargestSumNode(root.right)) {
			System.out.print(root.e + " ");
			return true;
		}
		return false;
	}

	private void nodeOnLargestSum(Node<Integer> root, int currSum) {
		if (root == null)
			return;

		currSum = currSum + root.e;

		if (root.left == null && root.right == null)
			if (currSum > sum) {
				sum = currSum;
				targetNode = root;
			}

		nodeOnLargestSum(root.left, currSum);
		nodeOnLargestSum(root.right, currSum);
	}

	public int largestBST(Node<Integer> root) {
		return 0;
	}

	public int toSumTree(final Node<Integer> root) {
		if (root == null)
			return 0;

		int oldValue = root.e;

		root.e = toSumTree(root.left) + toSumTree(root.right);

		return oldValue + root.e;
	}

	private Node<E> next = null;

	public void addNextSucc(Node<E> root) {

		if (root == null)
			return;

		addNextSucc(root.right);

		root.nextSucc = next;

		next = root;

		addNextSucc(root.left);
	}

	public void nextRight(Node<E> root) {
		root.nextRight = null;

		nextLevelRight(root);
	}

	private void nextLevelRight(Node<E> root) {
		if (root == null)
			return;

		if (root.nextRight != null)
			nextLevelRight(root.nextRight);

		if (root.left != null) {
			if (root.right != null) {
				root.left.nextRight = root.right;
				root.right.nextRight = getNextRight(root);
			} else
				root.left.nextRight = getNextRight(root);

			nextLevelRight(root.left);
		} else if (root.right != null) {
			root.right.nextRight = getNextRight(root);
			nextLevelRight(root.right);
		} else
			nextLevelRight(getNextRight(root));
	}

	private Node<E> getNextRight(Node<E> t) {
		Node<E> nextRight = t.nextRight;

		while (nextRight != null) {
			if (nextRight.left != null)
				return nextRight.left;
			if (nextRight.right != null)
				return nextRight.right;
			nextRight = nextRight.nextRight;
		}
		return null;
	}

	public boolean isSumTree(Node<Integer> root) {
		if (root == null || (root.left == null && root.right == null))
			return true;
		int ls = sum(root.left);
		int rs = sum(root.right);

		if ((root.e == (ls + rs)) && isSumTree(root.left)
				&& isSumTree(root.right))
			return true;
		return false;
	}

	private int sum(Node<Integer> root) {
		if (root == null)
			return 0;
		return sum(root.left) + root.e + sum(root.right);
	}

	public boolean isSumTreeTricky(Node<Integer> root) {

		int ls;
		int rs;

		if (root == null || isLeaf(root))
			return true;

		if (isSumTreeTricky(root.left) && isSumTreeTricky(root.right)) {
			if (root.left == null)
				ls = 0;
			else if (isLeaf(root.left))
				ls = root.left.e;
			else
				ls = 2 * root.left.e;

			if (root.right == null)
				rs = 0;
			else if (isLeaf(root.right))
				rs = root.right.e;
			else
				rs = 2 * root.right.e;

			return root.e == (ls + rs);
		}
		return false;
	}

	private boolean isLeaf(Node<Integer> leaf) {
		if (leaf == null)
			return false;
		if (leaf.left == null && leaf.right == null)
			return true;
		return false;
	}

	public void printValueInRange(int min, int max, Node<Integer> root) {
		if (root == null)
			return;

		if (min < root.e)
			printValueInRange(min, max, root.left);
		if (min <= root.e && max >= root.e)
			System.out.print(root.e + " ");
		if (max > root.e)
			printValueInRange(min, max, root.right);
	}

	public void levelOfKey(Node<E> root, E e) {
		int level = levelOfKey(root, e, 0);
		System.out.println("level is : " + level);
	}

	private int levelOfKey(Node<E> root, E e, int level) {
		if (root == null)
			return 0;
		if (root.e.equals(e))
			return level;

		int l = levelOfKey(root.left, e, level + 1);
		if (l != 0)
			return l;
		return levelOfKey(root.right, e, level + 1);
	}

	public E smallestKthElement(Node<E> root, int i) {
		if (root == null)
			return null;
		final DoubleLinkedList<Node<E>> stack = new DoubleLinkedList<Node<E>>();

		Node<E> n = root;

		while (true) {
			while (n != null) {
				stack.addFirst(n);
				n = n.left;
			}

			if (!stack.isEmpty()) {
				n = stack.poll();
				i--;
				if (i == 0)
					return n.e;
				n = n.right;
			} else
				break;
		}
		return null;
	}

	public void printInOrderArray(E[] arr, int i, int j) {
		if (i > j)
			return;
		printInOrderArray(arr, i * 2 + 1, j);
		System.out.print(arr[i] + " ");
		printInOrderArray(arr, i * 2 + 2, j);
	}

	public void mirrorTree(final Node<E> root) {
		if (root == null)
			return;

		mirrorTree(root.left);
		mirrorTree(root.right);

		final Node<E> temp = root.left;
		root.left = root.right;
		root.right = temp;
	}

	public boolean foldableTree(Node<E> root) {
		if (root == null)
			return true;
		
		// mirror left side
		mirrorTree(root.left);
		
		// compare both sides now
		boolean b = foldableTree(root.left, root.right);

		// return tree to original shape
		mirrorTree(root.left);

		return b;
	}

	private boolean foldableTree(final Node<E> left, final Node<E> right) {
		if (left == null && right == null)
			return true;
		if (left != null && right != null
				&& foldableTree(left.left, right.left)
				&& foldableTree(left.right, right.right))
			return true;
		return false;
	}

	public boolean foldableTreeUtil(Node<E> root) {
		if (root == null)
			return true;
		return foldableTreeUtil(root.left, root.right);
	}

	private boolean foldableTreeUtil(Node<E> left, Node<E> right) {
		if (left == null && right == null)
			return true;
		if (left == null || right == null)
			return false;
		return foldableTreeUtil(left.left, right.right)
				&& foldableTreeUtil(left.right, right.left);
	}

	public int getMaxWidth(Node<E> root) {
		int maxWidth = 0;
		int width;
		if (root == null)
			return 0;
		int h = heightOf(root);
		int i;

		for (i = 1; i <= h; i++) {
			width = getLevelWidth(root, i);
			if (width > maxWidth)
				maxWidth = width;
		}
		return maxWidth;
	}

	private int getLevelWidth(Node<E> root, int i) {
		if (root == null)
			return 0;
		if (i == 1)
			return 1;
		return getLevelWidth(root.left, i - 1)
				+ getLevelWidth(root.right, i - 1);
	}

	public int getMaxWidthWithPreOrder(Node<E> root) {
		int h = heightIterative(root);
		int[] count = new int[h];

		getMaxWidthWithPreOrder(root, count, 0);
		return getMax(count);
	}

	private int getMax(int[] count) {
		int max = count[0];
		for (int i = 1, l = count.length; i < l; i++)
			if (count[i] > max)
				max = count[i];
		return max;
	}

	private void getMaxWidthWithPreOrder(Node<E> root, int[] count, int i) {
		if (root == null)
			return;

		count[i]++;
		getMaxWidthWithPreOrder(root.left, count, i + 1);
		getMaxWidthWithPreOrder(root.right, count, i + 1);
	}

	public void doubleTree(Node<E> root) {
		if (root == null)
			return;

		doubleTree(root.left);
		doubleTree(root.right);

		final Node<E> temp = root.left;
		root.left = new Node<E>(root.e, temp, null, root);
		if (temp != null)
			temp.parent = root.left;
	}

	public void doubleTreeRight(Node<E> root) {
		if (root == null)
			return;

		doubleTree(root.left);
		doubleTree(root.right);

		final Node<E> temp = root.right;
		root.right = new Node<E>(root.e, null, temp, root);
		if (temp != null)
			temp.parent = root.right;

	}

	public void printPathLength(final Node<E> root) {
		@SuppressWarnings("unchecked")
		final E[] arrE = (E[]) new Comparable[size];
		printPathLength(root, arrE, 0);
	}

	private void printPathLength(final Node<E> root, final E[] arrE, int i) {
		if (root == null)
			return;
		arrE[i++] = root.e;
		if (isLeafNode(root))
			System.out.println(Arrays.toString(arrE));
		printPathLength(root.left, arrE, i);
		printPathLength(root.right, arrE, i);
	}

	/**
	 * To check whether given Node if leaf node or not. For leaf node, both
	 * child nodes must be empty.
	 * 
	 * @param root
	 *            Node to verify if it is leaf node or not
	 * @return true if it a leaf node else false.
	 */
	private boolean isLeafNode(final Node<E> root) {
		return root.left == null && root.right == null;
	}

	Node<E> constructTreeFromInPreOrder(final E[] in, final E[] pre) {
		preIndex = 0;
		return constructTreeFromInPreOrder(in, pre, 0, in.length - 1);
	}

	private Node<E> constructTreeFromInPreOrder(final E[] in, final E[] pre, final int start, final int end) {
		
		// handle end cases
		if ((start > end) || preIndex == in.length) return null;
		final Node<E> node = new Node<E>(pre[preIndex++], null, null, null); // first node in pre order is root

		// only node in array
		if (start == end) return node;

		int inIndex = searchNodeInInOrderArray(in, start, end, node.e);

		node.left = constructTreeFromInPreOrder(in, pre, start, inIndex - 1);
		node.right = constructTreeFromInPreOrder(in, pre, inIndex + 1, end);

		return node;
	}

	private int searchNodeInInOrderArray(final E[] in, final int start, final int end, final E e) {
		int i;
		for (i = start; i <= end; i++)
			if (in[i].equals(e))
				return i;
		return -1;
	}

	public boolean rootToLeafSum(Node<Integer> root, int sum) {
		if (root == null)
			return sum == 0;

		final int subSum = sum - root.e;
		if (subSum == 0 && isLeaf(root))
			return true;
		return rootToLeafSum(root.left, subSum) || rootToLeafSum(root.right, subSum);
	}

	public void childSumTree(Node<Integer> root) {
		if (root == null)
			return;

		if (isLeaf(root))
			return;

		childSumTree(root.left);
		childSumTree(root.right);

		int leftData = 0;
		int rightData = 0;
		int diff;

		if (root.left != null)
			leftData = root.left.e;
		if (root.right != null)
			rightData = root.right.e;

		diff = leftData + rightData - root.e;

		if (diff >= 0)
			root.e += diff;
		else
			incrementChildren(root, -diff);
	}

	private void incrementChildren(Node<Integer> root, int diff) {
		if (root.left != null) {
			root.left.e += diff;
			incrementChildren(root.left, diff);
		} else if (root.right != null) {
			root.right.e += diff;
			incrementChildren(root.right, diff);
		}
	}

	public boolean isBSTUsingInOrder() {
		prev = null;
		return isBSTUsingInOrder(root);
	}

	private boolean isBSTUsingInOrder(Node<E> root) {
		if (root == null)
			return true;
		if (!isBSTUsingInOrder(root.left))
			return false;
		if (prev != null && prev.e.compareTo(root.e) >= 0)
			return false;
		prev = root;
		return isBSTUsingInOrder(root.right);
	}

	public E lca(final Node<E> root, E i, E j) {
		if (root == null)
			return null;
		if (root.e.compareTo(i) > 0 && root.e.compareTo(j) > 0)
			return lca(root.left, i, j);
		if (root.e.compareTo(i) < 0 && root.e.compareTo(j) < 0)
			return lca(root.right, i, j);
		return root.e;
	}

	public E lcaIterative(Node<E> root, E i, E j) {
		while (root != null) {
			if (root.e.compareTo(i) > 0 && root.e.compareTo(j) > 0)
				root = root.left;
			else if (root.e.compareTo(i) < 0 && root.e.compareTo(j) < 0)
				root = root.right;
			else
				break;
		}
		if (root == null)
			return null;
		else
			return root.e;
	}
	
	public int diameter(Node<E> root) {
		if (root == null)
			return 0;
		int lH = heightOf(root.left);
		int rH = heightOf(root.right);

		int lD = diameter(root.left);
		int rD = diameter(root.right);
		return Math.max(lH + rH + 1, Math.max(lD, rD));
	}
	
	int maxHeight = 0;
	
	int getMaxDepth(Node<E> root, int depth) {
		if (root == null) {
			return depth;
		}
		int depthPassed = depth + 1;
		int leftDepth = getMaxDepth(root.left, depthPassed);
		int rightDepth = getMaxDepth(root.right, depthPassed);
		if (((leftDepth - depthPassed) + (rightDepth - depthPassed) + 1) > maxHeight) {
			maxHeight = (leftDepth - depthPassed) + (rightDepth - depthPassed) + 1;
		}
		return rightDepth > leftDepth ? rightDepth : leftDepth;
	}
	
	private class Height {
		int h;
	}

	public int diameterOpt(Node<E> root) {
		final Height height = new Height();
		return diameterOpt(root, height);
	}

	private int diameterOpt(final Node<E> root, final Height h) {
		
		Height rHeight = new Height();
		Height lHeight = new Height();
		
		if (root == null) {
			h.h = 0;
			return 0; // diameter is also 0
		}
		
		rHeight.h++; lHeight.h++; // increase height of both right & left subtree
		int ld = diameterOpt(root.left, lHeight);
		int rd = diameterOpt(root.right, rHeight);
		
		h.h = Math.max(rHeight.h, lHeight.h) +1; // node height is max of right sub tree & left subtree + 1

		return Math.max(lHeight.h + rHeight.h + 1, Math.max(ld, rd));
	}

	int heightOf(Node<E> root) {
		if (root == null)
			return 0;
		final int left = heightOf(root.left);
		final int right = heightOf(root.right);
		return 1 + ((left > right) ? left : right);
	}
	
	public void printLevelOrder(final Node<E> root) {
		
		if (root == null) {
			System.out.println("Tree is empty");
			return;
		}
		
		final DoubleLinkedList<Node<E>> queue = new DoubleLinkedList<>();
		queue.offer(root);
		
		while (true) {
			
			int size = queue.size();
			if (size == 0)
				break;
			
			while (size > 0) {
				
				final Node<E> node = queue.poll();
				System.out.print(node.e + " ");
				
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
				size--;
			}
			
			System.out.println();
		}
	}
	
	public int heightIterative(Node<E> root) {
		
		if (root == null)
			return 0;
		int height = 0;
		final DoubleLinkedList<Node<E>> queue = new DoubleLinkedList<>();

		queue.offer(root);
		int nodeCount;

		while (true) {
			nodeCount = queue.size();

			if (nodeCount == 0)
				return height;

			height++;
			while (nodeCount > 0) {
				Node<E> node = queue.poll();
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
				nodeCount--;
			}
		}
	}
	
	public void inOrderTraversalWithStack(final Node<E> root) {
		
		if (root == null) {
			System.out.println("Tree is empty");
			return;
		}
		
		final DoubleLinkedList<Node<E>> stack = new DoubleLinkedList<>();
		Node<E> node = root;
		
		while (true) {
			
			while (node != null) {
				stack.addFirst(node);
				node = node.left;
			}
			
			if (!stack.isEmpty()) {
				node = stack.poll();
				System.out.print(node.e + " ");
				node = node.right;
			} else {
				break;
			}
		}
		System.out.println();
	}
	
	
	public int size(final Node<E> root) {
		
		if (root == null) {
			return 0;
		}
		
		return size(root.left) + 1 + size(root.right);
		
	}
	
	public boolean identicalTrees(final Node<E> a, final Node<E> b) {
		
		// both are null
		if (a == null && b == null) {
			return true;
		}
		
		//  both are not null, compare and return
		if (a != null && b != null) {
			return a.e == b.e && identicalTrees(a.left, b.left) && identicalTrees(a.right, b.right);
		}
		
		// only one is null
		return false;
	}
	
	public boolean identicalTreesIterative(final Node<E> a, final Node<E> b) {
		
		// both are null
		if (a == null && b == null) {
			return true;
		}
		
		// only one is null
		if (a == null || b == null) {
			return false;
		}
		
		final DoubleLinkedList<Node<E>> queueA = new DoubleLinkedList<>();
		final DoubleLinkedList<Node<E>> queueB = new DoubleLinkedList<>();
		
		queueA.add(a);
		queueB.add(b);
		
		while (!queueA.isEmpty() && !queueB.isEmpty()) {
			
			final Node<E> nodeA = queueA.poll();
			final Node<E> nodeB = queueB.poll();
			
			if (!Objects.equals(nodeA.e, nodeB.e)) {
				return false;
			}
			
			// now check left subtree
			if (nodeA.left != null && nodeB.left != null) {
				queueA.add(nodeA.left);
				queueB.add(nodeB.left);
			} else if (nodeA.left == null || nodeB.left == null) {
				return false;
			}
			
			// now check right subtree
			if (nodeA.right != null && nodeB.right != null) {
				queueA.add(nodeA.right);
				queueB.add(nodeB.right);
			} else if (nodeA.right == null || nodeB.right == null) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public void deleteTree(Node<E> root) {
		
		if (root == null) {
			return;
		}
		
		deleteTree(root.left);
		deleteTree(root.right);
		
		System.out.println("Deleting Node : " + root.e);
		
		root = null;
	}
	
	// to count leaf nodes
	
	public int countLeafNodes(final Node<E> root) {
		
		if (root == null) return 0;
		
		if (isLeafNode(root)) return 1;
		
		return countLeafNodes(root.left) + countLeafNodes(root.right); 
		
	}
	
	// level order in spiral form
	public void levelOrderSpiral(final Node<E> root) {
			
		if (root == null) {
			System.out.println("Tree is empty");
			return;
		}
		
		DoubleLinkedList<Node<E>> stackOdd = new DoubleLinkedList<>();
		DoubleLinkedList<Node<E>> stackEven = new DoubleLinkedList<>();
		
		stackOdd.addFirst(root);
		
		// till one stack has data, keep running the loop
		while (!stackOdd.isEmpty() || !stackEven.isEmpty()) {
			
			while (!stackOdd.isEmpty()) {
				
				final Node<E> n = stackOdd.poll();
				
				System.out.print(n.e + " ");
				
				if (n.right != null) stackEven.addFirst(n.right);
				if (n.left != null) stackEven.addFirst(n.left);
			}
			
			System.out.println();
			
			while (!stackEven.isEmpty()) {
				
				final Node<E> n = stackEven.poll();
				
				System.out.print(n.e + " ");
				
				if (n.left != null) stackOdd.addFirst(n.left);
				if (n.right != null) stackOdd.addFirst(n.right);
			}
			System.out.println();
		}
	}
	
	// vertical sum of tree
	
	void verticalSumOfTree(final Node<Integer> root) {
		
		final HashMap<Integer, Integer> sumMap = new HashMap<>();
		int hd = 0;
		
		verticalSumUtil(sumMap, root, hd);
		
		System.out.println(sumMap);
		
	}

	private void verticalSumUtil(final HashMap<Integer, Integer> sumMap, final Node<Integer> root, final int hd) {
		
		if (root == null) return;
		
		// store left subtree
		verticalSumUtil(sumMap, root.left, hd-1);
		
		Integer sum = sumMap.get(hd);
		
		if (sum == null) {
			sum = 0;
		}
		
		sum += root.e;
		
		sumMap.put(hd, sum);
		
		// store right subtree
		verticalSumUtil(sumMap, root.right, hd+1);
	}
	
	// is complete Binary Tree
	
	public boolean isCompleteBinaryTree(final Node<E> root) {
		
		if (root == null) return true;
		
		final DoubleLinkedList<Node<E>> queue = new DoubleLinkedList<>();
		
		queue.offer(root);
		
		// to check whether a null node is found or not
		boolean flag = false;
		
		while (!queue.isEmpty()) {
			
			final Node<E> n = queue.poll();
			
			if (n.left == null) {
				flag = true;
			} else {
				if (flag) {
					return false; // since we have already a null on this level
				}
				queue.offer(n.left);
				flag = false;
			}
			
			if (n.right == null) {
				flag = true;
			} else {
				if (flag) {
					return false; // since we have already a null on this level
				}
				queue.offer(n.right);
				flag = false;
			}
		}
		// now we have reached the end, so return true
		return true;
	}
}