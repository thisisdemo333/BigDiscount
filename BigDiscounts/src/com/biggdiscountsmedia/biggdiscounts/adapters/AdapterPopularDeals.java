package com.biggdiscountsmedia.biggdiscounts.adapters;

import java.util.ArrayList;

import com.biggdiscountsmedia.biggdiscounts.dto.Regular;
import com.biggdiscountsmedia.biggdiscounts.viewholder.ViewHolderGridViewPopularDeals;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AdapterPopularDeals extends BaseAdapter {
	private ArrayList<Regular> arrayListRegularDeals;
	private ViewHolderGridViewPopularDeals viewHolderGridViewPopularDeals;
	private Activity mActivity;

	public AdapterPopularDeals(Activity mActivity) {
		// TODO Auto-generated constructor stub
		this.mActivity = mActivity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (arrayListRegularDeals != null && arrayListRegularDeals.size() > 0) {
			return arrayListRegularDeals.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (arrayListRegularDeals != null && arrayListRegularDeals.size() > 0) {
			return arrayListRegularDeals.get(position);
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
			viewHolderGridViewPopularDeals = new ViewHolderGridViewPopularDeals(
					mActivity);
			convertView = viewHolderGridViewPopularDeals.getConvertView();
			convertView.setTag(viewHolderGridViewPopularDeals);
		} else {
			viewHolderGridViewPopularDeals = (ViewHolderGridViewPopularDeals) convertView
					.getTag();
		}
		viewHolderGridViewPopularDeals.setData(arrayListRegularDeals
				.get(position));
		viewHolderGridViewPopularDeals.applyData();
		return convertView;
	}

	/**
	 * @return the arrayListRegularDeals
	 */
	public ArrayList<Regular> getArrayListRegularDeals() {
		return arrayListRegularDeals;
	}

	/**
	 * @param arrayListRegularDeals
	 *            the arrayListRegularDeals to set
	 */
	public void setArrayListRegularDeals(
			ArrayList<Regular> arrayListRegularDeals) {
		this.arrayListRegularDeals = arrayListRegularDeals;
	}
}
