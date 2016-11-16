package com.lxt.handlol.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lxt.handlol.utils.LogUtil;

/**
 * Created by ${reallyCommon} on 2016/10/18 0018.
 * e-mail:871281347@qq.com
 */

public class NoScrollViewpager2 extends ViewPager {
    private int x;

    public NoScrollViewpager2(Context context) {
        super(context);
    }

    public NoScrollViewpager2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                x = (int) ev.getX();
//                getParent().requestDisallowInterceptTouchEvent(true);
//            break;
//            case MotionEvent.ACTION_MOVE:
//                int endX = (int) getX();
//                int dx=endX- x;
//                LogUtil.e("zheshi"+dx);
//                if(Math.abs(dx)<=2) {
//                    //请求父控件拦截该事件
//                    LogUtil.e("请求父控件拦截该事件" + (dx));
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                }
//                x =(int)getX();
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        LogUtil.e("pppppppp");
        return super.onTouchEvent(ev);
    }


    //    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                startx = (int) getX();
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int endX = (int) getX();
//                int dx=endX-startx;
//                if(Math.abs(dx)<=2){
//                    //请求父控件拦截该事件
//                    LogUtil.e("请求父控件拦截该事件"+Math.abs(dx));
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                }else{
//                    LogUtil.e("请求父控件不要拦截该事件");
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }
}
