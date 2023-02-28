/**
 * 
 */
package com.adidas.backend.prioritysaleservice.queue;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetail;

/**
 * @author sushant
 *
 */
@ExtendWith(MockitoExtension.class)
class SalesPriorityQueueTest {

	@InjectMocks
	SalesPriorityQueue salesPriorityQueue;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.adidas.backend.prioritysaleservice.queue.SalesPriorityQueue#add(com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetail)}
	 * {@link com.adidas.backend.prioritysaleservice.queue.SalesPriorityQueue#length()}.
	 */
	@Test
	void testAddAndLength() {
		AdiClubUserDetail user = new AdiClubUserDetail("test@email.com", 1, null);
		salesPriorityQueue.add(user);
		assertEquals(1, salesPriorityQueue.length());
	}

	/**
	 * Test method for
	 * {@link com.adidas.backend.prioritysaleservice.queue.SalesPriorityQueue#read()}.
	 */
	@Test
	void testRead() {
		salesPriorityQueue.add(nonClubMember("fifth@email.com", ZonedDateTime.now(ZoneId.of("Z")).minusMinutes(1)));
		salesPriorityQueue.add(getClubMember("third@adiclub.com", 1000, 1));
		salesPriorityQueue.add(getClubMember("second@adiclub.com", 2000, 2));
		salesPriorityQueue.add(getClubMember("fourth@adiclub.com", 500, 3));
		salesPriorityQueue.add(getClubMember("first@adiclub.com", 2000, 8));
		salesPriorityQueue.add(nonClubMember("sixth@email.com", ZonedDateTime.now(ZoneId.of("Z"))));

		assertEquals("first@adiclub.com", salesPriorityQueue.read().getEmail());
		assertEquals("second@adiclub.com", salesPriorityQueue.read().getEmail());
		assertEquals("third@adiclub.com", salesPriorityQueue.read().getEmail());
		assertEquals("fourth@adiclub.com", salesPriorityQueue.read().getEmail());
		assertEquals("fifth@email.com", salesPriorityQueue.read().getEmail());
		assertEquals("sixth@email.com", salesPriorityQueue.read().getEmail());
	}

	/**
	 * Create and return a non club member with registration date default as current
	 * date time
	 * 
	 * @return {@link AdiClubUserDetail}
	 */
	private AdiClubUserDetail nonClubMember(String email, ZonedDateTime registration) {
		AdiClubUserDetail user = new AdiClubUserDetail(email, 0, registration);
		return user;
	}

	/**
	 * Create and return a Adi club member with registration date as hour minus to
	 * the current time
	 * 
	 * @param points
	 * @param hours
	 * @return {@link AdiClubUserDetail}
	 */
	private AdiClubUserDetail getClubMember(String email, int points, int hours) {
		ZonedDateTime registariondate = ZonedDateTime.now(ZoneId.of("Z")).minusHours(hours);
		return new AdiClubUserDetail(email, points, registariondate);
	}
}
