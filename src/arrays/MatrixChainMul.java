/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class MatrixChainMul {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Integer[] i = { 10, 20, 30, 40, 30 };

		final Array<Integer> array = new Array<Integer>(i);

		int max = array.matrixChainOrder();

		System.out.println(max);

		max = array.matrixChainOpt();
		System.out.println(max);
		
		array.palindromePartitioning("ababbbabbababa".toCharArray());
		
		array.partioningProblem();
		
		array.lps("BBABCBCAB");
		
		int result = array.knapSack(new int[]{60, 100, 120}, new int[]{10, 20, 30}, 50);
		System.out.println("Knapsack is : " + result);
		
		int maxCoins = array.optimalStrategyOfGame(new int[]{8, 15, 3, 7});
		System.out.println("Max coins : " + maxCoins);
		
		array.stockSpanProblem();
		
		array.assemblyLineScheduling(new int[][]{{4, 5, 3, 2},
                {2, 10, 1, 4}}, new int[][]{{0, 7, 4, 5},
                        {0, 9, 2, 8}}, new int[]{10, 12}, new int[]{18, 7});
	}
}