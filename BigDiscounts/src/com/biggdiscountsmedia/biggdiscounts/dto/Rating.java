package com.biggdiscountsmedia.biggdiscounts.dto;

import com.google.gson.annotations.SerializedName;

public class Rating {
	@SerializedName("id")
	private int id;
	@SerializedName("advertisement_id")
	private int advertisement_id;
	@SerializedName("created_at")
	private String created_at;
	@SerializedName("updated_at")
	private String updated_at;
	@SerializedName("total_count")
	private int total_count;
	@SerializedName("rating_value")
	private String rating_value;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}

	/**
	 * @param created_at
	 *            the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	/**
	 * @return the updated_at
	 */
	public String getUpdated_at() {
		return updated_at;
	}

	/**
	 * @param updated_at
	 *            the updated_at to set
	 */
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * @return the total_count
	 */
	public int getTotal_count() {
		return total_count;
	}

	/**
	 * @param total_count
	 *            the total_count to set
	 */
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	/**
	 * @return the rating_value
	 */
	public String getRating_value() {
		return rating_value;
	}

	/**
	 * @param rating_value
	 *            the rating_value to set
	 */
	public void setRating_value(String rating_value) {
		this.rating_value = rating_value;
	}

}
