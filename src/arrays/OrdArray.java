/**
 * 
 */
package arrays;

/**
 * @author apple
 * 
 */
public class OrdArray {

	private long[] a; // ref to array a
	private int nElems;

	public OrdArray(int max) {
		a = new long[max]; // create array
		nElems = 0;
	}

	public int size() {
		return nElems;
	}

	public int find(long searchKey) {
		return find(searchKey, 0, nElems - 1);
	}

	private int find(long searchKey, int leftEnd, int rightEnd) {
		if (leftEnd > rightEnd)
			return -1;
		int middle = (leftEnd + rightEnd) >> 1;
		if (a[middle] == searchKey)
			return middle;
		else if (a[middle] < searchKey)
			return find(searchKey, middle + 1, rightEnd);
		else
			return find(searchKey, leftEnd, middle - 1);
	}

	public void insert(long value) {
		int i = 0;
		while (i < nElems && a[i] < value)
			i++;

		for (int k = nElems; k > i; k--)
			// move bigger ones up
			a[k] = a[k - 1];
		a[i] = value; // insert it
		nElems++;
	}

	public boolean delete(long value) {
		int j = find(value);
		if (j < 0) // canÕt find it
			return false;
		else // found it
		{
			for (int k = j; k < nElems; k++)
				// move bigger ones down
				a[k] = a[k + 1];
			nElems--; // decrement size
			return true;
		}
	}

	public void display() {
		for (int j = 0; j < nElems; j++)
			// for each element,
			System.out.print(a[j] + " "); // display it
		System.out.println("");
	}
}