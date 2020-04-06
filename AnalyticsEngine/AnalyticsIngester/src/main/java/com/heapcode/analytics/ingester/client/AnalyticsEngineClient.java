/**
 * 
 */
package com.heapcode.analytics.ingester.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.heapcode.analytics.ApplicationConstants;
import com.heapcode.analytics.IJobQueue;
import com.heapcode.analytics.pojo.Event;

/**
 * @author Manjunath Sampath
 *
 */
@Component
@EnableScheduling
public class AnalyticsEngineClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticsEngineClient.class);

	@Autowired
	private IJobQueue iJobQueue;
	
	@Autowired
	Environment environment;

	/**
	 * @param args
	 */
	/*
	 * Scheduler based Ingester which hits the GitHub API every 10 seconds and fetches the data.
	 */
	@Scheduled(fixedDelay = 10000)
	public void ingestData() {
		String url = environment.getProperty(ApplicationConstants.DATA_END_POINT)+"?client_id="+environment.getProperty(ApplicationConstants.CLIENT_ID)+"&client_secret="+environment.getProperty(ApplicationConstants.CLIENT_SECRET);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Event> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Event[]> responseEntity = new RestTemplate().exchange(url,
				HttpMethod.GET, requestEntity, Event[].class);
		Event[] events = responseEntity.getBody();
		for (Event event : events) {
			LOGGER.error("Publishing event with id:{} to Kafka",event.getId());
			if (event.getPayload() != null && event.getPayload().getCommits() != null
					&& !event.getPayload().getCommits().isEmpty()) {
				iJobQueue.produceJob(event);
			}
		}
	}
}