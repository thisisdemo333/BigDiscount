package com.biggdiscountsmedia.biggdiscounts;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.biggdiscountsmedia.biggdiscounts.datacache.DataCache;
import com.biggdiscountsmedia.biggdiscounts.volleyutils.LruBitmapCache;
import com.biggdiscountsmedia.biggdiscounts.webservices.WebService;

import android.app.Application;
import android.text.TextUtils;

public class BiggDiscountsApplication extends Application {

	private WebService webService;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private DataCache dataCache;
	private static BiggDiscountsApplication mInstance;
	public static final String TAG = BiggDiscountsApplication.class
			.getSimpleName();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		initMembers();
	}

	private void initMembers() {
		// TODO Auto-generated method stub
		mInstance = this;
		webService = new WebService();
		dataCache = DataCache.getInstance();

	}

	public static synchronized BiggDiscountsApplication getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mRequestQueue,
					new LruBitmapCache());
		}
		return this.mImageLoader;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

	/**
	 * Provides WebService Instance
	 * 
	 * @return
	 */
	public WebService getWebService() {
		return webService;
	}
}
