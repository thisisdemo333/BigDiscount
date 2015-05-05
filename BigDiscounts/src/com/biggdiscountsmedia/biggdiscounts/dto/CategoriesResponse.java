package com.biggdiscountsmedia.biggdiscounts.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CategoriesResponse {

	@SerializedName("categories")
	private List<Categories> categories = new ArrayList<Categories>();

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

}
