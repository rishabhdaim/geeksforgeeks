/**
 * 
 */


/**
 * @author apple
 * 
 */
public class Recursion {

	private Recursion() {
	}

	public static int triangleLoop(int n) {
		int total = 0;
		while (n > 0)
			total = total + n--;
		return total;
	}

	public static int triangleRecursive(int n) {
		System.out.println("Entering: n= " + n);
		if (n == 1) {
			System.out.println("Returning 1");
			return 1;
		} else {
			int temp = n + triangleRecursive(n - 1);
			System.out.println("Returning " + temp);
			return temp;
		}
	}

	public static long factorialRecursive(int n) {
		System.out.println("Entering n= " + n);
		if (n == 0) {
			System.out.println("Returning 1");
			return 1;
		} else {
			long l = n * factorialRecursive(n - 1);
			System.out.println("Returning " + l);
			return l;
		}
	}

	public static void doTowers(int topN, char from, char inter, char to) {
		if (topN == 1)
			System.out.println("Disk 1 from " + from + " to " + to);
		else {
			doTowers(topN - 1, from, to, inter);
			System.out.println("Disk " + topN + " from " + from + " to " + to);
			doTowers(topN - 1, inter, to, from);
		}
	}
}