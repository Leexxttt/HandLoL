package com.lxt.handlol.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by ${reallyCommon} on 2016/10/17 0017.
 * e-mail:871281347@qq.com
 */

public class NoClickLinlayout extends LinearLayout {
    public NoClickLinlayout(Context context) {
        super(context);
    }

    public NoClickLinlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoClickLinlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //用来分发事件的
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
