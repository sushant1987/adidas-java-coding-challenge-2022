/**
 * 
 */
package com.adidas.backend.prioritysaleservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetail;
import com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetailService;
import com.adidas.backend.prioritysaleservice.queue.SalesPriorityQueue;

/**
 * @author sushant
 *
 */
@ExtendWith(MockitoExtension.class)
class SalesDefaultServiceTest {
	
	@InjectMocks
	SalesDefaultService salesDefaultService;
	
	@Mock
	AdiClubUserDetailService adiClubUserDetailService;
	
	@Mock
	SalesPriorityQueue salesPriorityQueue;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link com.adidas.backend.prioritysaleservice.service.SalesDefaultService#addUserToSalesQueue(java.lang.String)}.
	 */
	@Test
	void testAddUserToSalesQueue() {
		AdiClubUserDetail user = new AdiClubUserDetail("test@adiclub.com", 0, null);
		doReturn(user).when(adiClubUserDetailService).getAdiClubUser(anyString());
		salesDefaultService.addUserToSalesQueue("test@adiclub.com");
		verify(salesPriorityQueue, times(1)).add(any());
	}

}
