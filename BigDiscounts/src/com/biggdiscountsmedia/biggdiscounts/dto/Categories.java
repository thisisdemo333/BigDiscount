package com.biggdiscountsmedia.biggdiscounts.dto;

import com.google.gson.annotations.SerializedName;

public class Categories {

	@SerializedName("id")
	private int id;
	
	@SerializedName("category_name")
	private String categoryName;
	
	@SerializedName("icon_url")
	private String iconUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

}
