/**
 * 
 */
package com.adidas.backend.emailservice.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author sushant
 * 
 *This service will send email
 */
@Component
public class SendEmailDefaultService implements SendEmailService {

	Logger log = LoggerFactory.getLogger(SendEmailDefaultService.class);
	
	/**
	 * @param email
	 * 
	 * This method will send email as log
	 */
	@Override
	public void sendEmail(String email) {
		log.info("Email sent to {}", email);
		
	}

}
