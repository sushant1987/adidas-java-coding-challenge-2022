/**
 * 
 */
package com.adidas.backend.emailservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.adidas.backend.emailservice.communication.SendEmailService;

/**
 * @author sushant
 *
 *This class will listen to the email events 
 */
@Service
public class ListnerDefaultService implements ListnerService {

	Logger log = LoggerFactory.getLogger(ListnerDefaultService.class);
	private final SendEmailService sendEmailService;

	@Autowired
	public ListnerDefaultService(final SendEmailService emailService) {
		this.sendEmailService = emailService;
	}
	
	/**
	 * @param email
	 * 
	 * This method will consume email ids from email-events 
	 * and send to email service
	 */
	@KafkaListener(id = "winner-email-group", topics = "email-events")
	@Override
    public void sendEmailToWinner(String email) {
		log.info("Sending email to winner");
		sendEmailService.sendEmail(email);
	}
	
}
