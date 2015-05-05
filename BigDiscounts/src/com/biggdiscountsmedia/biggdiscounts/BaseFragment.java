package com.biggdiscountsmedia.biggdiscounts;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{

	// -----Abstract listeners--------
	public abstract void init(View view);

	public abstract void initViews(View view);

	public abstract void initListeners();

	public abstract void initMembers();

	public abstract void initData();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	

}
