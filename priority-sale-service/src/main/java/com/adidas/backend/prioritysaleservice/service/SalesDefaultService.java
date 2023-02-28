package com.adidas.backend.prioritysaleservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetail;
import com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetailService;
import com.adidas.backend.prioritysaleservice.queue.SalesPriorityQueue;

/**
 * This service is for interaction with SalesPriorityQueue
 * 
 * @author sushant
 *
 */
@Component
public class SalesDefaultService implements SalesService {

	Logger log = LoggerFactory.getLogger(SalesDefaultService.class);

	private final AdiClubUserDetailService adiClubUserDetailService;

	private final SalesPriorityQueue salesPriorityQueue;

	@Autowired
	public SalesDefaultService(final AdiClubUserDetailService adiClubUserDetailService,
			final SalesPriorityQueue salesPriorityQueue) {
		this.adiClubUserDetailService = adiClubUserDetailService;
		this.salesPriorityQueue = salesPriorityQueue;
	}

	/**
	 * @param email
	 * 
	 *              This method will add user to the sales priority queue based on
	 *              the priority. Priority is described below in decreasing order
	 * 
	 *              Adi club member with highest points Adi club member with older
	 *              registration date Non Adi club member
	 * 
	 */
	@KafkaListener(id = "group-id", topics = "registration-events")
	@Override
	public void addUserToSalesQueue(String email) {
		log.info("Adding user to sales queue");
		AdiClubUserDetail user = adiClubUserDetailService.getAdiClubUser(email);
		salesPriorityQueue.add(user);
		log.info("Email {} has point {} added to queue", user.getEmail(), user.getPoints());
	}

}
