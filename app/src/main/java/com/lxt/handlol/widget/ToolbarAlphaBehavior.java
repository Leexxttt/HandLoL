package com.lxt.handlol.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.lxt.handlol.R;
import com.lxt.handlol.utils.LogUtil;

/**
 * Created by ${reallyCommon} on 2016/10/10 0010.
 * e-mail:871281347@qq.com
 * 用来检测toolbar的行为，一开始是透明的后来滑动而变化
 */

public class ToolbarAlphaBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    private static final String TAG = "ToolbarAlphaBehavior";
    private int startOffset = 0;
    private int endOffset = 0;
    private Context context;
    private int offset=0;

    public ToolbarAlphaBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar toolbar, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        startOffset = 0;
        endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) - toolbar.getHeight();
        offset += dyConsumed;
        if (offset <=startOffset) {  //alpha为0o
            if(dyConsumed>=0&&dyUnconsumed>=0){
                toolbar.setBackgroundResource(R.mipmap.header_bg_small);
            }else{
            toolbar.setBackgroundResource(R.drawable.header_bg);}
        } else if (offset > startOffset && offset < endOffset) { //alpha为0到255
//            float precent = (float) (offset - startOffset) / endOffset;
//            int alpha = Math.round(precent * 255);
//            toolbar.getBackground().setAlpha(alpha);
            toolbar.setBackgroundResource(R.mipmap.header_bg_small);
        } else if (offset >= endOffset) {  //alpha为255
         //   toolbar.getBackground().setAlpha(255);
            toolbar.setBackgroundResource(R.mipmap.header_bg_small);
        }
        }

}
