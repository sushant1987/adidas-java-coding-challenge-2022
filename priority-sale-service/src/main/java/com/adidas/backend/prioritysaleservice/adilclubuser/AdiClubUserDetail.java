package com.adidas.backend.prioritysaleservice.adilclubuser;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * This class will hold the value of User from Adi club service
 * 
 * @author sushant
 * 
 */
public class AdiClubUserDetail implements Comparable<AdiClubUserDetail> {

	private String email;

	private Integer points;

	private ZonedDateTime registrationDate;

	public AdiClubUserDetail(String email, Integer points, ZonedDateTime registrationDate) {
		this.email = email;
		this.points = points;
		this.registrationDate = registrationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public ZonedDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(ZonedDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdiClubUserDetail other = (AdiClubUserDetail) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public int compareTo(AdiClubUserDetail o) {
		if (this.getPoints().intValue() < o.getPoints().intValue()) {
			return 1;
		} else if (this.getPoints().intValue() > o.getPoints().intValue()) {
			return -1;
		} else {
			return this.registrationDate.compareTo(o.getRegistrationDate());
		}
	}

}
