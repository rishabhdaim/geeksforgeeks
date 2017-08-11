/**
 * 
 */
package arrays;

/**
 * This is minimum heap..
 * 
 * @author aa49442
 * 
 */
public class Heap<E extends Comparable<? super E>> {

	private Node<E>[] arr;
	private int size;

	@SuppressWarnings("unchecked")
	public Heap() {
		arr = new Node[20];
		size = 0;
	}

	public E minElement() {
		return pop();
	}

	public Node<E> root() {
		return arr[0];
	}

	public void add(final E e, int rI, int cI) {
		Node<E> node = new Node<E>(e, rI, cI);

		int i = size;
		if (i >= arr.length)
			growUp();
		size = i + 1;
		if (i == 0)
			arr[0] = node;
		else
			siftUp(i, node);
	}

	public E replaceRoot(E e, int rI, int cI) {
		E result = arr[0].e;
		arr[0] = new Node<E>(e, rI, cI);
		siftDown(0, arr[0]);
		return result;
	}

	public void remove(E e) {
		int i = indexOf(e);
		if (i == -1)
			return;
		else
			removeAt(i);
	}

	private void removeAt(int i) {
		int s = --size;
		if (s == i) // remove last element..
			arr[i] = null;
		else {
			Node<E> last = arr[s];
			arr[s] = null;
			siftDown(i, last);
			if (arr[i] == last) // element didn't siftDown then move Up..
				siftUp(i, last);
		}
	}

	// remove first element..
	public E poll() {
		if (size == 0)
			return null;
		int s = --size;
		Node<E> last = arr[s];
		E result = arr[0].e;
		arr[s] = null;
		if (s != 0)
			siftDown(0, last);
		return result;
	}

	public E pop() {
		if (size == 0)
			return null;
		return arr[0].e;
	}

	private int indexOf(E e) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i].e.equals(e))
				return i;
		return -1;
	}

	private void siftUp(int i, Node<E> n) {
		while (i > 0) {
			int p = (i - 1) >>> 1;

			if (n.compareTo(arr[p]) >= 0)
				break;
			arr[i] = arr[p];
			i = p;
		}
		arr[i] = n;
	}

	private void siftDown(int i, Node<E> n) {

		final int half = size >>> 1;
		while (i < half) {
			int child = (i << 1) + 1; // assume left child is smallest
			int right = child + 1;
			Node<E> c = arr[child];
			if (right < size && arr[child].compareTo(arr[right]) > 0)
				c = arr[child = right];

			if (n.compareTo(c) <= 0)
				break;
			arr[i] = c;
			i = child;
		}
		arr[i] = n;
	}

	public void heapify() {
		for (int i = (size >>> 1) - 1; i >= 0; i--)
			siftDown(i, arr[i]);
	}

	public void buildHeapArr(E e, int rI, int cI) {
		if (size >= arr.length)
			growUp();
		arr[size++] = new Node<E>(e, rI, cI);
	}

	private void growUp() {
		@SuppressWarnings("unchecked")
		Node<E>[] newArr = new Node[2 * arr.length];

		for (int i = 0; i < arr.length; i++)
			newArr[i] = arr[i];
		arr = newArr;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}

	static class Node<E extends Comparable<? super E>> implements Comparable<Node<E>> {
		E e;
		int rIndex;
		int cIndex;

		/**
		 * @param e
		 * @param rIndex
		 * @param cIndex
		 */
		public Node(E e, int rIndex, int cIndex) {
			super();
			this.e = e;
			this.rIndex = rIndex;
			this.cIndex = cIndex;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Node [e=" + e + ", rIndex=" + rIndex + ", cIndex=" + cIndex
					+ "]";
		}

		@Override
		public int compareTo(Node<E> o) {
			return this.e.compareTo(o.e);
		}
	}
}