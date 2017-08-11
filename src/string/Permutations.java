/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class Permutations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("");

		demoString.printMsg(new int[] { 2, 3, 4 });
		demoString = new DemoString("zxcd");
		demoString.sortedPermutations();
		demoString.permutations();

		int rank = demoString.lexoGraphicOrder();
		System.out.println(rank);

		rank = demoString.lexoGraphicOpt();
		System.out.println(rank);

		demoString.printAllPermutations();
	}
}