/**
 * 
 */
package com.adidas.backend.prioritysaleservice.adilclubuser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

/**
 * @author sushant
 *
 */
@ExtendWith(MockitoExtension.class)
class AdiClubUserDetailDefaultServiceTest {

	@InjectMocks
	AdiClubUserDetailDefaultService adiClubUserDetailDefaultService;

	@Mock
	RestTemplate restTemplate;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * 
	 * Test for non club member
	 * 
	 * Test method for
	 * {@link com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetailDefaultService#getAdiClubUser(java.lang.String)}.
	 */
	@Test
	void testGetAdiClubUserNonClubMemeber() {
		assertEquals(0, adiClubUserDetailDefaultService.getAdiClubUser("test@xmail.com").getPoints());
	}

	/**
	 * 
	 * Test for club member
	 * 
	 * Test method for
	 * {@link com.adidas.backend.prioritysaleservice.adilclubuser.AdiClubUserDetailDefaultService#getAdiClubUser(java.lang.String)}.
	 */
	@Test
	void testGetAdiClubUser() {
		ZonedDateTime date = ZonedDateTime.now(ZoneId.of("Z")).minusDays(15);

		AdiClubUserDetail user = new AdiClubUserDetail("test@adiclub.com", 5, date);
		
		doReturn(user).when(restTemplate).getForObject(any(), eq(AdiClubUserDetail.class));
		assertEquals(user, adiClubUserDetailDefaultService.getAdiClubUser("test@adiclub.com"));
	}

}
