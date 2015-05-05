package com.biggdiscountsmedia.biggdiscounts.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class BiggDiscountsGridView extends GridView {

	public BiggDiscountsGridView(Context context) {
		super(context);
	}

	public BiggDiscountsGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BiggDiscountsGridView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int heightSpec = heightMeasureSpec;
		// HACK! TAKE THAT ANDROID!
		if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
			// Calculate entire height by providing a very large height hint.
			heightSpec = View.MeasureSpec.makeMeasureSpec(
					Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
		} else {
			heightSpec = heightMeasureSpec;
		}
		super.onMeasure(widthMeasureSpec, heightSpec);
	}

}