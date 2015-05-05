package com.biggdiscountsmedia.biggdiscounts.viewholder;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.biggdiscountsmedia.biggdiscounts.BaseViewHolder;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.dto.Cities;

public class ViewHolderCitiesList extends BaseViewHolder {

	private Cities cities;
	private TextView textViewCityName;

	public ViewHolderCitiesList(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setData(Object data) {
		// TODO Auto-generated method stub
		cities = (Cities) data;

	}

	@Override
	public void applyData() {
		// TODO Auto-generated method stub
		String cityName = cities.getCity_name();
		if (!TextUtils.isEmpty(cityName)) {
			textViewCityName.setText(cityName);
		}

	}

	@Override
	public View getConvertView() {
		// TODO Auto-generated method stub
		View view = mInflater.inflate(R.layout.view_right_drawe_list, null);
		textViewCityName = (TextView) view.findViewById(R.id.tv_city_name);
		return view;
	}

}
