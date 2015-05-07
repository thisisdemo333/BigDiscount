package com.biggdiscountsmedia.biggdiscounts.prefernces;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PrefHelper {

	SharedPreferences mSharedPreferences;
	Editor mEditor;

	public PrefHelper(Context mContext) {
		mSharedPreferences = mContext.getSharedPreferences(PrefNames.PREF_NAME,
				Application.MODE_PRIVATE);
		mEditor = mSharedPreferences.edit();
	}

	public void saveCityInPrefernce(String city) {
		mEditor.putString(PrefNames.KEY_CITY, city);
		mEditor.commit();
	}

	public String getCityFromPrefernce() {
		return mSharedPreferences.getString(PrefNames.KEY_CITY, null);
	}
}
