package com.biggdiscountsmedia.biggdiscounts.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ProductsResponse {

	@SerializedName("premium")
	private List<Premium> listPremium = new ArrayList<Premium>();

	@SerializedName("regular")
	private List<Regular> listRegular = new ArrayList<Regular>();

	/**
	 * @return the listPremium
	 */
	public List<Premium> getListPremium() {
		return listPremium;
	}

	/**
	 * @param listPremium
	 *            the listPremium to set
	 */
	public void setListPremium(List<Premium> listPremium) {
		this.listPremium = listPremium;
	}

	/**
	 * @return the listRegular
	 */
	public List<Regular> getListRegular() {
		return listRegular;
	}

	/**
	 * @param listRegular
	 *            the listRegular to set
	 */
	public void setListRegular(List<Regular> listRegular) {
		this.listRegular = listRegular;
	}

}
