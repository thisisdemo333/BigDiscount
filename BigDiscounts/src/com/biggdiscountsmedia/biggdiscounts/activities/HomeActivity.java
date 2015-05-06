package com.biggdiscountsmedia.biggdiscounts.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.biggdiscountsmedia.biggdiscounts.BaseActivity;
import com.biggdiscountsmedia.biggdiscounts.BiggDiscountsApplication;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.R.id;
import com.biggdiscountsmedia.biggdiscounts.adapters.AdapterCategories;
import com.biggdiscountsmedia.biggdiscounts.adapters.AdapterCities;
import com.biggdiscountsmedia.biggdiscounts.datacache.DataCache;
import com.biggdiscountsmedia.biggdiscounts.dto.Categories;
import com.biggdiscountsmedia.biggdiscounts.dto.Cities;
import com.biggdiscountsmedia.biggdiscounts.fragments.CategoryFragment;
import com.biggdiscountsmedia.biggdiscounts.prefernces.PrefHelper;
import com.biggdiscountsmedia.biggdiscounts.utils.Utils;

public class HomeActivity extends BaseActivity {
	private Context mContext;
	private HomeActivity mActivity;
	private BiggDiscountsApplication mApp;
	private DataCache dataCache;
	private Bundle savedInstanceState;
	private PrefHelper prefHelper;
	private ArrayList<Categories> arrayListCategories;
	private ArrayList<Cities> arrayListCities;
	private AdapterCategories adapterCategories;
	private AdapterCities adapterCities;
	private CharSequence title;
	private CharSequence drawerTitle;
	private int cityId;
	private String cityName;
	private DrawerLayout mDrawerLayout;
	private ListView lvDrawerLeftSide;
	private ListView lvDraweRightSide;
	private ProgressBar progressBar;
	private ActionBarDrawerToggle mDrawerToggle;
	private boolean typeFlag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_layout);
		this.savedInstanceState = savedInstanceState;
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		initViews();
		initMembers();
		initListeners();
		initData();
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		lvDrawerLeftSide = (ListView) findViewById(R.id.listview_slide_left);
		lvDraweRightSide = (ListView) findViewById(R.id.listview_slide_right);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);

	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		lvDrawerLeftSide
				.setOnItemClickListener(new ListViewCategoryClickListener());
		lvDraweRightSide
				.setOnItemClickListener(new ListViewCitiesClickListener());
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}

	@Override
	public void initMembers() {
		// TODO Auto-generated method stub
		mContext = this;
		mActivity = this;
		mApp = (BiggDiscountsApplication) getApplicationContext();
		dataCache = DataCache.getInstance();
		prefHelper = new PrefHelper(mContext);
		arrayListCategories = new ArrayList<Categories>();
		arrayListCities = new ArrayList<Cities>();

		cityName = prefHelper.getCityFromPrefernce();

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		title = getTitle();
		drawerTitle = getTitle();

		// intializing Categories Adapter
		adapterCategories = new AdapterCategories(mActivity);
		adapterCategories.setArrayListCategories(arrayListCategories);
		lvDrawerLeftSide.setAdapter(adapterCategories);

		// // initializing Cities Adapter
		adapterCities = new AdapterCities(mActivity);
		adapterCities.setArrayListCities(arrayListCities);
		lvDraweRightSide.setAdapter(adapterCities);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.categories // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(title);

				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(
						getResources().getString(R.string.categories));
				// calling onPrepareOptionsMenu() to hide action bar icons
				switch (drawerView.getId()) {
				case R.id.listview_slide_left:
					boolean drawerOpenRight = mDrawerLayout
							.isDrawerOpen(lvDraweRightSide);
					if (drawerOpenRight) {
						mDrawerLayout.closeDrawer(lvDraweRightSide);
					}

					break;
				case R.id.listview_slide_right:
					boolean drawerOpenLeft = mDrawerLayout
							.isDrawerOpen(lvDrawerLeftSide);
					if (drawerOpenLeft) {
						mDrawerLayout.closeDrawer(lvDrawerLeftSide);
					}

					break;

				}
				invalidateOptionsMenu();
			}
		};
		if (savedInstanceState == null) {
			// on first time display view for first nav item

			displayCategoryFragment(0, false);
		}

	}

	private void displayCategoryFragment(int pos, boolean typeFlag) {
		// TODO Auto-generated method stub
		int categoryId = 0;
		String categoryName = null;
		if (typeFlag) {
			if (arrayListCategories.size() <= 0) {
				categoryId = 0;
				categoryName = getResources()
						.getString(R.string.all_categories);
			} else {
				Categories categories = arrayListCategories.get(pos);
				categoryId = categories.getId();
				categoryName = categories.getCategoryName();
			}
		}

		CategoryFragment categoryFragment = new CategoryFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(getResources().getString(R.string.key_category_id),
				categoryId);
		bundle.putString(getResources().getString(R.string.key_category_name),
				categoryName);
		bundle.putInt(getResources().getString(R.string.key_city_id), cityId);
		bundle.putString(getResources().getString(R.string.key_city_name),
				cityName);
		categoryFragment.setArguments(bundle);

		if (categoryFragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, categoryFragment).commit();

			// update selected item and title, then close the drawer
			lvDrawerLeftSide.setItemChecked(pos, true);
			lvDrawerLeftSide.setSelection(pos);
			setTitle(categoryName);
			mDrawerLayout.closeDrawer(lvDrawerLeftSide);
		}

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		if (Utils.hasDataConnection(mContext)) {
			GetCategoriesAsyncTask getCategoriesAsyncTask = new GetCategoriesAsyncTask();
			getCategoriesAsyncTask.execute();

			GetCitiesAsyncTask getCitiesAsyncTask = new GetCitiesAsyncTask();
			getCitiesAsyncTask.execute();
		}

	}

	/**
	 * ListView Category item click listener
	 * */
	private class ListViewCategoryClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayCategoryFragment(position, true);
		}
	}

	/**
	 * ListView Cities menu item click listener
	 * */
	private class ListViewCitiesClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item

			mDrawerLayout.closeDrawer(Gravity.RIGHT);
			prefHelper.saveCityInPrefernce(arrayListCities.get(position)
					.getCity_name());
			cityName = prefHelper.getCityFromPrefernce();

			displayCategoryFragment(position, false);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
		case R.id.right_slider_menu:
			boolean drawerOpen = mDrawerLayout.isDrawerOpen(lvDraweRightSide);
			if (drawerOpen) {
				mDrawerLayout.closeDrawer(lvDraweRightSide);
			} else {
				mDrawerLayout.openDrawer(lvDraweRightSide);
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}

	}

	@Override
	public void setTitle(CharSequence title) {
		this.title = title;
		getActionBar().setTitle(title);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * Categories Async Task
	 *
	 */
	private class GetCategoriesAsyncTask extends
			AsyncTask<Void, Void, ArrayList<Categories>> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected ArrayList<Categories> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				ArrayList<Categories> arrayListCategories = mApp
						.getWebService().getCategoriesList();
				return arrayListCategories;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Categories> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressBar.setVisibility(View.INVISIBLE);
			if (result != null) {
				Categories categories = new Categories();
				categories.setId(0);
				categories.setCategoryName(getResources().getString(
						R.string.all_categories));
				categories.setIconUrl(null);
				arrayListCategories.add(categories);
				arrayListCategories.addAll(result);
				adapterCategories.notifyDataSetChanged();

			} else {

			}
		}

	}

	private class GetCitiesAsyncTask extends
			AsyncTask<Void, Void, ArrayList<Cities>> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

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
			if (result != null) {
				arrayListCities.addAll(result);
				// initializing Cities Adapter

				adapterCities.notifyDataSetChanged();
			}

		}
	}

}
