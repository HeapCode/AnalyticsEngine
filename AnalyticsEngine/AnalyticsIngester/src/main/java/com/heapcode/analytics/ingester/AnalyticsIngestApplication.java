package com.heapcode.analytics.ingester;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.heapcode.analytics.ingester.client.AnalyticsEngineClient;

/**
 * @author Manjunath Sampath
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.heapcode.analytics"})
@SpringBootApplication
public class AnalyticsIngestApplication {

	@Autowired
	private AnalyticsEngineClient client;

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsIngestApplication.class, args);
	}

	@PostConstruct
	private void init() throws InterruptedException {
		client.ingestData();
	}
}