package com.biggdiscountsmedia.biggdiscounts.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import com.biggdiscountsmedia.biggdiscounts.R;

/**
 * Created by de6 on 17/4/15.
 */
public class AdapterAddress extends PagerAdapter {

	Context mContext;
	LayoutInflater mLayoutInflater;

	ArrayList<HashMap<String, String>> data;

	HashMap<String, String> resultp = new HashMap<String, String>();

	public AdapterAddress(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		mContext = context;
		data = arraylist;

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((LinearLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = mLayoutInflater.inflate(
				R.layout.detailview_address_item, container, false);
		resultp = data.get(position);
		TextView tvAddress = (TextView) itemView
				.findViewById(R.id.tv_Detail_Address);

		tvAddress.setText(resultp.get("street_address") + ", "
				+ resultp.get("city_name"));
		container.addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((RelativeLayout) object);
	}
}