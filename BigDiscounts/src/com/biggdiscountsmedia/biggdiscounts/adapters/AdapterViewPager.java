package com.biggdiscountsmedia.biggdiscounts.adapters;

import java.util.ArrayList;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.activities.AdvertiesDetailActivity;
import com.biggdiscountsmedia.biggdiscounts.constants.URLConstants;
import com.biggdiscountsmedia.biggdiscounts.dialoguefragment.DialogueRegister;
import com.biggdiscountsmedia.biggdiscounts.dto.Images;
import com.biggdiscountsmedia.biggdiscounts.dto.Regular;
import com.biggdiscountsmedia.biggdiscounts.prefernces.PrefHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class AdapterViewPager extends PagerAdapter {
	private Activity mActivity;
	private ArrayList<Images> arrayListSliderImages;
	private LayoutInflater mLayoutInflater;
	private NetworkImageView networkImageViewSlider;
	private ProgressBar progressBar;
	private BiggDiscountsApplication mApp;
	private ImageLoader imageLoader;
	private PrefHelper prefHelper;
	private DialogueRegister dialogueRegister;

	public AdapterViewPager(Activity mActivity,
			ArrayList<Images> arrayListSliderImages) {
		// TODO Auto-generated constructor stub
		this.mActivity = mActivity;
		this.arrayListSliderImages = arrayListSliderImages;
		this.imageLoader = mApp.getInstance().getImageLoader();
		this.prefHelper = new PrefHelper(mActivity);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (arrayListSliderImages != null && arrayListSliderImages.size() > 0) {
			return arrayListSliderImages.size();
		}
		return 0;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == (RelativeLayout) object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		// TODO Auto-generated method stub
		mLayoutInflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = mLayoutInflater.inflate(R.layout.view_pager_layout,
				container, false);
		initViews(view);

		networkImageViewSlider.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// opening advertise activity
				if (prefHelper.isAlreadyRegistered()) {
					int adId = arrayListSliderImages.get(position)
							.getAdvertisement_id();

					String id = String.valueOf(adId);

					Intent intent = new Intent(mActivity,
							AdvertiesDetailActivity.class);
					intent.putExtra(
							mActivity.getResources().getString(
									R.string.key_ad_id), id);
					mActivity.startActivity(intent);
				} else {
					dialogueRegister = new DialogueRegister();
					dialogueRegister.show(mActivity.getFragmentManager(),
							"Dailog Register");
				}
			}
		});

		String imageUrl = arrayListSliderImages.get(position).getImage();
		if (!TextUtils.isEmpty(imageUrl)) {
			imageUrl = URLConstants.BASE_URL + imageUrl;
			networkImageViewSlider.setImageUrl(imageUrl, imageLoader);
		}

		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Remove viewpager_item.xml from ViewPager
		container.removeView((RelativeLayout) object);

	}

	public void initViews(View view) {
		networkImageViewSlider = (NetworkImageView) view
				.findViewById(R.id.imv_slider);
		progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

	}
}
