/**
 * 
 */
package trie;

/**
 * @author aa49442
 * 
 */
public class TernarySearchTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrieTree<Character> trieTree = new TrieTree<Character>();

		Character[] characters = new Character[] { 'c', 'a', 't' };

		trieTree.insert(characters);

		characters = new Character[] { 'c', 'a', 't', 's' };
		trieTree.insert(characters);
		characters = new Character[] { 'b', 'a', 't', 's' };
		trieTree.insert(characters);
		characters = new Character[] { 'r', 'a', 't', 's' };
		trieTree.insert(characters);

		characters = new Character[] { 'c', 'a', 't', 'e', 'r' };
		trieTree.insert(characters);
		characters = new Character[] { 'c', 'a', 't', 'e', 'r', 'e', 'r' };
		trieTree.insert(characters);
		characters = new Character[] {'b', 'u', 'g', 's'};
		trieTree.insert(characters);
	}

}
