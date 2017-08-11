/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class Atoi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("@12345");
		int i = demoString.atoi();
		System.out.println(i);
	}
}