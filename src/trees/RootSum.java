/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class RootSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 50; i++)
			list.add((int) (Math.random() * 100));
		System.out.println();
		boolean b = list.rootToLeafSum(list.root(), 345);
		System.out.println(b);
	}
}