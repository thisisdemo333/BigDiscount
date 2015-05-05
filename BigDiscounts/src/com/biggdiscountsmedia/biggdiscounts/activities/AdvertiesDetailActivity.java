package com.biggdiscountsmedia.biggdiscounts.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.adapters.AdapterAddress;
import com.biggdiscountsmedia.biggdiscounts.adapters.AdapterProductDetailImageList;
import com.biggdiscountsmedia.biggdiscounts.constants.URLConstants;
import com.biggdiscountsmedia.biggdiscounts.dto.AdvertisementImage;
import com.biggdiscountsmedia.biggdiscounts.dto.Advertiser;
import com.biggdiscountsmedia.biggdiscounts.dto.ProductDetail;
import com.biggdiscountsmedia.biggdiscounts.dto.Rating;
import com.biggdiscountsmedia.biggdiscounts.dto.RatingRequest;
import com.biggdiscountsmedia.biggdiscounts.dto.RatingResponse;
import com.biggdiscountsmedia.biggdiscounts.utils.JSONParser;
import com.biggdiscountsmedia.biggdiscounts.utils.Utils;
import com.biggdiscountsmedia.biggdiscounts.views.HorizontalListView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AdvertiesDetailActivity extends Activity implements
		OnClickListener, OnPageChangeListener, OnItemClickListener {

	private ArrayList<HashMap<String, String>> arrylist_image = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> arrylist_address = new ArrayList<HashMap<String, String>>();
	private ImageLoader imageLoader;

	private AdapterProductDetailImageList imageListAdapter;
	private AdapterAddress addressAdapter;

	private ProgressDialog pDialog;
	private HorizontalListView hvImagesList;
	private TextView tvProductSellingPrize;
	private TextView tvProductMRP;
	private TextView tvProductOffer;
	private TextView tvTotalViews;
	private TextView tvRatingValue;
	private TextView tvDiscription;
	private TextView tvAdvertiserName;
	private TextView tvAdvertiserPhone;
	private TextView tvAdvertiserEmail;
	private TextView tvNetworkStatus;
	private ViewPager pagerAddress;
	private NetworkImageView ivProductImage;
	private ImageView ivphone;
	private ImageView ivSMS;
	private ProgressBar progressBar;
	private LinearLayout linearLayoutAdvertise;
	private Button ivRateIt;
	private RatingBar ratingBar;
	private String productId;
	private String advertiser_phone;
	private GoogleMap googleMap;
	private Activity mActivity;
	private BiggDiscountsApplication mApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adverties_detail);
		setUpMapIfNeeded();
		init();

	}

	public void init() {
		initViews();
		initMembers();
		initData();
		initListeners();
	}

	public void initViews() {
		ivProductImage = (NetworkImageView) findViewById(R.id.iv_Detail_ProductImage);
		tvProductSellingPrize = (TextView) findViewById(R.id.tv_Detail_SellingPrize);
		tvProductMRP = (TextView) findViewById(R.id.tv_Detail_Mrp);
		tvProductOffer = (TextView) findViewById(R.id.tv_Detail_offer);
		tvTotalViews = (TextView) findViewById(R.id.tv_Detail_views);
		tvRatingValue = (TextView) findViewById(R.id.tv_Detail_RatingValue);
		tvDiscription = (TextView) findViewById(R.id.tv_Detail_ProductDiscription);
		tvNetworkStatus = (TextView) findViewById(R.id.tv_network_status);
		ivRateIt = (Button) findViewById(R.id.btn_detail_RateIt);
		ratingBar = (RatingBar) findViewById(R.id.rb_Detail_RatingBar);
		hvImagesList = (HorizontalListView) findViewById(R.id.hlv_Detail_ProductImageList);
		pagerAddress = (ViewPager) findViewById(R.id.pager_Address);
		ivphone = (ImageView) findViewById(R.id.iv_Detail_Call);
		ivSMS = (ImageView) findViewById(R.id.iv_Detail_Sms);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		linearLayoutAdvertise = (LinearLayout) findViewById(R.id.ll_advertise);
	}

	public void initListeners() {
		ivRateIt.setOnClickListener(this);
		ivphone.setOnClickListener(this);
		ivSMS.setOnClickListener(this);
		pagerAddress.setOnPageChangeListener(this);
		hvImagesList.setOnItemClickListener(this);
	}

	public void initMembers() {

		mActivity = this;
		mApp = (BiggDiscountsApplication) getApplication();

		imageListAdapter = new AdapterProductDetailImageList(mActivity,
				arrylist_image);
		hvImagesList.setAdapter(imageListAdapter);

		addressAdapter = new AdapterAddress(AdvertiesDetailActivity.this,
				arrylist_address);
		pagerAddress.setAdapter(addressAdapter);

		imageLoader = BiggDiscountsApplication.getInstance().getImageLoader();

		Intent intent = getIntent();
		productId = intent.getStringExtra("id");

		/*
		 * rating bar initialization
		 */

		LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
		stars.getDrawable(2).setColorFilter(Color.YELLOW,
				PorterDuff.Mode.SRC_ATOP);

	}

	public void initData() {
		progressBar.setVisibility(View.VISIBLE);
		tvNetworkStatus.setVisibility(View.VISIBLE);
		linearLayoutAdvertise.setVisibility(View.GONE);
		loadAddDetail();
	}

	private void loadAddDetail() {
		if (Utils.hasDataConnection(mActivity)) {
			new DetailView().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			progressBar.setVisibility(View.GONE);
			tvNetworkStatus.setText(getResources().getString(
					R.string.no_internert));
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		ivProductImage.setImageUrl(URLConstants.IMAGE_BASE_URL
				+ arrylist_image.get(position).get("image"), imageLoader);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {

		String addr = arrylist_address.get(position).get("street_address")
				+ "," + arrylist_address.get(position).get("city_name");

		plotAddressOnMap(addr);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	private void setUpMapIfNeeded() {
		try {
			if (googleMap == null) {
				googleMap = ((MapFragment) getFragmentManager()
						.findFragmentById(R.id.Map)).getMap();
				if (googleMap != null) {
					setUpMap();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpMap() {
		// googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0))
		// .title("Address"));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void RateToProduct() {
		final Dialog dialog = new Dialog(AdvertiesDetailActivity.this);
		// Include dialog.xml file
		dialog.setContentView(R.layout.ratingbar);
		// Set dialog title
		dialog.setTitle("Rate To Product");

		dialog.show();

		Button submit = (Button) dialog.findViewById(R.id.btn_Ratingbar_Submit);
		final RatingBar bar = (RatingBar) dialog.findViewById(R.id.ratingBar);
		LayerDrawable stars = (LayerDrawable) bar.getProgressDrawable();
		stars.getDrawable(2).setColorFilter(Color.YELLOW,
				PorterDuff.Mode.SRC_ATOP);
		bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				TextView tvRatingValue = (TextView) dialog
						.findViewById(R.id.tv_Ratingbar_Value);
				tvRatingValue.setText("" + bar.getRating());
			}
		});

		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				new RatingJson("" + bar.getRating())
						.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				dialog.dismiss();

			}
		});
		Button cancel = (Button) dialog.findViewById(R.id.btn_Ratingbar_Cancel);

		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				dialog.dismiss();
			}
		});

	}

	public void plotAddressOnMap(String address) {
		double latitude = 0, longitude = 0;
		Geocoder gc = new Geocoder(AdvertiesDetailActivity.this,
				Locale.getDefault());
		List<Address> addresses;
		try {
			addresses = gc.getFromLocationName(address, 5);
			if (addresses.size() > 0) {
				latitude = addresses.get(0).getLatitude();
				longitude = addresses.get(0).getLongitude();
				LatLng pos = new LatLng(latitude, longitude);
				if (googleMap != null) {
					googleMap.clear();
					googleMap.addMarker(new MarkerOptions().position(
							new LatLng(latitude, longitude)).title(address));
					googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
							pos, 15));
				}
			} else {
				Utils.ShowToast(mActivity,
						getResources().getString(R.string.address_plot_fail));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMapLocation(String addr) {
		String addressStr = addr;
		Geocoder geoCoder = new Geocoder(AdvertiesDetailActivity.this);
		double latitude = 0, longtitude = 0;
		try {
			List<Address> addresses = geoCoder.getFromLocationName(addressStr,
					1);
			if (addresses.size() > 0) {
				latitude = addresses.get(0).getLatitude();
				longtitude = addresses.get(0).getLongitude();
			}

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		LatLng pos = new LatLng(latitude, longtitude);
		if (googleMap != null) {
			googleMap.addMarker(new MarkerOptions().position(
					new LatLng(latitude, longtitude)).title("Address"));
			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		}
	}

	public class DetailView extends AsyncTask<String, String, String> {
		ArrayList<AdvertisementImage> imageArray = new ArrayList<AdvertisementImage>();
		ArrayList<com.biggdiscountsmedia.biggdiscounts.dto.Address> addressArray = new ArrayList<com.biggdiscountsmedia.biggdiscounts.dto.Address>();
		boolean isavailable = false;
		String productName;
		String productOffer;
		String discription;
		String rattingValue;
		String advertiser_email;
		String advertiser_name;
		int id;
		int sellingPrize;
		int productMRP;
		int totleViews;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... args) {

			try {

				ProductDetail productDetail = mApp.getWebService()
						.getProductDetail(productId);

				productName = productDetail.getProductName();
				sellingPrize = productDetail.getSellingPrice();
				productMRP = productDetail.getMrp();
				productOffer = productDetail.getOffer();
				totleViews = productDetail.getTotalViews();
				discription = productDetail.getDescription();

				Rating rating = productDetail.getRating();
				rattingValue = rating.getRating_value();

				imageArray = (ArrayList<AdvertisementImage>) productDetail
						.getAdvertisementImages();
				if (imageArray != null) {
					for (int i = 0; i < imageArray.size(); i++) {
						HashMap<String, String> mapimage = new HashMap<String, String>();
						AdvertisementImage adImage = imageArray.get(i);

						mapimage.put("advertisement_id",
								String.valueOf(adImage.getAdvertisement_id()));
						mapimage.put("image", adImage.getImage());

						arrylist_image.add(mapimage);
					}
				}

				addressArray = (ArrayList<com.biggdiscountsmedia.biggdiscounts.dto.Address>) productDetail
						.getAddresses();
				if (addressArray != null) {
					for (int i = 0; i < addressArray.size(); i++) {
						HashMap<String, String> mapaddress = new HashMap<String, String>();

						com.biggdiscountsmedia.biggdiscounts.dto.Address address = addressArray
								.get(i);

						mapaddress.put("street_address",
								address.getStreet_address());
						mapaddress.put("city_name", address.getCity_name());

						arrylist_address.add(mapaddress);
					}
				}

				Advertiser adviser = productDetail.getAdvertiser();
				advertiser_phone = adviser.getMobile_number();
				advertiser_email = adviser.getEmail();
				advertiser_name = adviser.getFull_name();

				isavailable = true;

			} catch (Exception e) {
				e.printStackTrace();
				isavailable = false;
			}
			return null;
		}

		protected void onPostExecute(String file_url) {
			progressBar.setVisibility(View.GONE);
			if (isavailable) {
				// ---initailizing visiblity---
				tvNetworkStatus.setVisibility(View.GONE);
				linearLayoutAdvertise.setVisibility(View.VISIBLE);
				// ---loading animation-----
				Animation animation = AnimationUtils.loadAnimation(mActivity,
						R.anim.fadeout_anim);
				linearLayoutAdvertise.setAnimation(animation);

				imageListAdapter.notifyDataSetChanged();
				addressAdapter.notifyDataSetChanged();

				String addr = arrylist_address.get(0).get("street_address")
						+ "," + arrylist_address.get(0).get("city_name");

				plotAddressOnMap(addr);

				ivProductImage.setImageUrl(URLConstants.IMAGE_BASE_URL
						+ arrylist_image.get(0).get("image"), imageLoader);

				tvProductSellingPrize.setText("Rs " + sellingPrize + "/-");
				tvProductMRP.setText("Rs " + productMRP + "/-");
				tvProductMRP.setPaintFlags(tvProductMRP.getPaintFlags()
						| Paint.STRIKE_THRU_TEXT_FLAG);
				tvProductOffer.setText(productOffer.trim() + " off");
				tvTotalViews.setText(totleViews + " Views");
				if (rattingValue.equals("null")) {
					rattingValue = "0.0";
					tvRatingValue.setText(rattingValue);
					ratingBar.setRating(0);
				} else {

					float ratevalue = Float.parseFloat(rattingValue.trim());

					String rate = String.format("%.01f", ratevalue);
					tvRatingValue.setText(rate);
					ratingBar.setRating(Float.parseFloat(rattingValue.trim()));
				}

				tvDiscription.setText(discription.trim());

			} else {
				tvNetworkStatus.setText(getResources().getString(
						R.string.no_data));
			}
		}

	}

	class RatingJson extends AsyncTask<String, String, String> {
		String ratingValue;

		RatingJson(String rate) {
			ratingValue = rate;

		}

		boolean success = false;
		String message;
		boolean isavailable = false;
		String totalRating, averageRating;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (pDialog == null) {
				pDialog = new ProgressDialog(AdvertiesDetailActivity.this);
				pDialog.setMessage("Rating To Product");
				pDialog.setCancelable(false);
				pDialog.show();
			}
		}

		@Override
		protected String doInBackground(String... args) {

			RatingRequest ratingRequest = new RatingRequest();

			ratingRequest.setAdvertisement_id(productId);
			ratingRequest.setRating_value(ratingValue);
			ratingRequest.setAccess_token("a5ecd598821ff13fa4bba3cbd6f13c88");

			try {

				RatingResponse ratingResponse = mApp.getWebService()
						.postRating(ratingRequest);

				success = ratingResponse.isSuccess();
				if (success) {

					totalRating = ratingResponse.getTotal_rating();
					averageRating = ratingResponse.getAverage_rating();

				}

				isavailable = true;

			} catch (Exception e) {
				e.printStackTrace();
				isavailable = false;
			}

			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			if (pDialog != null && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (isavailable) {
				if (success) {

					tvRatingValue.setText(averageRating);
					ratingBar.setRating(Float.parseFloat(averageRating));

					Utils.ShowToast(mActivity,
							getResources()
									.getString(R.string.succesfully_rated));

				} else {
					Utils.ShowToast(mActivity,
							getResources().getString(R.string.try_again));
				}

			} else {
				Utils.ShowToast(mActivity,
						getResources().getString(R.string.no_data));
			}
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_detail_RateIt:
			RateToProduct();
			break;
		case R.id.iv_Detail_Call:
			Intent intent = new Intent(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:" + advertiser_phone));
			startActivity(intent);
			break;
		case R.id.iv_Detail_Sms:
			Intent smsIntent = new Intent(Intent.ACTION_VIEW);
			smsIntent.setData(Uri.parse("sms:" + advertiser_phone));
			startActivity(smsIntent);
		default:
			break;
		}
	}

}
