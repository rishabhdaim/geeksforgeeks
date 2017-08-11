/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class SumProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] i = new Integer[40];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);

		Array<Integer> arr = new Array<Integer>(i);

		System.out.println(arr);
		int j = arr.smallestSubArrWithSumGreater(150);
		System.out.println(j);
		arr.smallestSubArrWithSumEqual(23);

		arr.quickSort();
		System.out.println(arr);
		arr.sumEqualTo4Numbers(125);

		i = new Integer[] { -1, -2, -18, 23, -24, 14, 2, -31, 1, -8, 16, -6, 18, -5, 4, 10 };
		boolean b = arr.zeroSumArr();
		System.out.println(b);
		arr = new Array<Integer>(i);
		int sum = arr.maxSubArrSumWithDnC(0, i.length - 1);
		System.out.println(sum);

		sum = arr.maxSubArrSumWithKadaneAlgo(i.length - 1);
		System.out.println(sum);

		sum = arr.maxSubArrSum(i.length - 1);
		System.out.println(sum);

		sum = arr.maxSubArrCircular(i.length - 1);
		System.out.println("circular sum is : " + sum);

		int prod = arr.maxSubArrProd(i.length - 1);
		System.out.println(prod);

		arr.sortedSubSequenceOf3(i.length);
		System.out.println();
		arr.midSubSequenceOf3(i.length);

		System.out.println("-----------------------");

		i = new Integer[10];
		for (int j1 = 0; j1 < i.length; j1++)
			i[j1] = (int) (Math.random() * 20);

		arr = new Array<Integer>(i);

		System.out.println(arr);

		sum = arr.maxSumWithOutAdj();
		System.out.println(sum);

		sum = arr.smallestNotPossibleSum();

		System.out.println("not possible sum is : " + sum);
	}
}