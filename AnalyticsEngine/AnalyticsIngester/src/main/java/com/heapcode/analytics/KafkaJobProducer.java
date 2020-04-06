/**
 * 
 */
package com.heapcode.analytics;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.processor.internals.StreamsPartitionAssignor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heapcode.analytics.pojo.Event;

/**
 * Kafka based Job queue to be used for queuing jobs.
 * 
 * @author Manjunath Sampath
 */
@Component
public class KafkaJobProducer implements IJobQueue {
	private static final Logger logger = LoggerFactory.getLogger(KafkaJobProducer.class);
	private static int producerTimeOut = 5; // default value
	private static final String defaultAcksMode = "-1";

	/**
	 * List of kafka servers. Should be in format
	 * <server1>:<port>,<server2>:<port>...
	 */
	private String brokerList;

	/**
	 * Properties that will be used to Kafka producer. This object is for internal
	 * maintenance.
	 */
	private Properties kafkaProperties = new Properties();

	/**
	 * Object mapper used to convert job objects into json
	 */
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Kafka producer instance. Please note, kafka producer is thread-safe
	 */
	private Producer<String, String> producer;

	public KafkaJobProducer() {
		// set default kafka properties
		kafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		kafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//		kafkaProperties.put(ProducerConfig.CLIENT_ID_CONFIG, "my-first-streams-application");
//		kafkaProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-first-streams-application");
//		kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, InsightsConfigConstants.KAFKA_CLIENT_ID_CONFIG);
		kafkaProperties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
				StreamsPartitionAssignor.class.getName());

	}

	/**
	 * Sets Kafka broker list
	 * 
	 * @param brokerList
	 */
	public void setBrokerList(String brokerList) {
		this.brokerList = brokerList;
	}

	/**
	 * init method to initialize producer
	 */
	@PostConstruct
	private void init() {
		String acksMode = "-1";
		if (acksMode == null) {
			acksMode = defaultAcksMode;
		}

		String prodTimeOut = "5";
		if (prodTimeOut != null) {
			producerTimeOut = Integer.parseInt(prodTimeOut);
		}

		logger.debug("Initializing Kafka Queue Producer...");

		brokerList = "localhost:9092";
		logger.debug("Kafka broker list : {}", brokerList);

		kafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
		kafkaProperties.put(ProducerConfig.ACKS_CONFIG, acksMode);

		logger.info("Using following properties for Kafka Queue - \n{}", kafkaProperties);
		producer = new KafkaProducer<String, String>(kafkaProperties);
		logger.info("Kafka producer created successfully with ackmode : {}", acksMode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yodlee.pengine.web.service.IJobQueue#submitJob(com.yodlee.pengine.entity.
	 * JobEntity)
	 */
	@Override
	public boolean produceJob(Event event) {
		boolean rslt = false;
		try {
			// initialize the kafka producer
			// producer = new KafkaProducer<String, String>(kafkaProperties);
			String jobTopicName = "ANALYTICS_INGEST";
			String queueRequest = objectMapper.writeValueAsString(event);
			logger.debug("Adding job request to queue {} - {}", jobTopicName, queueRequest);

			ProducerRecord<String, String> rec = new ProducerRecord<String, String>(jobTopicName, queueRequest);
			producer.send(rec).get(producerTimeOut, TimeUnit.SECONDS);

			logger.debug("Submitted event to kafka topic {}", jobTopicName);
			rslt = true;

		} catch (JsonProcessingException ex) {
			logger.error("An error occurred while submitting event to queue", ex);
		} catch (Exception e) {
			logger.error("An error occurred while submitting event to queue", e);
		}
		return rslt;
	}

	/**
	 * Cleanup method used to close producer
	 */
	@PreDestroy
	private void clean() {
		try {
			producer.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}