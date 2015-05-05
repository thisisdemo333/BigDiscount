package com.biggdiscountsmedia.biggdiscounts.dto;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CitiesResponse {

	@SerializedName("cities")
	private List<Cities> citiesList = new ArrayList<Cities>();

	public List<Cities> getCitiesList() {
		return citiesList;
	}

	public void setCitiesList(List<Cities> citiesList) {
		this.citiesList = citiesList;
	}
	
	
}
