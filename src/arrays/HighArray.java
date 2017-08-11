/**
 * 
 */
package arrays;

/**
 * @author apple
 * 
 */
public class HighArray {
	private long[] a; // ref to array a
	private int nElems;

	public HighArray(int max) {
		a = new long[max]; // create the array
		nElems = 0; // no items yet
	}

	public boolean find(long searchKey) {
		for (int i = 0, l = a.length; i < l; i++)
			if (a[i] == searchKey)
				return true;
		return false;
	}

	public void insert(long value) {
		a[nElems++] = value;
	}

	public boolean delete(long value) {
		for (int i = 0, l = a.length; i < l; i++)
			if (a[i] == value) {
				for (int j = i; j < l - i; j++)
					a[j] = a[j + 1];
				nElems--;
				return true;
			}
		return false;
	}

	public void display() {
		for (int j = 0; j < nElems; j++)
			// for each element,
			System.out.print(a[j] + " "); // display it
		System.out.println("");
	}
}
