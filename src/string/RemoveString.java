/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class RemoveString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("abcddcab");
		// demoString.removeDup();

		System.out.println(demoString);

		// demoString.filterStr('b', 'a', 'c');

		String str = "This is string from which characters are to be removed";
		String mask = "Aasdfetir ";

		str = demoString.removeCharacter(str, mask);
		System.out.println(str);
	}
}