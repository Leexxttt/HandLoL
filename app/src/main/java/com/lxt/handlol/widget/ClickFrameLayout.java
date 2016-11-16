package com.lxt.handlol.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.lxt.handlol.utils.LogUtil;

/**
 * Created by ${reallyCommon} on 2016/10/17 0017.
 * e-mail:871281347@qq.com
 */

public class ClickFrameLayout extends FrameLayout {

    private int startX;
    boolean isIntercept = false;
    public ClickFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClickFrameLayout(Context context) {
        super(context);
    }

    public ClickFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            // 只有水平滑动时才拦截touch
//            case MotionEvent.ACTION_DOWN:
//                startX = (int) (ev.getX());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int newX = (int) (ev.getX());
//                int dx = Math.abs(startX - newX);
//                if (dx<=2) {
//                    LogUtil.e("拦截事件");
//                    return true;
//                }
//                startX = (int) ev.getX();// 初始化当前位置
//               break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("skskkkk");
        return super.onTouchEvent(event);
    }
}
