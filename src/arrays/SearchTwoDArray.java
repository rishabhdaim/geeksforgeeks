/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class SearchTwoDArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TwoDArray twoDArray = new TwoDArray(3, 3);

		twoDArray.fillSortedArray(19);

		System.out.println(twoDArray);

		twoDArray.linearSearchElement(56);
		// twoDArray.divideAndConquerSearch(39);

		TwoDArray twoDArray1 = new TwoDArray(3, 3);

		twoDArray1.fillSortedArray(23);

		twoDArray.multiplyArray(twoDArray1);

		int i = twoDArray.countPaths(4, 7);
		System.out.println("total paths are : " + i);

		twoDArray.printPaths(3, 3);

		twoDArray.printDiagonal();

		twoDArray = new TwoDArray(5, 7);

		twoDArray.fillSortedArray(39);

		System.out.println(twoDArray);

		twoDArray.maxSumArray();

		System.out.println("---------------------------------");
		
		twoDArray = new TwoDArray(5, 7);

		twoDArray.fillBooleanArray();

		twoDArray.booleanToString();

		twoDArray = new TwoDArray(5, 7);
		twoDArray.fillZeroOne();
		twoDArray.maxSizeArrOf1();
		System.out.println(twoDArray.tilingProblem(5));
		System.out.println(twoDArray.tilingProblem(10));
		
		System.out.println(twoDArray.tilingProblemGeneric(5, 2));
		System.out.println(twoDArray.tilingProblemGeneric(7, 4));
		
		twoDArray = new TwoDArray(5, 5);
		twoDArray.fillTrainFares();
		System.out.println(twoDArray);
		System.out.println(twoDArray.minTrainFare());
		
		twoDArray = new TwoDArray(5, 5);
		twoDArray.fillArray(10);
		System.out.println(twoDArray);
		System.out.println(twoDArray.longestIncreasingCellSeq());
	}
}