package com.biggdiscountsmedia.biggdiscounts.dto;

import com.google.gson.annotations.SerializedName;

public class Images {

	@SerializedName("image")
	private String image;

	@SerializedName("advertisement_id")
	private int advertisement_id;

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the advertisement_id
	 */
	public int getAdvertisement_id() {
		return advertisement_id;
	}

	/**
	 * @param advertisement_id
	 *            the advertisement_id to set
	 */
	public void setAdvertisement_id(int advertisement_id) {
		this.advertisement_id = advertisement_id;
	}

}
