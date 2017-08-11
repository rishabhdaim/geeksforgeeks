/**
 * 
 */
package arrays;

/**
 * @author Rishabh.Daim
 *
 */
public class CatalanNumber {
	
	public static void main(String[] args) {
		
		final int n = 100;
		
		Array<Integer> array = new Array<>(0);
		
		System.out.println(array.catalanNumber(n));
		
		System.out.println(array.binomialCoefficientDP(4, 2));
		System.out.println(array.binomialCoefficientDP(5, 2));
		
		System.out.println(array.binomialCoefficientSpaceEfficient(4, 2));
		System.out.println(array.binomialCoefficientSpaceEfficient(5, 2));
		
		System.out.println(array.bCSpaceTimeEfficient(4, 2));
		System.out.println(array.bCSpaceTimeEfficient(5, 2));
		System.out.println(array.bCSpaceTimeEfficient(50, 30));
		
		System.out.println("coin change");
		System.out.println(array.coinChange(new int[]{1,2,5}, 20));
		System.out.println(array.coinChange(new int[]{1,3,9,12}, 14));
		
		System.out.println("Min Coin");
		System.out.println(array.minCoin(new int[]{9,2,5}, 20));
		System.out.println(array.minCoin(new int[]{1,3,9,12}, 15));
		
		System.out.println("count steps");
		System.out.println(array.countSteps(10, 2));
		System.out.println(array.countSteps(50, 10));
		
		System.out.println("count sum ways");
		System.out.println(array.countWaysForScore(new int[]{1,3,9,12}, 14));
		System.out.println(array.countWaysForScore(new int[]{10,3,5}, 20));
		System.out.println(array.countWaysForScore(new int[]{10,3,5}, 13));
		
		System.out.println("count ways to sum");
		System.out.println(array.countWaysToSum(new int[]{10,3,5}, 13));
		System.out.println(array.countWaysToSum(new int[]{1,3,9,12}, 14));
		System.out.println(array.countWaysToSum(new int[]{1,5,6}, 7));
		
		System.out.println("count binary sequence length");
		System.out.println(array.countBinaySequence(2));
		System.out.println(array.countBinaySequence(8));
		System.out.println(array.countBinaySeq(2));
		System.out.println(array.countBinaySeq(8));
	}
}
