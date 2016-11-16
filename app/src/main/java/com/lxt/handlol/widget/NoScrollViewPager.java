package com.lxt.handlol.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ${reallyCommon} on 2016/10/5 0005.
 * e-mail:871281347@qq.com
 * viewpager之间不可以滑动
 */

public class NoScrollViewPager extends ViewPager {
    private boolean noScroll = false;
    public NoScrollViewPager(Context context) {
        super(context);
    }
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
        public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;

    }


}
