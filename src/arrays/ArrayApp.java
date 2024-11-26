/**
 * 
 */
package arrays;

/**
 * @author apple
 * 
 */
public class ArrayApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long[] arr; // reference to array
		arr = new long[100]; // make array
		int nElems = 0; // number of items
		int j; // loop counter
		long searchKey; // key of item to search for
		// --------------------------------------------------------------
		arr[0] = 77; // insert 10 items
		arr[1] = 99;
		arr[2] = 44;
		arr[3] = 55;
		arr[4] = 22;
		arr[5] = 88;
		arr[6] = 11;
		arr[7] = 00;
		arr[8] = 66;
		arr[9] = 33;
		nElems = 10; // now 10 items in array
		// --------------------------------------------------------------
		for (j = 0; j < nElems; j++)
			// display items
			System.out.print(arr[j] + " ");
		System.out.println("");
		// --------------------------------------------------------------j
		searchKey = 66; // find item with key 66
		for (j = 0; j < nElems; j++)
			// for each element,
			if (arr[j] == searchKey) // found item?
				break; // yes, exit before endj
		if (j == nElems) // at the end?
			System.out.println("Can't find " + searchKey); // yes
		else
			System.out.println("Found " + searchKey); // no
		// --------------------------------------------------------------
		searchKey = 55;
		for (int i = 0; i < nElems; i++)
			if (arr[i] == searchKey) {
				for (int j1 = i; j1 < nElems - 1; j1++)
					arr[j1] = arr[j1 + 1];
				nElems--;
			}
		// --------------------------------------------------------------
		for (j = 0; j < nElems; j++)
			// display items
			System.out.print(arr[j] + " ");
		System.out.println("");
	}
}
