package com.biggdiscountsmedia.biggdiscounts;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseViewHolder {

	protected LayoutInflater mInflater;

	public BaseViewHolder(Activity mActivity) {

		mInflater = mActivity.getLayoutInflater();
	}

	public abstract void setData(Object data);

	public abstract void applyData();

	public abstract View getConvertView();

}
