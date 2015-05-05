package com.biggdiscountsmedia.biggdiscounts.adapters;

import java.util.ArrayList;

import com.biggdiscountsmedia.biggdiscounts.dto.Cities;
import com.biggdiscountsmedia.biggdiscounts.viewholder.ViewHolderCategoryList;
import com.biggdiscountsmedia.biggdiscounts.viewholder.ViewHolderCitiesList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AdapterCities extends BaseAdapter {
	private Activity mActivity;
	private ArrayList<Cities> arrayListCities;
	private ViewHolderCitiesList viewHolderCitiesList;

	public AdapterCities(Activity mActivity) {
		// TODO Auto-generated constructor stub
		this.mActivity = mActivity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (arrayListCities != null) {
			return arrayListCities.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (arrayListCities != null) {
			return arrayListCities.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			viewHolderCitiesList = new ViewHolderCitiesList(mActivity);
			convertView = viewHolderCitiesList.getConvertView();
			convertView.setTag(viewHolderCitiesList);
		} else {
			viewHolderCitiesList = (ViewHolderCitiesList) convertView.getTag();
		}

		viewHolderCitiesList.setData(arrayListCities.get(position));
		viewHolderCitiesList.applyData();
		return convertView;
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
