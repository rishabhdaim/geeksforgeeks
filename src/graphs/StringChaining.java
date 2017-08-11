/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class StringChaining {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] arr = { "for", "geek", "rig", "kaf" };

		int n = arr.length;

		DGraph<Character> dGraph = new DGraph<Character>(26);

		for (int i = 0; i < 26; i++)
			dGraph.addVertex((char) ('a' + i));
		boolean b = dGraph.canBeChained(n, arr);

		System.out.println(b);
	}
}