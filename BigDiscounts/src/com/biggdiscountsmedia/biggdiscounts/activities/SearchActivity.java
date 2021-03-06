package com.biggdiscountsmedia.biggdiscounts.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.biggdiscountsmedia.biggdiscounts.BaseActivity;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.adapters.AdapterSearchList;
import com.biggdiscountsmedia.biggdiscounts.dto.Regular;
import com.biggdiscountsmedia.biggdiscounts.dto.SearchQuery;
import com.google.android.gms.internal.lb;

public class SearchActivity extends BaseActivity implements
		OnQueryTextListener, OnItemClickListener {

	private SearchView searchView;

	private ListView lvSearchList;

	private AdapterSearchList adapterSearchList;

	private ArrayList<Regular> arrayListSearchList = new ArrayList<Regular>();

	private BiggDiscountsApplication mApp;

	private SearchActivity mActivity;

	// progress view

	private RelativeLayout relativeLayoutProgress;
	private TextView textViewNetworkStatus;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search_menu, menu);

		MenuItem menuItem = menu.findItem(R.id.menu_search);
		searchView = (SearchView) menuItem.getActionView();
		searchView.setOnQueryTextListener(this);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void init() {
		initViews();
		initMembers();
		initData();
		initListeners();
	}

	@Override
	public void initViews() {
		lvSearchList = (ListView) findViewById(R.id.lv_search_item);
		relativeLayoutProgress = (RelativeLayout) findViewById(R.id.rl_progress);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		textViewNetworkStatus = (TextView) findViewById(R.id.tv_network_status);
	}

	@Override
	public void initListeners() {
		lvSearchList.setOnItemClickListener(this);
	}

	@Override
	public void initMembers() {
		mActivity = this;
		mApp = (BiggDiscountsApplication) getApplication();

		adapterSearchList = new AdapterSearchList(mActivity);
		adapterSearchList.setArrayListSearchList(arrayListSearchList);

		lvSearchList.setAdapter(adapterSearchList);

	}

	@Override
	public void initData() {

	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		doSearch(query);
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {

		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		if (arrayListSearchList != null && arrayListSearchList.size() > 0) {
			Regular regular = arrayListSearchList.get(position);
			int ad_id = regular.getAdvertisement_image().getAdvertisement_id();

			Intent intent = new Intent(SearchActivity.this,
					AdvertiesDetailActivity.class);
			intent.putExtra("id", String.valueOf(ad_id));
			startActivity(intent);
		}
	}

	private class GetSearchedProductList extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			relativeLayoutProgress.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.VISIBLE);
			textViewNetworkStatus.setVisibility(View.VISIBLE);

			lvSearchList.setVisibility(View.GONE);
		}

		@Override
		protected Void doInBackground(String... args) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("search_term", args[0]));

			try {
				arrayListSearchList = mApp.getWebService()
						.getSearchedProductList(params);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			if (arrayListSearchList.size() == 0) {
				progressBar.setVisibility(View.GONE);
				textViewNetworkStatus.setText(getResources().getString(
						R.string.no_data));
			} else {
				relativeLayoutProgress.setVisibility(View.GONE);
				// setting Animation
//				Animation animation = AnimationUtils.loadAnimation(mActivity,
//						R.anim.fadeout_anim);
				lvSearchList.setVisibility(View.VISIBLE);
//				lvSearchList.setAnimation(animation);
				// initializng adapter
				adapterSearchList.setArrayListSearchList(arrayListSearchList);
				adapterSearchList.notifyDataSetChanged();
			}

		}
	}

	private void doSearch(String query) {
		new GetSearchedProductList().execute(query);
	}

}
