package com.biggdiscountsmedia.biggdiscounts.dto;

public class RatingRequest {

	private String advertisement_id;
	private String rating_value;
	private String access_token;
	public String getAdvertisement_id() {
		return advertisement_id;
	}
	public void setAdvertisement_id(String advertisement_id) {
		this.advertisement_id = advertisement_id;
	}
	public String getRating_value() {
		return rating_value;
	}
	public void setRating_value(String rating_value) {
		this.rating_value = rating_value;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	@Override
	public String toString() {
		return "RatingRequest [advertisement_id=" + advertisement_id
				+ ", rating_value=" + rating_value + ", access_token="
				+ access_token + "]";
	}
	
}
