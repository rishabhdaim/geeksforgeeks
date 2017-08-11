/**
 * 
 */
package stack;

/**
 * @author apple
 * 
 */
public class Reverser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = "Rishabh Daim";
		ArrayStack<Character> arrayStack = new ArrayStack<Character>();

		for (int i = 0; i < name.length(); i++)
			arrayStack.push(name.charAt(i));
		
		//System.out.println(arrayStack.size());
		char[] cs = new char[name.length()];
		int i = 0;
		while (!arrayStack.isEmpty())
			cs[i++] = arrayStack.pop();
		name = new String(cs);
		System.out.println(name);
	}

}
