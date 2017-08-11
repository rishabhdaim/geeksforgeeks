/**
 * 
 */
package linkedlists;

import hashing.HashMap;

/**
 * @author aa49442
 * 
 */
public class LRUCacheTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LruCache<String> cache = new LruCache<String>(10);
		HashMap<Integer, String> dbMap = new HashMap<Integer, String>(0.7f, 10);

		for (int i = 0; i < 10000; i++)
			dbMap.put(i, "a" + i);

		for (int i = 0; i < 200; i++)
			cache.add(dbMap.get(i));

		System.out.println(cache);
	}
}