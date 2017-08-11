/**
 * 
 */
package arrays;

import graphs.Job;

/**
 * @author rishabh.daim
 *
 */
public class JobSequencing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Job[] jobs = new Job[5];
		
		jobs[0] = new Job("A", 2, 100);
		jobs[1] = new Job("B", 1, 19);
		jobs[2] = new Job("C", 2, 27);
		jobs[3] = new Job("D", 1, 25);
		jobs[4] = new Job("E", 3, 15);
		
		Array<Integer> arr = new Array<>(0);
		
		arr.printJobSequence(jobs);
	}

}
