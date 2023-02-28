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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
	@Operation(summary = "Registeration API for priority sales")
	@ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Accepted for registartion",
	content = @Content(mediaType = "text/plain;charset=UTF-8")), @ApiResponse(responseCode = "400", description = 
	"Bad Resquest", content = @Content(mediaType = "text/plain;charset=UTF-8"))})
	@PostMapping
	public ResponseEntity<String> createSubscription(@Valid @RequestBody SubscriptionUser user) {
		log.info("Subscription initiated for {}", user.getEmail());
		kafkaTemplate.send("registration-events", user.getEmail());
		return ResponseEntity.accepted().body(SUBSCRIPTION_CREATED);

	}
}
