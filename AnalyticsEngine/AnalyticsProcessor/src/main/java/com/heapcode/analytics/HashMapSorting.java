package com.heapcode.analytics;

/**
 * @author Manjunath Sampath
 *
 */
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapSorting {
	public static void main(String args[]) throws ParseException {
		// let's create a map with Java releases and their code names
		HashMap<String, Integer> codenames = new HashMap<String, Integer>();
		codenames.put("JDK 1.1.4", 1);
		codenames.put("J2SE 1.2", 2);
		codenames.put("J2SE 1.3", 3);
		codenames.put("J2SE 1.4", 4);
		codenames.put("J2SE 5.0", 5);
		codenames.put("Java SE 6", 6);
		codenames.put("Java SE 7", 7);
		Set<Entry<String, Integer>> entries = codenames.entrySet();
		
		Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				Integer v1 = e1.getValue();
				Integer v2 = e2.getValue();
				return v2.compareTo(v1);
			}
		};

		List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries);
		Collections.sort(listOfEntries, valueComparator);
		LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());
		for (Entry<String, Integer> entry : listOfEntries) {
			sortedByValue.put(entry.getKey(), entry.getValue());
		}
		Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
		for (Entry<String, Integer> mapping : entrySetSortedByValue) {
			System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
		}
	}
}