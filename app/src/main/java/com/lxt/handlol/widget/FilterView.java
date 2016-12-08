package com.lxt.handlol.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxt.handlol.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/11/8 0008.
 * e-mail:871281347@qq.com
 */

public class FilterView extends LinearLayout implements View.OnClickListener {
    @Bind(R.id.tv_category)
    TextView mTvCategory;
    @Bind(R.id.iv_category_arrow)
    ImageView mIvCategoryArrow;
    @Bind(R.id.ll_hero_type)
    LinearLayout mLlHeroType;
    @Bind(R.id.tv_sort)
    TextView mTvSort;
    @Bind(R.id.iv_sort_arrow)
    ImageView mIvSortArrow;
    @Bind(R.id.ll_location)
    LinearLayout mLlLocation;
    @Bind(R.id.tv_filter)
    TextView mTvFilter;
    @Bind(R.id.iv_filter_arrow)
    ImageView mIvFilterArrow;
    @Bind(R.id.ll_filter_price)
    LinearLayout mLlFilterPrice;
    @Bind(R.id.tv_paixu)
    TextView mTvPaixu;
    @Bind(R.id.iv_filter_paixu)
    ImageView mIvFilterPaixu;
    @Bind(R.id.ll_filter)
    LinearLayout mLlFilter;
    @Bind(R.id.ll_head_layout)
    LinearLayout mLlHeadLayout;
    @Bind(R.id.ll_content_list_view)
    LinearLayout mLlContentListView;
    @Bind(R.id.view_mask_bg)
    View mViewMaskBg;
    private int filterPosition = -1;
    private boolean isShowing=false;

    public FilterView(Context context) {
        super(context);
        init(context);
    }

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_filter_viewe, this);
        ButterKnife.bind(this, view);
        initView();
        initListerner();
    }

    private void initListerner() {
        mLlHeroType.setOnClickListener(this);
        mLlLocation.setOnClickListener(this);
        mLlFilterPrice.setOnClickListener(this);
        mLlFilter.setOnClickListener(this);
        mViewMaskBg.setOnClickListener(this);
        mLlContentListView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void initView() {
        mLlContentListView.setVisibility(View.GONE);
        mViewMaskBg.setVisibility(View.GONE);
    }

    /**
     * 筛选视图点击
     * @param
     */
    private OnFilterClickListener OnFilterClickListener;
    public interface OnFilterClickListener{
        void onFilterClick(int position);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_hero_type:
                //点击了英雄类型这一排序按钮
                filterPosition=0;
                if(OnFilterClickListener!=null){
                    OnFilterClickListener.onFilterClick(filterPosition);
                }
                break;
            case R.id.ll_location:
                //点击了位置这一排序按钮
                filterPosition=1;
                if(OnFilterClickListener!=null){
                    OnFilterClickListener.onFilterClick(filterPosition);
                }
                break;
            case R.id.ll_filter_price:
                //点击了价格这一排序按钮
                filterPosition=2;
                if(OnFilterClickListener!=null){
                    OnFilterClickListener.onFilterClick(filterPosition);
                }
                break;
            case R.id.ll_filter:
                //点击了排序这一排序按钮
                filterPosition=3;
                if(OnFilterClickListener!=null){
                    OnFilterClickListener.onFilterClick(filterPosition);
                }
                break;
            case R.id.view_mask_bg:
                //点击了非下来筛选的部分
                hide();
                break;

        }
    }

    private void hide() {
        isShowing = false;
        resetFilterStatus();
        mViewMaskBg.setVisibility(View.GONE);
    }

    /**
     * 将一些因为筛选而改变状态的复位
     */
    private void resetFilterStatus() {
    }
}
