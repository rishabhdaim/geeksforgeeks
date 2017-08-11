/**
 * 
 */
package linkedlists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author aa49442
 * 
 */
public class DoubleLinkedList<E extends Comparable<? super E>> implements Iterable<E>{

	private Node<E> first;
	private Node<E> last;
	// no if elements in this list..
	private int size;

	public E head() {
		final Node<E> f = first;
		if (f == null)
			return null;
		else
			return f.e;
	}

	public E tail() {
		final Node<E> l = last;
		if (l == null)
			return null;
		else
			return l.e;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public DoubleLinkedList() {
		first = last = null;
		size = 0;
	}

	public void add(E e) {
		linkLast(e);
	}

	public Node<E> addLast(E e) {
		return linkLast(e);
	}

	public Node<E> addFirst(E e) {
		return linkFirst(e);
	}

	public void push(E e) {
		if (size < 2)
			linkFirst(e);
		else
			linkAfter(e, getMiddle());
	}

	/**
	 * @param e
	 */
	public void offer(E e) {
		linkLast(e);
	}

	/**
	 * @return
	 */
	public E poll() {
		return removeFirst();
	}

	/**
	 * @return
	 */
	public E peek() {
		return getFirst();
	}

	public E peekFirst() {
		return getFirst();
	}

	public E peekLast() {
		return getLast();
	}

	private E getFirst() {
		final Node<E> f = first;
		if (f == null)
			throw new NoSuchElementException();
		return f.e;
	}

	private E getLast() {
		final Node<E> l = last;
		if (l == null)
			throw new NoSuchElementException();
		return l.e;
	}

	/**
	 * @return middle element
	 */
	public E pop() {
		if (size > 1)
			if (size % 2 == 0)
				return unLink(getMiddle());
			else
				return unLink(getMiddle().next);
		else
			return unLinkLast(last);
	}

	public E removeFirst() {
		final Node<E> f = first;
		if (f == null)
			throw new NoSuchElementException();
		return unLinkFirst(f);
	}

	public E removeLast() {
		final Node<E> l = last;
		if (l == null)
			throw new NoSuchElementException();
		return unLinkLast(l);
	}

	private Node<E> getMiddle() {
		int i = size >> 1;
		Node<E> middle = first;
		for (; i > 1; i--)
			middle = middle.next;
		return middle;
	}

	private Node<E> linkFirst(E e) {
		final Node<E> f = first;
		final Node<E> newNode = new Node<E>(e, null, f);
		first = newNode;

		if (last == null)
			last = newNode;
		else
			f.prev = newNode;
		size++;
		return first;
	}

	private Node<E> linkLast(E e) {
		final Node<E> l = last;
		final Node<E> newNode = new Node<E>(e, l, null);
		last = newNode;

		if (first == null)
			first = newNode;
		else
			l.next = newNode;
		size++;
		return last;
	}

	private Node<E> linkAfter(E e, Node<E> prev) {
		final Node<E> succ = prev.next;
		final Node<E> newNode = new Node<E>(e, prev, succ);
		prev.next = newNode;

		if (succ == null)
			last = newNode;
		else
			succ.prev = newNode;
		size++;
		return newNode;
	}

	/**
	 * @param node
	 *            node to be deleted..
	 * @return
	 */
	private E unLink(Node<E> node) {
		if (node == null)
			return null;
		final E e = node.e;
		final Node<E> next = node.next;
		final Node<E> prev = node.prev;

		if (prev == null)
			first = next;
		else
			prev.next = next;

		if (next == null)
			last = prev;
		else
			next.prev = prev;

		node.next = node.prev = null;
		node.e = null;
		size--;
		return e;
	}

	private E unLinkFirst(Node<E> f) {
		final E e = f.e;
		final Node<E> next = f.next;

		if (next == null)
			last = null;
		else
			next.prev = null;
		first = next;

		f.next = null;
		f.e = null;
		size--;
		return e;

	}

	private E unLinkLast(Node<E> l) {
		final E e = l.e;
		final Node<E> prev = l.prev;

		l.e = null;
		l.prev = null;

		if (prev == null)
			first = null;
		else
			prev.next = null;
		last = prev;
		size--;
		return e;
	}

	public int size() {
		return size;
	}

	/**
	 * to sort this linkedList
	 */
	public void quickSort() {
		final Node<E> head = this.first;
		final Node<E> tail = this.last;
		quickSort(head, tail);
	}

	/**
	 * @param head
	 * @param tail
	 */
	private void quickSort(Node<E> head, Node<E> tail) {
		if (tail != null && head != null && head != tail.next) {
			Node<E> p = this.partition(head, tail);
			quickSort(head, p.prev);
			quickSort(p.next, tail);
		}

	}

	/**
	 * @param head
	 *            head of linkedList to sort
	 * @param tail
	 *            tail of linkedList to sort
	 * @return
	 */
	private Node<E> partition(Node<E> head, Node<E> tail) {
		// let take tail as pivot..
		E e = tail.e;
		// similar to i = head -1 in array..
		Node<E> i = head.prev;

		for (Node<E> curr = head; curr != tail; curr = curr.next) {
			if (curr.e.compareTo(e) <= 0) {
				// similar to i++
				i = i == null ? head : i.next;
				swap(i, curr);
			}
		}
		i = i == null ? head : i.next;
		swap(i, tail);
		return i;
	}

	private void swap(Node<E> first, Node<E> last) {
		E e = first.e;
		first.e = last.e;
		last.e = e;
	}

	public boolean isPresent(E e) {
		Node<E> t = getNode(e);
		return t == null ? false : true;
	}

	public E remove(E e) {
		return unLink(getNode(e));
	}

	public E removeNode(Node<E> node) {
		return unLink(node);
	}

	private Node<E> getNode(E e) {
		for (Node<E> curr = first; curr != null; curr = curr.next)
			if (curr.e.equals(e))
				return curr;
		return null;
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
		return "DoubleLinkedList [first=" + first + ", last=" + last
				+ ", size=" + size + ", elements = " + builder.toString() + "]";
	}

	public final static class Node<E> {
		E e;
		Node<E> prev;
		Node<E> next;

		/**
		 * @param e
		 * @param prev
		 * @param next
		 */
		public Node(E e, Node<E> prev, Node<E> next) {
			super();
			this.e = e;
			this.prev = prev;
			this.next = next;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E prevE = prev == null ? null : prev.e;
			E nextE = next == null ? null : next.e;
			return "Node [e=" + e + ", prev=" + prevE + ", next=" + nextE + "]";
		}
	}

	public Node<E> toTree() {
		int n = countList(this.first);
		return sortedListToTree(this.first, n);
	}

	private Node<E> sortedListToTree(Node<E> head, int n) {
		if (n <= 0)
			return null;
		Node<E> left = sortedListToTree(head, n / 2);
		final Node<E> root = new Node<E>(head.e, left, null);
		root.prev = left;
		head = head.next;
		root.next = sortedListToTree(head, n - (n / 2) - 1);
		return root;

	}

	private int countList(Node<E> first) {
		int count = 0;
		Node<E> t = first;
		while (t != null) {
			t = t.next;
			count++;
		}
		return count;
	}

	public void reverse() {
		Node<E> curr = this.first;
		this.last = curr;
		Node<E> temp = null;
		while (curr != null) {
			if (curr.next == null)
				this.first = curr;
			temp = curr.next;
			curr.next = curr.prev;
			curr.prev = temp;
			curr = temp;
		}
	}
	
	
	private class GetItr implements Iterator<E> {
		
		private Node<E> lastReturned;
		private Node<E> next;
		
		public GetItr() {
			
			// first is null, then list is empty
			if (first == null) {
				next = null;
			}
			
			next = first; // initialize next to first element of list
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public E next() {
			
			lastReturned = next;
			next = next.next;
			return lastReturned.e;
		}
	}

	/**
	 * Iterator to iterate this list. Remove is not supported in this
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new GetItr();
	}
}
