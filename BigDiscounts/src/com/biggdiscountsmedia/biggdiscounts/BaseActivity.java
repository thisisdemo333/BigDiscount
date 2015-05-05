package com.biggdiscountsmedia.biggdiscounts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {

	//-----Abstract listeners--------
	public abstract void init();

	public abstract void initViews();

	public abstract void initListeners();

	public abstract void initMembers();

	public abstract void initData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
}
