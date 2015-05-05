package com.biggdiscountsmedia.biggdiscounts.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.constants.URLConstants;
import com.biggdiscountsmedia.biggdiscounts.viewholder.ViewHolderProductDetailImageList;

/**
 * Created by de6 on 17/4/15.
 */
public class AdapterProductDetailImageList extends BaseAdapter {

	// Declare Variables
	ViewHolderProductDetailImageList viewHolderProductDetailImageList;
	Activity mActivity;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;

	HashMap<String, String> resultp = new HashMap<String, String>();
	ImageLoader imageLoader;

	public AdapterProductDetailImageList(Activity mActivity,
			ArrayList<HashMap<String, String>> arraylist) {
		this.mActivity = mActivity;
		data = arraylist;
		imageLoader = BiggDiscountsApplication.getInstance().getImageLoader();
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables

		if(convertView==null){
			viewHolderProductDetailImageList = new ViewHolderProductDetailImageList(mActivity);
			convertView = viewHolderProductDetailImageList.getConvertView();
			convertView.setTag(viewHolderProductDetailImageList);
		}else{
			viewHolderProductDetailImageList = (ViewHolderProductDetailImageList)convertView.getTag();
		}
		
		viewHolderProductDetailImageList.setData(data.get(position));
		viewHolderProductDetailImageList.applyData();
		


		return convertView;
	}
}
