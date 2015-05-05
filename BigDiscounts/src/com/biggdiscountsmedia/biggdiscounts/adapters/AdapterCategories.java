package com.biggdiscountsmedia.biggdiscounts.adapters;

import java.util.ArrayList;

import com.biggdiscountsmedia.biggdiscounts.dto.Categories;
import com.biggdiscountsmedia.biggdiscounts.viewholder.ViewHolderCategoryList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AdapterCategories extends BaseAdapter {

	private Activity mActivity;
	private ArrayList<Categories> arrayListCategories;
	private ViewHolderCategoryList viewHolderCategoryList;

	public AdapterCategories(Activity mActivity) {
		this.mActivity = mActivity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayListCategories.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (arrayListCategories != null) {
			return arrayListCategories.get(position);
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
			viewHolderCategoryList = new ViewHolderCategoryList(mActivity);
			convertView = viewHolderCategoryList.getConvertView();
			convertView.setTag(viewHolderCategoryList);
		} else {
			viewHolderCategoryList = (ViewHolderCategoryList) convertView
					.getTag();
		}

		viewHolderCategoryList.setData(arrayListCategories.get(position));
		viewHolderCategoryList.applyData();
		return convertView;
	}

	public ArrayList<Categories> getArrayListCategories() {
		return arrayListCategories;
	}

	public void setArrayListCategories(ArrayList<Categories> arrayListCategories) {

		this.arrayListCategories = arrayListCategories;
	}

}
