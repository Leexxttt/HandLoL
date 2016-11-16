package com.lxt.handlol.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxt.handlol.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by ${reallyCommon} on 2016/11/3 0003.
 * e-mail:871281347@qq.com
 */

public class BigPterHeader extends FrameLayout implements PtrUIHandler {

    private ImageView imgview;
    private TextView text;
    private AnimationDrawable animation;
    private ImageView img;

    public BigPterHeader(Context context) {
        super(context);
        init();
    }

    public BigPterHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BigPterHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_refresh, this);
        imgview = (ImageView) view.findViewById(R.id.refresh_ani);
        text = (TextView) view.findViewById(R.id.textView);
        img = (ImageView) view.findViewById(R.id.refresh);
        animation = (AnimationDrawable) imgview.getDrawable();
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        resetView();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        pullStep0(0.0f);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        pullStep1(frame);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
           // pullStep1(frame);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();
        if (lastPos < mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                float scale = lastPos / Float.valueOf(mOffsetToRefresh);
                pullStep0(scale);
            }
        }

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                pullStep3(frame);
            }
        }

    }

    private void pullStep0(float scale) {
        text.setText("下拉刷新");
        img.setVisibility(View.VISIBLE);
        imgview.setVisibility(View.INVISIBLE);
    }

    private void pullStep3(PtrFrameLayout frame) {
        if(!frame.isPullToRefresh()){
            text.setText("释放刷新");
        }
    }

    private void pullStep1(PtrFrameLayout frame) {
        if (!frame.isPullToRefresh()) {
            img.setVisibility(View.INVISIBLE);
            imgview.setVisibility(View.VISIBLE);
            animation.start();
            text.setText("刷新中");
        }
    }
    private void pullStep2(PtrFrameLayout frame) {
        if (!frame.isPullToRefresh()) {
            text.setText("释放刷新");
        }
    }
    private void resetView() {
        cancelAnimations();
    }
    private void cancelAnimations() {
        if(animation!=null&&animation.isRunning()){
            animation.stop();
        }
    }
}
