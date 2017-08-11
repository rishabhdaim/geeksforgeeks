/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class Transform {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("a1b2c3d4e5f6g7h8i9j1k2l3m4");
		demoString.transform();
		
		demoString = new DemoString("GeeksForGeeks");
		demoString.printWaveForm(2);
		System.out.println();
		demoString.printWaveForm(4);
	}
}