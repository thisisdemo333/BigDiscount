package com.biggdiscountsmedia.biggdiscounts.dto;

import com.google.gson.annotations.SerializedName;

public class Address {
	
	@SerializedName("street_address")
	private String street_address;

	@SerializedName("city_name")
	private String city_name;
	
	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

}
