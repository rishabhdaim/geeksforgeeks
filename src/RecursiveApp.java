/**
 * 
 */


/**
 * @author apple
 * 
 */
public class RecursiveApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Recursion.triangleRecursive(12));
		System.out.println();
		System.out.println(Recursion.triangleLoop(12));
		System.out.println();
		System.out.println(Recursion.factorialRecursive(13));
		System.out.println();
		Recursion.doTowers(3, 'A', 'B', 'C');
	}
}