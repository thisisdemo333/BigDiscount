package com.biggdiscountsmedia.biggdiscounts.adapters;

import java.util.ArrayList;

import com.biggdiscountsmedia.biggdiscounts.dto.Premium;
import com.biggdiscountsmedia.biggdiscounts.viewholder.ViewHolderHotDeals;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AdapterHotdealsHorizontalListview extends BaseAdapter {
	private ViewHolderHotDeals viewHolderHotDeals;
	private Activity mActivity;
	private ArrayList<Premium> arrayListPremiums;

	public AdapterHotdealsHorizontalListview(Activity mActivity) {
		// TODO Auto-generated constructor stub
		this.mActivity = mActivity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (arrayListPremiums != null && arrayListPremiums.size() > 0) {
			return arrayListPremiums.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (arrayListPremiums != null && arrayListPremiums.size() > 0) {
			return arrayListPremiums.get(position);
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
			viewHolderHotDeals = new ViewHolderHotDeals(mActivity);
			convertView = viewHolderHotDeals.getConvertView();
			convertView.setTag(viewHolderHotDeals);
		} else {
			viewHolderHotDeals = (ViewHolderHotDeals) convertView.getTag();
		}
		viewHolderHotDeals.setData(arrayListPremiums.get(position));
		viewHolderHotDeals.applyData();
		return convertView;
	}

	/**
	 * @return the arrayListPremiums
	 */
	public ArrayList<Premium> getArrayListPremiums() {
		return arrayListPremiums;
	}

	/**
	 * @param arrayListPremiums
	 *            the arrayListPremiums to set
	 */
	public void setArrayListPremiums(ArrayList<Premium> arrayListPremiums) {
		this.arrayListPremiums = arrayListPremiums;
	}
}
