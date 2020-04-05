package com.heapcode.analytics;

import com.heapcode.analytics.pojo.Event;

/**
 * Abstraction of job queue where jobs can be submitted
 * 
 * @author Manjunath Sampath
 */
public interface IJobQueue {
	/**
	 * Submits the specified job to underlying queue
	 * 
	 * @param job Job to be submitted
	 */
	boolean produceJob(Event event);

}