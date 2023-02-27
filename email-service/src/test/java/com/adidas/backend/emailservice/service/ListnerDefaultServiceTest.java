/**
 * 
 */
package com.adidas.backend.emailservice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adidas.backend.emailservice.communication.SendEmailService;

/**
 * @author sushant
 *
 */
@ExtendWith(MockitoExtension.class)
class ListnerDefaultServiceTest {

	@InjectMocks
	public ListnerDefaultService listnerDefaultService;

	@Mock
	public SendEmailService sendEmailService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test for valid email
	 * 
	 * Test method for
	 * {@link com.adidas.backend.emailservice.service.ListnerDefaultService#sendEmailToWinner(java.lang.String)}.
	 * 
	 */
	@Test
	void testSendEmailToWinner() {
		doNothing().when(sendEmailService).sendEmail(anyString());
		listnerDefaultService.sendEmailToWinner("abc@wmail.com");
		verify(sendEmailService, times(1)).sendEmail(anyString());
	}

}
