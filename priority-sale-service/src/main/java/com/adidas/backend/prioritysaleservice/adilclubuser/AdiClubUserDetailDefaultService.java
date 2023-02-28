package com.adidas.backend.prioritysaleservice.adilclubuser;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This class provides interaction with adi club service
 * 
 * @author sushant
 * 
 */
@Service
public class AdiClubUserDetailDefaultService implements AdiClubUserDetailService {

	private static final String BASE_URL = "http://adidas-be-challenge-adiclubservice:8080/adiclub?emailAddress=";

	private static final String EMAIL_PATTERN = "^(.+)@adiclub.com";

	Logger log = LoggerFactory.getLogger(AdiClubUserDetailDefaultService.class);

	private final RestTemplate restTemplate;

	@Autowired
	public AdiClubUserDetailDefaultService(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * This method will return Adi club member for member email like
	 * ^(.+)@adiclub.com else return an AdiClubUserDetail with 0 points and current
	 * date time as registration
	 * 
	 * @param email
	 * @return {@link AdiClubUserDetail}
	 */
	@Override
	public AdiClubUserDetail getAdiClubUser(String email) {
		AdiClubUserDetail user;
		if (adiClubUser(email)) {
			log.info("Adi Club user");
			user = getByEmailId(email);
		} else {
			log.info("Non Adi Club user");
			ZonedDateTime date = ZonedDateTime.now(ZoneId.of("Z"));
			user = new AdiClubUserDetail(email, 0, date);
		}
		return user;
	}

	private AdiClubUserDetail getByEmailId(String email) {
		URI url;
		AdiClubUserDetail result = null;
		try {
			url = new URI(BASE_URL + email);
			result = restTemplate.getForObject(url, AdiClubUserDetail.class);
		} catch (URISyntaxException e) {
			log.error("URI syntax is wrong");
		}
		return result;
	}

	private boolean adiClubUser(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		return pattern.matcher(email).matches();
	}

}
