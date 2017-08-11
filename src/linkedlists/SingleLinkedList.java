/**
 * 
 */
package linkedlists;

import java.util.Objects;


/**
 * @author aa49442
 * 
 */
public class SingleLinkedList<E extends Comparable<? super E>> {

	private Node<E> first;
	private Node<E> last;
	private int size;

	public SingleLinkedList() {
		first = null;
		last = null;
		size = 0;
	}

	/**
	 * @return the first
	 */
	public Node<E> getFirst() {
		return first;
	}

	public void clear() {
		first = last = null;
		size = 0;
	}

	public boolean isEmpty() {
		return this.first == null;
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

	public void push(E e) {
		linkFirst(e);
	}

	public E pop() {
		return unlinkFirst();
	}

	public E remove(Node<E> node) {
		final E e = node.e;
		Node<E> curr = this.first;
		Node<E> prev = null;

		while (curr != null) {
			if (curr == node) {
				if (prev == null)
					this.first = curr.next;
				else
					prev.next = curr.next;
				if (curr.next == null)
					this.last = curr;
				break;
			}
			prev = curr;
			curr = curr.next;
		}
		return e;
	}

	private void linkLast(E e) {
		final Node<E> l = last;
		Node<E> newNode = new Node<E>(e, null);
		last = newNode;
		if (first == null)
			first = newNode;
		else
			l.next = newNode;
		size++;
	}

	private E unlinkLast() {
		if (isEmpty())
			return null;
		final Node<E> l = last;
		E e = l.e;
		Node<E> f = first;
		Node<E> prev = null;

		while (f != last) {
			prev = f;
			f = f.next;
		}
		if (prev == null)
			first = prev;
		else
			prev.next = null;
		last = prev;
		size--;
		return e;
	}

	public int size() {
		return size;
	}

	private void linkFirst(E e) {
		final Node<E> f = first;
		final Node<E> newNode = new Node<E>(e, f);
		first = newNode;
		if (last == null)
			last = newNode;
		size++;
	}

	private E unlinkFirst() {
		if (isEmpty())
			return null;
		final Node<E> f = first;
		E e = f.e;
		first = first.next;
		if (first == null)
			last = first;
		size--;
		return e;
	}

	public static class Node<E extends Comparable<? super E>> implements
			Comparable<Node<E>> {
		private E e;
		private Node<E> next;

		/**
		 * @param element
		 * @param next
		 */
		public Node(E element, Node<E> next) {
			super();
			this.e = element;
			this.next = next;
		}

		/**
		 * @return the e
		 */
		public E getE() {
			return e;
		}

		/**
		 * @return the next
		 */
		public Node<E> getNext() {
			return next;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E e = next == null ? null : next.e;
			return "Node [element=" + this.e + ", next=" + e + "]";
		}

		@Override
		public int compareTo(Node<E> o) {
			return this.e.compareTo(o.e);
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
		for (Node<E> curr = first; curr != null; curr = curr.next)
			builder.append(curr.e + " ");
		return "SingleLinkedList [first=" + first + ", last=" + last
				+ ", size=" + size + ", elements= " + builder.toString() + "]";
	}

	public SingleLinkedList<E> rearrange() {
		if (first == null || first.next == null || first.next.next == null)
			return this;
		SingleLinkedList<E> evenLinkedList = new SingleLinkedList<E>();
		Node<E> odd = first;
		while (odd != null && odd.next != null) {
			Node<E> temp = odd.next.next;
			evenLinkedList.addFirst(odd.next.e);
			odd.next = odd.next.next;
			odd = temp;
		}
		Node<E> curr = this.first;
		while (curr != null && curr.next != null)
			curr = curr.next;
		curr.next = evenLinkedList.first;
		return this;
	}

	/**
	 * to swap alt elements of list..
	 */
	public void swapList() {
		if (first == null || first.next == null)
			return;
		Node<E> head = first;

		while (head != null && head.next != null) {
			swap(head, head.next);
			head = head.next.next;
		}
	}

	public void merge(final SingleLinkedList<E> list) {
		
		Node<E> head = this.first;
		Node<E> otherHead = list.first;

		if (otherHead == null)
			return;
		if (head == null) {
			this.first = otherHead;
			return;
		}

		while (head != null && otherHead != null) {
			final Node<E> temp = head.next; // save pointer to next element
			// next element is null, then add other list and return
			if (temp == null) {
				head.next = otherHead;
				break;
			}
			final Node<E> otherTemp = otherHead.next; // save pointer to next of other list's head
			head.next = otherHead; // add other list in current list
			otherHead.next = temp; // move other list next pointer to current list's next element

			head = temp; // move to next element
			otherHead = otherTemp; // move to next element
		}
	}

	/**
	 * 
	 */
	public void quickSort() {
		quickSort(this.first, this.last);
	}

	/**
	 * @param head
	 * @param tail
	 * @return
	 */
	private Node<E> quickSort(Node<E> head, Node<E> tail) {
		if (head == null || head == tail)
			return head;
		Node<E> newHead = null;
		Node<E> newTail = null;

		Node<E> pivot = partition(head, tail, newHead, newTail);

		if (newHead != pivot) {
			Node<E> temp = newHead;
			while (temp.next != pivot)
				temp = temp.next;
			temp.next = null;

			newHead = quickSort(newHead, temp);

			temp = this.last;
			temp.next = pivot;
		}
		pivot.next = quickSort(pivot.next, newTail);
		return newHead;
	}

	/**
	 * @param head
	 * @param tail
	 * @param newHead
	 * @param newTail
	 * @return pivot node..
	 */
	private Node<E> partition(Node<E> head, Node<E> tail, Node<E> newHead, Node<E> newTail) {
		Node<E> pivot = tail;
		Node<E> prev = null;
		Node<E> curr = head;
		Node<E> end = pivot;

		while (curr != pivot) {
			if (curr.e.compareTo(pivot.e) < 0) {
				if (newHead == null)
					newHead = curr;
				prev = curr;
				curr = curr.next;
			} else {
				if (prev != null)
					prev.next = curr.next;
				final Node<E> temp = curr.next;
				curr.next = null;
				end.next = curr;
				end = curr;
				curr = temp;
			}
		}

		if (newHead == null)
			newHead = pivot;
		newTail = end;
		return pivot;
	}

	/**
	 * @param n
	 *            no. if items to be deleted after m items..
	 * @param m
	 *            no. if items after which n items are to be deleted..
	 */
	public void deleteNAfterM(int n, int m) {
		Node<E> head = this.first;
		if (head == null)
			return;
		head = increamentMTimes(head, m);
		while (head != null) {
			head = deleteNNodes(head, n);
			head = increamentMTimes(head, m);
		}

		Node<E> t = first;
		while (t.next != null)
			t = t.next;
		this.last = t;
	}

	private Node<E> deleteNNodes(Node<E> head, int n) {
		for (; n > 0; n--)
			if (head.next != null)
				head.next = head.next.next;
			else
				return null;
		return head.next;
	}

	private Node<E> increamentMTimes(Node<E> head, int m) {
		for (; m > 1; m--) {
			if (head != null)
				head = head.next;
			else
				return null;
		}
		return head;
	}

	/**
	 * @param k
	 *            the elements to be swapped from start and end..
	 */
	public void swapKthElements(int k) {
		Node<E> ahead = this.first;
		if (ahead == null)
			return;

		ahead = increamentMTimes(ahead, k);
		// list has less elements than k
		if (ahead == null)
			return;
		final Node<E> k1 = ahead;

		// behind pointer..
		Node<E> behind = this.first;

		while (ahead.next != null) {
			ahead = ahead.next;
			behind = behind.next;
		}

		final Node<E> k2 = behind;

		swap(k1, k2);
	}

	/**
	 * @param k1
	 * @param k2
	 */
	private void swap(Node<E> k1, Node<E> k2) {
		E e = k1.e;
		k1.e = k2.e;
		k2.e = e;
	}

	/**
	 * to sort 0s, 1s & 2s..
	 * 
	 * @return
	 */
	public int[] sort012() {
		int zeroCount = 0;
		int oneCount = 0;
		int twoCount = 0;
		for (Node<E> curr = first; curr != null; curr = curr.next)
			if (curr.e.equals(0))
				zeroCount++;
			else if (curr.e.equals(1))
				oneCount++;
			else
				twoCount++;

		return new int[] { zeroCount, oneCount, twoCount };
	}

	public SingleLinkedList<E> union(SingleLinkedList<E> list1,
			SingleLinkedList<E> list2) {

		SingleLinkedList<E> result = new SingleLinkedList<E>();

		for (Node<E> curr = list1.first; curr != null; curr = curr.next)
			result.add(curr.e);
		for (Node<E> curr = list2.first; curr != null; curr = curr.next)
			if (!result.isPresent(curr.e))
				result.add(curr.e);
		return result;
	}

	private boolean isPresent(E e) {
		for (Node<E> curr = first; curr != null; curr = curr.next)
			if (curr.e.equals(e))
				return true;
		return false;
	}

	public SingleLinkedList<E> intersection(SingleLinkedList<E> list1,
			SingleLinkedList<E> list2) {
		SingleLinkedList<E> result = new SingleLinkedList<E>();
		for (Node<E> curr = list1.first; curr != null; curr = curr.next)
			if (list2.isPresent(curr.e))
				result.add(curr.e);
		return result;
	}

	public void mergeSort() {
		mergeSort(this.first);
	}

	private void mergeSort(Node<E> head) {
		if (head == null || head.next == null)
			return;

		Node<E> a = null;
		Node<E> b = null;

		Node<E>[] t = frontBackSplit(head, a, b);
		mergeSort(t[0]);
		mergeSort(t[1]);

		this.first = mergeSorted(t[0], t[1]);
	}

	private Node<E> mergeSorted(Node<E> a, Node<E> b) {
		Node<E> result = null;
		if (a == null)
			return b;
		if (b == null)
			return a;
		if (a.e.compareTo(b.e) <= 0) {
			result = a;
			result.next = mergeSorted(a.next, b);
		} else {
			result = b;
			result.next = mergeSorted(a, b.next);
		}
		return result;
	}

	private Node<E>[] frontBackSplit(Node<E> source, Node<E> frontEnd,
			Node<E> backEnd) {
		@SuppressWarnings("unchecked")
		Node<E>[] t = new Node[2];
		frontEnd = source;
		// length less than 2..
		if (source == null || source.next == null)
			backEnd = null;
		else {
			Node<E> fast = source.next;
			Node<E> slow = source;
			/* Advance 'fast' two nodes, and advance 'slow' one node */
			while (fast != null) {
				fast = fast.next;
				if (fast != null) {
					slow = slow.next;
					fast = fast.next;
				}
			}
			backEnd = slow.next;
			slow.next = null;
		}
		t[0] = frontEnd;
		t[1] = backEnd;
		return t;
	}

	public TreeNode<E> toTree() {
		return sortedListToBST(this.first, size);
	}

	private TreeNode<E> sortedListToBST(Node<E> head, int n) {
		if (n <= 0)
			return null;
		TreeNode<E> left = sortedListToBST(head, n / 2);
		final TreeNode<E> root = new TreeNode<E>(head.e, left, null);
		head = head.next;
		root.right = sortedListToBST(head, n - (n / 2) - 1);
		return root;
	}

	private static class TreeNode<E> {
		E e;
		TreeNode<E> left;
		TreeNode<E> right;

		/**
		 * @param e
		 * @param left
		 * @param right
		 */
		public TreeNode(E e, TreeNode<E> left, TreeNode<E> right) {
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
			return "TreeNode [e=" + e + ", left=" + leftE + ", right=" + rightE
					+ "]";
		}
	}

	public SingleLinkedList<Integer> addTwoList(
			SingleLinkedList<Integer> first, SingleLinkedList<Integer> second) {
		SingleLinkedList<Integer> result = new SingleLinkedList<Integer>();

		Node<Integer> firstHead = first.first;
		Node<Integer> secondHead = second.first;
		int sum = 0, carry = 0;
		;
		while (firstHead != null && second != null) {
			sum = carry + (firstHead == null ? 0 : firstHead.e)
					+ (secondHead == null ? 0 : secondHead.e);
			carry = sum / 10;
			sum = sum % 10;
			result.add(sum);
			if (firstHead != null)
				firstHead = firstHead.next;
			if (secondHead != null)
				secondHead = secondHead.next;
		}
		if (carry != 0)
			result.add(carry);

		return result;
	}

	public SingleLinkedList<E> createLoopedLinkedList(E[] e) {
		SingleLinkedList<E> linkedList = new SingleLinkedList<E>();
		for (E e2 : e)
			linkedList.add(e2);

		linkedList.last.next = linkedList.first.next.next;

		return linkedList;
	}

	public void deleteLoop() {
		Node<E> fast = this.first;
		Node<E> slow = this.first;

		while (slow != null && fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;

			if (fast.e.equals(slow.e)) {
				removeLoop(fast);
				break;
			}
		}
	}

	private void removeLoop(Node<E> fast) {
		int loopLength = loopLength(fast);
		Node<E> t = this.first;
		Node<E> k = nodeAtIndex(loopLength);
		Node<E> head = null;
		while (!t.e.equals(k.e)) {
			t = t.next;
			k = k.next;
		}
		head = k;

		while (!k.next.e.equals(head.e))
			k = k.next;
		k.next = null;
	}

	private Node<E> nodeAtIndex(int loopLength) {
		Node<E> t = this.first;
		while (loopLength-- > 0)
			t = t.next;
		return t;
	}

	private int loopLength(final Node<E> fast) {
		int count = 1;
		E e = fast.e;
		Node<E> t = fast.next;
		while (!t.e.equals(e)) {
			count++;
			t = t.next;
		}
		return count;
	}

	public void deleteLesserNodes() {
		first = reverseList(this.first, size);

		System.out.println(this);
		deleteNodes(this.first);
		System.out.println(this);
		first = reverseList(this.first, size);
	}

	private void deleteNodes(Node<E> head) {
		Node<E> maxNode = head;
		Node<E> curr = head;
		Node<E> t = null;
		while (curr != null && curr.next != null) {
			t = curr.next;
			if (t.e.compareTo(maxNode.e) < 0)
				curr.next = t.next;
			else {
				curr = curr.next;
				maxNode = curr;
			}
		}
	}
	
	private Node<E> reverseList(Node<E> head, int k) {
		Node<E> curr = head;
		Node<E> prev = null;
		Node<E> next = null;
		int count = 0;
		while (curr != null && count < k) {
			next = curr.next; // to save next pointer..
			curr.next = prev; // change next to prev to reverse..
			prev = curr; // move prev ahead..
			curr = next; // move curr ahead..
			count++;
		}
		if (next != null)
			head.next = reverseList(next, k); // make head.next to next part of
												// reversed list..
		return prev;
	}
	
	public void reverseAltKNodes(int k) {
		Node<E> front = this.first;
		Node<E> behind = increamentMTimes(front, k);
		while (behind != null) {
			swap(front, behind);
			behind = behind.next;
			behind = increamentMTimes(behind, k);
			if (behind == null)
				return;
			front = behind.next;
			behind = behind.next;
			behind = increamentMTimes(behind, k);
		}
	}

	public void reverseList(int i) {
		Node<E> t = this.first;
		final Node<E> f = t = reverseList(t, i);
		this.first = f;
	}

	public boolean areIdentical(Node<E> first, Node<E> second) {
		if (first == null && second == null)
			return true;
		if (first == null && second != null)
			return false;
		if (first != null && second == null)
			return false;
		if (!first.e.equals(second.e))
			return false;
		return areIdentical(first.next, second.next);

	}

	public SingleLinkedList<E> splitAlt() {
		Node<E> t = this.first;
		Node<E> prev = this.first;
		SingleLinkedList<E> result = new SingleLinkedList<E>();
		while (t != null && t.next != null) {
			t = t.next;
			result.add(t.e);
			prev.next = t.next;
			size--;
			prev = t.next;
			t = t.next;
		}
		return result;
	}

	public void deleteAlt(Node<E> head) {
		if (head == null)
			return;
		Node<E> next = head.next;
		if (next == null)
			return;
		// delete next node..
		head.next = next.next;
		deleteAlt(head.next);
	}

	public void pairWiseSwap(Node<E> head) {
		if (head != null && head.next != null) {
			swap(head, head.next);
			pairWiseSwap(head.next.next);
		}
	}

	public void setLastLink() {
		Node<E> t = this.first;
		while (t != null && t.next != null)
			t = t.next;
		this.last = t;
	}

	public void moveLastToFirst() {
		final E e = unlinkLast();
		addFirst(e);
	}

	public void printReverse(Node<E> head) {
		if (head == null)
			return;
		printReverse(head.next);
		System.out.print(head.e + " ");
	}

	public void intersectWith(SingleLinkedList<E> linkedList) {
		this.last.next = linkedList.first.next.next.next.next.next;
	}

	public Node<E> intersectingNode(SingleLinkedList<E> linkedList) {
		int c1 = count(this);
		int c2 = count(linkedList);
		if (c1 > c2)
			return intersectingNode(c1 - c2, this.first, linkedList.first);
		else
			return intersectingNode(c2 - c1, linkedList.first, this.first);
	}

	private Node<E> intersectingNode(int i, Node<E> bHead, Node<E> sHead) {
		while (i-- > 0)
			// moving bigger list ahead i times..
			bHead = bHead.next;

		while (bHead != null && sHead != null) {
			if (bHead == sHead)
				return bHead;
			bHead = bHead.next;
			sHead = sHead.next;
		}
		return null;
	}

	private int count(SingleLinkedList<E> singleLinkedList) {
		int count = 0;
		Node<E> t = singleLinkedList.first;
		while (t != null) {
			count++;
			t = t.next;
		}
		return count;
	}

	public boolean isPalendromeUsingStack() {
		final DoubleLinkedList<E> stack = new DoubleLinkedList<E>();

		for (Node<E> curr = first; curr != null; curr = curr.next)
			stack.addFirst(curr.e);
		for (Node<E> curr = first; curr != null; curr = curr.next)
			if (!curr.e.equals(stack.removeFirst()))
				return false;
		return true;
	}

	public boolean isPalendromeUsingReverse() {
		Node<E> fast = this.first;
		Node<E> slow = this.first;
		Node<E> prevSlow = null;
		Node<E> midNode = null;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			prevSlow = slow; // to get first half of list..
			slow = slow.next;
		}

		// fast only becomes null if there are even no. of elements..
		if (fast != null) {
			midNode = slow;
			slow = slow.next;
		}
		System.out.println("mid = " + midNode);
		Node<E> secondHalf = slow;
		prevSlow.next = null; // end first half..

		secondHalf = reverseList(secondHalf, size >> 1);

		System.out.println(secondHalf);
		boolean res = compareLists(this.first, secondHalf);

		// restore main list..
		secondHalf = reverseList(secondHalf, size >> 1);
		if (midNode != null) {
			prevSlow.next = midNode;
			midNode.next = secondHalf;
		} else
			prevSlow.next = secondHalf;

		return res;
	}

	private boolean compareLists(Node<E> firstHalf, Node<E> secondHalf) {

		while (firstHalf != null && secondHalf != null)
			if (!firstHalf.e.equals(secondHalf.e))
				return false;
			else {
				firstHalf = firstHalf.next;
				secondHalf = secondHalf.next;
			}
		return true;
	}

	public boolean isPalendromeUsingRecur() {
		this.left = this.first;
		return isPalendromeUsingRecur(this.first);
	}

	private Node<E> left = null;;

	private boolean isPalendromeUsingRecur(Node<E> right) {
		System.out.println(right);
		if (right == null)
			return true;
		boolean res = isPalendromeUsingRecur(right.next);
		// sub lists are not palendrome, return false, no need to go further..
		if (res == false)
			return false;
		boolean b = left.e.equals(right.e);
		left = left.next;
		return b;
	}

	public void reverseRecur() {
		this.last = this.first;
		reverseRecur(this.last);
	}

	private void reverseRecur(Node<E> head) {
		if (head == null)
			return;
		Node<E> first = head;
		Node<E> rest = first.next;

		if (rest == null) {
			this.first = first;
			return;
		}

		reverseRecur(rest);

		first.next.next = first;
		first.next = null;

		head = rest;
	}
	
	/**
	 * Rotate the linked list counter-clockwise by k nodes. Where k is a given
	 * positive integer. For example, if the given linked list is
	 * 10->20->30->40->50->60 and k is 4, the list should be modified to
	 * 50->60->10->20->30->40. Assume that k is smaller than the count of nodes
	 * in linked list.
	 * 
	 * To rotate the linked list, we need to change next of kth node to NULL,
	 * next of last node to previous head node, and finally change head to
	 * (k+1)th node. So we need to get hold of three nodes: kth node, (k+1)th
	 * node and last node. Traverse the list from beginning and stop at kth
	 * node. Store pointer to kth node. We can get (k+1)th node using
	 * kthNode->next. Keep traversing till end and store pointer to last node
	 * also. Finally, change pointers as stated above.
	 * 
	 * @param k
	 */
	public void rotate(final int k) {
		
		if (k == 0) return;
		
		final Node<E> f = this.first;
		Node<E> temp = this.first;
		
		for(int i = 0; i < k-1; i++) {
			temp = temp.next; // reach to Kth node
		}
		this.first = temp.next; // make (k+1) as first node
		temp.next = null; // make temp's next as null, now it is end of list
		this.last.next = f; // change last node's next to previous first node
		this.last = temp; // kth node is last node now
	}
	
	/**
	 * remove last occurrence of e from list.
	 * 
	 * @param e element to be deleted.
	 */
	public void deleteLast(final E e) {
		
		if (this.first == null || e == null) return;
		
		Node<E> f = this.first;
		
		Node<E> n = null;
		
		while (f != null) {
			if (f.next != null && Objects.equals(f.next.e, e))
				n = f;
			f = f.next;
		}
		
		// if n is not null, then f is to be deleted node
		if (n != null) {
			Node<E> t = n.next;
			n.next = n.next.next;
			t.next = null; // remove t
			t.e = null;
			size--;
		} else {
			if (Objects.equals(this.first.e, e)) {
				Node<E> t = this.first.next;
				this.first = this.first.next;
				t.next = null;
				t.e = null;
				size--;
			}
		}
	}
}