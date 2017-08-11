/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class Palendrome {
	public static void main(String[] args) {

		DemoString demoString = new DemoString("asdsa");

		boolean b = demoString.isRotationOfPalenDrome();
		System.out.println(b);

		b = demoString.isRotationOfPalenDromeOpt();
		System.out.println(b);

		int i = demoString.longestPalendrome();
		System.out.println(i);

		int minPalPar = demoString.minPalPar();
		System.out.println(minPalPar);

		minPalPar = demoString.minPalParOpt();
		System.out.println(minPalPar);
	}
}