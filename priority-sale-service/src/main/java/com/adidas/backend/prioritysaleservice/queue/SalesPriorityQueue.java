package com.adidas.backend.prioritysaleservice.queue;

import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetail;

/**
 * This class will hold Adi club user in the order of their priority to
 *         be winner
 *         
 * @author sushant
 * 
 */
@Component
public class SalesPriorityQueue {

	// TODO Can be injected as bean
	private PriorityQueue<AdiClubUserDetail> queue = new PriorityQueue<>();

	public void add(AdiClubUserDetail user) {
		queue.add(user);
	}

	public int length() {
		return queue.size();
	}

	public AdiClubUserDetail read() {
		return queue.poll();
	}

}
