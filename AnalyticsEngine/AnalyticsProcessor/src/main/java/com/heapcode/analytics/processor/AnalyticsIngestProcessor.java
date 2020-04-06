/*
 * Copyright (c) 2020 Yodlee, Inc. All Rights Reserved.
 *
 *
 * This software is the confidential and proprietary information of Yodlee, Inc. Use is subject to license terms.
 */
package com.heapcode.analytics.processor;

import java.io.IOException;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heapcode.analytics.SlidingWindowQueue;
import com.heapcode.analytics.pojo.Event;

/**
 * @author Manjunath Sampath
 *
 */
@Component
@Scope("prototype")
public class AnalyticsIngestProcessor implements Processor<String, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticsIngestProcessor.class);

	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void init(ProcessorContext context) {
	}

	@Override
	public void process(String key, String value) {
		try {
			if (value != null && !value.trim().isEmpty()) {
				Event event = objectMapper.readValue(value, Event.class);
				SlidingWindowQueue.addEvaluateEvent(event);
			}
		} catch (IOException e) {
			LOGGER.error("AnalyticsIngestProcessor-Exception during consuming requests from Kafka", e);
		}
	}

	public void punctuate(long timestamp) {
	}

	@Override
	public void close() {
	}
}