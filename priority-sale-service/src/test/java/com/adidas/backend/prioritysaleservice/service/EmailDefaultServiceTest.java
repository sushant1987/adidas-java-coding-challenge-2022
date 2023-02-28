/**
 * 
 */
package com.adidas.backend.prioritysaleservice.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetail;
import com.adidas.backend.prioritysaleservice.queue.SalesPriorityQueue;

/**
 * @author sushant
 *
 */
@ExtendWith(MockitoExtension.class)
class EmailDefaultServiceTest {


	@InjectMocks
	EmailDefaultService emailDefaultService;
	
	@Mock
	SalesPriorityQueue salesPriorityQueue;
	
	@Mock
	KafkaTemplate< String, String> kafkaTemplate;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test for add user to email queue
	 * 
	 * Test method for {@link com.adidas.backend.prioritysaleservice.service.EmailDefaultService#addWinnerToEmail()}.
	 */
	@Test
	void testAddWinnerToEmail() {
		doReturn(getAdiClubUser()).when(salesPriorityQueue).read();
		emailDefaultService.addWinnerToEmail();
		verify(kafkaTemplate, times(1)).send(anyString(), anyString());
	}
	
	/**
	 * Test for empty queue
	 * 
	 * Test method for {@link com.adidas.backend.prioritysaleservice.service.EmailDefaultService#addWinnerToEmail()}.
	 */
	@Test
	void testAddWinnerToEmailNullTest() {
		doReturn(null).when(salesPriorityQueue).read();
		emailDefaultService.addWinnerToEmail();
		verify(kafkaTemplate, times(0)).send(anyString(), anyString());
	}
	
	private AdiClubUserDetail getAdiClubUser() {
		return new AdiClubUserDetail("abc@xyz.com", 2,
				ZonedDateTime.now(ZoneId.of("Z")));
	}

}
