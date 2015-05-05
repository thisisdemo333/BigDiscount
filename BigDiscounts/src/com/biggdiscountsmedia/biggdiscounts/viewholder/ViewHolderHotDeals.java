package com.biggdiscountsmedia.biggdiscounts.viewholder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.biggdiscountsmedia.biggdiscounts.BaseViewHolder;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.constants.URLConstants;
import com.biggdiscountsmedia.biggdiscounts.dto.Premium;
import com.biggdiscountsmedia.biggdiscounts.utils.Utils;

public class ViewHolderHotDeals extends BaseViewHolder {
	private NetworkImageView networkImageViewHotDeals;
	private ProgressBar progressBar;
	private Premium premium;
	private ImageLoader imageLoader;
	private Activity mActivity;

	public ViewHolderHotDeals(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
		this.mActivity = mActivity;
		imageLoader = BiggDiscountsApplication.getInstance().getImageLoader();
	}

	@Override
	public void setData(Object data) {
		// TODO Auto-generated method stub
		premium = (Premium) data;

	}

	@Override
	public void applyData() {
		// TODO Auto-generated method stub
		String imageUrl = premium.getAdvertisement_image().getImage();
		imageUrl = URLConstants.IMAGE_BASE_URL + imageUrl;
		if (!TextUtils.isEmpty(imageUrl)) {
			networkImageViewHotDeals.setImageUrl(imageUrl, imageLoader);
		}

	}

	@Override
	public View getConvertView() {
		// TODO Auto-generated method stub
		View view = mInflater.inflate(R.layout.view_horizontal_lv_hotdeals,
				null);
		networkImageViewHotDeals = (NetworkImageView) view
				.findViewById(R.id.imv_hotdeals);

		progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
		return view;
	}
}
