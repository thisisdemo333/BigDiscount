package com.biggdiscountsmedia.biggdiscounts.viewholder;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.biggdiscountsmedia.biggdiscounts.BaseViewHolder;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.dto.Regular;

public class ViewHolderSearchList extends BaseViewHolder {

	private NetworkImageView ivProductImage;

	private TextView tvProdutName;
	private TextView tvSellingPrise;
	private TextView tvMrp;
	private TextView tvOffer;

	private Regular regular;

	private ImageLoader imageLoader;
	
	public ViewHolderSearchList(Activity mActivity) {
		super(mActivity);
		imageLoader = BiggDiscountsApplication.getInstance().getImageLoader();
	}

	@Override
	public void setData(Object data) {
		regular = (Regular) data;

		String productName = String.valueOf(regular.getProduct_name());
		String sellingPrise = String.valueOf(regular.getSelling_price());
		String mrp = String.valueOf(regular.getMrp());
		String offer = String.valueOf(regular.getOffer());

		String productImageUrl = String.valueOf(regular
				.getAdvertisement_image().getImage());

		tvMrp.setText(mrp);
		tvOffer.setText(offer);
		tvProdutName.setText(productName);
		tvSellingPrise.setText(sellingPrise);

		if (!TextUtils.isEmpty(productImageUrl)) {
			ivProductImage.setImageUrl(productImageUrl, imageLoader);

		}

	}

	@Override
	public void applyData() {

	}

	@Override
	public View getConvertView() {
		View view = mInflater.inflate(R.layout.view_lv_search_list, null);

		ivProductImage = (NetworkImageView) view
				.findViewById(R.id.iv_product_image);

		tvProdutName = (TextView) view.findViewById(R.id.tv_product_name);
		tvSellingPrise = (TextView) view
				.findViewById(R.id.tv_product_selling_prise);
		tvMrp = (TextView) view.findViewById(R.id.tv_product_mrp);
		tvOffer = (TextView) view.findViewById(R.id.tv_product_offer);

		return view;
	}
}
