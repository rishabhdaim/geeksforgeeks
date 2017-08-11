/**
 * 
 */
package stack;

/**
 * @author apple
 * 
 */
public interface Deque<E> extends Queue<E> {

	boolean addFirst(E e);

	E pollLast();

	E peekLast();
}
