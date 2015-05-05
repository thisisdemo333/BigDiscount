package com.biggdiscountsmedia.biggdiscounts.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.constants.URLConstants;

/**
 * Created by de6 on 13/4/15.
 */
public class AdapterAdverties extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;

	HashMap<String, String> resultp = new HashMap<String, String>();
	ImageLoader imageLoader;

	public AdapterAdverties(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
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
		TextView productName, sellingPrize, mrp, offer;
		NetworkImageView productImage;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.primium_list_item, parent,
				false);
		// Get the position
		resultp = data.get(position);
		ProgressBar bar = (ProgressBar) itemView.findViewById(R.id.progressBar);

		productName = (TextView) itemView
				.findViewById(R.id.tv_premium_ProductName);
		sellingPrize = (TextView) itemView
				.findViewById(R.id.tv_premium_SellingPrize);
		mrp = (TextView) itemView.findViewById(R.id.tv_premium_Mrp);
		offer = (TextView) itemView.findViewById(R.id.tv_premium_off);

		productName.setText(resultp.get("product_name"));
		sellingPrize.setText("Rs " + resultp.get("selling_price"));
		mrp.setText("Rs " + resultp.get("mrp"));
		mrp.setPaintFlags(mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		offer.setText(resultp.get("offer") + " Off");

		productImage = (NetworkImageView) itemView
				.findViewById(R.id.iv_premium_list_image);
		productImage
				.setImageUrl(
						URLConstants.IMAGE_BASE_URL + resultp.get("image"),
						imageLoader);

		bar.setVisibility(View.GONE);

		return itemView;
	}
}