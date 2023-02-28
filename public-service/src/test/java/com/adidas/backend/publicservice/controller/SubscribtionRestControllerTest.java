/**
 * 
 */
package com.adidas.backend.publicservice.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.adidas.backend.publicservice.dto.SubscriptionUser;

/**
 * 
 * @author sushant
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SubscribtionRestController.class)
class SubscribtionRestControllerTest {


	@Autowired
	SubscribtionRestController subscribtionRestController;
	
	@MockBean
	KafkaTemplate<String, String> kafkaTemplate;
	
	/**
	 * Test method for {@link com.adidas.backend.publicservice.controller.SubscribtionRestController#createSubscription(com.adidas.backend.publicservice.dto.SubscriptionUser)}.
	 */
	@Test
	void testCreateSubscription() {
		String expectedResponse = "Subsciption created";
		SubscriptionUser user = new SubscriptionUser();
		user.setEmail("test@Email.com");
		ResponseEntity<String> response = subscribtionRestController.createSubscription(user);
		Assertions.assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		Assertions.assertEquals(expectedResponse, response.getBody());
	}
	
}
