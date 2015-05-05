package com.biggdiscountsmedia.biggdiscounts.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SliderImagesResponse {

	@SerializedName("images")
	private List<Images> imagesList = new ArrayList<Images>();

	public List<Images> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<Images> imagesList) {
		this.imagesList = imagesList;
	}

}
