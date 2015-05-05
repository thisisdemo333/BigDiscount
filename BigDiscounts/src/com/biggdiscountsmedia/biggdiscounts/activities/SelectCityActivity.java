package com.biggdiscountsmedia.biggdiscounts.activities;

import java.util.ArrayList;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.biggdiscountsmedia.biggdiscounts.BaseActivity;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.R.layout;
import com.biggdiscountsmedia.biggdiscounts.datacache.DataCache;
import com.biggdiscountsmedia.biggdiscounts.dto.Cities;
import com.biggdiscountsmedia.biggdiscounts.utils.Utils;

public class SelectCityActivity extends BaseActivity implements
		OnClickListener, OnItemClickListener {

	private Context mContext;
	private BiggDiscountsApplication mApp;
	private DataCache dataCache;
	private ProgressBar progressBar;
	private ArrayList<Cities> arrayListCities;
	private ArrayList<String> arrayListCitiesName;
	private ArrayAdapter<String> adapterSpinner;
	private TextView textViewNetworkStatus;
	private Button buttonNext;
	private LinearLayout linearLayoutCity;
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_select_city_layout);
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		initViews();
		initListeners();
		initMembers();
		initData();

	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		linearLayoutCity = (LinearLayout) findViewById(R.id.ll_city);
		buttonNext = (Button) findViewById(R.id.button_next);
		spinner = (Spinner) findViewById(R.id.spineer_city);
		textViewNetworkStatus = (TextView) findViewById(R.id.tv_network_status);
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		buttonNext.setOnClickListener(this);
	}

	@Override
	public void initMembers() {
		// TODO Auto-generated method stub
		mContext = this;
		mApp = (BiggDiscountsApplication) getApplicationContext();
		dataCache = DataCache.getInstance();
		arrayListCities = new ArrayList<Cities>();
		arrayListCitiesName = new ArrayList<String>();
		adapterSpinner = new ArrayAdapter<String>(mContext,
				R.layout.spinner_textview_layout, arrayListCitiesName);
		adapterSpinner
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapterSpinner);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

		if (Utils.hasDataConnection(mContext)) {
			new GetCitiesAsyncTask().execute();
		} else {
			progressBar.setVisibility(View.INVISIBLE);
			textViewNetworkStatus.setText(getResources().getString(
					R.string.no_internert));
		}
	}

	private class GetCitiesAsyncTask extends
			AsyncTask<Void, Void, ArrayList<Cities>> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);

		}

		@Override
		protected ArrayList<Cities> doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {
				ArrayList<Cities> arrayListCities = mApp.getWebService()
						.getCityList();
				return arrayListCities;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(ArrayList<Cities> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressBar.setVisibility(View.GONE);
			textViewNetworkStatus.setVisibility(View.GONE);
			if (result != null) {
				dataCache.setArrayListCities(result);
				arrayListCities.addAll(result);
				for (Cities cities : result) {
					arrayListCitiesName.add(cities.getCity_name());
				}
				adapterSpinner.notifyDataSetChanged();
				linearLayoutCity.setVisibility(View.VISIBLE);
				linearLayoutCity.startAnimation(AnimationUtils.loadAnimation(
						mContext, R.anim.fadeout_anim));
			} else {
				textViewNetworkStatus.setText(getResources().getString(
						R.string.could_not_connect_to_server));
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pos = spinner.getSelectedItemPosition();
		int cityId = arrayListCities.get(pos).getId();
		String cityName = arrayListCities.get(pos).getCity_name();
		Intent intentHomeActivity = new Intent(mContext, HomeActivity.class);
		intentHomeActivity.putExtra(
				getResources().getString(R.string.key_city_id), cityId);
		intentHomeActivity.putExtra(
				getResources().getString(R.string.key_city_name), cityName);
		startActivity(intentHomeActivity);
		finish();

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		// TODO Auto-generated method stub

	}

}
