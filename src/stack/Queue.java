/**
 * 
 */
package stack;

/**
 * @author apple
 * 
 */
public interface Queue<E> {

	boolean offer(E e);

	E poll();

	E peek();

	int size();

	boolean isEmpty();

	boolean search(E e);
}
