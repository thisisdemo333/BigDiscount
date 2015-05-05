package com.biggdiscountsmedia.biggdiscounts.dto;

public class RatingResponse {
	
	private boolean success;
	private String message;
	private String total_rating;
	private String average_rating;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTotal_rating() {
		return total_rating;
	}
	public void setTotal_rating(String total_rating) {
		this.total_rating = total_rating;
	}
	public String getAverage_rating() {
		return average_rating;
	}
	public void setAverage_rating(String average_rating) {
		this.average_rating = average_rating;
	}
	@Override
	public String toString() {
		return "RatingResponse [success=" + success + ", message=" + message
				+ ", total_rating=" + total_rating + ", average_rating="
				+ average_rating + "]";
	}
	
	
}
