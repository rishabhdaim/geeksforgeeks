/**
 * 
 */
package trie;

import java.io.IOException;

/**
 * @author aa49442
 * 
 */
public class LongestPrefixMatching {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String[] words = { "are", "area", "base", "cat", "cater", "children", "basement" };
		Trie<Character> trie = new Trie<Character>();
		Character[] ch;
		char[] cs;
		for (String string : words) {
			cs = string.toCharArray();
			ch = new Character[cs.length];
			for (int i = 0; i < cs.length; i++) ch[i] = cs[i];
			trie.insert(ch);
		}
		System.out.println(trie);

		words = new String[] { "caterer", "basement", "basemexz", "xyz", "cater", "arex", "child3renwer" };
		for (String string : words) {
			cs = string.toCharArray();
			ch = new Character[cs.length];
			for (int i = 0; i < cs.length; i++) ch[i] = cs[i];
			System.out.println(string + " --- " + trie.longestString(ch));
		}
	}
}