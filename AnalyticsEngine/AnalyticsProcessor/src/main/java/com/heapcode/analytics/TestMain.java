/**
 * 
 */
package com.heapcode.analytics;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author msampath
 *
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Instant s = Instant.parse("2019-09-28T18:12:17Z");
        LocalDateTime l = LocalDateTime.ofInstant(s, ZoneId.of("Asia/Kolkata"));
        System.out.println(l);
        System.out.println(l.getHour());
	}
}
