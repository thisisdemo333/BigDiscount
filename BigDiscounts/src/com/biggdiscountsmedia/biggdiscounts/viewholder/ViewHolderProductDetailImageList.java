package com.biggdiscountsmedia.biggdiscounts.viewholder;

import java.util.HashMap;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.biggdiscountsmedia.biggdiscounts.BaseViewHolder;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.constants.URLConstants;

public class ViewHolderProductDetailImageList extends BaseViewHolder {
	private NetworkImageView networkImageViewProductImage;
	private HashMap<String, String> data;
	private ImageLoader imageLoader;
	private Activity mActivity;

	public ViewHolderProductDetailImageList(Activity mActivity) {
		super(mActivity);
		this.mActivity = mActivity;
		imageLoader = BiggDiscountsApplication.getInstance().getImageLoader();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(Object data) {
		// TODO Auto-generated method stub
		this.data = (HashMap<String, String>) data;

	}

	@Override
	public void applyData() {
		// TODO Auto-generated method stub
		String imageUrl = data.get("image");
		imageUrl = URLConstants.IMAGE_BASE_URL + imageUrl;

		if (!TextUtils.isEmpty(imageUrl)) {
			networkImageViewProductImage.setImageUrl(imageUrl, imageLoader);
		}
	}

	@Override
	public View getConvertView() {
		View view = mInflater.inflate(R.layout.detailview_image_item, null);
		networkImageViewProductImage = (NetworkImageView) view
				.findViewById(R.id.iv_image);
		return view;
	}
}
