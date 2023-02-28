/**
 * 
 */
package com.adidas.backend.publicservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.backend.publicservice.dto.SubscriptionUser;

/**
 * This controller provides REST API for subscription
 * 
 * @author sushant
 *
 */
@RestController
@RequestMapping("/subscribe")
public class SubscribtionRestController {

	Logger log = LoggerFactory.getLogger(SubscribtionRestController.class);

	private static final String SUBSCRIPTION_CREATED = "Subsciption created";

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public SubscribtionRestController(final KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	/**
	 * Create subscription for given user
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<String> createSubscription(@Valid @RequestBody SubscriptionUser user) {
		log.info("Subscription initiated for {}", user.getEmail());
		kafkaTemplate.send("registration-events", user.getEmail());
		return ResponseEntity.accepted().body(SUBSCRIPTION_CREATED);

	}
}
