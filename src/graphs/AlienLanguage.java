/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class AlienLanguage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] words = { "caa", "aaa", "aab" };

		int n = words.length;

		DGraph<Character> dGraph = new DGraph<Character>(26);

		for (int i = 0; i < 26; i++)
			dGraph.addVertex((char) ('a' + i));

		dGraph.alienLang(n, words);
	}
}