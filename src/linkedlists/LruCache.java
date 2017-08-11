/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class LruCache<E extends Comparable<? super E>> {

	private DoubleLinkedList<E> doubleLinkedList = new DoubleLinkedList<E>();
	private int cacheSize;

	/**
	 * @param cacheSize
	 */
	public LruCache(int cacheSize) {
		super();
		this.cacheSize = cacheSize;
	}

	/**
	 * @param e
	 *            element to be added into cache..
	 */
	public void add(E e) {
		if (e == null)
			return;
		if (doubleLinkedList.isPresent(e))
			doubleLinkedList.remove(e);
		if (cacheSize <= doubleLinkedList.size())
			doubleLinkedList.removeLast();
		doubleLinkedList.addFirst(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LruCache [doubleLinkedList=" + doubleLinkedList.toString()
				+ ", cacheSize=" + cacheSize + "]";
	}
}
