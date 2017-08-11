/**
 * 
 */
package stack;

/**
 * @author apple
 * 
 */
public class QueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// CircularQueue<Integer> circularQueue = new CircularQueue<Integer>();
		ArrayPriorityQueue<Integer> circularQueue = new ArrayPriorityQueue<Integer>();
		circularQueue.offer(128);
		circularQueue.offer(121);
		circularQueue.offer(122);
		System.out.println(circularQueue);
		circularQueue.poll();
		circularQueue.poll();
		System.out.println(circularQueue);
		circularQueue.offer(213);
		circularQueue.offer(123);
		circularQueue.offer(231);
		circularQueue.offer(234);
		System.out.println(circularQueue);

	}

}
