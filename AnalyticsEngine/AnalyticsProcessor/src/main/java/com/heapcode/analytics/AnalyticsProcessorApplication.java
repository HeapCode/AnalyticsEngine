package com.heapcode.analytics;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.heapcode.analytics.processor.AnalyticsIngestProcessor;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.heapcode.analytics" })
@SpringBootApplication
public class AnalyticsProcessorApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticsProcessorApplication.class);

	@Autowired
	Environment environment;

	@Autowired
	private AnalyticsIngestProcessor analyticsIngestProcessor;

	private KafkaStreams processStream;

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsProcessorApplication.class, args);
	}

	@PostConstruct
	private void init() throws InterruptedException {

		try {

			final Properties props = new Properties();
			props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
					environment.getProperty(ApplicationConstants.KAFKA_BROKERS));
			props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerde.class.getName());
			props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, StringSerde.class.getName());
			props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
			props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");

			Topology processTopologyBuilder = new Topology();
			processTopologyBuilder.addSource("ProcessSource", environment.getProperty(ApplicationConstants.KAFKA_TOPIC))
					.addProcessor("IngestProcess", () -> analyticsIngestProcessor, "ProcessSource");

			props.put(StreamsConfig.APPLICATION_ID_CONFIG, "ingestAppIdConfig");
			props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, "1");
			processStream = new KafkaStreams(processTopologyBuilder, props);
			processStream.start();

		} catch (Exception e) {
			LOGGER.error("Error starting Kafka Streams Application", e);
		}
	}

	@PreDestroy
	private void clean() {
		try {
			if (processStream != null) {
				processStream.close();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}