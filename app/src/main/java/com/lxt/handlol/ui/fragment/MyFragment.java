package com.lxt.handlol.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.ui.fragment.my.MoneyFragment;
import com.lxt.handlol.ui.fragment.my.NengLiFragment;
import com.lxt.handlol.ui.fragment.my.ZhanjiFragment;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.BigPterHeader;
import com.lxt.handlol.widget.CircleImageView;
import com.lxt.handlol.widget.HorizontalScrollViewPager;
import com.lxt.handlol.widget.MyPterHeader;
import com.lxt.handlol.widget.StickyNavLayout2;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public class MyFragment extends BaseFragment {
    public static MyFragment myFragment;
    @Bind(R.id.touxiang)
    CircleImageView mTouxiang;
    @Bind(R.id.round_header)
    FrameLayout mRoundHeader;
    @Bind(R.id.id_stickynavlayout_topview)
    LinearLayout mIdStickynavlayoutTopview;
    @Bind(R.id.id_stickynavlayout_indicator)
    TabLayout mTablayout;
    @Bind(R.id.id_stickynavlayout_viewpager)
    HorizontalScrollViewPager mIdStickynavlayoutViewpager;
    @Bind(R.id.main_content)
    StickyNavLayout2 mMainContent;
    @Bind(R.id.qufu)
    TextView mQufu;
    @Bind(R.id.daqu)
    LinearLayout mDaqu;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.levle)
    TextView mLevle;
    @Bind(R.id.user_info)
    RelativeLayout mUserInfo;
    @Bind(R.id.add_info)
    ImageView mAddInfo;
    @Bind(R.id.toolbar_my)
    Toolbar mToolbarMy;
    @Bind(R.id.status_bar_my)
    LinearLayout mStatusBarMy;
//    @Bind(R.id.store_house_ptr_frame)
//    PtrFrameLayout mPtrFrame;
    private String[] mTitles = new String[]{"战绩", "能力", "资产"};
    private Fragment[] mFragments = new Fragment[mTitles.length];
    private float mLastY;

    private MyFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_my;
    }

    @Override
    public void initView() {
//        BigPterHeader header=new BigPterHeader(getContext());
//        mPtrFrame.autoRefresh(false);
//        mPtrFrame.setHeaderView(header);
//        mPtrFrame.addPtrUIHandler(header);
//        mPtrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                new Work().execute();
//            }
//
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                //设置下拉刷新的条件
//                return mMainContent.getScrollY() == 0;
//            }
//        });
//        // the following are default settings
//        mPtrFrame.setResistance(1.7f);
//        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.0f);
//        mPtrFrame.setDurationToClose(200);
//        mPtrFrame.setDurationToCloseHeader(1000);
//        // default is false
//        mPtrFrame.setPullToRefresh(false);
//        // default is true
//        mPtrFrame.setKeepHeaderWhenRefresh(true);
//        mPtrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPtrFrame.autoRefresh();
//            }
//        }, 150);
        mStatusBarMy.getBackground().setAlpha(0);
        mFragments[0] = new ZhanjiFragment();
        mFragments[1] = new NengLiFragment();
        mFragments[2] = new MoneyFragment();
        SecondAdapter adapter = new SecondAdapter(getChildFragmentManager());
        mIdStickynavlayoutViewpager.setAdapter(adapter);
        mIdStickynavlayoutViewpager.setCurrentItem(0);
        mTablayout.setupWithViewPager(mIdStickynavlayoutViewpager);
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        mTablayout.getTabAt(0).getCustomView().setSelected(true);
        final View view = mTablayout.getTabAt(0).getCustomView();
        TextView textView = (TextView) view.findViewById(R.id.news_tab_title);
        textView.setTextColor(getResources().getColor(R.color.btn_pressed_color));
        ImageView textImg = (ImageView) view.findViewById(R.id.news_tab_img);
        textImg.setVisibility(View.VISIBLE);
        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabNormal(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mMainContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int scrollY = v.getScrollY();
                LogUtil.e("这是滑动的距离" + scrollY);
                if (scrollY >= 255) {
                    mStatusBarMy.getBackground().setAlpha(255);
                    mDaqu.setVisibility(View.INVISIBLE);
                    mUserInfo.setVisibility(View.VISIBLE);
                } else {
                    mStatusBarMy.getBackground().setAlpha(scrollY);
                    mDaqu.setVisibility(View.VISIBLE);
                    mUserInfo.setVisibility(View.INVISIBLE);
                }

                return false;
            }
        });
    }

    private void changeTabNormal(TabLayout.Tab tab) {
        //当tab未被选中的时候
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.news_tab_img);
        TextView txt_title = (TextView) view.findViewById(R.id.news_tab_title);
        txt_title.setTextColor(getResources().getColor(R.color.text_normal));
        img_title.setVisibility(View.INVISIBLE);
    }

    private void changeTabSelect(TabLayout.Tab tab) {
        //当tab被选中的时候
        //当tab未被选中的时候
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.news_tab_img);
        TextView txt_title = (TextView) view.findViewById(R.id.news_tab_title);
        txt_title.setTextColor(getResources().getColor(R.color.btn_pressed_color));
        img_title.setVisibility(View.VISIBLE);
        if (txt_title.getText().toString().equals("战绩")) {
            mIdStickynavlayoutViewpager.setCurrentItem(0);
            LogUtil.e(mIdStickynavlayoutViewpager.getCurrentItem() + "=====");
        } else if (txt_title.getText().toString().equals("能力")) {
            mIdStickynavlayoutViewpager.setCurrentItem(1);
        } else {
            mIdStickynavlayoutViewpager.setCurrentItem(2);
        }

    }

    public static MyFragment getInstance() {
        myFragment = new MyFragment();
        return myFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
//    private class Work extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            SystemClock.sleep(2000);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            if(mPtrFrame!=null) {
//                mPtrFrame.refreshComplete();
//            }
//        }
//    }
    class SecondAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = new String[]{"战绩", "能力", "资产"};

        public SecondAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        public View getTabView(int position) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.news_tab, null);
            TextView tv = (TextView) v.findViewById(R.id.news_tab_title);
            tv.setText(tabTitles[position]);
            return v;
        }
    }
}
