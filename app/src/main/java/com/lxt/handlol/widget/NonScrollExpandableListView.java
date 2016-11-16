package com.lxt.handlol.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

/**
 * Created by ${reallyCommon} on 2016/10/10 0010.
 * e-mail:871281347@qq.com
 */

public class NonScrollExpandableListView extends ExpandableListView {
    public NonScrollExpandableListView(Context context) {
        super(context);
    }

    public NonScrollExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}
