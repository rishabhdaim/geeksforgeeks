/**
 * 
 */
package hashing;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class HashMap<K, V> {

	private Entry<K, V>[] table;
	private int size;
	private final float loadFactor;
	private int threshold;

	/**
	 * @return the table
	 */
	public Entry<K, V>[] getTable() {
		return table;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the loadFactor
	 */
	public float getLoadFactor() {
		return loadFactor;
	}

	/**
	 * @return the threshold
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * @param size
	 * @param loadFactor
	 * @param threshold
	 */
	@SuppressWarnings("unchecked")
	public HashMap(float loadFactor, int capacity) {
		super();
		this.loadFactor = loadFactor;
		this.threshold = (int) (loadFactor * capacity);
		this.size = 0;
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	public HashMap() {
		this(0.75f, 2);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		for (int i = 0, l = table.length; i < l; i++)
			table[i] = null;
		size = 0;
	}

	private int indexFor(int h, int length) {
		return h & length - 1;
	}

	public V put(K key, V value) {
		int hash = key.hashCode();
		int i = indexFor(hash, table.length);

		for (Entry<K, V> e = table[i]; e != null; e = e.next) {
			if (hash == e.hash && (key == e.key || e.key.equals(key))) {
				e.recordAccess(this);
				return e.setValue(value);
			}
		}

		addEntry(key, value, hash, i);
		return null;
	}

	private void addEntry(K key, V value, int hash, int index) {
		// if we are adding to empty place then no issue..
		if (size >= this.threshold && null != table[index]) {
			rehash(2 * table.length);
			hash = null == key ? 0 : key.hashCode();
			index = indexFor(hash, table.length);
		}

		createEntry(key, value, hash, index);
	}

	private void createEntry(K key, V value, int hash, int index) {
		Entry<K, V> e = table[index];
		table[index] = new Entry<K, V>(key, value, e, hash);
		size++;
	}

	@SuppressWarnings("unchecked")
	private void rehash(int newCapacity) {
		// if can't go further..
		if (table.length == Integer.MAX_VALUE) {
			this.threshold = Integer.MAX_VALUE;
			return;
		}
		@SuppressWarnings("rawtypes")
		Entry[] newTable = new Entry[newCapacity];

		transfer(newTable);

		table = newTable;
		threshold = (int) Math.min(newCapacity * loadFactor, Integer.MAX_VALUE);
	}

	@SuppressWarnings("unchecked")
	private void transfer(@SuppressWarnings("rawtypes") Entry[] newTable) {
		int newCapacity = newTable.length;

		for (Entry<K, V> e : table) {
			while (e != null) {
				Entry<K, V> next = e.next;
				int index = indexFor(e.hash, newCapacity);
				// point next entry to first entry of new index of new table..
				e.next = newTable[index];
				newTable[index] = e;
				e = next;
			}
		}
	}

	public V get(K key) {
		Entry<K, V> e = getEntry(key);
		return e == null ? null : e.value;
	}

	public boolean containsKey(K key) {
		Entry<K, V> e = getEntry(key);
		return e == null ? false : true;
	}

	private Entry<K, V> getEntry(K key) {
		int hash = key == null ? 0 : key.hashCode();
		int i = indexFor(hash, table.length);
		for (Entry<K, V> e = table[i]; e != null; e = e.next)
			if (e.hash == hash
					&& (e.key == key || (key != null && key.equals(e.key))))
				return e;
		return null;
	}

	public V remove(K key) {
		Entry<K, V> e = removeEntry(key);
		return e == null ? null : e.value;

	}

	private Entry<K, V> removeEntry(K key) {
		int hash = key == null ? 0 : key.hashCode();
		int i = indexFor(hash, table.length);
		Entry<K, V> prev = null;
		Entry<K, V> e = table[i];
		while (e != null) {
			if (e.hash == hash
					&& (e.key == key || (key != null && key.equals(e.key)))) {
				if (prev == null)
					table[i] = e.next;
				else
					prev.next = e.next;
				e.recordRemoval(this);
				size--;
				return e;
			}
			prev = e;
			e = e.next;
		}
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HashMap [Table=" + Arrays.toString(table) + "]";
	}

	static class Entry<K, V> {
		final K key;
		V value;
		Entry<K, V> next;
		int hash;

		/**
		 * @param key
		 * @param value
		 * @param next
		 * @param hash
		 */
		public Entry(K key, V value, Entry<K, V> next, int hash) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
			this.hash = hash;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "[key=" + key + ", val=" + value + "]";
		}

		/**
		 * @return the key
		 */
		public K getKey() {
			return key;
		}

		/**
		 * @return the next
		 */
		public Entry<K, V> getNext() {
			return next;
		}

		public V setValue(V value) {
			V v = this.value;
			this.value = value;
			return v;
		}

		/**
		 * This method is invoked whenever the value in an entry is overwritten
		 * by an invocation of put(k,v) for a key k that's already in the
		 * HashMap.
		 */
		void recordAccess(HashMap<K, V> m) {
		}

		/**
		 * This method is invoked whenever the entry is removed from the table.
		 */
		void recordRemoval(HashMap<K, V> m) {
		}
	}
}
