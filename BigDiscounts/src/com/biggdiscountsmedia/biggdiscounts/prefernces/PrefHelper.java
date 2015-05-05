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
}
