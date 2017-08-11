/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class Scores {

	private static final int MAX_ENTRIES = 5;

	private int numEntries;
	private GameEntry[] entries;

	public Scores() {
		entries = new GameEntry[MAX_ENTRIES];
		numEntries = 0;
	}

	public void add(GameEntry e) {
		if (numEntries == entries.length)
			removeLeastEntry();

		addEntry(e);
	}

	private void addEntry(GameEntry e) {
		int i = numEntries;

		while ((i > 0) && (e.compareTo(entries[i - 1]) > 0)) {
			entries[i] = entries[i - 1];
			i--;
		}
		numEntries++;
		entries[i] = e;
	}

	private void removeLeastEntry() {
		entries[numEntries - 1] = null;
		numEntries--;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Scores [entries=%s]", Arrays.toString(entries));
	}
}
