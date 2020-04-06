/**
 * 
 */
package com.heapcode.analytics.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Manjunath Sampath
 *
 */
public class EventsResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2335011073709178989L;
	
	List<Event> events;

	/**
	 * @return the events
	 */
	public final List<Event> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public final void setEvents(List<Event> events) {
		this.events = events;
	}
}