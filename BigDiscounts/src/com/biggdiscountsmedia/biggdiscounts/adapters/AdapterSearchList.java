package com.biggdiscountsmedia.biggdiscounts.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.biggdiscountsmedia.biggdiscounts.dto.Regular;
import com.biggdiscountsmedia.biggdiscounts.viewholder.ViewHolderCategoryList;
import com.biggdiscountsmedia.biggdiscounts.viewholder.ViewHolderSearchList;

public class AdapterSearchList extends BaseAdapter {

	private ArrayList<Regular> arrayListSearchList;

	private Activity mActivity;

	private ViewHolderSearchList viewHolderSearchList;

	public AdapterSearchList(Activity mActivity) {
		this.mActivity = mActivity;
	}

	@Override
	public int getCount() {
		return arrayListSearchList.size();
	}

	@Override
	public Object getItem(int position) {
		if (arrayListSearchList != null) {
			return arrayListSearchList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			viewHolderSearchList = new ViewHolderSearchList(mActivity);
			convertView = viewHolderSearchList.getConvertView();
			convertView.setTag(viewHolderSearchList);
		} else {
			viewHolderSearchList = (ViewHolderSearchList) convertView
					.getTag();
		}

		viewHolderSearchList.setData(arrayListSearchList.get(position));
		viewHolderSearchList.applyData();
		return convertView;
	}

	public ArrayList<Regular> getArrayListSearchList() {
		return arrayListSearchList;
	}

	public void setArrayListSearchList(ArrayList<Regular> arrayListSearchList) {
		this.arrayListSearchList = arrayListSearchList;
	}

}
