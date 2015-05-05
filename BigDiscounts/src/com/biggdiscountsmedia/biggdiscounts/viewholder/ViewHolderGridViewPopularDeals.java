package com.biggdiscountsmedia.biggdiscounts.viewholder;

import android.app.Activity;
import android.provider.SyncStateContract.Constants;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.biggdiscountsmedia.biggdiscounts.BaseViewHolder;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.R.string;
import com.biggdiscountsmedia.biggdiscounts.constants.URLConstants;
import com.biggdiscountsmedia.biggdiscounts.dto.Regular;

public class ViewHolderGridViewPopularDeals extends BaseViewHolder {
	private Regular regular;
	private NetworkImageView networkImageViewProduct;
	private ImageLoader imageLoader;
	private ProgressBar progressBar;

	public ViewHolderGridViewPopularDeals(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
		imageLoader = BiggDiscountsApplication.getInstance().getImageLoader();
	}

	@Override
	public void setData(Object data) {
		// TODO Auto-generated method stub
		regular = (Regular) data;

	}

	@Override
	public void applyData() {
		// TODO Auto-generated method stub
		String imageUrl = regular.getAdvertisement_image().getImage();
		if (!TextUtils.isEmpty(imageUrl)) {
			imageUrl = URLConstants.IMAGE_BASE_URL + imageUrl;
			networkImageViewProduct.setImageUrl(imageUrl, imageLoader);
		}

	}

	@Override
	public View getConvertView() {
		// TODO Auto-generated method stub
		View view = mInflater
				.inflate(R.layout.view_gridview_popular_deal, null);
		networkImageViewProduct = (NetworkImageView) view
				.findViewById(R.id.imv_product);
		progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
		return view;
	}
}
