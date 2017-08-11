/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class NonRepeatingString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("xbanaxnabs");
		String str = demoString.nonRepeatingString();
		System.out.println(str);
		str = demoString.nonRepeatingStrOpt();
		System.out.println(str);
		demoString.nonRepeatStrDll();
	}
}