/**
 * 
 */
package com.heapcode.analytics;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heapcode.analytics.pojo.Commit;
import com.heapcode.analytics.pojo.Event;

public class SlidingWindowQueue {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticsProcessorApplication.class);

	static String commitMessage = "";

	private static Queue<Event> fifo = new CircularFifoQueue<Event>(10);

	private static Map<String, Integer> stringCountMap = new HashMap<>();
	private static Map<Integer, Integer> commitHourCountMap = new HashMap<>();

	public static void addEvaluateEvent(Event event) {
		fifo.add(event);
		stringCountMap.clear();
		commitHourCountMap.clear();
		evaluateSlidingWindowQueue();
	}

	public static void evaluateSlidingWindowQueue() {
		for (Iterator<Event> iterator = fifo.iterator(); iterator.hasNext();) {
			Event event = (Event) iterator.next();

			int hourOfDay = getHourOfDay(event.getCreatedAt());

			if (commitHourCountMap.containsKey(hourOfDay)) {
				commitHourCountMap.put(hourOfDay, commitHourCountMap.get(hourOfDay + 1));
			} else {
				commitHourCountMap.put(hourOfDay, 1);
			}

			Set<Entry<Integer, Integer>> hourEntrySetSortedByValue = getSortedCommitHourEntrySet();
			Entry<Integer, Integer> topEntry = hourEntrySetSortedByValue.iterator().next();
			
			LOGGER.error("########### Most popular hour of commit is for the last 10 commits is {} hours as per IST.",topEntry.getKey());
			List<Commit> commits = event.getPayload().getCommits();
			for (Iterator<Commit> iterator2 = commits.iterator(); iterator2.hasNext();) {
				Commit commit = (Commit) iterator2.next();
				commitMessage = commit.getMessage();
				String[] messageStrings = commitMessage.split(" ");
				for (int i = 0; i < messageStrings.length; i++) {
					if (stringCountMap.containsKey(messageStrings[i])) {
						stringCountMap.put(messageStrings[i], stringCountMap.get(messageStrings[i] + 1));
					} else {
						stringCountMap.put(messageStrings[i], 1);
					}
				}				
				LinkedHashMap<String, Integer> sortedByValue = getSortedCommitCountEntrySet();
				
				LOGGER.error("########### Printing the most frequently  used words in the last 10 Commits.");
				Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
				int i = 1;
				for (Entry<String, Integer> mapping : entrySetSortedByValue) {
					if (i > 5) {
						break;
					}
					System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
					i++;
				}
				LOGGER.error("########### Finished Printing the most frequently used words.");
				System.out.println();
				System.out.println();
			}
		}
	}

	/**
	 * @return
	 */
	private static LinkedHashMap<String, Integer> getSortedCommitCountEntrySet() {
		Set<Entry<String, Integer>> entries = stringCountMap.entrySet();
		Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				Integer v1 = e1.getValue();
				Integer v2 = e2.getValue();
				if (v1 == null && v2 == null) {
					return 0;
				} else if (v1 == null && v2 != null) {
					return 1;
				} else if (v1 != null && v2 == null) {
					return -1;
				}
				return v2.compareTo(v1);
			}
		};

		List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries);
		Collections.sort(listOfEntries, valueComparator);
		LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());
		for (Entry<String, Integer> entry : listOfEntries) {
			sortedByValue.put(entry.getKey(), entry.getValue());
		}
		return sortedByValue;
	}

	private static Set<Entry<Integer, Integer>> getSortedCommitHourEntrySet() {
		Set<Entry<Integer, Integer>> hourEntries = commitHourCountMap.entrySet();
		Comparator<Entry<Integer, Integer>> hourValueComparator = new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {
				Integer v1 = e1.getValue();
				Integer v2 = e2.getValue();
				if (v1 == null && v2 == null) {
					return 0;
				} else if (v1 == null && v2 != null) {
					return 1;
				} else if (v1 != null && v2 == null) {
					return -1;
				}
				return v2.compareTo(v1);
			}
		};

		List<Entry<Integer, Integer>> hourListOfEntries = new ArrayList<Entry<Integer, Integer>>(hourEntries);
		Collections.sort(hourListOfEntries, hourValueComparator);
		LinkedHashMap<Integer, Integer> hourSortedByValue = new LinkedHashMap<Integer, Integer>(
				hourListOfEntries.size());
		for (Entry<Integer, Integer> entry : hourListOfEntries) {
			hourSortedByValue.put(entry.getKey(), entry.getValue());
		}

		Set<Entry<Integer, Integer>> hourEntrySetSortedByValue = hourSortedByValue.entrySet();
		return hourEntrySetSortedByValue;
	}

	private static int getHourOfDay(String date) {
		Instant s = Instant.parse(date);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(s, ZoneId.of("Asia/Kolkata"));
		return localDateTime.getHour();
	}

}