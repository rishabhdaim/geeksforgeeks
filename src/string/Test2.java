/**
 * 
 */
package string;

class Test1 {
	Test1(int x) {
		System.out.println("Constructor called " + x);
	}
}

// This class contains an instance of Test1
class Test2 {
	Test1 t1 = new Test1(10);

	Test2(int i) {
		t1 = new Test1(i);
	}

	public static void main(String[] args) {
		new Test2(5);
	}
}