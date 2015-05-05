package com.biggdiscountsmedia.biggdiscounts.datacache;

import java.util.ArrayList;

import com.biggdiscountsmedia.biggdiscounts.dto.Cities;

public class DataCache {

	private static DataCache instance;
	private ArrayList<Cities> arrayListCities;

	private DataCache() {
		
	}

	public static synchronized DataCache getInstance() {
		if (instance == null) {
			instance = new DataCache();
		}
		return instance;
	}

	/**
	 * @return the arrayListCities
	 */
	public ArrayList<Cities> getArrayListCities() {
		return arrayListCities;
	}

	/**
	 * @param arrayListCities
	 *            the arrayListCities to set
	 */
	public void setArrayListCities(ArrayList<Cities> arrayListCities) {
		this.arrayListCities = arrayListCities;
	}

}
