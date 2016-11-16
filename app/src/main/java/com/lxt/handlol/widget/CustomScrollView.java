package com.lxt.handlol.widget;


import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 自定义ScrollView，解决：ScrollView嵌套ViewPager，导致ViewPager不能滑动的问题
 * 对于时间的分发
 */
public class CustomScrollView extends NestedScrollView {

	public CustomScrollView(Context context) {
		super(context);
	}
	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

}