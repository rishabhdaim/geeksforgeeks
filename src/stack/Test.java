/**
 * 
 */
package stack;

import java.util.Arrays;

/**
 * @author Rishabh.Daim
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		solution(new int[] { 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 });
	}

	static public int[] solution(int[] T) {

		int l = T.length;
		final Intervals[] is = new Intervals[T.length - 1];

		int capital = 0;
		for (int i = 0, j = 0; i < l; i++) {
			if (T[i] == i)
				capital = i;
			else
				is[j++] = new Intervals(i, T[i], 1);
		}

		System.out.println(Arrays.toString(is));
		Arrays.sort(T);
		System.out.println(Arrays.toString(is));

		for (int j = 0; j < l - 1; j++) {
			if (is[j].end == capital) {
			} else if (is[j].start == capital) {
			}

		}

		return T;
	}

	private static class Intervals implements Comparable<Intervals> {
		private int start;
		private int end;
		private int distance;

		/**
		 * @param start
		 * @param end
		 */
		public Intervals(int start, int end, int distance) {
			super();
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Intervals [start=" + start + ", end=" + end + ", distance="
					+ distance + "]";
		}

		@Override
		public int compareTo(Intervals o) {
			int i = Integer.valueOf(this.start).compareTo(o.start);
			if (i != 0)
				return i;
			else
				return Integer.valueOf(this.end).compareTo(o.end);
		}

	}
}
