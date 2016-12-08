package com.lxt.handlol.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lxt.handlol.utils.LogUtil;

/**
 * Created by ${reallyCommon} on 2016/10/31 0031.
 * e-mail:871281347@qq.com
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    private boolean disallowIntercept;
    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

        if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            disallowIntercept = false;
        }
        //如果子控件请求不要拦截这个手势，那么就直接返回false，不拦截它的事件
        if(disallowIntercept) {
            return false;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        //disallowIntercept为true代表子控件请求父控件不要拦截这个事件
        this.disallowIntercept = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}
