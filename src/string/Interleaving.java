/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class Interleaving {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("as");
		String str = "zx";

		demoString.interleavingString(str);
		boolean b = demoString.isInterLeaved("asdf", "zxcv", "zaxscdvf");
		System.out.println(b);

		b = demoString.isInterLeavedOpt("as", "zx", "zasx");
		System.out.println(b);
	}
}