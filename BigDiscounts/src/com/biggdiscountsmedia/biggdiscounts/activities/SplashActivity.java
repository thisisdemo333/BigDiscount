package com.biggdiscountsmedia.biggdiscounts.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.biggdiscountsmedia.biggdiscounts.BaseActivity;
import com.biggdiscountsmedia.biggdiscounts.R;
import com.biggdiscountsmedia.biggdiscounts.prefernces.PrefHelper;

public class SplashActivity extends BaseActivity {
	private Context mContext;
	private Handler mHandler;
	private PrefHelper prefHelper;
	private int splashTime = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_layout);
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

	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initMembers() {
		// TODO Auto-generated method stub
		mContext = this;
		mHandler = new Handler();
		prefHelper = new PrefHelper(mContext);

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		splashHandler();
	}

	private void splashHandler() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(prefHelper.getCityFromPrefernce())) {
					Intent intentScreenActivity = new Intent(mContext,
							SelectCityActivity.class);
					startActivity(intentScreenActivity);
					finish();
				} else {
					Intent intentHomeActivityy = new Intent(mContext,
							HomeActivity.class);
					startActivity(intentHomeActivityy);
					finish();
				}
			}
		}, splashTime);

	}

}
