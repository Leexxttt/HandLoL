package com.lxt.handlol.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ${reallyCommon} on 2016/10/19 0019.
 * e-mail:871281347@qq.com
 */

public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) height = h;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // 返回false
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // 返回false
        return false;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }


    // 防止viewpager跟scrollview冲突
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        boolean ret = super.dispatchTouchEvent(ev);
        if(ret)
        {
            requestDisallowInterceptTouchEvent(true);
        }
        return ret;
    }


}
