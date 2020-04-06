package com.heapcode.analytics;

import com.heapcode.analytics.pojo.Event;

/**
 * Abstraction of event queue where events can be submitted
 * 
 * @author Manjunath Sampath
 */
public interface IJobQueue {
	/**
	 * Submits the specified event to underlying queue
	 * 
	 * @param event Event to be submitted
	 */
	boolean produceJob(Event event);

}