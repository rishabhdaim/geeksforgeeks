/**
 * 
 */
package arrays;

import graphs.Job;
import hashing.HashMap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import linkedlists.DoubleLinkedList;

/**
 * @author aa49442
 * 
 */
public class Array<E extends Comparable<? super E>> {

	E[] elements;
	private final int n;

	public Array(E[] e) {
		this.elements = e;
		n = elements.length;
	}

	@SuppressWarnings("unchecked")
	public Array(int n) {
		elements = (E[]) new Comparable[n];
		this.n = n;
	}

	public void printKClosestElements(int k, E e) {
		int l = findCrossOver(e, 0, elements.length - 1);
		System.out.println("cross over is : " + (l + 1));
		int r = l + 1;// right search index
		int count = 0;

		if (elements[l].compareTo(e) == 0)
			l--;

		while (l > 0 && r < elements.length && count < k) {
			if (((Integer) e - (Integer) elements[l]) < (Integer) elements[r]
					- (Integer) e)
				System.out.print(elements[l--] + " ");
			else
				System.out.print(elements[r++] + " ");
			count++;
		}

		while (count++ < k && l > 0)
			System.out.print(elements[l--] + " ");
		while (count++ < k && r < elements.length)
			System.out.print(elements[r++] + " ");
	}

	private int findCrossOver(E e, int low, int high) {

		if (elements[high].compareTo(e) <= 0)
			return high;
		if (elements[low].compareTo(e) > 0)
			return low;

		int mid = (high + low) >> 1;

		if (elements[mid].compareTo(e) <= 0
				&& elements[mid + 1].compareTo(e) > 0)
			return mid;

		if (elements[mid].compareTo(e) < 0)
			return findCrossOver(e, mid + 1, high);
		return findCrossOver(e, low, mid);
	}

	public int countStrings(int n) {
		int[] a = new int[n];
		int[] b = new int[n];

		a[0] = b[0] = 1;

		for (int i = 1; i < n; i++) {
			a[i] = a[i - 1] + b[i - 1];
			b[i] = a[i - 1];
		}
		return a[n - 1] + b[n - 1];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Array [e=" + Arrays.toString(elements) + "]";
	}

	public void quickSort() {
		quickSort(0, elements.length - 1);
	}

	private void quickSort(int i, int j) {
		if (elements == null)
			return;

		if (i >= j)
			return;
		E pivot = elements[j];
		int p = partition(i, j, pivot);
		quickSort(i, p - 1);
		quickSort(p + 1, j);
	}

	/**
	 * @param i
	 * @param j
	 * @param pivot
	 * @return
	 */
	private int partition(final int i, final int j, final E pivot) {
		int lower = i - 1;
		int upper = i;
		// boundary condition
		while (upper <= j - 1) {
			if (elements[upper].compareTo(pivot) <= 0) {
				lower++;
				swap(lower, upper);
			}
			upper++;
		}
		swap(lower + 1, j);
		return lower + 1;
	}

	private void swap(int lower, int upper) {
		E e = elements[lower];
		elements[lower] = elements[upper];
		elements[upper] = e;
	}

	public int smallestSubArrWithSumGreater(int sum) {
		int minL = elements.length;
		int start = 0, end = 0;
		int currSum = 0;
		while (end < elements.length) {

			while (currSum <= sum && end < elements.length)
				currSum += (Integer) elements[end++];

			while ((currSum > sum) && (start < elements.length)) {
				if ((end - start) < minL)
					minL = (end - start);
				currSum -= (Integer) elements[start++];
			}
		}
		return minL;
	}

	public void smallestSubArrWithSumEqual(int sum) {
		int start = 0;
		int currSum = (Integer) elements[start];
		for (int i = 1, l = elements.length; i <= l; i++) {

			while (currSum > sum && start < i - 1)
				currSum -= (Integer) elements[start++];
			if (currSum == sum) {
				System.out.printf("sum found between %d and %d indexes.",
						start, i);
				return;
			}
			if (i < l)
				currSum += (Integer) elements[i];
		}
		System.err.println("no sum exists..");
	}

	/**
	 * @param start
	 * @param end
	 *            end of arr inclusive
	 * @return
	 */
	private int maxValueIndex(int start, int end) {

		int max = start;
		for (int i = start + 1; i <= end; i++)
			if (elements[max].compareTo(elements[i]) < 0)
				max = i;
		return max;
	}

	@SuppressWarnings("unused")
	private int minValue(int start, int end) {

		int min = start;
		for (int i = start + 1; i <= end; i++)
			if (elements[min].compareTo(elements[i]) > 0)
				min = i;
		return min;
	}

	public int countZeros(int l, int h) {
		if (l >= h)
			return -1;
		int mid = (h + l) >> 1;

		if (mid == 0 || (Integer) elements[mid - 1] == 1 && (Integer) elements[mid] == 0)
			return mid;
		if ((Integer) elements[mid] == 1)
			return countZeros(mid + 1, h);
		else
			return countZeros(l, mid);
	}

	public boolean zeroSumArr() {
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

		int sum = 0;

		for (int i = 0; i < elements.length; i++) {
			sum += (Integer) elements[i];

			if ((Integer) elements[i] == 0 || sum == 0
					|| hashMap.get(sum) != null) {
				System.out.println("the indics are : " + hashMap.get(sum)
						+ " | " + i);
				return true;
			}
			hashMap.put(sum, i);
		}
		return false;
	}

	/**
	 * linear time sorting if number set is not too large
	 */
	public void countSort() {
		@SuppressWarnings("unchecked")
		E[] temp = (E[]) new Comparable[elements.length];

		int[] count = new int[100 + 1];

		// Store count of each character
		for (int i = 0; i < elements.length; i++)
			++count[(Integer) elements[i]];

		// Change count[i] so that count[i] now contains actual position of this
		// character in output array
		for (int i = 1; i <= 100; i++)
			count[i] += count[i - 1];

		// Build the output character array
		for (int i = 0; i < elements.length; i++) {
			temp[count[(Integer) elements[i]] - 1] = elements[i];
			--count[(Integer) elements[i]];
		}
		// Copy the output array to str, so that str now contains sorted
		// characters
		for (int i = 0; i < elements.length; i++)
			elements[i] = temp[i];
	}

	public void radixSort(int base) {
		int maxIndex = maxValueIndex(0, elements.length - 1);
		int max = (Integer) elements[maxIndex];

		for (int i = 1; max / i > 0; i *= base)
			countSort(i, base);
	}

	/**
	 * @param exp
	 *            the number by which we divide values in unsorted array, its
	 *            value increases by factor of 10 everytime..
	 */
	@SuppressWarnings("unchecked")
	private void countSort(int exp, int base) {
		Integer[] output = new Integer[elements.length];
		int[] count = new int[base];

		// filling count arr with no. of occurences..
		for (int i = 0; i < elements.length; i++)
			count[((Integer) elements[i] / exp) % base]++;

		// calculating sum..
		for (int i = 1; i < base; i++)
			count[i] += count[i - 1];
		// filling output array for temp storage..
		// this is done opposite to normal countSort as we had to maintain order
		// in lower bits..
		for (int i = elements.length - 1; i >= 0; i--) {
			output[count[((Integer) elements[i] / exp) % base] - 1] = (Integer) elements[i];
			count[((Integer) elements[i] / exp) % base]--;
		}

		// storing back to arr

		for (int i = 0; i < elements.length; i++)
			elements[i] = (E) output[i];
	}

	@SuppressWarnings("unchecked")
	public void rearrangeToArrOfArrIndex() {
		Integer j;
		int l = elements.length;
		for (int i = 0; i < l; i++) {
			j = (Integer) elements[i];
			j += ((Integer) elements[(Integer) elements[i]] % l) * l;
			elements[i] = (E) j;
		}

		for (int i = 0; i < l; i++) {
			j = (Integer) elements[i] / l;
			elements[i] = (E) j;
		}
	}

	public int countPairWithXY(Integer[] y) {
		Integer[] x = (Integer[]) elements;
		int[] noOfY = new int[5];
		for (int i = 0; i < y.length; i++)
			if (y[i] < 5)
				noOfY[y[i]]++;

		Arrays.sort(y);

		int count = 0;
		for (int i = 0; i < x.length; i++)
			count += countPairWithXY(x[i], noOfY, y);
		return count;
	}

	private int countPairWithXY(int x, int[] noOfY, Integer[] y) {

		if (x == 0)
			return 0;
		if (x == 1)
			return noOfY[0];

		int index = upperBound(x, y, 0, y.length - 1);
		int count = y.length - index;

		// If we have reached here, then x must be greater than 1, increase
		// number of pairs for y=0 and y=1
		count += noOfY[0] + noOfY[1];

		if (x == 2)
			count -= noOfY[3] + noOfY[4];
		if (x == 3)
			count += noOfY[2];
		return count;
	}

	private int upperBound(int x, Integer[] y, int l, int h) {
		if (l >= h)
			return -1;
		if (y[h] <= x)
			return h;
		if (y[l] > x)
			return l;
		int mid = (l + h) >> 1;

		if (y[mid] == x || (y[mid - 1] < x && y[mid] > x))
			return mid;
		else if (y[mid] < x)
			return upperBound(x, y, l, mid);
		else
			return upperBound(x, y, mid + 1, h);
	}

	@SuppressWarnings("unchecked")
	public void pushZeroToEnd() {
		int count = 0;
		int l = elements.length;
		for (int i = 0; i < l; i++)
			if ((Integer) elements[i] != 0)
				elements[count++] = elements[i];

		while (count < l)
			elements[count++] = (E) new Integer(0);
	}

	public int[] mergeKSortedArrays(int[][] arr, int k) {
		int[] output = new int[arr.length * k];

		Heap<Integer> heap = new Heap<Integer>();

		for (int i = 0; i < arr.length; i++)
			heap.buildHeapArr(arr[i][0], i, 1);

		heap.heapify();

		for (int count = 0, l = output.length; count < l; count++) {

			Heap.Node<Integer> root = heap.root();

			output[count] = root.e;

			if (root.cIndex < k)
				heap.replaceRoot(arr[root.rIndex][root.cIndex], root.rIndex, ++root.cIndex);
			else
				heap.replaceRoot(Integer.MAX_VALUE, root.rIndex, root.cIndex);
		}
		return output;
	}

	@SuppressWarnings("unchecked")
	public E findMinInRotatedArray(int l, int h) {
		// This condition is needed to handle the case when array is not rotated at all
		if (elements[h].compareTo(elements[l]) > 0)
			return elements[0];

		if (elements[h] == elements[l])
			return elements[l];

		int mid = (h + l) >> 1;
		// Check if element (mid+1) is minimum element.
		if (mid < h && elements[mid + 1].compareTo(elements[mid]) < 0)
			return elements[mid + 1];
		// This case causes O(n) time in case of duplicates..
		if (elements[l].compareTo(elements[mid]) == 0 && elements[h].compareTo(elements[mid]) == 0)
			return (E) new Integer(Math.min((Integer) findMinInRotatedArray(l, mid - 1), (Integer) findMinInRotatedArray(mid + 1, h)));
		// Check if mid itself is minimum element
		if (mid > l && elements[mid].compareTo(elements[mid - 1]) < 0)
			return elements[mid];
		if (elements[h].compareTo(elements[mid]) > 0)
			return findMinInRotatedArray(l, mid - 1);
		return findMinInRotatedArray(mid + 1, h);
	}

	public int inputWithPositiveOutput() {
		if (function(0) >= 0)
			return 0;
		int i = 1;
		while (function(i) <= 0)
			i *= 2;
		return binarySearch(i / 2, i);
	}

	private int binarySearch(int l, int h) {
		if (l >= h)
			return -1;

		int mid = (l + h) >> 1;

		if (function(mid) > 0 && (mid == l || function(mid - 1) <= 0))
			return mid;
		else if (function(mid) <= 0)
			return binarySearch(mid + 1, h);
		else
			return binarySearch(l, mid - 1);
	}

	private int function(int i) {
		return i - 20;
	}

	public void moreThanK(int k) {
		if (k < 2)
			return;

		/*
		 * Step 1: Create a temporary array (contains element and count) of size
		 * k-1. Initialize count of all elements as 0
		 */
		@SuppressWarnings("unchecked")
		EleCount<E>[] eleCounts = new EleCount[k - 1];

		for (int i = 0; i < k - 1; i++)
			eleCounts[i] = new EleCount<E>(null, 0);
		/*
		 * Step 2: Process all elements of input array
		 */

		for (int i = 0; i < elements.length; i++) {
			int j;
			/*
			 * If elements[i] is already present in the element count array,
			 * then increment its count
			 */
			for (j = 0; j < k - 1; j++) {
				if (elements[i].equals(eleCounts[j].e)) {
					eleCounts[j].count++;
					break;
				}
			}
			/*
			 * If elements[i] is not present in eleCounts[]
			 */

			if (j == k - 1) {
				int l;

				/*
				 * If there is position available in eleCounts[], then place
				 * elements[i] in the first available position and set count as
				 * 1
				 */

				for (l = 0; l < k - 1; l++) {
					if (eleCounts[l].count == 0) {
						eleCounts[l].e = elements[i];
						eleCounts[l].count = 1;
						break;
					}
				}
				/*
				 * If all the position in the eleCounts[] are filled, then
				 * decrease count of every element by 1
				 */
				if (l == k - 1)
					for (EleCount<E> eleCount : eleCounts)
						eleCount.count -= 1;
			}
		}

		/*
		 * Step 3: Check actual counts of potential candidates in eleCounts[]
		 */

		for (int i = 0; i < k - 1; i++) {
			int count = 0;

			for (int j = 0; j < elements.length; j++)
				if (elements[j].equals(eleCounts[i].e))
					count++;

			if (count > elements.length / k)
				System.out.println(eleCounts[i].e + " -- " + count);
		}
	}

	private static final class EleCount<E> {
		E e;
		int count;

		/**
		 * @param e
		 * @param count
		 */
		public EleCount(E e, int count) {
			super();
			this.e = e;
			this.count = count;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "EleCount [e=" + e + ", count=" + count + "]";
		}
	}

	public int peakElement(int l, int h) {
		int mid = (l + h) >> 1;

		if ((mid == 0 || elements[mid - 1].compareTo(elements[mid]) <= 0)
				&& (mid == elements.length - 1 || elements[mid + 1]
						.compareTo(elements[mid]) <= 0))
			return mid;
		else if (mid > 0 && elements[mid - 1].compareTo(elements[mid]) > 0)
			return peakElement(l, mid - 1);
		else
			return peakElement(mid + 1, h);
	}

	public void printCombination(int r) {
		@SuppressWarnings("unchecked")
		E[] data = (E[]) new Comparable[r];
		Arrays.sort(elements);
		printCombination(0, elements.length - 1, 0, r, data);
		System.out.println("-------------------------------");
		printCombinationWithDP(0, elements.length - 1, 0, r, data);
	}

	private void printCombinationWithDP(int start, int end, int index, int r,
			E[] data) {
		if (index == r) {
			for (E e : data)
				System.out.print(e + " ");
			System.out.println();
			return;
		}

		if (start >= elements.length)
			return;
		data[index] = elements[start];
		// current is included, put next at next location
		printCombinationWithDP(start + 1, end, index + 1, r, data);

		// Remove duplicates
		while ((start < end) && elements[start] == elements[start + 1])
			start++;
		// current is excluded, replace it with next (Note that i+1 is passed,
		// but index is not changed)
		printCombinationWithDP(start + 1, end, index, r, data);
	}

	private void printCombination(int start, int end, int index, int r, E[] data) {
		if (index == r) {
			for (E e : data)
				System.out.print(e + " ");
			System.out.println();
			return;
		}

		// logic goes here to fix element & recur..
		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element at index
		// will make a combination with remaining elements at remaining
		// positions
		for (int i = start; i <= end && ((end - i + 1) >= (r - index)); i++) {
			data[index] = elements[i];
			printCombination(i + 1, end, index + 1, r, data);
			while (i < elements.length - 1 && elements[i] == elements[i + 1])
				i++;
		}
	}

	public void rearrangePositiveAndNegative() {
		int i = -1;
		int n = elements.length;

		for (int j = 0; j < n; j++) {
			if ((Integer) elements[j] < 0) {
				i++;
				if (i != j)
					swap(i, j);
			}
		}

		int pos = i + 1, neg = 0;

		while (pos < n && neg < pos && (Integer) elements[neg] < 0) {
			swap(pos, neg);
			pos++;
			neg += 2;
		}
	}

	public int maxDiff() {
		Integer[] i = (Integer[]) elements;
		if (i.length < 2)
			return 0;
		int maxD = i[1] - i[0];
		int minE = i[0];
		for (int j = 1; j < i.length; j++) {
			if (i[j] - minE > maxD)
				maxD = i[j] - minE;
			if (i[j] < minE)
				minE = i[j];
		}
		return maxD;
	}

	public void elementsWithMaxCount(int k) {
		int n = elements.length;
		Integer[] count = (Integer[]) elements;

		for (int i = 0; i < n; i++)
			count[count[i] % k] += k;

		int maxV = maxValueIndex(count, 0, n);

		for (int i = 0; i < n; i++)
			count[i] = count[i] % k;

		System.out.println("max occuring is : " + count[maxV]);
	}

	private int maxValueIndex(Integer[] count, int i, int n) {
		int max = 0;

		for (int j = i; j < n; j++)
			if (count[j] > count[max])
				max = j;
		return max;
	}

	public void sellAndBuyStocks() {
		Integer[] prices = (Integer[]) elements;
		int n = elements.length;

		Interval[] intervals = new Interval[n / 2 - 1];
		for (int i = 0; i < intervals.length; i++)
			intervals[i] = new Interval(0, 0);

		int i = 0;
		int count = 0;
		while (i < n - 1) {

			while (i < n - 1 && prices[i + 1] <= prices[i])
				i++;
			if (i == n - 1)
				break;

			intervals[count].buy = i++;

			while (i < n && prices[i - 1] <= prices[i])
				i++;

			intervals[count].sell = i - 1;

			count++;
		}

		if (count == 0)
			System.out.println("no profit..");
		else
			for (int j = 0; j < count; j++)
				System.out.println(intervals[j].buy + " -- " + intervals[j].sell);
	}

	private static class Interval implements Comparable<Interval> {
		int buy;
		int sell;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Interval [buy=" + buy + ", sell=" + sell + "]";
		}

		/**
		 * @param buy
		 * @param sell
		 */
		public Interval(int buy, int sell) {
			super();
			this.buy = buy;
			this.sell = sell;
		}

		@Override
		public int compareTo(Interval o) {
			return this.buy - o.buy;
		}
	}

	public void mergeOverlappingIntervals(int[][] arr) {
		DoubleLinkedList<Interval> stack = new DoubleLinkedList<Interval>();
		Interval[] intervals = new Interval[arr.length];
		for (int i = 0; i < arr.length; i++)
			intervals[i] = new Interval(arr[i][0], arr[i][1]);
		Arrays.sort(intervals);

		stack.addFirst(intervals[0]);
		int i = 1;
		while (i < intervals.length) {

			Interval top = stack.peek();

			if (top.sell < intervals[i].buy)
				stack.addFirst(intervals[i]);

			else if (top.sell < intervals[i].sell) {
				top.sell = intervals[i].sell;
				stack.poll();
				stack.addFirst(top);
			}
			i++;
		}

		while (!stack.isEmpty()) {
			Interval top = stack.peek();
			System.out.println(top.buy + " , " + top.sell);
			stack.poll();
		}
	}

	public Integer maxSubArrSumWithDnC(int l, int h) {
		if (l == h)
			return (Integer) elements[l];
		int mid = (l + h) >> 1;
		return Math.max(
				Math.max(maxSubArrSumWithDnC(l, mid), maxSubArrSumWithDnC(mid + 1, h)), maxCrossingSum(l, mid, h));
	}

	private int maxCrossingSum(int l, int mid, int h) {
		int sum = 0;
		int leftSum = Integer.MIN_VALUE;
		for (int i = mid; i >= l; i--) {
			sum += (Integer) elements[i];
			if (sum > leftSum)
				leftSum = sum;
		}

		int rightSum = Integer.MIN_VALUE;
		sum = 0;
		for (int i = mid + 1; i <= h; i++) {
			sum += (Integer) elements[i];
			if (sum > rightSum)
				rightSum = sum;
		}
		return leftSum + rightSum;
	}

	public int maxSubArrSumWithKadaneAlgo(int size) {
		int maxSoFar = 0, maxEndSoFar = 0;

		for (int i = 0; i <= size; i++) {
			maxEndSoFar += (Integer) elements[i];
			if (maxEndSoFar < 0)
				maxEndSoFar = 0;
			else if (maxSoFar < maxEndSoFar)
				maxSoFar = maxEndSoFar;
		}
		return maxSoFar;
	}

	public int maxSubArrSum(int size) {
		Integer[] integers = (Integer[]) elements;
		int maxSoFar = integers[0], maxEndSoFar = integers[0];
		for (int i = 1; i <= size; i++) {
			maxEndSoFar = Math.max(integers[i], maxEndSoFar + integers[i]);
			maxSoFar = Math.max(maxSoFar, maxEndSoFar);
		}
		return maxSoFar;
	}

	public int maxSubArrProd(int size) {
		
		Integer[] e = (Integer[]) elements;
		int maxSoFar = 1, maxEndSoFar = 1, minEndSoFar = 1;
		
		for (int i = 0; i <= size; i++) {
			if (e[i] > 0) {
				maxEndSoFar = maxEndSoFar * e[i];
				minEndSoFar = Math.min(minEndSoFar * e[i], 1);
			} else if (e[i] == 0)
				maxEndSoFar = minEndSoFar = 1;
			else {
				int tmp = maxEndSoFar;
				maxEndSoFar = Math.max(minEndSoFar * e[i], 1);
				minEndSoFar = tmp * e[i];
			}

			if (maxSoFar < maxEndSoFar)
				maxSoFar = maxEndSoFar;
		}
		return maxSoFar;
	}

	public void pancakeSort(int n) {
		for (int currSize = n; currSize >= 0; currSize--) {
			int maxI = maxValueIndex(0, currSize);

			if (maxI != currSize) {
				flip(maxI);
				flip(currSize);
			}
		}
	}

	private void flip(int maxI) {
		int start = 0;

		while (start < maxI) {
			swap(start, maxI);
			start++;
			maxI--;
		}
	}

	public void findPetrolPump(int[][] arr) {
		Interval[] petrolPumps = new Interval[arr.length];
		for (int i = 0; i < petrolPumps.length; i++)
			petrolPumps[i] = new Interval(arr[i][0], arr[i][1]);
		int start = 0;
		int end = 1;

		int currPetrol = petrolPumps[start].buy - petrolPumps[start].sell;

		while (start != end || currPetrol < 0) {

			while (currPetrol < 0 && start != end) {
				currPetrol -= petrolPumps[start].buy - petrolPumps[start].sell;
				start = (start + 1) % petrolPumps.length;
			}
			currPetrol += petrolPumps[end].buy - petrolPumps[end].sell;
			end = (end + 1) % petrolPumps.length;
			// If 0 is being considered as start again, then there is no
			// possible solution
			if (start == 0)
				System.err.println("no solution.");
		}
		// Return starting point
		System.out.println("starting point is : " + start);
	}

	public void transpose(int l, int k) {
		int size = l * k - 1;
		E t;// holds element to be replaced, eventually becomes next element to
		int next;// location of t to be moved
		int cycleBegin;
		int i;
		boolean[] hashSet = new boolean[l * k];
		// Note that A[0] and A[size-1] won't move
		hashSet[0] = true;
		hashSet[size] = true;

		i = 1;

		while (i < size) {
			cycleBegin = i;
			t = elements[i];
			do {
				next = (i * l) % size;
				{
					E temp = t;
					t = elements[next];
					elements[next] = temp;
				}
				hashSet[i] = true;
				i = next;
			} while (i != cycleBegin);

			// Get Next Move (what about querying random location?)
			for (i = 1; i < size && hashSet[i]; i++)
				;
		}
	}

	public void noOfTriangles() {
		Integer[] vertices = (Integer[]) elements;
		int n = elements.length;
		int count = 0;
		for (int i = 0; i < n - 2; i++) {
			int k = i + 2;
			for (int j = i + 1; j < n; j++) {
				while (k < n && vertices[i] + vertices[j] > vertices[k])
					k++;
				count += k - j - 1;
			}
		}
		System.out.println("no of triangles : " + count);
	}

	public void randomize() {
		final Random random = new Random(System.currentTimeMillis());
		for (int i = n - 1; i > 0; i--) {
			int j = random.nextInt(i) % (i + 1);
			swap(i, j);
		}
	}

	public int getMedian(Integer[] a1, Integer[] a2, int n) {
		return getMedian(a1, 0, n - 1, a2, 0, n - 1, n);
	}

	private int getMedian(Integer[] a1, int l1, int h1, Integer[] a2, int l2, int h2, int n) {
		if (n == 0)
			return -1;
		if (n == 1)
			return (a1[l1] + a2[l2]) >> 1;
		if (n == 2)
			return ((Math.max(a1[l1], a2[l2])) + Math.min(a1[h1], a2[h2])) >> 1;

		int m1 = median(a1, l1, h1);
		int m2 = median(a2, l2, h2);

		if (m1 == m2)
			return m1;
		else if (m1 > m2) {
			if (n % 2 == 0)
				return getMedian(a1, l1 + 0, h1 - (n / 2) - 1, a2, l2 + n / 2, n - 1, n / 2);
			else
				return getMedian(a1, l1 + 0, h1 - n / 2, a2, l2 + n / 2 + 1, n - 1, n / 2 + 1);
		} else {
			if (n % 2 == 0)
				return getMedian(a1, l1 + n / 2, n - 1, a2, l2 + 0, h2 - (n / 2) - 1, n / 2);
			else
				return getMedian(a1, l1 + n / 2 + 1, n - 1, a2, l2 + 0, h2 - n/ 2, n / 2 + 1);
		}
	}

	private int median(Integer[] a, int l, int h) {
		int n = h - l + 1;
		int x = (h + l + 1);
		if (n % 2 == 0)
			return (a[x >> 1] + a[(x >> 1) - 1]) >> 1;
		return a[x >> 1];
	}

	public int getMedRec(Integer[] i, Integer[] i1, int n) {
		if (i[n - 1] < i1[0])
			return (i[n - 1] + i1[0]) >> 1;
		if (i1[n - 1] < i[0])
			return (i1[n - 1] + i[0]) >> 1;
		return getMedRec(i, i1, 0, n - 1, n);
	}

	private int getMedRec(Integer[] i1, Integer[] i2, int l, int h, int n) {
		// we have reached end, now reverse..
		if (l > h)
			getMedRec(i2, i1, 0, n - 1, n);

		// middle of arr1..
		int i = (l + h) >> 1;
		// index which should be less than median
		int j = n - 1 - i;

		/*
		 * Recursion terminates here. if arr1[i] > arr2[j] && arr1[i]< arr[j+1],
		 * then it is median..
		 */

		if (i1[i] > i2[j] && ((j == n - 1) || i1[i] <= i2[j + 1]))
			/*
			 * ar1[i] is decided as median 2, now select the median 1 (element
			 * just before ar1[i] in merged array) to get the average of both
			 */

			if (i == 0 || i2[j] > i1[i - 1])
				return (i1[i] + i2[j]) >> 1;
			else
				return (i1[i] + i1[i - 1]) >> 1;
		else if (i1[i] > i2[j] && (j != n - 1) && i1[i] > i2[j + 1])
			return getMedRec(i1, i2, l, i - 1, n);
		else
			return getMedRec(i1, i2, i + 1, h, n);
	}

	@SuppressWarnings({ "unchecked" })
	public int maxSubArrCircular(final int n) {
		
		// Case 1: get the maximum sum using standard kadane's algorithm
		int maxKadane = maxSubArrSumWithKadaneAlgo(n);
		int maxWrap = 0;
		
		// Case 2: Now find the maximum sum that includes corner elements
		for (int i = 0; i <= n; i++) {
			maxWrap += (Integer) elements[i]; // Calculate array-sum
			elements[i] = (E) new Integer(-(Integer) elements[i]); // invert the array (change sign)
		}
		
		// max sum with corner elements will be: array-sum - (-max subarray sum of inverted array)
		maxWrap += maxSubArrSumWithKadaneAlgo(n);
		
		// fix the array to original position
		for (int i = 0; i <= n; i++) elements[i] = (E) new Integer(-(Integer) elements[i]);
		return Math.max(maxKadane, maxWrap);
	}

	public void sumEqualTo4Numbers(int n) {
		Integer[] i = (Integer[]) elements;
		int size = i.length;
		for (int j = 0; j < size - 3; j++) {

		}
	}

	public int maxChainPairs(int[][] arr) {
		int n = arr.length;
		Interval[] chains = new Interval[n];
		int[] mcl = new int[n];
		for (int i = 0; i < n; i++) {
			chains[i] = new Interval(arr[i][0], arr[i][1]);
			// Initialize MCL (max chain length) values for all indexes
			mcl[i] = 1;
		}

		Arrays.sort(chains);

		/* Compute optimized chain length values in bottom up manner */
		for (int i = 1; i < n; i++)
			for (int j = 0; j < i; j++)
				if (chains[i].buy > chains[j].sell && mcl[i] < mcl[j] + 1)
					mcl[i] = mcl[j] + 1;

		// mcl[i] now stores the maximum chain length ending with pair i
		int max = 0;
		for (int i = 0; i < n; i++)
			if (max < mcl[i])
				max = mcl[i];
		return max;
	}

	/**
	 * The Longest Increasing Subsequence (LIS) problem is to find the length of
	 * the longest subsequence of a given sequence such that all elements of the
	 * subsequence are sorted in increasing order. For example, the length of
	 * LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
	 * 
	 * @param length
	 *            size of array
	 * @return longest increasing subsequence in this array
	 */
	
	// Let arr[0..n-1] be the input array and L(i) be the length of the LIS ending at index i such that arr[i] is the last element of the LIS.
	// Then, L(i) can be recursively written as:
	// L(i) = 1 + max( L(j) ) where 0 < j < i and arr[j] < arr[i]; or
	// L(i) = 1, if no such j exists. 
	// To find the LIS for a given array, we need to return max(L(i)) where 0 < i < n.
	public int lis(final int length) {

		final int[] lis = new int[length];
		
		/* Initialize LIS values for all indexes as 1*/
		for (int i = 0; i < length; i++)
			lis[i] = 1;

		for (int i = 1; i < length; i++)
			for (int j = 0; j < i; j++)
				if (elements[i].compareTo(elements[j]) > 0 && lis[i] < lis[j] + 1) // I element is greater than J and lis of I is less than J
					lis[i] = lis[j] + 1;

		return max(lis);
	}

	private int max(int[] lis) {
		int max = lis[0];
		int n = lis.length;
		for (int i = 1; i < n; i++)
			if (max < lis[i])
				max = lis[i];
		return max;
	}

	public int lds(final int length) {
		int[] lds = new int[length];
		for (int i = 0; i < length; i++)
			lds[i] = 1;

		for (int i = length - 2; i >= 0; i--)
			for (int j = length - 1; j > i; j--)
				if (elements[i].compareTo(elements[j]) > 0 && lds[i] < lds[j] + 1)
					lds[i] = lds[j] + 1;
		return max(lds);
	}

	public int lisNLogN(final int length) {

		final Integer[] integers = (Integer[]) elements;
		int size = 1;
		final int[] lis = new int[length];

		for (int i = 0; i < length; i++)
			if (integers[i] < lis[0])
				lis[0] = integers[i];
			else if (integers[i] > lis[size - 1])
				lis[size++] = integers[i];
			else
				lis[ceilIndex(integers[i], -1, size - 1, lis)] = integers[i];
		return size - 1;
	}

	private int ceilIndex(int e, int l, int h, int[] lis) {
		int m;
		while (h - l > 1) {
			m = (h + l) >> 1;
			if (lis[m] >= e)
				h = m;
			else
				l = m;
		}
		return h;
	}

	public int ldsNLogN(int length) {
		Integer[] integers = (Integer[]) elements;
		int size = 1;
		int[] lds = new int[length];
		for (int i = 0; i < length; i++)
			if (integers[i] > lds[0])
				lds[0] = integers[i];
			else if (integers[i] < lds[size - 1])
				lds[size++] = integers[i];
			else
				lds[floorIndex(integers[i], -1, size - 1, lds)] = integers[i];
		return size;
	}

	private int floorIndex(int e, int l, int h, int[] lds) {
		int m;
		while (h - l > 1) {
			m = (h + l) >> 1;
			if (lds[m] <= e)
				h = m;
			else
				l = m;
		}
		return h;
	}

	public void replaceWithGreatest(int n) {
		E maxE = elements[n - 1];
		elements[n - 1] = null;
		E tmp;
		for (int i = n - 2; i >= 0; i--) {
			tmp = elements[i];
			elements[i] = maxE;

			if (maxE.compareTo(tmp) < 0)
				maxE = tmp;
		}
	}

	public void maxSubArrWithEqual0And1(int n) {

		Integer[] arr = (Integer[]) elements;
		int[] sumArr = new int[n];
		sumArr[0] = arr[0] == 0 ? -1 : 1;
		int max = arr[0], min = arr[0];

		for (int i = 1; i < n; i++) {
			sumArr[i] = sumArr[i - 1] + (arr[i] == 0 ? -1 : 1);
			if (max < sumArr[i])
				max = sumArr[i];
			if (min > sumArr[i])
				min = sumArr[i];
		}

		int[] hash = new int[max - min + 1];
		int l = hash.length;
		for (int i = 0; i < l; i++)
			hash[i] = -1;

		int maxSize = -1, startIndex = 0;
		;
		for (int i = 0; i < n; i++) {

			// Case 1: when the subarray starts from index 0
			if (sumArr[i] == 0) {
				maxSize = i + 1;
				startIndex = 0;
			}
			// Case 2: fill hash table value. If already filled, then use it
			if (hash[sumArr[i] - min] == -1)
				hash[sumArr[i] - min] = i;
			else {
				if (i - hash[sumArr[i] - min] > maxSize) {
					maxSize = i - hash[sumArr[i] - min];
					startIndex = hash[sumArr[i] - min] + 1;
				}
			}
		}

		if (maxSize == -1)
			System.out.println("no such array..");
		else
			System.out.printf("%d, %d", startIndex, startIndex + maxSize - 1);
	}

	public void sortedSubSequenceOf3(int n) {
		Integer[] integers = (Integer[]) elements;
		int[] smaller = new int[n];
		int min = 0;
		smaller[0] = -1;
		for (int i = 1; i < n; i++)
			if (integers[i] <= integers[min]) {
				min = i;
				smaller[i] = -1;
			} else
				smaller[i] = min;

		int[] greater = new int[n];
		greater[n - 1] = -1;
		int max = n - 1;
		for (int i = n - 2; i >= 0; i--)
			if (integers[i] >= integers[max]) {
				max = i;
				greater[i] = -1;
			} else
				greater[i] = max;

		for (int i = 1; i < n - 1; i++)
			if (smaller[i] != -1 && greater[i] != -1)
				System.out.printf("%d, %d, %d %n", integers[smaller[i]],
						integers[i], integers[greater[i]]);
	}

	public void midSubSequenceOf3(int n) {
		Integer[] integers = (Integer[]) elements;
		int[] smaller = new int[n];
		int min = 0;
		smaller[0] = -1;

		for (int i = 1; i < n; i++)
			if (integers[i] <= integers[min]) {
				min = i;
				smaller[i] = -1;
			} else
				smaller[i] = min;

		int[] invertSmaller = new int[n];
		int invertMin = n - 1;
		invertSmaller[n - 1] = -1;

		for (int i = n - 2; i >= 0; i--)
			if (integers[i] <= integers[invertMin]) {
				invertMin = i;
				invertSmaller[i] = -1;
			} else
				invertSmaller[i] = invertMin;

		for (int i = 1; i < n - 1; i++)
			if (smaller[i] != -1 && invertSmaller[i] != -1)
				System.out.printf("%d, %d, %d %n", integers[smaller[i]],
						integers[i], integers[invertSmaller[i]]);
	}

	public int bitonic(int n) {
		int[] lis = new int[n];
		int[] lds = new int[n];
		for (int i = 0; i < n; i++)
			lis[i] = lds[i] = 1;

		for (int i = 1; i < n; i++)
			for (int j = 0; j < i; j++)
				if (elements[i].compareTo(elements[j]) > 0
						&& lis[i] < lis[j] + 1)
					lis[i] = lis[j] + 1;

		for (int i = n - 2; i >= 0; i--)
			for (int j = n - 1; j > i; j--)
				if (elements[i].compareTo(elements[j]) > 0
						&& lds[i] < lds[j] + 1)
					lds[i] = lds[j] + 1;

		for (int i = 0; i < n; i++)
			lis[i] += lds[i];
		return max(lis) - 1;
	}

	public void printLis(int n) {
		Integer[] integers = (Integer[]) elements;
		int[] lis = new int[n];
		lis[0] = 0;
		int[] prevIndex = new int[n];
		prevIndex[0] = -1;
		int size = 1;

		for (int i = 1; i < n; i++)
			if (integers[i] < integers[lis[0]])
				lis[0] = i;
			else if (integers[i] > integers[lis[size - 1]]) {
				prevIndex[i] = lis[size - 1];
				lis[size++] = i;
			} else {
				int pos = ceilIndexLis(integers[i], -1, size - 1, lis, integers);
				prevIndex[i] = lis[pos - 1];
				lis[pos] = i;
			}

		for (int i = lis[size - 1]; i >= 0; i = prevIndex[i])
			System.out.print(elements[i] + " ");
		System.out.println();
	}

	public void printLds(int n) {
		Integer[] integers = (Integer[]) elements;
		int[] lds = new int[n];
		lds[0] = 0;
		int[] prevIndex = new int[n];
		prevIndex[0] = -1;
		int size = 1;

		for (int i = 1; i < n; i++)
			if (integers[i] > integers[lds[0]])
				lds[0] = i;
			else if (integers[i] < integers[lds[size - 1]]) {
				prevIndex[i] = lds[size - 1];
				lds[size++] = i;
			} else {
				int pos = floorIndexLds(integers, lds, integers[i], -1,
						size - 1);
				prevIndex[i] = lds[pos - 1];
				lds[pos] = i;
			}
		for (int i = lds[size - 1]; i >= 0; i = prevIndex[i])
			System.out.print(elements[i] + " ");
		System.out.println();
	}

	private int floorIndexLds(Integer[] integers, int[] lds, Integer key,
			int l, int h) {
		int m;
		while (h - l > 1) {
			m = (h + l) >> 1;
			if (integers[lds[m]] <= key)
				h = m;
			else
				l = m;
		}
		return h;
	}

	private int ceilIndexLis(Integer key, int l, int h, int[] lis,
			Integer[] integers) {

		int m;
		while (h - l > 1) {
			m = (h + l) >> 1;
			if (integers[lis[m]] >= key)
				h = m;
			else
				l = m;
		}
		return h;
	}

	public void oddOccurances(int[] arr) {
		int n = arr.length;
		int x = 0, y = 0;
		int xor = arr[0];

		for (int i = 1; i < n; i++)
			xor = xor ^ arr[i];

		int setBitNo = xor & ~(xor - 1);

		for (int i = 0; i < n; i++)
			if ((arr[i] & setBitNo) >= 1)
				x = x ^ arr[i];
			else
				y = y ^ arr[i];

		System.out.printf("the odd no. are %d, %d %n", x, y);
	}

	public int firstMissingPositiveNo(int[] arr) {
		// move -ve to end of array
		int p = moveNegativeToEnd(arr);
		System.out.println(p);
		System.out.println(Arrays.toString(arr));
		return firstMissingPositiveNo(arr, p);
	}

	private int firstMissingPositiveNo(int[] arr, int i) {
		for (int j = 0; j < i; j++)
			if (Math.abs(arr[j]) - 1 < i && arr[Math.abs(arr[j]) - 1] > 0)
				arr[Math.abs(arr[j]) - 1] = -arr[Math.abs(arr[j]) - 1];

		for (int j = 0; j < i; j++)
			if (arr[j] > 0)
				return j + 1;
		return i + 1;
	}

	private int moveNegativeToEnd(int[] arr) {
		int n = arr.length;
		int j = 0;
		for (int i = 0; i < n; i++)
			if (arr[i] > 0) {
				swap(i, j, arr);
				j++;
			}
		return j;
	}

	private void swap(int i, int j, int[] arr) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public void printDuplicates(int[] arr) {
		int size = arr.length;

		for (int i = 0; i < size; i++)
			if (arr[Math.abs(arr[i])] >= 0)
				arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
			else
				System.out.print(Math.abs(arr[i]) + " ");
	}

	/**
	 * This problem is a variation of standard Longest Increasing Subsequence
	 * (LIS) problem. We need a slight change in the Dynamic Programming
	 * solution of LIS problem. All we need to change is to use sum as a
	 * criteria instead of length of increasing subsequence.
	 * 
	 * @param length
	 * @return Max sum of LIS
	 */
	public int msis(final int length) {
		
		final Integer[] integers = (Integer[]) elements;
		final int[] msis = new int[length];
		for (int i = 0; i < length; i++)
			msis[i] = integers[i];

		for (int i = 1; i < length; i++)
			for (int j = 0; j < i; j++)
				if (integers[i] > integers[j] && msis[i] < msis[j] + integers[i])
					msis[i] = msis[j] + integers[i];
		return max(msis);
	}
	
	/**
	 * Given an array of n non-negative numbers, the task is to find the minimum sum of elements 
	 * (picked from the array) such that at least one element is picked out of every 3 consecutive elements in the array.
	 * 
	 * 
	 * Input : arr[] = {1, 2, 3, 6, 7, 1, 8, 6, 2, 7, 7, 1} 
	 * Output : 7 
	 * 
	 * The result is obtained as sum of 3 + 1 + 2 + 1
	 * 
	 * @return min sum when every 1 element from 3 consecutive elements is taken
	 */
	public int minSumOfConsecutive3() {
		final Integer[] integers = (Integer[]) elements;
		
		if (integers.length > 3) {

			int a = integers[0];
			int b = integers[1];
			int c = integers[2];
			int result = 0;
			
			for (int i = 3; i < integers.length; i++) {
				result = Math.min(Math.min(a, b), c) + integers[i];
				a = b;
				b = c;
				c = result;
			}
			return result;
		} else {
			return integers.length == 1 ? integers[0] : integers.length == 2 ? Math.min(integers[0], integers[1]) : Math.min(Math.min(integers[2], integers[1]), integers[0]);
		}
	}

	/**
	 * This problem is a variation of standard Longest Decreasing Subsequence
	 * (LDS) problem. We need a slight change in the Dynamic Programming
	 * solution of LDS problem. All we need to change is to use sum as a
	 * criteria instead of length of decreasing subsequence.
	 * 
	 * @param length
	 * @return Max sum of LDS
	 */
	public int msds(int length) {
		
		final Integer[] integers = (Integer[]) elements;
		final int[] msds = new int[length];
		for (int i = 0; i < length; i++)
			msds[i] = integers[i];

		for (int i = length - 2; i >= 0; i--)
			for (int j = length - 1; j > i; j--)
				if (integers[i] > integers[j] && msds[i] < msds[j] + integers[i])
					msds[i] = msds[j] + integers[i];
		return max(msds);
	}

	/**
	 * @param k
	 *            size of frame
	 */
	public void maxOfWindow(final int k) {
		
		// Create a Double Ended Queue, Qi that will store indexes of array elements
	    // The queue will store indexes of useful elements in every window and it will
	    // maintain decreasing order of values from front to rear in Qi, i.e., 
	    // arr[Qi.front[]] to arr[Qi.rear()] are sorted in decreasing order
		final DoubleLinkedList<Integer> deque = new DoubleLinkedList<Integer>();
		int i;
		/* Process first k (or first window) elements of array */
		for (i = 0; i < k; i++) {
			// For very element, the previous smaller elements are useless so
	        // remove them from Qi
			while (!deque.isEmpty() && elements[i].compareTo(elements[deque.peekLast()]) >= 0)
				deque.removeLast();
			deque.offer(i); // Add index new element at rear of queue
		}
		
		final int n = elements.length;
		
		// Process rest of the elements, i.e., from arr[k] to arr[n-1]
		for (; i < n; i++) {
			// The element at the front of the queue is the largest element of
	        // previous window, so print it
			System.out.print(elements[deque.peekFirst()] + " ");
			
			// Remove the elements which are out of this window
			while (!deque.isEmpty() && deque.peekFirst() <= i - k)
				deque.removeFirst();
			
			// Remove all elements smaller than the currently
	        // being added element (remove useless elements)
			while (!deque.isEmpty() && elements[i].compareTo(elements[deque.peekLast()]) >= 0)
				deque.removeLast();
			
			// Add index of current element at the rear of Qi
			deque.offer(i);
		}

		System.out.println(elements[deque.peekFirst()]);
	}

	public int minJumps() {
		
		final Integer[] integers = (Integer[]) elements;
		int n = elements.length;

		final int[] jump = new int[n];
		if (n == 0 || integers[0] == 0)
			return Integer.MAX_VALUE;
		jump[0] = 0;

		for (int i = 1; i < n; i++) {
			jump[i] = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (i <= j + integers[j] && jump[j] != Integer.MAX_VALUE) {
					// second check is for in case element is zero
					jump[i] = jump[j] + 1;
					break;
				}
			}
		}
		return jump[n - 1];
	}

	public void greaterNextElement() {
		
		final int n = elements.length;
		final DoubleLinkedList<E> stack = new DoubleLinkedList<E>();

		if (n == 0) return;
		
		E next, curr;
		stack.addFirst(elements[0]); // add first element to stack

		for (int i = 1; i < n; i++) {
			next = elements[i];
			
			if (!stack.isEmpty()) {
				curr = stack.poll(); // poll last element and compare with next one
				while (curr.compareTo(next) < 0) {
					System.out.printf("the next element for %s is %s %n", curr.toString(), next.toString());
					if (stack.isEmpty()) break; // break if stack is empty, else continue till all elements of stack are smaller than next
					curr = stack.poll();
				}
				// if curr is larger than next, push back curr, better option is
				// to just peek element, not poll them, if they are smaller only
				// then poll them
				if (curr.compareTo(next) > 0) stack.addFirst(curr);
			}
			stack.addFirst(next); // add next to stack
		}
		
		// remaining elements have no greater element in right side
		while (!stack.isEmpty())
			System.out.printf("the next element for %s is %s %n", stack.poll().toString(), null);
	}
	
	// arr[0 .. low] should be 0
	// arr[low .. mid] should be 1
	// arr[high .. n] should be 2
	// we need to sort elements between arr[mid .. high]
	public void sort012(final int[] arr) {
		
		int low = 0;
		int mid = 0;
		int high = arr.length - 1;
		
		// loop till mid is low then high element
		while (mid <= high) {
			switch (arr[mid]) {
			case 0:
				swap(mid++, low++, arr); // if middle element is 0, swap with low and increase both mid & low
				break;
			case 1:
				mid++; // middle should be 1
				break;
			case 2:
				swap(mid, high--, arr); // if mid is 2, move it to high and decrease high, but keep mid as it is, cause it can be that high is already 2
				break;
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * Given an array of positive numbers, find the maximum sum of a subsequence
	 * with the constraint that no 2 numbers in the sequence should be adjacent
	 * in the array. So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10
	 * 7 should return 15 (sum of 3, 5 and 7).Answer the question in most
	 * efficient way.
	 * 
	 * @return
	 */
	
	// Loop for all elements in arr[] and maintain two sums incl and excl where
	// incl = Max sum including the previous element and excl = Max sum
	// excluding the previous element. Max sum excluding the current element
	// will be max(incl, excl) and max sum including the current element will be
	// excl + current element (Note that only excl is considered because
	// elements cannot be adjacent). At the end of the loop return max of incl
	// and excl.
	public int maxSumWithOutAdj() {
		final Integer[] e = (Integer[]) elements;
		final int len = e.length;
		if (len == 0) return 0;
		
		int sumIncl = e[0];
		int sumExcl = 0;
		int maxSum = 0;

		for (int i = 1; i < len; i++) {
			// max excluding i
			maxSum = Math.max(sumExcl, sumIncl);
			// max including i
			sumIncl = sumExcl + e[i];
			sumExcl = maxSum;
		}
		return Math.max(sumExcl, sumIncl);
	}

	public void rotateArrLeft(int size, int d) {
		// if array to be rotated by equal size or 0 then return..
		if (d == 0 || d == size)
			return;

		if (size - d == d) {
			swap(0, size - d, d);
			return;
		}
		// first part is shorter..
		if (d < size - d) {
			swap(0, size - d, d);
			rotateArrLeft(size - d, d);
		} else {
			swap(0, d, size - d);
			rotateArrLeft(d, 2 * d - size);
		}
	}

	private void swap(int i, int j, int d) {
		for (int k = 0; k < d; k++)
			swap(i + k, j + k);
	}

	public void rotateArrLeftItr(int length, int d) {
		if (d == 0 || d == length)
			return;

		int i = d;
		int j = length - d;

		while (i != j) {
			if (i < j) {
				swap(d - i, d + j - i, i);
				j -= i;
			} else {
				swap(d - i, d, j);
				i -= j;
			}
		}
		swap(d - i, d, i);
	}

	public void rotateArrWithReversal(int n, int d) {
		reverseArr(0, d - 1);
		reverseArr(d, n - 1);
		reverseArr(0, n - 1);
	}

	private void reverseArr(int i, int j) {
		E tmp;
		while (i < j) {
			tmp = elements[i];
			elements[i] = elements[j];
			elements[j] = tmp;
			i++;
			j--;
		}
	}

	public void reverseArrayRecur(int i, int j) {
		if (i >= j)
			return;
		E tmp = elements[i];
		elements[i] = elements[j];
		elements[j] = tmp;
		reverseArrayRecur(++i, --j);
	}

	public void majorElement(int length) {
		E majorE = findMajor(length);

		if (isMajor(majorE, length))
			System.out.println("major element is : " + majorE);
		else
			System.out.println("no major element");
	}

	private boolean isMajor(E majE, int length) {
		int count = 0;
		for (int i = 0; i < length; i++)
			if (majE.equals(elements[i]))
				count++;
		if (count >= length >> 1)
			return true;
		else
			return false;
	}

	private E findMajor(int length) {
		int majorIndex = 0, count = 1;

		for (int i = 1; i < length; i++) {
			if (elements[majorIndex].equals(elements[i]))
				count++;
			else
				count--;
			if (count == 0) {
				majorIndex = i;
				count = 1;
			}
		}
		return elements[majorIndex];
	}

	public int matrixChainOrder() {
		final Integer[] i = (Integer[]) elements;
		return matrixChainOrder(i, 1, elements.length - 1);
	}

	private int matrixChainOrder(final Integer[] arr, final int j, final int k) {
		if (j == k) return 0;
		
		int min = Integer.MAX_VALUE; int count;

		for (int l = j; l < k; l++) {
			// count from i to K, k+1 to J & I*K*J elements
			count = matrixChainOrder(arr, j, l) + matrixChainOrder(arr, l + 1, k) + arr[j - 1] * arr[l] * arr[k];
			if (count < min) min = count;
		}
		return min;
	}

	public int matrixChainOpt() {
		final Integer[] arr = (Integer[]) elements;
		final int len = arr.length;
		
		final int[][] res = new int[len][len];
		final int[][] bracket = new int[len][len];

		for (int i = 2; i < len; i++) {
			for (int j = 1; j < len - i + 1; j++) {
				int k = i + j - 1; // end condition of loop
				res[j][k] = Integer.MAX_VALUE;
				for (int l = j; l <= k - 1; l++) {
					int count = res[j][l] + res[l + 1][k] + arr[j - 1] * arr[l] * arr[k];
					if (count < res[j][k]) {
						res[j][k] = count; bracket[j][k] = l;
					}
				}
			}
		}
		return res[1][len - 1];
	}

	public void palindromePartitioning(final char[] str) {
		final int len = str.length;
		// Create two arrays to build the solution in bottom up manner 
		// count[i] = Minimum number of cuts needed for palindrome partitioning of substring str[0..i]
	    // palindrome[i][j] = true if substring str[i..j] is palindrome, else false
	    // Note that count[i] is 0 if palindrome[0][i] is true
		final int[][] count = new int[len][len];
		final boolean[][] palindrome = new boolean[len][len];
		
		for (int i = 0; i < len; i++) palindrome[i][i] = true; // A single character string is always palindrome
		
		// len is substring length. Build the solution in bottom up manner by considering all substrings of length starting from 2 to n.
		for (int i = 2; i <= len ; i++) {
			for(int j = 0; j < len - i + 1; j++) {
				int k = i + j -1; // end condition
				// If L is 2, then we just need to compare two characters. 
				// Else need to check two corner characters and value of P[i+1][j-1]
				if (i == 2) palindrome[j][k] = str[j] == str[k]; 
				else palindrome[j][k] = str[j] == str[k] && palindrome[j+1][k-1]; // next from start and previous from end must be equal
				// IF str[i..j] is palindrome, then C[i][j] is 0
				if (palindrome[j][k]) {
					count[j][k] = 0; // if string is palindrome then count is 0
				} else {
					count[j][k] = Integer.MAX_VALUE;
					for (int l = j; l < k; l++) {
						count[j][k] = Math.min(count[j][k], count[j][l] + count[l+1][k] + 1);
					}
				}
			}
		}
		System.out.println("Min Cut : " + count[0][len-1]);
	}
	
	/**
	 * @param str to calculate longest palindromic subsequence of string
	 */
	public void lps(final String str) {
		
		final int n = str.length(); // length of string
		final int[][] lpsArr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			lpsArr[i][i] = 1; // every string is palindromic to itself
		}
		
		// outer loop for substring length, we will check longest palindromic in every substring starting with substring of length 2 up to full string length
		for (int i = 2; i <=n; i++) {
			
			// loop up to last 
			for (int j = 0; j < n - i + 1; j++) {
				int k = j + i - 1; // loop from starting index to length of substring
				
				if (str.charAt(j) == str.charAt(k) && i == 2) {
					lpsArr[j][k] = 2;
				} else if (str.charAt(j) == str.charAt(k)) {
					lpsArr[j][k] = lpsArr[j + 1][k -1] + 2;
				} else {
					lpsArr[j][k] = Math.max(lpsArr[j][k - 1], lpsArr[j + 1][k]);
				}
			}
		}
		
		System.out.println("Longest Palindromic Subsequence is : " + lpsArr[0][n-1]);
	}
	
	public void eggDropProblem(final int eggs, final int floors) {
		
	}

	/**
	 * Given an array of integers, find the first repeating element in it. We
	 * need to find the element that occurs more than once and whose index of
	 * first occurrence is smallest.
	 * 
	 * 
	 * We can Use Hashing to solve this in O(n) time on average. The idea is to
	 * traverse the given array from right to left and update the minimum index
	 * whenever we find an element that has been visited on right side
	 */
	public void firstRepeatingElement() {

		final Integer[] integers = (Integer[]) elements;
		final HashMap<Integer, Object> hashMap = new HashMap<Integer, Object>();

		int min = -1;
		for (int i = elements.length - 1; i >= 0; i--)
			if (hashMap.containsKey(integers[i]))
				min = i;
			else
				hashMap.put(integers[i], new Object());

		// Print the result
		if (min != -1)
			System.out.println("The first repeating element is " + integers[min]);
		else
			System.out.println("There are no repeating elements");
	}

	/**
	 * Given a sorted array (sorted in non-decreasing order) of positive
	 * numbers, find the smallest positive integer value that cannot be
	 * represented as sum of elements of any subset of given set. Expected time
	 * complexity is O(n).
	 * 
	 * @return smallest not possible sum
	 */
	public int smallestNotPossibleSum() {
		
		final Integer[] integers = (Integer[]) elements;
		Arrays.sort(integers);
		System.out.println(Arrays.toString(integers));
		int sum = 1;
		
		for (int i = 0; i < elements.length && sum >= integers[i]; i++)
			sum += integers[i];

		return sum;
	}

	/**
	 * Let the current element traversed in ar1[] be x, in ar2[] be y and in
	 * ar3[] be z. We can have following cases inside the loop.
	 * 
	 * 1) If x, y and z are same, we can simply print any of them as common
	 * element and move ahead in all three arrays.
	 * 
	 * 2) Else If x < y, we can move ahead in ar1[] as x cannot be a common
	 * element
	 * 
	 * 3) Else If y < z, we can move ahead in ar2[] as y cannot be a common
	 * element
	 * 
	 * 4) Else (We reach here when x > y and y > z), we can simply move ahead in
	 * ar3[] as z cannot be a common element.
	 * 
	 * @param a
	 *            first array
	 * @param b
	 *            second array
	 * @param c
	 *            third array
	 */
	public void commonElements(int[] a, int[] b, int[] c) {
		int i = 0, j = 0, k = 0;

		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		Arrays.sort(b);
		System.out.println(Arrays.toString(b));
		Arrays.sort(c);
		System.out.println(Arrays.toString(c));
		while (i < this.n && j < this.n && k < this.n) {
			if (a[i] == b[j] && b[j] == c[k]) {
				System.out.println("common element is : " + a[i]);
				i++;
				j++;
				k++;
			} else if (a[i] < b[j])
				i++;
			else if (b[j] < c[k])
				j++;
			else
				k++;
		}
	}
	
	/**
	 * @param a
	 * @param b
	 * @return longest common subsequence in given char arrays
	 */
	public int lcs(final char[] a, final char b[]) {
		final int n = a.length;
		final int m = b.length;
		
		final int[][] arr = new int[n+1][m+1];
		
		for (int i = 0; i <= a.length; i++) {
			for (int j = 0; j <= b.length; j++) {
				
				if (i == 0 || j == 0) arr[i][j] = 0; // base case
				else if (a[i-1] == b[j-1]) arr[i][j] = arr[i-1][j-1] + 1; // if element match increase count
				else arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]); // get max of previous calculated values
			}
		}
		return arr[n][m];
	}
	
	
	/**
	 * @param a
	 * @param b
	 * @return longest repeating subsequence
	 */
	public int lrs(final char[] a, final char b[]) {
		final int n = a.length;
		final int m = b.length;
		
		final int[][] arr = new int[n+1][m+1];
		
		for (int i = 0; i <= a.length; i++) {
			for (int j = 0; j <= b.length; j++) {
				
				if (i == 0 || j == 0) arr[i][j] = 0; // base case
				else if (a[i-1] == b[j-1] && i != j) arr[i][j] = arr[i-1][j-1] + 1; // if element match increase count
				else arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]); // get max of previous calculated values
			}
		}
		return arr[n][m];
	}
	
	/**
	 * @param a
	 * @param b
	 * @return
	 */
	public String lrNonOverlappingSubSeq(final char[] a, final char b[]) {
		
		final int n = a.length;
		final int m = b.length;
		
		final int[][] arr = new int[n+1][m+1];
		
		int resLength = 0;
		int index = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = i+1; j <= m; j++) {
				
				if (a[i-1] == b[j-1] && arr[i-1][j-1] < (j-i)) {
					arr[i][j] = arr[i-1][j-1] + 1; 
					
					if (arr[i][j] > resLength) {
						resLength = arr[i][j];
						index = Math.max(i, index);
					}
				}
			}
		}
		
		System.out.println("Longest Non-Overlapping Sub Seq : " + resLength);
		
		final char[] lrs = new char[resLength];
		
		for (int i = resLength - 1 ; i >= 0; i--) {
			lrs[i] = a[index-- - 1];
		}
		
		return new String(lrs);
	}
	
	/**
	 * @param n number of stairs
	 * @param m max number of stairs can be covered in 1 steps
	 * @return total no. of ways to reach top
	 */
	// this is like fibbonacci series
	public long countSteps(final int n, final int m) {

		final long[] res = new long[n+1];
		
		res[0] = 1; 
		res[1] = 1;
		
		for(int i = 2; i <= n ;i++) {
			for (int j = 1; j <=m && j <=i; j++)
				res[i] += res[i-j];
		}
		return res[n];
	}

	/**
	 * @param coins array of coins
	 * @param n amount to be made by using these coins
	 * @return numbers of ways to create n using coins
	 */
	public long coinChange(final int[] coins, final int n) {

		final int m = coins.length;
		final long[] table = new long[n+1];
		
		table[0] = 1; // base case
		
		for(int i = 0; i < m; i ++) {
			for (int j = coins[i]; j <=n ; j++) {
				table[j] += table[j - coins[i]];
			}
		}
		return table[n];
	}
	
	/**
	 * @param coins array of coins
	 * @param value amount be to made
	 * @return minimum number of coins required to create value
	 */
	public int minCoin(final int[] coins, final int value) {

		final int m = coins.length;
		final int[] table = new int[value+1];
		
		Arrays.fill(table, Integer.MAX_VALUE);
		
		table[0] = 0; // base case
		
		// loop for all values to find minimum number of coins required
		for(int i = 1; i <= value; i ++) {
			for (int j = 0; j < m ; j++) {
				// Go through all coins smaller than i
				if (coins[j] <= i) {
					final int subRes = table[i-coins[j]]; // minimum number till table[i - (value of coin)]
					if (subRes != Integer.MAX_VALUE && subRes + 1 < table[i]) {
						table[i] = subRes + 1;
					}
				}
			}
		}
		return table[value];
	}
	
	/**
	 * This problem is a variation of coin change problem and can be solved in O(n) time and O(n) auxiliary space.
	 * 
	 * The idea is to create a table of size n+1 to store counts of all scores from 0 to n. 
	 * For every possible move (3, 5 and 10), increment values in table.
	 * 
	 * @param scores array of possible scores
	 * @param n final score to create
	 * @return numbers of ways to create n using scores
	 */
	public int countWaysForScore(final int[] scores, final int n) {
		
		final int[] ways = new int[n+1];
		
		ways[0] = 1; // base case, 1 ways to make zero
		
		for(int i = 0; i < scores.length; i++) {
			for(int j = 1; j <= n; j++) {
				if (scores[i] <= j) {
					ways[j] += ways[j - scores[i]]; // ways to create is the same ways to create scores[i] value
				}
			}
		}
		return ways[n];
	}
	
	/**
	 * @param numbers array of numbers
	 * @param sum sum to be calculated using numbers
	 * @return numbers ways to claculate sum from given array of numbers
	 */
	public int countWaysToSum(final int[] numbers, final int sum) {
		
		final int[] ways = new int[sum+1];
		
		ways[0] = 1; // base case, 1 ways to make zero
		
		for(int i = 1; i <= sum; i++) {
			for(int j = 0; j < numbers.length; j++) {
				if (numbers[j] <= i) {
					ways[i] += ways[i - numbers[j]]; // ways to create is the same ways to create scores[i] value
				}
			}
		}
		return ways[sum];
	}
	
	/**
	 * Number of n-bit strings with 0 ones = nC0
	 * Number of n-bit strings with 1 ones = nC1
	 * ...
	 * Number of n-bit strings with k ones = nCk
	 * ...
	 * Number of n-bit strings with n ones = nCn
	 * 
	 * 
	 * No. of 2*n bit strings such that first n bits have 0 ones & last n bits have 0 ones = nC0 * nC0
	 * No. of 2*n bit strings such that first n bits have 1 ones & last n bits have 1 ones = nC1 * nC1
	 * 
	 * 
	 * Result = nC0*nC0 + nC1*nC1 + ... + nCn*nCn 
	 * 		  = ∑(nCk)2 
	 * 			0 <= k <= n
	 * 
	 * @param n
	 * @return numbers of bits where we have same number of 1's and 0's in first and last n in binary sequence of 2n
	 * 
	 * Eg : Input:  n = 2 
	 * Output: 2 
	 * There are 6 sequences of length 2*n, the sequences are 0101, 0110, 1010, 1001, 0000 and 1111
	 */
	public int countBinaySeq(final int n) {
		
		int ncr = 1; 
		int res = 1;
		
		for(int i = 1; i <= n; i++) {
			// Compute nCr using nC(r-1) : nCr/nC(r-1) = (n+1-r)/r;
			ncr = (ncr * (n-i+1))/i;
			res += ncr*ncr;
		}
		return res;
	}
	
	/**
	 * The idea is to fix first and last bits and then recur for n-1, i.e., remaining 2(n-1) bits. 
	 * There are following possibilities when we fix first and last bits. 
	 * 1) First and last bits are same, remaining n-1 bits on both sides should also have the same sum. 
	 * 2) First bit is 1 and last bit is 0, sum of remaining n-1 bits on left side should be 1 less than the sum n-1 bits on right side. 
	 * 3) First bit is 0 and last bit is 1, sum of remaining n-1 bits on left side should be 1 more than the sum n-1 bits on right side.
	 * 			  		// When first and last bits are same
	 * 					// there are two cases, 00 and 11 
     * count(n, diff) =  2*count(n-1, diff) +    
     * 					// When first bit is 1 and last bit is 0 count(n-1, diff-1) +
     * 					// When first bit is 0 and last bit is 1 count(n-1, diff+1)
     * 
     * What should be base cases? 
     * // When n == 1 (2 bit sequences)
     * 	1) If n == 1 and diff == 0, return 2 
     * 	2) If n == 1 and |diff| == 1, return 1 
     * 	// We can't cover difference of more than n with 2n bits 
     * 	3) If |diff| > n, return 0
	 * 
	 * @param n
	 * @return numbers of bits where we have same number of 1's and 0's in first and last n in binary sequence of 2n
	 * 
	 * Eg : Input:  n = 2 
	 * Output: 2 
	 * There are 6 sequences of length 2*n, the sequences are 0101, 0110, 1010, 1001, 0000 and 1111
	 */
	public int countBinaySequence(final int n) {
		
		final int[][] lookUp = new int[n+1][2*n + 1];
		for (int[] is : lookUp) {
			Arrays.fill(is, -1);
		}
		
		return countBinaySequenceUtil(n, lookUp, 0);
	}
	
	private int countBinaySequenceUtil(final int n, final int[][] lookUp, final int diff) {
		
		if (Math.abs(diff) > n) return 0; // if diff is greater than n, then no possible way
		
		if (n == 1 && diff == 0) return 2; // if n == 1 i.e. 2 bit long sequence
		
		if (n == 1 && Math.abs(diff) == 1) return 1;
		
		// if already calculated then return value from loopUp table
		if (lookUp[n][n+diff] != -1) return lookUp[n][n+diff];
		
		int res = countBinaySequenceUtil(n-1, lookUp, diff-1) + // first bit is 1, decrease diff by 1 
					countBinaySequenceUtil(n-1, lookUp, diff+1) +  // first bit is 0, increase diff by 1
					2 * countBinaySequenceUtil(n-1, lookUp, diff); // same bits either 1 or 0
		
		return lookUp[n][n + diff] = res;
	}
	
	/**
	 * @param coeff
	 * @param rhs
	 * @return number of solution possible for linear equation represented by coeff array
	 */
	public int countLinearEqSolution(final int[] coeff, final int rhs) {
		return n;
	}

	public int maxSumIS(int[] e) {
		final int n = e.length;
		final int[] msis = new int[n];
		
		for (int i = 0; i < n; i++) {
			msis[i] = e[i]; // copy all elements to msis array
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (e[i] > e[j] && msis[i] < msis[j] + e[i]) {
					// element at I is larger than element at J, but MSIS is smaller
					msis[i] = msis[j] + e[i];
				}
			}
		}
		
		return max(msis);
	}

	/**
	 * Working of Algorithm :
	 * 
	 * Find minimum and maximum values in array. 
	 * Let the minimum and maximum values be ‘min’ and ‘max’ respectively. Also find range as ‘max-min-1’.
	 * Set up an array of initially empty “pigeonholes” the same size as of the range. 
	 * Visit each element of the array and then put each element in its pigeonhole. 
	 * An element arr[i] is put in hole at index arr[i] – min. 
	 * Start the loop all over the pigeonhole array in order and put the elements from
	 * non- empty holes back into the original array.
	 * @param arr 
	 */
	public void pigeonSort(final int[] arr) {
		
		final int n = arr.length;
		
		int min = arr[0];
		int max = arr[0];
		
		for (int i = 1; i < n; i++) {
			if (arr[i] < min) min = arr[i];
			if (arr[i] > max) max = arr[i];
		}
		
		final int range = max - min + 1;
		
		@SuppressWarnings("unchecked")
		final List<Integer>[] pigoenHole = new List[range];
		
		for (int i = 0; i < n; i++) {
			if (pigoenHole[arr[i]-min] == null) pigoenHole[arr[i]-min] = new LinkedList<>();
			pigoenHole[arr[i]-min].add(arr[i]);
		}
		
		int index = 0;
		for (int i = 0; i < pigoenHole.length; i++) {
			if (pigoenHole[i] != null) {
				
				for (Integer e : pigoenHole[i]) {
					arr[index++] = e; 
				}
			}
		}
	}

	/**
	 * @param k size of window
	 */
	public void printKMax(final int k) {
		
		Integer max = Integer.MIN_VALUE;
		
		for (int i = 0; i <= elements.length - k; i++) {
			max = (Integer) elements[i]; // let first be max
			
			// now loop for next K elements and print max in K elements
			
			for (int j = 1; j < k; j++) {
				if (max < (Integer) elements[i+j])
					max = (Integer) elements[i+j];
			}
			System.out.print(max +" ");
		}
		
		System.out.println();
		
	}
	
	// The idea is to compute value of a rotation using value of previous rotation. When we rotate an array by one, following changes happen in sum of i*arr[i].
	// 1) Multiplier of arr[i-1] changes from 0 to n-1, i.e., arr[i-1] * (n-1) is added to current value.
	// 2) Multipliers of other terms is decremented by 1. i.e., (cum_sum – arr[i-1]) is subtracted from current value where cum_sum is sum of all numbers.
	public void maxSumInRotation() {
		
		final Integer[] e = (Integer[])elements;
		final int length = e.length;
		
		int currSum = 0;
		int currVal = 0;
		int maxSum = 0;
		
		for (int i = 0; i < length; i++) {
			currSum += e[i];
			currVal += i*e[i];
		}
		
		maxSum = currVal;
		
		for (int i = 1; i < length; i++) {
			int nextVal = currVal - (currSum - e[i-1]) + e[i-1]*(length-1);
			maxSum = Math.max(nextVal, currVal);
		}
		
		System.out.println("max sum in rotation is : " + maxSum);
	}
	
	public void decodeMsg(final int[] decodedArray) {
		final int len = decodedArray.length;
		
		if (len == 0 || len == 1) {
			System.out.println("Possible messages : " + 1);
			return;
		}
		
		final int[] count = new int[len+1];
		count[0] = 1;
		count[1] = 1;
		
		for (int i = 2; i <= len; i++) {
			
			// if next is not zero, it is part of message
			if (decodedArray[i-1] > 0) count[i] += count[i-1];
			
			// If second last digit is smaller than 2 and last digit is smaller than 7, then last two digits form a valid character
			if (decodedArray[i-2] < 2 || (decodedArray[i-2] == 2 && decodedArray[i-1] < 7)) count[i] += count[i-2];
		}
		System.out.println("Possible messages : " + count[len]);
	}
	
	
	/**
	 * to find maximum J-I index such that arr[j] > arr[i]
	 */
	
	// To solve this problem, we need to get two optimum indexes of arr[]: left
	// index i and right index j. For an element arr[i], we do not need to
	// consider arr[i] for left index if there is an element smaller than arr[i]
	// on left side of arr[i]. Similarly, if there is a greater element on right
	// side of arr[j] then we do not need to consider this j for right index. So
	// we construct two auxiliary arrays LMin[] and RMax[] such that LMin[i]
	// holds the smallest element on left side of arr[i] including arr[i], and
	// RMax[j] holds the greatest element on right side of arr[j] including
	// arr[j]. After constructing these two auxiliary arrays, we traverse both
	// of these arrays from left to right. While traversing LMin[] and RMax[] if
	// we see that LMin[i] is greater than RMax[j], then we must move ahead in
	// LMin[] (or do i++) because all elements on left of LMin[i] are greater
	// than or equal to LMin[i]. Otherwise we must move ahead in RMax[j] to look
	// for a greater j – i value.
	public void maxIndexDiff() {
		
		final Integer[] arr = (Integer[])elements;
		final int len = arr.length;
		
		final int[] lMin = new int[len];
		final int[] rMax = new int[len];
		
		lMin[0] = arr[0]; // no element smaller than arr[0] on left side, so store this
		for (int i = 1; i < len; i++) {
			lMin[i] = Math.min(arr[i], lMin[i-1]);
		}
		System.out.println("Min arr : " + Arrays.toString(lMin));
		
		rMax[len-1] = arr[len-1]; // no element larger than arr[len-1] on right side, so store this
		for (int i = len-2; i >= 0; i--) {
			rMax[i] = Math.max(arr[i], rMax[i+1]);
		}
		System.out.println("Max arr : " + Arrays.toString(rMax));
		
		int i = 0;
		int j = 0;
		int maxDiff = 0;
		while (i < len && j < len) {
			if (lMin[i] < rMax[j]) {
				maxDiff = Math.max(maxDiff, j-i);
				j++; // increase to check next value in rMax
			} else i++; // increase i, cause our first value is greater than rMax[j]
		}
		
		System.out.println("Max diff is : " + maxDiff);
	}
	
	
	/**
	 * Following are the two main steps to solve this problem:
	 * 1) Calculate sum of the array. If sum is odd, there can not be two subsets with equal sum, so return false.
	 * 2) If sum of array elements is even, calculate sum/2 and find a subset of array with sum equal to sum/2. 
	 */
	
	// Let isSubsetSum(arr, n, sum/2) be the function that returns true if there is a subset of arr[0..n-1] with sum equal to sum/2

	// The isSubsetSum problem can be divided into two subproblems
	// a) isSubsetSum() without considering last element (reducing n to n-1)
	// b) isSubsetSum considering the last element (reducing sum/2 by arr[n-1] and n to n-1)
	// If any of the above the above subproblems return true, then return true. 
	// isSubsetSum (arr, n, sum/2) = isSubsetSum (arr, n-1, sum/2) || isSubsetSum (arr, n-1, sum/2 - arr[n-1])
	public void partioningProblem() {
		
		final Integer[] arr = (Integer[]) elements;
		final int n = arr.length;
		
		int sum = 0; // initialize sum as zero
		
		for(int i = 0; i < n; i++) sum += arr[i]; // take sum of array
		
		int sumValue = sum >> 1; // half the sum
		
		if (sumValue%2 != 0) {
			// can't divide odd no.
			System.out.println("No solution possible");
			return;
		}
		
		final boolean[][] result = new boolean[sumValue+1][n+1];
		
		for (int i = 0; i <= n; i++) {
			result[0][i] = true; //initialize 1st row as true
		}
		
		for (int i = 1; i <= sumValue; i++) {
			result[i][0] = false; // initialize 1st column as false but result[0][0]
		}
		
		for (int i = 1; i <= sumValue; i++) {
			for (int j = 1; j <= n; j++) {
				result[i][j] = result[i][j-1];
				if (i >= arr[j-1]) {
					result[i][j] = result[i][j] || result[i-arr[j-1]][j-1];
				}
			}
		}
		System.out.println("Solution exists : " + result[sumValue][n]);
	}
	
	/**
	 * Given weights and values of n items, put these items in a knapsack of
	 * capacity W to get the maximum total value in the knapsack. In other
	 * words, given two integer arrays val[0..n-1] and wt[0..n-1] which
	 * represent values and weights associated with n items respectively. Also
	 * given an integer W which represents knapsack capacity, find out the
	 * maximum value subset of val[] such that sum of the weights of this subset
	 * is smaller than or equal to W. You cannot break an item, either pick the
	 * complete item, or don’t pick it (0-1 property).
	 * 
	 * @param value
	 *            arr of values of item
	 * @param weight
	 *            arr of weights of item
	 * @param w
	 *            weight required in knapsack
	 */
	
	// To consider all subsets of items, there can be two cases for every item: (1) the item is included in the optimal subset, (2) not included in the optimal set.
	// Therefore, the maximum value that can be obtained from n items is max of following two values.
	// 1) Maximum value obtained by n-1 items and W weight (excluding nth item).
	// 2) Value of nth item plus maximum value obtained by n-1 items and W minus weight of the nth item (including nth item).

	// If weight of nth item is greater than W, then the nth item cannot be included and case 1 is the only possibility.
	public int knapSack(final int[] value, final int[] weight, final int w) {
		
		final int n = value.length;
		
		if (n == 0 || w == 0) {
			return 0;
		}
		
		final int[][] result = new int[n+1][w+1];
		
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= w; j++) {
				//base case, a zero if either i or j is zero
				if (i == 0 || w == 0) result[i][w] = 0; // no need for this in JAVA, since all elements of int[] are 0 by default
				
				// weight if i index element is less than required weight
				else if (weight[i-1] <= w) {
					// result is max of (current value + removing last element) OR including last element
					result[i][w] = Math.max(value[i-1] + result[i-1][w - weight[i-1]], result[i-1][w]);
				} else {
					result[i][w] = result[i-1][w]; // copy result of previous row, same column
				}
			}
		}
		return result[n][w];
	}
	
	
	/**
	 * Problem statement: Consider a row of n coins of values v1 . . . vn, where
	 * n is even. We play a game against an opponent by alternating turns. In
	 * each turn, a player selects either the first or last coin from the row,
	 * removes it from the row permanently, and receives the value of the coin.
	 * Determine the maximum possible amount of money we can definitely win if
	 * we move first. Note: The opponent is as clever as the user.
	 * 
	 * @return
	 */
	
	// F(i, j)  represents the maximum value the user can collect from i'th coin to j'th coin.

	// F(i, j)  = Max(V[i] + min(F(i+2, j), F(i+1, j-1) ), V[j] + min(F(i+1, j-1), F(i, j-2) )) 
	// Base Cases
	// F(i, j)  = Vi           If j == i
	// F(i, j)  = max(Vi, Vj)  If j == i+1
	public int optimalStrategyOfGame(final int[] coins) {
		
		final int n = coins.length;
		final int[][] result = new int[n][n];
		int x, y, z = 0;
		
		// Fill table using above recursive formula. Note that the table is filled in diagonal fashion 
		// (similar to http://goo.gl/PQqoS), from diagonal elements to table[0][n-1] which is the result.
		for (int k = 0; k < n; k++) {
			for (int i = 0, j = k; j < n; i++, j++) {
				// Here x is value of F(i+2, j), y is F(i+1, j-1) and z is F(i, j-2) in above recursive formula
	            x = ((i+2) <= j) ? result[i+2][j] : 0;
	            y = ((i+1) <= (j-1)) ? result[i+1][j-1] : 0;
	            z = (i <= (j-2))? result[i][j-2]: 0;
	 
	            result[i][j] = Math.max(coins[i] + Math.min(x, y), coins[j] + Math.min(y, z));
			}
		}
		
		return result[0][n-1];
	}
	
	/**
	 * The span Si of the stock’s price on a given day i is defined as the
	 * maximum number of consecutive days just before the given day, for which
	 * the price of the stock on the current day is less than or equal to its
	 * price on the given day. For example, if an array of 7 days prices is
	 * given as {100, 80, 60, 70, 60, 75, 85}, then the span values for
	 * corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
	 */
	public void stockSpanProblem() {
		final Integer[] i = (Integer[]) elements;
		final int n = i.length;
		final int[] span = new int[n];
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();
		
		stack.addFirst(0); // first index
		span[0] = 1; // span of first element is 1 day at minimum
		
		for (int j = 1; j < i.length; j++) {
			
			// Pop elements from stack while stack is not empty and top of stack is smaller than price[i]
			while(!stack.isEmpty() && i[stack.peek()] <= i[j]) stack.poll();
			
			// If stack becomes empty, then price[i] is greater than all
			// elements on left of it, i.e., price[0], price[1],..price[i-1].
			// Else price[i] is greater than elements after top of stack
			span[j] = stack.isEmpty() ? j+1: j-stack.peek();
			
			// push next element to stack
			stack.addFirst(j);
		}
	}
	
	/**
	 * The following information can be extracted from the problem statement to make it simpler: 
	 * 1) Two assembly lines, 1 and 2, each with stations from 1 to n. 
	 * 2) A car chassis must pass through all stations from 1 to n in order(in any of the two assembly lines). i.e. it cannot jump from station i to station j if they are not at one move distance.
	 * 3) The car chassis can move one station forward in the same line, or one station diagonally in the other line. It incurs an extra cost ti, j to move to station j from line i. No cost is incurred for movement in same line.
	 * 4) The time taken in station j on line i is ai, j.
	 * 5) S [i,j] represents a station j on line i.
	 * 
	 * @param carAssembly time taken at each car assembly unit
	 * @param time time taken to jump from one car assembly line to other
	 * @param entry entry time
	 * @param exit exit time
	 */
	// T1(j) indicates the minimum time taken by the car chassis to leave station j on assembly line 1.

	// T2(j) indicates the minimum time taken by the car chassis to leave station j on assembly line 2.
	public void assemblyLineScheduling(final int[][] carAssembly, final int[][] time, final int[] entry, final int[] exit) {
		
		final int units = carAssembly[0].length;
		final int[] t1 = new int[units];
		final int[] t2 = new int[units];
		
		t1[0] = carAssembly[0][0] + entry[0]; // time for entry & first unit time
		t2[0] = carAssembly[1][0] + entry[1]; // time for entry & first unit time
		
		for (int i = 1; i < units; i++) {
			// min cost is cost of either Min((previous time in same + time of current) or (previous time in other + time of current + jump time)) 
			t1[i] = Math.min(t1[i-1] + carAssembly[0][i], t2[i-1] + time[1][i] + carAssembly[0][i]);
			t2[i] = Math.min(t2[i-1] + carAssembly[1][i], t1[i-1] + time[0][i] + carAssembly[1][i]);
		}
		
		System.out.println("Min time for car manufacturing is : " + Math.min(t1[units-1] + exit[0], t2[units-1] + exit[1]));
	}

	/**
	 * @param jobs
	 *            Array of jobs where every job has a deadline and associated
	 *            profit if the job is finished before the deadline. Every job
	 *            takes single unit of time, so the minimum possible deadline
	 *            for any job is 1.
	 *            
	 *            
	 *  1) Sort all jobs in decreasing order of profit.
	 *  2) Initialize the result sequence as first job in sorted jobs.
	 *  3) Do following for remaining n-1 jobs
	 *  	a) If the current job can fit in the current result sequence without missing the deadline, add current job to the result. Else ignore the current job.
	 */
	public void printJobSequence(final Job[] jobs) {
		
		Arrays.sort(jobs); // sort array on basis of profit, decreasing order
		
		final boolean[] slot = new boolean[jobs.length];
		final int[] result = new int[jobs.length]; // to store result of jobs scheduled
		int profit = 0;
		
		for (int i = 0; i < jobs.length; i++) {
			for (int j = Math.min(jobs.length, jobs[i].getDeadline()); j > 0; j--) {
				// slot is free, then add to slot
				if (!slot[j]) {
					slot[j] = true; // add to slot if free
					result[j] = i; // add job in result
					profit += jobs[i].getProfit();
					break; // break the loop
				}
			}
		}
		System.out.println("Total profit : " + profit);
		
		for (int i = 0; i < slot.length; i++) {
			if (slot[i])
				System.out.print(jobs[result[i]] + " ");
		}
		System.out.println();
	}
	
	
	/**
	 * To get nth catalan number
	 * 
	 * @param n
	 * @return nth catalan number
	 */
	public long catalanNumber(final int n) {
		
		final long[] catalan = new long[n+1];
		
		catalan[0] = 1; // catalan number of zero is always 1
		
		for(int i = 1; i <=n; i++) {
			for (int j = 0; j < i; j++) {
				catalan[i] += catalan[j]*catalan[i-j-1];
			}
		}
		return catalan[n];
	}
	
	/**
	 * Following are common definition of Binomial Coefficients.
	 * 	1) A binomial coefficient C(n, k) can be defined as the coefficient of X^k in the expansion of (1 + X)^n.
	 * 	2) A binomial coefficient C(n, k) also gives the number of ways, disregarding order, that k objects can be 
	 * 		chosen from among n objects; more formally, the number of k-element subsets 
	 * 		(or k-combinations) of an n-element set.
	 * 
	 * Optimal Substructure is :
	 * 	1) C(n, k) = C(n-1, k-1) + C(n-1, k)
	 *  2) C(n, 0) = C(n, n) = 1
	 * 
	 * @param n power of x in expansion
	 * @param k index of which coefficient we need to get
	 * @return binomial Coefficient of x^n power in expansion of (1+x)^n
	 */
	public long binomialCoefficientDP(final int n, final int k) {
		
		final long[][] bc = new long[n+1][k+1];
		
		for (int i  = 0; i <= n ; i++) {
			for(int j = 0; j <= i && j <= k ; j++) {
				
				// base cases
				if (j == 0 || j == i) {
					bc[i][j] = 1;
				} else {
					bc[i][j] = bc[i-1][j-1] + bc[i-1][j];
				}
			}
		}
		return bc[n][k];
	}
	
	/**
	 * It uses way to build pascal's triangle and below is explanation.
	 * 
	 * 1==========>> n = 0, C(0,0) = 1
	 * 1–1========>> n = 1, C(1,0) = 1, C(1,1) = 1
	 * 1–2–1======>> n = 2, C(2,0) = 1, C(2,1) = 2, C(2,2) = 1
	 * 1–3–3–1====>> n = 3, C(3,0) = 1, C(3,1) = 3, C(3,2) = 3, C(3,3)=1
	 * 1–4–6–4–1==>> n = 4, C(4,0) = 1, C(4,1) = 4, C(4,2) = 6, C(4,3)=4, C(4,4)=1
	 * 
	 * So here every loop on i, builds i’th row of pascal triangle, using (i-1)th row
	 * 
	 * At any time, every element of array C will have some value (ZERO or more) and in next iteration, 
	 * value for those elements comes from previous iteration.
	 * 
	 * 
	 * In statement,
	 * C[j] = C[j] + C[j-1]
	 * Right hand side represents the value coming from previous iteration 
	 * (A row of Pascal’s triangle depends on previous row). 
	 * Left Hand side represents the value of current iteration which will be obtained by this statement.
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public long binomialCoefficientSpaceEfficient(final int n, final int k) {
		
		final long[] bc = new long[k+1];
		bc[0] = 1; // base case.
		
		for(int i = 1; i <=n; i++) {
			for (int j = Math.min(i, k); j > 0; j--) {
				bc[j] += bc[j-1];
			}
		}
		return bc[k];
	}
	
	/**
	 * C(n, k) = n! / (n-k)! * k! = [n * (n-1) *....* 1]  / [ ( (n-k) * (n-k-1) * .... * 1) * ( k * (k-1) * .... * 1 ) ]
	 * After simplifying, we get
	 * C(n, k) = [n * (n-1) * .... * (n-k+1)] / [k * (k-1) * .... * 1]
	 * 
	 * Also, C(n, k) = C(n, n-k)  // we can change r to n-r if r > n-r
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public long bCSpaceTimeEfficient(final int n, int k) {
		
		long bc = 1;
		
		if (k > n - k) k = n - k;
		
		for(int i = 0; i < k; i++) {
			bc *= (n-i);
			bc/= (i+1);
		}
		return bc;
	}
}