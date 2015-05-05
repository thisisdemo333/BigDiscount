package com.biggdiscountsmedia.biggdiscounts.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Utils {

	/**
	 * Show Toast
	 * 
	 * @param mContext
	 * @param message
	 */
	public static void ShowToast(Context mContext, String message) {
		Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Returns the network connectivity state
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean hasDataConnection(Context ctx) {

		ConnectivityManager cm = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			if (netInfo != null) {
				if (netInfo.isAvailable() && netInfo.isConnected()) {
					return true;
				}
			}
		}
		return false;
	}

}
