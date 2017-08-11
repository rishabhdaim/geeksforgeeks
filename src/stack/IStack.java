/**
 * 
 */
package stack;

/**
 * @author apple
 * 
 */
public interface IStack<E> {

	void push(E e);

	E pop();

	E peek();

	boolean isEmpty();

	int size();

	boolean search(E e);
}
