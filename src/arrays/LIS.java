/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class LIS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] i = new int[] { 4, 6 };
		int[] j = new int[] { 6, 9 };
		int[] k = new int[] { 7, 12 };
		int[] l = new int[] { 4, 15 };
		int[] m = new int[] { 11, 12 };
		int[] n = new int[] { 15, 25 };
		int[] o = new int[] { 12, 19 };

		int[][] arr = new int[7][];

		arr[0] = i;
		arr[1] = j;
		arr[2] = k;
		arr[3] = l;
		arr[4] = m;
		arr[5] = n;
		arr[6] = o;

		Array<Integer> array = new Array<Integer>(0);

		int mcl = array.maxChainPairs(arr);
		System.out.println(mcl);

		final Integer[] ints = new Integer[10];
		int length = ints.length;
		for (int j1 = 0; j1 < length; j1++)
			ints[j1] = (int) (Math.random() * 100);

		Array<Integer> arr1 = new Array<>(ints);

		System.out.println(arr1);

		int lis = arr1.lis(length);
		System.out.println("LIS: " + lis);

		lis = arr1.lisNLogN(length);

		System.out.println(lis);

		arr1.printLis(length);
		int lds = arr1.lds(length);
		System.out.println("LDS : " + lds);

		lds = arr1.ldsNLogN(length);
		System.out.println(lds);

		arr1.printLds(length);
		int b = arr1.bitonic(length);
		System.out.println(b);

		int msis = arr1.msis(length);
		System.out.println("MSIS : " + msis);

		int msds = arr1.msds(length);
		System.out.println( "MSDS : " + msds);
		
		int minSumConsecutive3 = arr1.minSumOfConsecutive3();
		System.out.println("minSumConsecutive3 : " + minSumConsecutive3);
		
		int lcs = arr1.lcs("AGGTAB".toCharArray(), "GXTXAYB".toCharArray());
		System.out.println("lcs : " + lcs);
		lcs = arr1.lcs("ABCDGH".toCharArray(), "AEDFHR".toCharArray());
		System.out.println("lcs : " + lcs);
		lcs = arr1.lcs("ABCDGH".toCharArray(), "ABCDGH".toCharArray());
		System.out.println("lcs : " + lcs);
		
		int lrs = arr1.lrs("ABCDGH".toCharArray(), "ABCDGH".toCharArray());
		System.out.println("lrs : " + lrs);
		lrs = arr1.lrs("AABEBCDD".toCharArray(), "AABEBCDD".toCharArray());
		System.out.println("lrs : " + lrs);
		lrs = arr1.lrs("axxxy".toCharArray(), "axxxy".toCharArray());
		System.out.println("lrs : " + lrs);
		lrs = arr1.lrs("aabb".toCharArray(), "aabb".toCharArray());
		System.out.println("lrs : " + lrs);
		
		String lrNonOverlapSubSeq = arr1.lrNonOverlappingSubSeq("aab".toCharArray(), "aab".toCharArray());
		System.out.println("lrNonOverlapSubSeq : " + lrNonOverlapSubSeq);
		lrNonOverlapSubSeq = arr1.lrNonOverlappingSubSeq("geeksforgeeks".toCharArray(), "geeksforgeeks".toCharArray());
		System.out.println("lrNonOverlapSubSeq : " + lrNonOverlapSubSeq);
	}
}