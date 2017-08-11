/**
 * 
 */
package string;

import java.util.Arrays;
import java.util.List;

import linkedlists.DoubleLinkedList;
import linkedlists.DoubleLinkedList.Node;

/**
 * @author aa49442
 * 
 */
public class DemoString {

	private String demoString;
	private int length;

	/**
	 * @param demoString
	 */
	public DemoString(String demoString) {
		super();
		if (demoString == null)
			throw new NullPointerException();
		this.demoString = demoString;
		this.length = demoString.length();
	}

	public DemoString() {
		this("");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DemoString [demoString=" + demoString + "]";
	}

	public boolean isRotationOfPalenDrome() {
		if (isPalendrome())
			return true;

		int n = length;

		for (int i = 0; i < n - 1; i++) {
			String s1 = demoString.substring(i + 1, n - 1);
			String s2 = demoString.substring(0, i + 1);
			if (isPalendrome(new StringBuilder(s1).append(s2).toString()))
				return true;
		}
		return false;
	}

	private boolean isPalendrome(String str) {
		int l = 0;
		int h = str.length() - 1;
		return isPalendrome(str, l, h);
	}

	private boolean isPalendrome(String str, int l, int h) {
		while (h > l)
			if (demoString.charAt(l++) != demoString.charAt(h--))
				return false;
		return true;
	}

	private boolean isPalendrome() {
		return isPalendrome(demoString);
	}

	public boolean isRotationOfPalenDromeOpt() {
		String s = findLongestPalenDrome(new StringBuilder(demoString).append(
				demoString).toString());
		if ("".equals(s) || s == null)
			return false;
		else
			return s.length() >= demoString.length();
	}

	public String findLongestPalenDrome(String str) {
		char[] cs = addBoundries(str.toCharArray());
		int[] p = new int[cs.length];
		int c = 0, r = 0; // first element of cs
		int m = 0, n = 0;// indices to comapre if two elements are same..

		for (int i = 1; i < cs.length; i++) {
			if (i > r) {
				p[i] = 0;
				m = i - 1;
				n = i + 1;
			} else {
				int j = c * 2 - i;
				if (p[j] < (r - i)) {
					p[i] = p[j];
					m = -1;
				} else {
					p[i] = r - i;
					n = r + 1;
					m = i * 2 - n;
				}
			}

			while (m > 0 && n < cs.length && cs[m] == cs[n]) {
				p[i]++;
				m--;
				n++;
			}
			if (i + p[i] > r) {
				c = i;
				r = i + p[i];
			}
		}

		int len = 0;
		c = 0;
		for (int i = 1; i < cs.length; i++)
			if (len < p[i]) {
				len = p[i];
				c = i;
			}

		char[] ss = Arrays.copyOfRange(cs, c - len, c + len + 1);
		return String.valueOf(removeBoundries(ss));
	}

	private char[] removeBoundries(char[] ss) {
		if (ss == null || ss.length < 3)
			return "".toCharArray();

		char[] cs = new char[(ss.length + 1) >> 1];
		for (int i = 0; i < cs.length; i++)
			cs[i] = ss[i * 2];
		return cs;
	}

	private char[] addBoundries(char[] chArr) {
		char[] cs = new char[chArr.length * 2 + 1];
		for (int i = 0; i < cs.length - 1; i += 2) {
			cs[i] = '|';
			cs[i + 1] = chArr[i / 2];
		}
		cs[cs.length - 1] = '|';
		return cs;
	}

	private String[] keyPad = { "", "", "abc", "def", "ghi", "jkl", "mno",
			"pqrs", "tuv", "wxyz" };

	public void printMsg(int[] is) {
		char[] s = new char[is.length];
		printMsgUtil(is, 0, s, is.length);
		System.out.println();
	}

	private void printMsgUtil(int[] is, int i, char[] s, int n) {
		if (i == n) {
			System.out.print(new String(s) + " ");
			return;
		}

		for (int j = 0; j < keyPad[is[i]].length(); j++) {
			s[i] = keyPad[is[i]].charAt(j);
			printMsgUtil(is, i + 1, s, n);
			if (is[i] == 0 || is[i] == 1)
				return;
		}
	}

	public int lcs(String s) {
		return lcs(demoString, s, demoString.length(), s.length());
	}

	private int lcs(String s1, String s2, int l1, int l2) {
		if (l1 == 0 || l2 == 0)
			return 0;

		if (s1.charAt(l1 - 1) == s2.charAt(l2 - 1))
			return 1 + lcs(s1, s2, l1 - 1, l2 - 1);
		else
			return Math.max(lcs(s1, s2, l1, l2 - 1), lcs(s1, s2, l1 - 1, l2));
	}

	public int lcsOpt(String s) {
		return lcsOpt(demoString, s, demoString.length(), s.length());
	}

	private int lcsOpt(final String s1, final String s2, final int l1, final int l2) {
		
		final int[][] lcs = new int[l1 + 1][l2 + 1];

		for (int i = 0; i <= l1; i++)
			lcs[i][0] = 0;
		for (int i = 1; i <= l2; i++)
			lcs[0][i] = 0;
		for (int i = 0; i < l1; i++)
			for (int j = 0; j < l2; j++)
				if (s1.charAt(i) == s2.charAt(j))
					lcs[i + 1][j + 1] = lcs[i][j] + 1;
				else
					lcs[i + 1][j + 1] = Math.max(lcs[i][j + 1], lcs[i + 1][j]);
		return lcs[l1][l2];
	}

	public void printLcs(String s) {
		printLcs(demoString, s, demoString.length(), s.length());
	}

	private void printLcs(String s1, String s2, int l1, int l2) {
		final int[][] lcs = new int[l1 + 1][l2 + 1];

		for (int i = 0; i <= l1; i++)
			lcs[i][0] = 0;
		for (int i = 1; i <= l2; i++)
			lcs[0][i] = 0;
		for (int i = 0; i < l1; i++)
			for (int j = 0; j < l2; j++)
				if (s1.charAt(i) == s2.charAt(j))
					lcs[i + 1][j + 1] = lcs[i][j] + 1;
				else
					lcs[i + 1][j + 1] = Math.max(lcs[i][j + 1], lcs[i + 1][j]);

		int index = lcs[l1][l2];

		char[] cs = new char[index];

		int i = l1, j = l2;

		while (i > 0 && j > 0)
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				cs[index - 1] = s1.charAt(i - 1);
				i--;
				j--;
				index--;
			} else if (lcs[i - 1][j] > lcs[i][j - 1])
				i--;
			else
				j--;

		System.out.println(new String(cs));
	}

	public void suffixArray() {
		buildSuffixArray(demoString, demoString.length());
	}

	/**
	 * This is the main function that takes a string 'txt' of size n as an
	 * argument, builds and return the suffix array for the given string
	 * 
	 * @param cs
	 *            string whose suffix array is to be build
	 * @param n
	 *            length of string
	 * @return suffix array of cs
	 */
	private Suffix[] buildSuffixArray(String cs, int n) {
		Suffix[] suffixs = new Suffix[n];

		// Store suffixes and their indexes in an array of structures. The
		// structure is needed to sort the suffixes alphabetically and maintain
		// their old indexes while sorting
		for (int i = 0; i < n; i++)
			suffixs[i] = new Suffix(i, cs.substring(i));

		Arrays.sort(suffixs);

		System.out.println(Arrays.toString(suffixs));

		// Store indexes of all sorted suffixes in the suffix array
		int[] suffIndex = new int[n];
		for (int i = 0; i < n; i++)
			suffIndex[i] = suffixs[i].index;

		System.out.println(Arrays.toString(suffIndex));
		return suffixs;
	}

	/**
	 * Structure to store information of a suffix
	 * 
	 * @author Rishabh.Daim
	 *
	 */
	private static class Suffix implements Comparable<Suffix> {
		int index;
		String suff;

		/**
		 * @param index
		 * @param suff
		 */
		public Suffix(int index, String suff) {
			super();
			this.index = index;
			this.suff = suff;
		}

		@Override
		public int compareTo(Suffix o) {
			return this.suff.compareTo(o.suff);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Suffix [index=" + index + ", e=" + suff + "]";
		}
	}

	/**
	 * To search a pattern in a text, we preprocess the text and build a suffix
	 * array of the text. Since we have a sorted array of all suffixes, Binary
	 * Search can be used to search. Following is the search function.
	 * 
	 * @param pattren
	 */
	public void patternSearch(String pattren) {
		// Do simple binary search for the pat in txt using the built suffix
		// array
		Suffix[] suffixs = buildSuffixArray(demoString, length);
		// Initilize left and right indexes
		int l = 0, h = suffixs.length - 1;
		int m, cmp;
		while (h >= l) {
			m = (l + h) >> 1;
			cmp = pattren.compareTo(suffixs[m].suff);

			// If match found at the middle, print it and return
			if (cmp == 0) {
				System.out.println("pattern found at : " + m);
				return;
			}
			// Move to left half if pattern is alphabetically less than the mid
			// suffix
			else if (cmp < 0)
				h = m - 1;
			// Otherwise move to right half
			else
				l = m + 1;
		}
	}

	public void suffixArrayOpt(String str) {
		buildSuffixArrOpt(str, str.length());
	}

	private int[] buildSuffixArrOpt(String str, int n) {
		SuffRank[] suffRanks = new SuffRank[n];
		int[] rank;
		for (int i = 0; i < n; i++) {
			rank = new int[2];
			rank[0] = str.charAt(i) - 'a';
			rank[1] = i + 1 < n ? str.charAt(i + 1) - 'a' : -1;
			suffRanks[i] = new SuffRank(i, str.substring(i), rank);
		}

		Arrays.sort(suffRanks);

		// This array is needed to get the index in suffixes[] from original
		// index. This mapping is needed to get next suffix.
		int[] index = new int[n];

		// At his point, all suffixes are sorted according to first 2
		// characters. Let us sort suffixes according to first 4 characters,
		// then first 8 and so on

		for (int k = 4; k < (n << 1); k <<= 1) {
			int prevRank = suffRanks[0].rank[0]; // first value in sorted
													// suffixs
			suffRanks[0].rank[0] = 0;
			index[suffRanks[0].index] = 0;

			for (int i = 1; i < n; i++) {
				if (suffRanks[i].rank[0] == suffRanks[i - 1].rank[0]
						&& suffRanks[i].rank[1] == suffRanks[i - 1].rank[1])
					suffRanks[i].rank[0] = prevRank;
				else
					suffRanks[i].rank[0] = ++prevRank;

				index[suffRanks[i].index] = i;
			}

			// Assign next rank to every suffix
			for (int i = 0; i < n; i++) {
				int nextIndex = suffRanks[i].index + k >> 1;
				suffRanks[i].rank[1] = nextIndex < n ? suffRanks[index[nextIndex]].rank[0]
						: -1;
			}
			// Sort the suffixes according to first k characters
			Arrays.sort(suffRanks);
		}

		// Store indexes of all sorted suffixes in the suffix array
		int[] suffInd = new int[n];
		for (int i = 0; i < n; i++)
			suffInd[i] = suffRanks[i].index;
		System.out.println(Arrays.toString(suffRanks));
		System.out.println(Arrays.toString(suffInd));
		return suffInd;
	}

	private static class SuffRank implements Comparable<SuffRank> {

		private int index;
		private String suff;
		private int[] rank;

		/**
		 * @param index
		 * @param suff
		 * @param rank
		 */
		public SuffRank(int index, String suff, int[] rank) {
			super();
			this.index = index;
			this.suff = suff;
			this.rank = rank;
		}

		@Override
		public int compareTo(SuffRank o) {
			int cmp = this.rank[0] - o.rank[0];
			if (cmp != 0)
				return cmp;
			return this.rank[1] - o.rank[1];
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "SuffRank [index=" + index + ", suff=" + suff + ", rank="
					+ Arrays.toString(rank) + "]";
		}
	}

	public void suffixArrRadixSort(String str) {
		buildSuffixArrRadixSort(str, str.length());
	}

	private int[] buildSuffixArrRadixSort(String str, int n) {
		SuffRank[] suffRanks = new SuffRank[n];

		int[] rank;

		for (int i = 0; i < n; i++) {
			rank = new int[2];
			rank[0] = str.charAt(i) - 'a';
			rank[1] = i + 1 < n ? str.charAt(i + 1) - 'a' : -1;
			suffRanks[i] = new SuffRank(i, str.substring(i), rank);
		}

		radixSort(suffRanks);

		int[] index = new int[n];
		for (int k = 4; k < (n << 1); k <<= 1) {
			// first element rank..
			int prevRank = suffRanks[0].rank[0];
			suffRanks[0].rank[0] = 0;

			index[suffRanks[0].index] = 0;

			for (int i = 1; i < n; i++) {
				if (suffRanks[i].rank[0] == suffRanks[i - 1].rank[0]
						&& suffRanks[i].rank[1] == suffRanks[i - 1].rank[1])
					suffRanks[i].rank[0] = prevRank;
				else
					suffRanks[i].rank[0] = ++prevRank;

				index[suffRanks[i].index] = i;
			}

			for (int i = 0; i < n; i++) {
				int nextIndex = suffRanks[i].index + k >> 1;
				suffRanks[i].rank[1] = nextIndex < n ? suffRanks[index[nextIndex]].rank[0]
						: -1;
			}
			// Sort the suffixes according to first k characters
			radixSort(suffRanks);
		}

		int[] suffInd = new int[n];

		for (int i = 0; i < n; i++)
			suffInd[i] = suffRanks[i].index;

		System.out.println(Arrays.toString(suffRanks));
		System.out.println(Arrays.toString(suffInd));
		return suffInd;

	}

	private void radixSort(SuffRank[] suffRanks) {
		int n = suffRanks.length;
		for (int i = 0; i < 2; i++) {
			int max = maxOf(suffRanks, i);
			for (int exp = 1; max / exp > 0; exp *= 10)
				countSort(suffRanks, n, i, exp);
		}
	}

	private int maxOf(SuffRank[] suffRanks, int i) {
		int max = suffRanks[0].rank[i];
		int n = suffRanks.length;
		for (int j = 1; j < n; j++)
			if (max < suffRanks[j].rank[i])
				max = suffRanks[j].rank[i];
		return max;
	}

	private void countSort(SuffRank[] suffRanks, int n, int index, int exp) {
		int[] count = new int[10];

		// Store count of occurrences in count[]
		for (int i = 0; i < n; i++)
			count[(suffRanks[i].rank[index] / exp) % 10]++;

		for (int i = 1; i < 10; i++)
			count[i] += count[i - 1];

		// Build the output array
		SuffRank[] output = new SuffRank[n];

		// Build the output array
		for (int i = n - 1; i >= 0; i--) {
			output[count[(suffRanks[i].rank[index] / exp) % 10] - 1] = suffRanks[i];
			count[(suffRanks[i].rank[index] / exp) % 10]--;
		}

		// Copy the output array to arr[], so that arr[] now contains sorted
		// numbers according to curent digit
		for (int i = 0; i < n; i++)
			suffRanks[i] = output[i];
	}

	public void removeDup() {
		removeUtil(demoString.toCharArray(), '0');
	}

	private String removeUtil(char[] str, char lastRemoved) {
		if (str.length <= 1)
			return str.toString();

		// Remove leftmost same characters and recur for remaining string
		if (str[0] == str[1]) {
			lastRemoved = str[0];
			// while (str.c);
		}
		return demoString;
	}

	/**
	 * The function returns index of first non-repeating character in a string.
	 * If all characters are repeating then returns -1
	 * 
	 * @return
	 */
	public String nonRepeatingString() {
		final int[] count = getCountArr(demoString);
		System.out.println(Arrays.toString(count));

		for (int i = 0, l = length; i < l; i++)
			if (count[demoString.charAt(i)] == 1)
				return Character.valueOf(demoString.charAt(i)).toString();
		return null;
	}

	/**
	 * Returns an array of size 256 containing count of characters in the passed
	 * char array
	 * 
	 * @param str
	 *            whose words count has to be done
	 * @return
	 */
	private int[] getCountArr(String str) {
		int[] count = new int[256];

		for (int i = 0; i < length; i++)
			count[str.charAt(i)]++;
		return count;
	}

	/**
	 * The function returns index of the first non-repeating character in a
	 * string. If all characters are repeating then reurns INT_MAX
	 * 
	 * @return
	 */
	public String nonRepeatingStrOpt() {
		final CountIndex[] countIndexs = getCountIndex(demoString);

		int result = Integer.MAX_VALUE;

		for (int i = 0; i < 256; i++)
			// If this character occurs only once and appears before the current
			// result, then update the result
			if (countIndexs[i] != null && 1 == countIndexs[i].count
					&& result > countIndexs[i].index)
				result = countIndexs[i].index;
		return Character.valueOf(demoString.charAt(result)).toString();
	}

	/**
	 * Returns an array of above structure type. The size of array is
	 * NO_OF_CHARS
	 * 
	 * @param str
	 * @return countIndex array with counts and first index of each character
	 */
	private CountIndex[] getCountIndex(String str) {
		final CountIndex[] countIndexs = new CountIndex[256];

		for (int i = 0; i < length; i++)
			// If it's first occurrence, then store the index
			if (countIndexs[str.charAt(i)] == null)
				countIndexs[str.charAt(i)] = new CountIndex(1, i);
			else
				// increase count if already present
				countIndexs[str.charAt(i)].count++;
		return countIndexs;
	}

	/**
	 * Structure to store count of a character and index of the first occurrence
	 * in the input string
	 * 
	 * @author aa49442
	 * 
	 */
	private static class CountIndex {
		int count;
		int index;

		/**
		 * @param count
		 * @param c
		 */
		public CountIndex(int count, int index) {
			super();
			this.count = count;
			this.index = index;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "CountIndex [count=" + count + ", index=" + index + "]";
		}
	}

	public void nonRepeatStrDll() {
		boolean[] repeated = new boolean[256];
		@SuppressWarnings("unchecked")
		Node<Character>[] linkedLists = (Node<Character>[]) new Node[256];

		DoubleLinkedList<Character> doubleLinkedList = new DoubleLinkedList<Character>();

		char[] stream = demoString.toCharArray();

		for (int i = 0; i < length; i++) {
			char x = stream[i];
			System.out.println("reading from stream : " + x);

			if (!repeated[x]) {
				if (linkedLists[x] == null) // first time..
					linkedLists[x] = doubleLinkedList.addLast(x);
				else {
					doubleLinkedList.removeNode(linkedLists[x]);
					linkedLists[x] = null;
					repeated[x] = true;
				}
			}

			if (doubleLinkedList.head() != null)
				System.out.println("first repeating element so far is : "
						+ doubleLinkedList.head());
			else
				System.out.println("no repeating element..");
		}
	}

	public void interleavingString(String str) {
		interleavingStringUtil(demoString, demoString.length(), str,
				str.length(), new char[demoString.length() + str.length()], 0);
	}

	private void interleavingStringUtil(String str1, int l1, String str2,
			int l2, char[] output, int index) {
		if (l1 == 0 && l2 == 0) {
			System.out.print(Arrays.toString(output) + " ");
			return;
		}

		if (l1 != 0) {
			output[index] = str1.charAt(str1.length() - l1);
			interleavingStringUtil(str1, l1 - 1, str2, l2, output, index + 1);
		}
		if (l2 != 0) {
			output[index] = str2.charAt(str2.length() - l2);
			interleavingStringUtil(str1, l1, str2, l2 - 1, output, index + 1);
		}
	}

	public boolean isInterLeaved(String s1, String s2, String str) {
		System.out.println();
		int i = 0, j = 0, k = 0;

		while (i < str.length()) {
			if (str.charAt(i) == s1.charAt(j))
				j++;
			else if (str.charAt(i) == s2.charAt(k))
				k++;
			else
				return false;
			i++;
		}
		if (j != s1.length() || k != (s2.length()))
			return false;
		return true;
	}

	/**
	 * The main function that returns true if C is an interleaving of A and B,
	 * otherwise false.
	 * 
	 * @param s1
	 *            first string - A
	 * @param s2
	 *            second string -B
	 * @param str
	 *            interleaved - C
	 * @return true if str is interleaved of s1 & s2
	 */
	public boolean isInterLeavedOpt(String s1, String s2, String str) {
		int i = s1.length();
		int j = s2.length();

		// Let us create a 2D table to store solutions of subproblems. C[i][j]
		// will be true if C[0..i+j-1] is an interleaving of A[0..i-1] and
		// B[0..j-1].
		boolean[][] il = new boolean[i + 1][j + 1];

		int k = str.length();

		// C can be an interleaving of A and B only of sum of lengths of A & B
		// is equal to length of C.
		if (k != i + j)
			return false;

		for (int l = 0; l < i; l++)
			il[l][0] = true;
		for (int l = 1; l < j; l++)
			il[0][l] = true;

		for (int l = 1; l <= i; l++)
			for (int m = 1; m <= j; m++) {
				// s1 is empty
				if (l == 1 && s2.charAt(m - 1) == str.charAt(m - 1))
					il[l][m] = il[l][m - 1];
				// s2 is empty
				else if (m == 1 && s1.charAt(l - 1) == str.charAt(l - 1))
					il[l][m] = il[l - 1][m];
				// Current character of C matches with current character of A,
				// but doesn't match with current character of B
				else if (s1.charAt(l - 1) == str.charAt(l + m - 1)
						&& s2.charAt(m - 1) != str.charAt(l + m - 1))
					il[l][m] = il[l - 1][m];
				// Current character of C matches with current character of B,
				// but doesn't match with current character of A
				else if (s1.charAt(l - 1) != str.charAt(l + m - 1)
						&& s2.charAt(m - 1) == str.charAt(l + m - 1))
					il[l][m] = il[l][m - 1];
				// Current character of C matches with that of both A and B
				else if (s1.charAt(l - 1) == str.charAt(l + m - 1)
						&& s2.charAt(m - 1) == str.charAt(l + m - 1))
					il[l][m] = il[l - 1][m] || il[l][m - 1];
			}
		return il[i][j];
	}

	public void filterStr(char b, char a, char c) {
		int state = 1;

		int j = 0;
		char[] str = demoString.toCharArray();
		int n = str.length;

		for (int i = 0; i < n - 1; i++) {

			if (state == 1 && str[i] != b && str[i] != a)
				str[j++] = str[i];
			else if (state == 2 && str[i] != c) {
				str[j++] = a;
				if (str[i] != b && str[i] != a)
					str[j++] = str[i];
			}

			// state change..
			state = str[i] == a ? 2 : 1;
		}

		if (state == 2)
			str[j++] = a;
		int count = 0;
		for (int i = 0; str[i] != 0; i++)
			count++;

		char[] ds = new char[count];
		for (int i = 0; i < count; i++)
			ds[i] = str[i];
		System.out.println(new String(ds));
	}

	public int lcSubStrOpt(final String s) {
		final int n = demoString.length();
		final int m = s.length();

		final int[][] lcSub = new int[n + 1][m + 1];
		int result = 0;

		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= m; j++)
				if (i == 0 || j == 0)
					lcSub[i][j] = 0;
				else if (demoString.charAt(i - 1) == s.charAt(j - 1)) {
					lcSub[i][j] = lcSub[i - 1][j - 1] + 1;
					result = Math.max(result, lcSub[i][j]);
					continue;
				}
		return result;
	}

	public void printLcSubStrOpt(String s) {

		final int n = demoString.length();
		final int m = s.length();

		int[][] lcSub = new int[n + 1][m + 1];
		int result = lcSub[0][0];
		int x = 0, y = 0;
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= m; j++)
				if (i == 0 || j == 0)
					lcSub[i][j] = 0;
				else if (demoString.charAt(i - 1) == s.charAt(j - 1)) {
					lcSub[i][j] = lcSub[i - 1][j - 1] + 1;
					if (result < lcSub[i][j]) {
						result = lcSub[i][j];
						x = i;
						y = j;
					}
					continue;
				}

		while (x > 0 && y > 0) {
			System.out.print(demoString.charAt(x - 1) + " ");
			y--;
			x--;
		}
	}

	public int atoi() {
		String num = demoString;

		int res = 0;

		int n = num.length();
		int i = 0;
		int sign = 1;

		if (n > 0) {
			char firstChar = num.charAt(i);
			if (firstChar < '0') {
				if (firstChar == '-')
					sign = -1;
				else if (firstChar != '+')
					throw new NumberFormatException();
				if (n == 1)
					throw new NumberFormatException();
				i++;
			}
			for (; i < n; i++) {
				int t = num.charAt(i);
				if (t < '0' || t > '9')
					throw new NumberFormatException();
				res = res * 10 + (num.charAt(i) - '0');
			}
		} else
			throw new NumberFormatException();
		return sign * res;
	}

	public boolean wildCardMatch(String str, char a, char b) {
		return wildCardMatch(str, 0, demoString, 0, a, b);
	}

	private boolean wildCardMatch(String str1, int l1, String str2, int l2,
			char a, char b) {
		if (l1 == str1.length() && l2 == str2.length())
			return true;

		if ((l1 == str1.length() || l2 == str2.length()))
			return false;
		if (str1.charAt(l1) == a && l1 + 1 < str1.length()
				&& l2 == str2.length() - 1)
			return false;
		if (str1.charAt(l1) == b || str1.charAt(l1) == str2.charAt(l2))
			return wildCardMatch(str1, l1 + 1, str2, l2 + 1, a, b);
		if (str1.charAt(l1) == a)
			return (wildCardMatch(str1, l1 + 1, str2, l2, a, b) || wildCardMatch(
					str1, l1, str2, l2 + 1, a, b));
		return false;
	}

	public int countWords() {
		int n = 0;
		int state = 2; // out state
		int wc = 0;
		while (n < length) {
			char c = demoString.charAt(n++);
			if (c == ' ' || c == '\n' || c == '\t')
				state = 2; // out
			else if (state == 2) // if out
			{
				state = 1; // in state
				wc++;
			}
		}
		return wc;
	}

	public void anagram(String[] str) {
		int s = str.length;
		DupWord dupWord = new DupWord(s);
		for (int i = 0; i < s; i++)
			dupWord.words[i] = new Word(str[i], i);

		System.out.println(dupWord);
		for (int i = 0; i < s; i++) {
			char[] cs = dupWord.words[i].str.toCharArray();
			Arrays.sort(cs);
			dupWord.words[i].str = new String(cs);
		}

		System.out.println(dupWord);

		Arrays.sort(dupWord.words);

		System.out.println(dupWord);

		for (int i = 0; i < s; i++)
			System.out.print(str[dupWord.words[i].index] + " ");
		System.out.println();
	}

	private static class DupWord {
		private Word[] words;
		private int size;

		/**
		 * @param size
		 */
		public DupWord(int size) {
			super();
			this.size = size;
			this.words = new Word[size];
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "DupWord [words=" + Arrays.toString(words) + ", size="
					+ size + "]";
		}
	}

	private static class Word implements Comparable<Word> {
		private String str;
		private int index;

		/**
		 * @param str
		 * @param index
		 */
		public Word(String str, int index) {
			super();
			if (str == null)
				throw new NullPointerException();
			this.str = str;
			this.index = index;
		}

		@Override
		public int compareTo(Word o) {
			return this.str.compareTo(o.str);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Word [str=" + str + ", index=" + index + "]";
		}
	}

	public int longestPalendrome() {
		String str = demoString;
		int maxLength = 1;
		int l = 0;
		int h = 0;
		int start = 0;
		int len = length;

		for (int i = 1; i < len; i++) {
			// Find the longest even length palindrome with center points as i-1
			// and i.
			l = i - 1;
			h = i;

			while (l >= 0 && h < len && str.charAt(l) == str.charAt(h)) {
				if (h - l + 1 > maxLength) {
					start = l;
					maxLength = h - l + 1;
				}
				l--;
				h++;
			}
			// longest odd length palendrome;
			l = i - 1;
			h = i + 1;

			while (l >= 0 && h < len && str.charAt(l) == str.charAt(h)) {
				if (h - l + 1 > maxLength) {
					start = l;
					maxLength = h - l + 1;
				}
				l--;
				h++;
			}
		}
		System.out.printf("palendrome start from %d to %d ", start, maxLength);
		return maxLength;
	}

	public void transform() {
		char[] s = demoString.toCharArray();
		int lenFirst = 0, lenRemainig = length;

		int k = 0;
		int shift = 0;
		while (lenRemainig > 0) {
			k = 0;
			while (3 * k + 1 <= lenRemainig)
				k++;
			lenFirst = 3 * (k - 1) + 1;
			lenRemainig -= lenFirst;
			// to perform rotation..
			cycleLeader(s, shift, lenFirst);

			// reverse second half of first array..

			reverse(s, shift >> 1, shift - 1);

			// reverse first half of second array..

			reverse(s, shift, shift + (lenFirst >> 1) - 1);

			// reverse first half of second & second half of first together..
			reverse(s, shift >> 1, shift + (lenFirst >> 1) - 1);

			shift += lenFirst;
		}

		System.out.println(new String(s));
	}

	private void reverse(char[] s, int i, int j) {
		while (i < j)
			swap(s, i++, j--);
	}

	private void swap(char[] s, int i, int j) {
		
		if (i == j) return; // no need to swap
		
		char c = s[i];
		s[i] = s[j];
		s[j] = c;
	}

	private void cycleLeader(char[] s, int shift, int lenFirst) {
		for (int i = 1; i < lenFirst; i *= 3) {
			int j = i;

			char c = s[j + shift];

			do {
				if (j % 2 != 0)// odd
					j = (lenFirst >> 1) + (j >> 1);
				else
					j >>= 1;

				{
					char tmp = s[j + shift];
					s[j + shift] = c;
					c = tmp;
				}
			} while (j != i);
		}
	}

	public void sortedPermutations() {

	}

	public int lexoGraphicOrder() {
		char[] s = demoString.toCharArray();
		int l = length;
		int mul = factorial(l);
		int rank = 1;
		int smallRight;

		for (int i = 0; i < l; i++) {
			mul /= (l - i);

			smallRight = findRightSmallElements(s, i, l - 1);

			rank += smallRight * mul;
		}
		return rank;
	}

	private int findRightSmallElements(char[] s, int i, int j) {
		int count = 0;
		for (int k = i + 1; k <= j; k++)
			if (s[k] < s[i])
				count++;
		return count;
	}

	private int factorial(int l) {
		return l <= 1 ? 1 : l * factorial(l - 1);
	}

	public int lexoGraphicOpt() {
		char[] s = demoString.toCharArray();
		int l = length;
		int rank = 1;
		int[] count = new int[256];
		int mul = factorial(l);

		populateAndIncreaseCount(s, count, l);
		for (int i = 0; i < l; i++) {
			mul /= l - i;

			rank += count[s[i] - 1] * mul;

			// reduce counts

			updateCount(count, s[i]);
		}
		return rank;
	}

	private void updateCount(int[] count, char c) {
		for (int i = c; i < 256; i++)
			count[i]--;
	}

	private void populateAndIncreaseCount(char[] s, int[] count, int len) {
		for (int i = 0; i < len; i++)
			count[s[i]]++;
		for (int i = 1; i < 256; i++)
			count[i] += count[i - 1];
	}

	public int minPalPar() {
		String s = demoString;
		return minPalPar(s, 0, length - 1);
	}

	private int minPalPar(String str, int i, int j) {
		if (i == j)
			return 0;
		if (isPalendrome(str, i, j))
			return 0;
		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			int count = (minPalPar(str, i, k) + 1 + minPalPar(str, k + 1, j));
			if (count < min)
				min = count;
		}
		return min;
	}

	public int minPalParOpt() {
		return minPalParOpt(demoString.toCharArray());
	}

	private int minPalParOpt(char[] charArray) {
		int l = charArray.length;

		int[][] cuts = new int[l][l];
		boolean[][] palen = new boolean[l][l];

		// every substring of length 1 is palendrome

		for (int i = 0; i < l; i++) {
			cuts[i][i] = 0;
			palen[i][i] = true;
		}

		for (int s = 2; s <= l; s++) {
			// starting index..
			for (int i = 0; i < l - s + 1; i++) {
				int j = i + s - 1; // end index

				if (s == 2)
					palen[i][j] = charArray[i] == charArray[j];
				else
					palen[i][j] = ((charArray[i] == charArray[j]) && (palen[i + 1][j - 1]));

				// is palen
				if (palen[i][j])
					cuts[i][j] = 0;
				else {
					cuts[i][j] = Integer.MAX_VALUE;
					for (int k = i; k <= j - 1; k++)
						cuts[i][j] = Math.min(cuts[i][j], cuts[i][k]
								+ cuts[k + 1][j] + 1);
				}
			}
		}
		return cuts[0][l - 1];
	}

	public void faPatSearch(String txt, String pat) {
		int n = txt.length();
		int m = pat.length();

		int[][] tf = new int[m + 1][256];
		computeTF(pat, tf, m);
		int s = 0;
		for (int i = 0; i < n; i++) {
			s = tf[s][txt.charAt(i)];
			if (s == m)
				System.out.printf("FA pattern find at %d \n", i - m + 1);
		}
	}

	private void computeTF(String pat, int[][] tf, int m) {
		for (int i = 0; i <= m; i++)
			for (int j = 0; j < 256; j++)
				tf[i][j] = getNextState(pat, m, i, j);
	}

	private int getNextState(String pat, int m, int i, int j) {
		if (i < m && j == pat.charAt(i))
			return i + 1;
		int k;
		for (int ns = i; ns > 0; ns--) {
			if (pat.charAt(ns - 1) == j) {
				for (k = 0; k < ns - 1; k++)
					if (pat.charAt(k) != pat.charAt(i - ns + 1 + k))
						break;
				if (k == ns - 1)
					return ns;
			}
		}
		return 0;
	}

	public void bmBadPatSearch(String txt, String pat) {
		int n = txt.length();
		int m = pat.length();

		int[] badChars = new int[256];

		computeBadChar(pat, m, badChars);

		int s = 0;

		while (s <= (n - m)) {
			int j = m - 1;

			while (j >= 0 && pat.charAt(j) == txt.charAt(s + j))
				j--;

			if (j < 0) {
				System.out.printf("BM Bad pattern at %d\n", s);
				s += (s + m) < n ? m - badChars[txt.charAt(s + m)] : 1;
			} else
				s += Math.max(1, j - badChars[txt.charAt(s + j)]);
		}
	}
	
	
	/**
	 * Z Algo for pattern matching
	 * 
	 * @param txt text to be matched for given pattern
	 * @param pat pattern to be matched
	 */
	public void zAlgoPatSearch(final String txt, final String pat) {
		final int n = txt.length();
		final int m = pat.length();
		
		final int[] zArr = new int[n+m];
		
		final String zString = pat+"$"+txt;
		
		computeZArray(zArr, zString);
		
		for (int i = 0; i < zArr.length; i++) {
			if (zArr[i] == m) {
				System.out.print(i - m - 1);
			}
		}
		System.out.println();
	}

	/**
	 * To compute Z Array. This array saves the maximum length of substring that matches with pattern
	 * 
	 * @param zArr
	 * @param zString
	 */
	// 1) If i > R then there is no prefix substring that starts before i and ends after i, 
	// so we reset L and R and compute new [L,R] by comparing str[0..] to str[i..] and get Z[i] (= R-L+1).

	// 2) If i <= R then let K = i-L,  now Z[i] >= min(Z[K], R-i+1)  because str[i..] matches with str[K..] for atleast R-i+1 characters (they are in
    // 	[L,R] interval which we know is a prefix substring).     
	//  Now two sub cases arise – 
	//      a) If Z[K] < R-i+1  then there is no prefix substring starting at 
	//         str[i] (otherwise Z[K] would be larger)  so  Z[i] = Z[K]  and 
	//         interval [L,R] remains same.
	//      b) If Z[K] >= R-i+1 then it is possible to extend the [L,R] interval
	//         thus we will set L as i and start matching from str[R]  onwards  and
	//         get new R then we will update interval [L,R] and calculate Z[i] (=R-L+1).
	private void computeZArray(final int[] zArr, final String zString) {
		
		zArr[0] = 0; // first value is always 0
		final int n = zArr.length;
		
		int l = 0; // start of matching window
		int r = 0; // end of matching window
		
		for (int i = 1; i < n; i++) {
			
			if (i > r) { // no previous match, must do brute force
				
				l = i; r = i; // start both left & right window from i
				// right must be less than end of array
				while (r < n && zString.charAt(r - l) == zString.charAt(r)) {
					r++; // increase k is we get a match, k can have maximum length till pattern
				}
				
				zArr[i] = r - l; // add difference of  window (r - l)
				r--; // to counter last un-matched element
			} else {
				// now we are in box, i has increased and l is (i-1)
				int k = i - l; // 1st index of array
				
				//while (zArr[k] < r - k )
			}
		}
	}

	private void computeBadChar(String pat, int m, int[] badChars) {
		for (int i = 0; i < 256; i++)
			badChars[i] = -1;
		// Fill the actual value of last occurrence of a character
		for (int i = 0; i < m; i++)
			badChars[pat.charAt(i)] = i;
	}

	public void printAllPermutations() {
		int l = length;
		char[] ip = demoString.toCharArray();
		Arrays.sort(ip);

		allPermutations(ip, 0, new char[l], l);
		System.out.println();
	}

	private void allPermutations(char[] ip, int index, char[] op, int len) {
		if (index == len) {
			System.out.print(new String(op) + " ");
			return;
		}

		for (int i = 0; i < len; i++) {
			op[index] = ip[i];
			allPermutations(ip, index + 1, op, len);
		}
	}

	public void permutations() {
		char[] s = demoString.toCharArray();
		permutations(s, 0, length);
		System.out.println();
	}

	private void permutations(char[] s, int i, int l) {
		if (i == l)
			System.out.print(new String(s) + " ");
		else
			for (int j = i; j < l; j++) {
				swap(s, i, j);
				permutations(s, i + 1, l);
				swap(s, i, j);
			}
	}

	/**
	 * This method is used to find the longest non-repeating sub Str in given
	 * String. It Dynamic Programming for this approach and keeps track of
	 * longest non-repeating Character substring seen so far
	 * 
	 * 
	 * The idea is to scan the string from left to right, keep track of the
	 * maximum length Non-Repeating Character Substring (NRCS) seen so far. Let
	 * the maximum length be max_len. When we traverse the string, we also keep
	 * track of length of the current NRCS using cur_len variable. For every new
	 * character, we look for it in already processed part of the string (A temp
	 * array called visited[] is used for this purpose). If it is not present,
	 * then we increase the cur_len by 1. If present, then there are two cases:
	 * 
	 * a) The previous instance of character is not part of current NRCS (The
	 * NRCS which is under process). In this case, we need to simply increase
	 * cur_len by 1. b) If the previous instance is part of the current NRCS,
	 * then our current NRCS changes. It becomes the substring staring from the
	 * next character of previous instance to currently scanned character. We
	 * also need to compare cur_len and max_len, before changing current NRCS
	 * (or changing cur_len).
	 */
	public int longestSubStr() {
		int n = length;
		int curLen = 1;
		int maxLen = 1;
		int prevIndex;
		final int[] visited = new int[256];
		final int[] lss = new int[n];

		// initialize all value to 1
		for (int i = 0; i < n; i++)
			lss[i] = 1;
		/*
		 * Initialize the visited array as -1, -1 is used to indicate that
		 * character has not been visited yet.
		 */
		Arrays.fill(visited, -1);

		/*
		 * Mark first character as visited by storing the index of first
		 * character in visited array.
		 */
		final char[] str = demoString.toCharArray();
		visited[str[0]] = 0; // mark first element as visited and add value of its index

		/*
		 * Start from the second character. First character is already processed
		 * (cur_len and max_len are initialized as 1, and visited[str[0]] is set
		 */
		int i;
		for (i = 1; i < n; i++) {
			prevIndex = visited[str[i]];

			/*
			 * If the currentt character is not present in the already processed
			 * substring or it is not part of the current NRCS, then do cur_len++
			 */

			if (prevIndex == -1 || i - curLen > prevIndex)
				curLen++;

			/*
			 * If the current character is present in currently considered NRCS,
			 * then update NRCS to start from the next character of previous instance.
			 */
			else {
				/*
				 * Also, when we are changing the NRCS, we should also check whether 
				 * length of the previous NRCS was greater than max_len or not.
				 */
				if (curLen >= maxLen) {
					maxLen = curLen;
					lss[i - maxLen] = maxLen;
				}
				curLen = i - prevIndex;
			}
			visited[str[i]] = i; // update the index of current character
		}
		// Compare the length of last NRCS with max_len and update max_len if needed
		if (curLen >= maxLen) {
			maxLen = curLen;
			lss[i - maxLen] = maxLen;
		}
		printLongSubStr(lss, str, maxLen);
		return maxLen;
	}

	private void printLongSubStr(int[] lss, char[] str, int maxLen) {
		for (int i = 0, l = lss.length; i < l; i++)
			if (lss[i] == maxLen)
				System.out.println("Longest Sub Str starts from : " + i);
	}

	public String runLengthStr() {
		int l = length;
		char[] op = new char[l * 2];
		int j = 0;
		int rLen;
		char[] ip = demoString.toCharArray();
		for (int i = 0; i < l; i++) {
			op[j++] = ip[i];
			rLen = 1;

			while (i + 1 < l && ip[i] == ip[i + 1]) {
				rLen++;
				i++;
			}

			op[j++] = (char) (rLen + 48);
		}
		return new String(op);
	}

	public String reverseWords() {
		char[] words = demoString.toCharArray();
		int l = length;
		int i = 0;
		int wordBegin = 0;
		while (i < l) {
			i++;
			// to reverse last word
			if (i == l)
				reverse(words, wordBegin, i - 1);
			// to reverse words
			else if (words[i] == ' ') {
				reverse(words, wordBegin, i - 1);
				wordBegin = i + 1;
			}
		}

		reverse(words, 0, l - 1);
		return new String(words);
	}

	public void printListStr(List<String> strings) {
		int len = length;
		char[] ip = demoString.toCharArray();

		int[] count = new int[256];

		for (int i = 0; i < len; i++)
			count[ip[i]] = 1;
		int c, j, l;
		for (int i = 0, s = strings.size(); i < s; i++) {
			for (j = 0, c = 0, l = strings.get(i).length(); j < l; j++) {
				if (count[strings.get(i).charAt(j)] == 1) {
					c++;
					count[strings.get(i).charAt(j)] = 0;
				}
			}

			if (c == len)
				System.out.println(strings.get(i));
			// prepare for next iteration
			for (int i1 = 0; i1 < len; i1++)
				count[ip[i1]] = 1;
		}
	}

	public void reverseStr() {
		int l = length;
		reverseStr(0, l);
	}

	private void reverseStr(int i, int l) {
		if (i == l)
			return;
		reverseStr(i + 1, l);
		System.out.print(demoString.charAt(i));
	}

	/**
	 * This method is used to remove all characters from str which are present
	 * ins mask
	 * 
	 * @param str
	 *            from which characters are to be removed
	 * @param mask
	 *            whose character are to be removed
	 * @return
	 */
	public String removeCharacter(String str, String mask) {
		boolean[] count = getBoolArr(mask);

		char[] op = new char[str.length()];
		int l = str.length();
		int j = 0;
		for (int i = 0; i < l; i++)
			if (!count[str.charAt(i)])
				op[j++] = str.charAt(i);
		return new String(op);
	}

	/**
	 * @param mask
	 * @return a boolean array indicating which characters are present
	 */
	private boolean[] getBoolArr(String mask) {
		int l = mask.length();
		boolean[] count = new boolean[256];
		for (int i = 0; i < l; i++)
			count[mask.charAt(i)] = true;
		return count;
	}

	/**
	 * Slide the pattern over text one by one and check for a match. If a match
	 * is found, then slides by 1 again to check for subsequent matches.
	 * 
	 * @param txt
	 *            where pattern is to be searched
	 * @param pat
	 *            pttern to be searched
	 */
	public void naivePatternSearching(final String txt, final String pat) {
		final int m = txt.length();
		final int n = pat.length();

		for (int i = 0; i < m - n; i++) {
			int j;

			for (j = 0; j < n; j++)
				if (txt.charAt(i + j) != pat.charAt(j))
					break;

			if (j == n)
				System.out.println("naive pattern found at : " + i);
		}
	}

	/**
	 * KMP algorithm does some preprocessing over the pattern pat[] and
	 * constructs an auxiliary array lps[] of size m (same as size of pattern).
	 * Here name lps indicates longest proper prefix which is also suffix.. For
	 * each sub-pattern pat[0…i] where i = 0 to m-1, lps[i] stores length of the
	 * maximum matching proper prefix which is also a suffix of the sub-pattern
	 * pat[0..i].
	 * 
	 * 
	 * Examples: For the pattern “AABAACAABAA”, lps[] is [0, 1, 0, 1, 2, 0, 1,
	 * 2, 3, 4, 5] For the pattern “ABCDE”, lps[] is [0, 0, 0, 0, 0] For the
	 * pattern “AAAAA”, lps[] is [0, 1, 2, 3, 4] For the pattern “AAABAAA”,
	 * lps[] is [0, 1, 2, 0, 1, 2, 3] For the pattern “AAACAAAAAC”, lps[] is [0,
	 * 1, 2, 0, 1, 2, 3, 3, 3, 4]
	 * 
	 * 
	 * 
	 * Searching Algorithm: Unlike the Naive algorithm where we slide the
	 * pattern by one, we use a value from lps[] to decide the next sliding
	 * position. Let us see how we do that. When we compare pat[j] with txt[i]
	 * and see a mismatch, we know that characters pat[0..j-1] match with
	 * txt[i-j+1...i-1], and we also know that lps[j-1] characters of
	 * pat[0...j-1] are both proper prefix and suffix which means we do not need
	 * to match these lps[j-1] characters with txt[i-j...i-1] because we know
	 * that these characters will anyway match.
	 * 
	 * 
	 * @param txt
	 *            where pattern is to be searched
	 * @param pat
	 *            pttern to be searched
	 */
	public void kmpPatSearch(String txt, String pat) {
		int m = txt.length();
		int n = pat.length();

		// create lps[] that will hold the longest prefix suffix values for
		// pattern
		final int[] lps = new int[n];

		// Preprocess the pattern (calculate lps[] array)
		computeLpsArray(lps, pat, n);
		System.out.println(Arrays.toString(lps));
		int i = 0;
		int j = 0;
		while (i < m && j < n) {
			if (txt.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			}
			if (j == n)
				System.out.println("KMP pattern at : " + (i - j));
			// mismatch after j matches
			else if (txt.charAt(i) != pat.charAt(j))
				// Do not match lps[0..lps[j-1]] characters, they will match
				// anyway
				if (j != 0)
					j = lps[j - 1];
				else
					i++;
		}
	}

	/**
	 * 
	 * Preprocessing Algorithm: 
	 * 
	 * In the preprocessing part, we calculate values in lps[]. 
	 * To do that, we keep track of the length of the longest prefix suffix value (we use len variable for this purpose) for the previous index. 
	 * We initialize lps[0] and len as 0. If pat[len] and pat[i] match, we increment len by 1 and assign the incremented value to lps[i]. 
	 * If pat[i] and pat[len] do not match and len is not 0, we update len to lps[len-1].
	 * 
	 * @param lps
	 *            lps array
	 * @param pat
	 *            pattern
	 * @param n
	 *            length of pattern
	 */
	private void computeLpsArray(final int[] lps, final String pat, final int n) {
		// length of the previous longest prefix suffix
		int len = 0;
		lps[0] = 0;
		int i = 1;

		// the loop calculates lps[i] for i = 1 to M-1
		while (i < n) {
			if (pat.charAt(i) == pat.charAt(len))
				lps[i++] = ++len;
			else {
				if (len != 0)
					// This is tricky. Consider the example AAACAAAA and i = 7.
					// Also, note that we do not increment i here
					len = lps[len - 1];
				else
					// if (len == 0)
					lps[i++] = 0;
			}
		}
	}
	
	public static int editDistance(final DemoString str1, final DemoString str2) {
		
		if (str1 == null && str2 == null) return 0;
		
		if (str1 == null) return str2.length;
		if (str2 == null) return str1.length;
		
		// both strings are present
		final int l1 = str1.length;
		final int l2 = str2.length;
		
		final int[][] editMatrix = new int[l1 + 1][l2 + 1];
		
		for (int i = 0; i <= l1; i++) {
			for (int j = 0; j <= l2; j++) {
				
				if (i == 0) editMatrix[i][j] = j; // we need J changes for this
				else if (j == 0) editMatrix[i][j] = i; // we need I changes for this
				
				// if both character are same, then simply copy value from previous
				else if (str1.demoString.charAt(i-1) == str2.demoString.charAt(j-1)) {
					editMatrix[i][j] = editMatrix[i-1][j-1];
				} else {
					// elements are different. either delete from Str1, or Insert in Str1 or replace str1 char with str2 character
					editMatrix[i][j] = 1 + Math.min(editMatrix[i-1][j], Math.min(editMatrix[i][j-1], editMatrix[i-1][j-1]));
				}
			}
		}
		
		return editMatrix[l1][l2];
	}

	/**
	 * Given a string, you need to transform this string in the form of a wave pattern
	 * 
	 * @param n level for wave
	 */
	public void printWaveForm(final int n) {
		final String str = this.demoString;
		final int len = str.length();
		
		if (n == 1) {
			return; // no need to print
		}
		
		final char[][] arr = new char[n][len];
		int row = 0;
		boolean down = true;
		
		for (int i = 0; i < len; i++) {
			
			arr[row][i] = str.charAt(i);
			
			if (row == n-1) {
				down = false;
			} else if (row == 0) {
				down = true;
			}
			
			row = down ? row+1 : row-1;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < len; j++) {
				System.out.print(arr[i][j] + "");
			}
			System.out.println();
		}
	}
}