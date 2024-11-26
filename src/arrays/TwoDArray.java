/**
 * 
 */
package arrays;

import java.util.Arrays;
import java.util.Random;

import linkedlists.DoubleLinkedList;

/**
 * @author aa49442
 * 
 */
public class TwoDArray {

	private int[][] arr;
	final int x;
	final int y;
	private boolean[][] bArr;

	public TwoDArray(int x, int y) {
		this.x = x;
		this.y = y;
		arr = new int[x][y];
		bArr = new boolean[x][y];
	}

	public void fillSortedArray(int k) {
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				arr[i][j] = k++;
	}

	private final Random random = new Random(System.currentTimeMillis());

	public void fillBooleanArray() {
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				bArr[i][j] = random.nextBoolean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++)
				System.out.print(arr[i][j] + " | ");
			System.out.println();
		}
		return "";
	}

	public void booleanToString() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++)
				System.out.print(bArr[i][j] + " | ");
			System.out.println();
		}
	}

	public void fill0X(int e1, int e2) {
		/*
		 * k - starting row index m - ending row index l - starting column index
		 * n - ending column index i - iterator
		 */
		int i, k = 0, m = x; // row
		int l = 0, n = y; // column
		int e = e1;

		while (k < m && l < n) {

			/*
			 * Fill the first row from the remaining rows
			 */
			for (i = l; i < n; i++)
				arr[k][i] = e;
			k++;

			/*
			 * Fill the last column from the remaining columns
			 */
			for (i = k; i < m; i++)
				arr[i][n - 1] = e;
			n--;
			/*
			 * Fill the last row from the remaining rows
			 */
			if (k < m) {
				for (i = n - 1; i >= l; i--)
					arr[m - 1][i] = e;
				m--;
			}
			/*
			 * Print the first column from the remaining columns
			 */
			if (l < n) {
				for (i = m - 1; i >= k; i--)
					arr[i][l] = e;
				l++;
			}

			e = e == e1 ? e2 : e1;
		}

		for (i = 0; i < x; i++) {
			for (int j = 0; j < y; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
	}

	public void linearSearchElement(int e) {
		int i = 0, j = y - 1;

		while (i < x && j >= 0) {

			if (arr[i][j] == e) {
				System.out.printf("elements found at [%d][%d] index..%n", i, j);
				return;
			} else if (arr[i][j] > e)
				j--; // previous index in same column
			else
				i++; // next row..
		}

		System.err.println("elements not present..");
	}

	public void divideAndConquerSearch(int e) {
		int sRow = 0, eRow = x - 1;
		int sCol = 0, eCol = y - 1;
		divideAndConquerSearch(sRow, eRow, sCol, eCol, e);
	}

	private void divideAndConquerSearch(int sRow, int eRow, int sCol, int eCol,
			int e) {
		int rMid = (sRow + eRow) >> 1;
		int cMid = (sCol + eCol) >> 1;

		if (arr[rMid][cMid] == e) {
			System.out.printf("elements found at [%d][%d] index..", rMid, cMid);
			return;
		} else {
			// right-up quarter of matrix is searched in all cases. Provided it
			// is different from current call

			if (rMid != eRow || cMid != sCol)
				divideAndConquerSearch(sRow, rMid, cMid, eCol, e);

			// Special case for iteration with 1*2 matrix mat[i][j] and
			// mat[i][j+1] are only two elements. So just check second element

			if (sRow == eRow && sCol + 1 == eCol)
				if (arr[sRow][eCol] == e) {
					System.out.printf("elements found at [%d][%d] index..",
							sRow, eCol);
					return;
				}

			// If middle key is lesser then search lower horizontal matrix and
			// right hand side matrix

			if (arr[rMid][cMid] < e) {
				if (rMid + 1 <= eRow)
					divideAndConquerSearch(rMid + 1, eRow, sCol, eCol, e);
			} else {
				if (cMid - 1 >= sCol)
					divideAndConquerSearch(sRow, eRow, sCol, cMid - 1, e);
			}
		}
	}

	public void multiplyArray(TwoDArray twoDArray) {
		int[][] result = new int[x][twoDArray.y];

		for (int i = 0; i < x; i++)
			for (int j = 0; j < twoDArray.y; j++)
				for (int k = 0; k < twoDArray.y; k++)
					result[i][j] += arr[i][k] * twoDArray.arr[k][j];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < twoDArray.y; j++)
				System.out.print(result[i][j] + " | ");
			System.out.println();
		}
	}

	public int countPaths(int x, int y) {
		int[][] count = new int[x][y];

		// Count of paths to reach any cell in first column is 1
		for (int i = 0; i < x; i++)
			count[i][0] = 1;
		for (int i = 0; i < y; i++)
			count[0][i] = 1;

		for (int i = 1; i < x; i++)
			for (int j = 1; j < y; j++)
				count[i][j] = count[i - 1][j] + count[i][j - 1];
		// + count[i - 1][j - 1]; if diagonal allowed
		return count[x - 1][y - 1];
	}

	public void printPaths(int i, int j) {
		int[] path = new int[i + j];

		printPaths(i, j, path, 0, 0, 0);
	}

	/**
	 * @param i
	 *            no. of rows of arr
	 * @param j
	 *            no. of col of arr
	 * @param path
	 *            path arr
	 * @param k
	 *            current row index
	 * @param l
	 *            current col index
	 * @param index
	 *            next index to be filled of path arr
	 */
	private void printPaths(int i, int j, int[] path, int k, int l, int index) {
		// Reached the bottom of the matrix so we are left with only option to
		// move right
		if (k == i - 1) {
			for (int m = l; m < j; m++) {

			}
		}
	}

	public void printDiagonal() {
		
		int i = 0, j = 0;
		
		int k = 0;
		
		boolean isUp = true;
		
		// till all characters are printed
		while (k < this.x * this.y) {
			
			if (isUp) {
				// while going up, increase J & decrease i
				for (; i >=0 && j < this.y; j++, i--) {
					System.out.print(arr[i][j] +" ");
					k++; // increase K always
				}
				
				if (i < 0 && j <= this.y - 1) {
					i = 0;
				}
				
				if (j == this.y) {
					i += 2; j--;
				}
				
			} else {
				// while going down, increase i & decrease J
				for (; j >=0 && i < this.x; j--, i++) {
					System.out.print(arr[i][j] +" ");
					k++; // increase K always
				}
				
				if (j < 0 && i <= this.x - 1) {
					j = 0;
				}
				
				if (i == this.x) {
					j += 2; i--;
				}
			}
			
			isUp = !isUp; // opposite the boolean
		}
		
		System.out.println();
		
	}

	private int start = 0, finish = 0;

	public void maxSumArray() {
		// Variables to store the final output
		int maxSum = Integer.MIN_VALUE, finalLeft = 0, finalRight = 0, finalTop = 0, finalBottom = 0;

		int left, right, i, sum;
		int[] tmp;

		// Set the left column
		for (left = 0; left < this.y; left++) {
			tmp = new int[this.x];
			// Set the right column for the left column set by outer loop
			for (right = left; right < this.y; right++) {
				// Calculate sum between current left and right for every row
				// 'i'
				for (i = 0; i < this.x; i++)
					tmp[i] += arr[i][right];

				// Find the maximum sum subarray in temp[]. The kadane()
				// function also sets values of start and finish. So 'sum' is
				// sum of rectangle between (start, left) and (finish, right)
				// which is the maximum sum with boundary columns strictly as
				// left and right.

				sum = kadane2DSum(tmp);

				// Compare sum with maximum sum so far. If sum is more, then
				// update maxSum and other output values

				if (sum > maxSum) {
					maxSum = sum;
					finalLeft = left;
					finalRight = right;
					finalTop = start;
					finalBottom = finish;
				}
			}
		}

		// Print final values
		System.out.printf("Top & Left are : (%d, %d) %n", finalTop, finalLeft);
		System.out.printf("Bottom & Right are : (%d, %d) %n", finalBottom, finalRight);
		System.out.printf("Max Sum is : %d %n", maxSum);
	}

	// Implementation of Kadane's algorithm for 1D array. The function returns
	// the maximum sum and stores starting and ending indexes of the maximum sum
	// subarray at addresses pointed by start and finish pointers respectively.
	private int kadane2DSum(int[] tmp) {
		// initialize sum, maxSum and
		int sum = 0, maxSum = Integer.MIN_VALUE, i;

		// Just some initial value to check for all negative values case
		finish = -1;

		// local variable
		int localStart = 0;

		for (i = 0; i < this.x; i++) {
			sum += tmp[i];

			if (sum < 0) {
				sum = 0;
				localStart = i + 1;
			} else if (sum > maxSum) {
				maxSum = sum;
				start = localStart;
				finish = i;
			}
		}
		// There is at-least one non-negative number
		if (finish != -1)
			return maxSum;

		// Special Case: When all numbers in arr[] are negative
		maxSum = tmp[0];
		start = finish = 0;
		for (i = 1; i < this.x; i++) {
			if (tmp[i] > maxSum) {
				maxSum = tmp[i];
				start = finish = 1;
			}
		}
		return maxSum;
	}

	public int getCelebrity() {
		
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<Integer>();
		for (int i = 0; i < x; i++)
			stack.addFirst(i);

		int a = stack.poll();
		int b = stack.poll();

		while (stack.size() != 1) {
			if (haveAcquitance(a, b)) // a can't be celebrity as A know b
				a = stack.poll();
			else
				b = stack.poll(); // b can't be celebrity as a doesn't know b
		}
		
		int c = stack.poll();// c could be celebrity
		
		// Last candidate was not examined, it leads one excess comparison (optimize)
		if (haveAcquitance(c, b)) c = b;
		if (haveAcquitance(c, a)) c = a;
		
		for (int i = 0; i < x; i++) {
			if ((i != c) && (haveAcquitance(c, i) || !haveAcquitance(i, c))) return -1;
		}
		return c;
	}

	private boolean haveAcquitance(int a, int b) {
		return bArr[a][b];
	}

	/**
	 * to print size of maximum square of 1's.
	 */
	public void maxSizeArrOf1() {
		
		System.out.println("Matrix is :");
		System.out.println(this.toString());
		
		final int[][] sizeArr = new int[x][y];

		for (int i = 0; i < x; i++) sizeArr[i][0] = arr[i][0]; // first row..
		for (int i = 1; i < y; i++) sizeArr[0][i] = arr[0][i]; // first column

		// find max in sumArr
		int maxSum = sizeArr[0][0], maxX = 0, maxY = 0;

		// fill other entries..
		for (int i = 1; i < x; i++)
			for (int j = 1; j < y; j++)
				if (arr[i][j] == 1) {
					sizeArr[i][j] = Math.min(Math.min(arr[i][j - 1], arr[i - 1][j]), arr[i - 1][j - 1]) + 1;
					if (maxSum < sizeArr[i][j]) {
						maxSum = sizeArr[i][j];
						maxX = i;
						maxY = j;
					}
				}
				else sizeArr[i][j] = 0;
		System.out.printf("the max size is %d, maxI is %d, maxY is %d %n", maxSum, maxX, maxY);
	}
	
	/**
	 * Let count(n) be the count of ways to place tiles on a '2 x n' grid, we have following two ways to place first tile.
	 * 1) If we place first tile vertically, the problem reduces to count(n-1)
	 * 2) If we place first tile horizontally, we have to place second tile also horizontally. So the problem reduces to count(n-2).
	 * Therefore, count(n) can be written as below.
	 * 		count(n) = n if n = 1 or n = 2
	 * 		count(n) = count(n-1) + count(n-2)
	 * 
	 *  
	 * Above solution is simply fibbonacci series
	 * 
	 * @return numbers ways we can add 2*1 size tiles in our grid of 2*n
	 */
	public int tilingProblem(final int n) {
		
		int i = 1; // fibbonacci of n = 0;
		int j = 1; // fibbonacci of n = 1;
		int f = 0;
		for(int k = 2; k <= n; k++) {
			f = i + j;
			j = i;
			i = f;
		}
		return f;
	}
	
	/**
	 * Given a floor of size n x m and tiles of size 1 x m. The problem is to count the number of ways to tile 
	 * the given floor using 1 x m tiles. A tile can either be placed horizontally or vertically.
	 * 
	 *   			|  1, 1 < = n < m
	 *   count(n) = |  2, n = m
	 *   			| count(n-1) + count(n-m), m < n
	 * 
	 * @param n length of floor
	 * @param m width of floor
	 * @return numbers of ways we can add (1 * m) tiles to (n * m) floor
	 */
	public int tilingProblemGeneric(final int n, final int m) {
		
		final int[] count = new int[n+1];
		count[0] = 1; // bsae cases
		count[1] = 1; // base cases
		
		for(int i = 2; i <= n; i++) {
			if (i == m) {
				count[i] = 2;
			} else {
				count[i] += count[i-1];
				if ((i - m) > 0) {
					count[i] += count[i-m];
				}
			}
		}
		return count[n];
	}
	
	/**
	 * @return minimum train fare to reach from 0th station to last by breaking journey at any possible station
	 */
	public int minTrainFare() {
		
		final int[] cost = new int[x];
		
		Arrays.fill(cost, Integer.MAX_VALUE); // fill array with MAX value
		
		cost[0] = 0; // cost from 0 to 0 is always zero
		
		for (int i = 0; i < x; i++) {
			for (int j = i + 1; j < y; j++) {
				if (cost[j] > cost[i] + arr[i][j]) {
					cost[j] = cost[i] + arr[i][j];
				}
			}
		}
		return cost[x-1];
	}
	
	/**
	 * Given a n*n matrix where all numbers are distinct, find the maximum length path (starting from any cell) 
	 * such that all cells along the path are in increasing order with a difference of 1.
	 * 
	 * We can move in 4 directions from a given cell (i, j), i.e., we can move to 
	 * (i+1, j) or (i, j+1) or (i-1, j) or (i, j-1) with the condition that the adjacent cells have a difference of 1.
	 * 
	 * Input:  mat[][] = {{1, 2, 9}
	 * 					  {5, 3, 8}
	 * 					  {4, 6, 7}}
	 * 
	 * Output: 4
	 * The longest path is 6-7-8-9. 
	 * 
	 * @return maximum increasing sequence in matrix with max difference is 1.
	 */
	public int longestIncreasingCellSeq() {
		
		final int[][] result = copyOf(-1, false);
		
		int maxValue = 0;
		
		for(int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (result[i][j] == -1) {
					int val = longestIncreasingCellUtil(i, j, result);
					if (val > maxValue) {
						maxValue = val;
					}
				}
			}
		}
		return maxValue;
	}
	
	final int[] xPath = new int[]{1,0,-1,0};
	final int[] yPath = new int[]{0,-1,0,1};

	
	private int longestIncreasingCellUtil(final int i, final int j, final int[][] result) {
		
		if (result[i][j] != -1) return result[i][j];
		
		for(int k = 0; k < 4; k++) {
			final int nextX = i + xPath[k];
			final int nextY = j + yPath[k];
			
			if (isSafe(nextX, nextY, i, j)) {
				return result[i][j] = 1 + longestIncreasingCellUtil(nextX, nextY, result);
			}
		}
		return result[i][j] = 1;
	}

	private boolean isSafe(final int nextX, final int nextY, final int i, final int j) {
		return nextX < x && nextX >= 0 && nextY < y && nextY >= 0 && (arr[i][j] + 1) == arr[nextX][nextY];
	}

	int[][] copyOf(final int defaultValue, final boolean copyArray) {
		
		final int[][] copy = new int[x][y];
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (copyArray)
					copy[i][j] = arr[i][j];
				else
					copy[i][j] = defaultValue;
			}
		}
		return copy;
	}

	void fillZeroOne() {
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				arr[i][j] = (int) (Math.random() * 10) % 2;
	}
	
	void fillArray(final int n) {
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				arr[i][j] = (int) (Math.random() * n);
	}
	
	void fillTrainFares() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (i == j) {
					arr[i][j] = 0;
				} else if (i > j) {
					arr[i][j] = Integer.MAX_VALUE;
				} else {
					arr[i][j] = (int) (Math.random() * 100);
				}
			}
		}
	}
}