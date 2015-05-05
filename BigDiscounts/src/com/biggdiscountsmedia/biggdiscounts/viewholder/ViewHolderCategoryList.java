package com.biggdiscountsmedia.biggdiscounts.viewholder;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.biggdiscountsmedia.biggdiscounts.BaseViewHolder;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.constants.URLConstants;
import com.biggdiscountsmedia.biggdiscounts.dto.Categories;

public class ViewHolderCategoryList extends BaseViewHolder {
	private Categories categories;
	private TextView textViewCategoryName;
	private NetworkImageView networkImageViewCategory;
	private ImageLoader imageLoader;

	public ViewHolderCategoryList(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
		imageLoader = BiggDiscountsApplication.getInstance().getImageLoader();
	}

	@Override
	public void setData(Object data) {
		// TODO Auto-generated method stub
		categories = (Categories) data;

	}

	@Override
	public void applyData() {
		// TODO Auto-generated method stub
		String categoryName = categories.getCategoryName();
		String imageUrl = URLConstants.IMAGE_BASE_URL + categories.getIconUrl();
		if (!TextUtils.isEmpty(categoryName)) {
			textViewCategoryName.setText(categoryName);
		}
		if (!TextUtils.isEmpty(imageUrl)) {
			networkImageViewCategory.setImageUrl(imageUrl, imageLoader);
		}

	}

	@Override
	public View getConvertView() {
		// TODO Auto-generated method stub
		View view = mInflater.inflate(R.layout.view_left_drawer_list, null);
		textViewCategoryName = (TextView) view
				.findViewById(R.id.tv_category_name);
		networkImageViewCategory = (NetworkImageView) view
				.findViewById(R.id.imv_category);
		return view;
	}

}
