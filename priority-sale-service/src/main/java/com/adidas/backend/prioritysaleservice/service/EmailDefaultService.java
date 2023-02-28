package com.adidas.backend.prioritysaleservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetail;
import com.adidas.backend.prioritysaleservice.queue.SalesPriorityQueue;

/**
 * This service will produce events for email-service
 * 
 * @author sushant
 */
@Service
public class EmailDefaultService implements EmailService {

	private KafkaTemplate<String, String> kafkaTemplate;

	private final SalesPriorityQueue salesPriorityQueue;

	Logger log = LoggerFactory.getLogger(EmailDefaultService.class);

	@Autowired
	public EmailDefaultService(final KafkaTemplate<String, String> kafkaTemplate,
			final SalesPriorityQueue salesPriorityQueue) {
		this.kafkaTemplate = kafkaTemplate;
		this.salesPriorityQueue = salesPriorityQueue;
	}

	/**
	 * Adding winners email to queue for sending email
	 */
	@Scheduled(fixedRate = 100000)
	@Override
	public void addWinnerToEmail() {
		log.info("Adding winner to Email queue");
		AdiClubUserDetail user = salesPriorityQueue.read();
		if (user != null) {
			kafkaTemplate.send("email-events", user.getEmail());
		} else {
			log.debug("Adi club user priority queue is empty");
		}
	}
}
