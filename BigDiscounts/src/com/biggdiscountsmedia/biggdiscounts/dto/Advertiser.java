package com.biggdiscountsmedia.biggdiscounts.dto;

import com.google.gson.annotations.SerializedName;

public class Advertiser {
	
	@SerializedName("mobile_number")
	private String mobile_number;
	
	@SerializedName("email")
	private String email;
	
	@SerializedName("full_name")
	private String full_name;

	/**
	 * @return the mobile_number
	 */
	public String getMobile_number() {
		return mobile_number;
	}

	/**
	 * @param mobile_number the mobile_number to set
	 */
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the full_name
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * @param full_name the full_name to set
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
}
