package com.lxt.handlol.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by ${reallyCommon} on 2016/11/3 0003.
 * e-mail:871281347@qq.com
 */

public class PtrFrameLayout extends in.srain.cube.views.ptr.PtrFrameLayout {
    public PtrFrameLayout(Context context) {
        super(context);
        init();
    }

    public PtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init() {
        MyPterHeader mHeaderView = new MyPterHeader(getContext());
        setHeaderView(mHeaderView);
        addPtrUIHandler(mHeaderView);
    }


}
