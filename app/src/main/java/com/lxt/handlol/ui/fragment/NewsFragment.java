package com.lxt.handlol.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.adapter.viewpager.LunboHeaderAdapter;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.module.LunboBean;
import com.lxt.handlol.net.RetrofitHelper;
import com.lxt.handlol.ui.fragment.newspagr.ActivityFragment;
import com.lxt.handlol.ui.fragment.newspagr.BeautyFragment;
import com.lxt.handlol.ui.fragment.newspagr.GonglueFragment;
import com.lxt.handlol.ui.fragment.newspagr.GuanfangFragment;
import com.lxt.handlol.ui.fragment.newspagr.LatstNewsFragment2;
import com.lxt.handlol.ui.fragment.newspagr.YuleFragment;
import com.lxt.handlol.utils.DisplayUtil;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.CircleImageView;
import com.lxt.handlol.widget.StickyNavLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public class NewsFragment extends BaseFragment {
    public static NewsFragment newsFragment;
    @Bind(R.id.id_stickynavlayout_topview)
    RelativeLayout mIdStickynavlayoutTopview;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager mIdStickynavlayoutViewpager;
    @Bind(R.id.viewpager_head)
    ViewPager mViewpagerHead;
    @Bind(R.id.id_stickynavlayout_indicator)
    TabLayout mTablayout;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.news_toolbar)
    Toolbar mNewsToolbar;
    @Bind(R.id.main_content)
    StickyNavLayout mMainContent;
    @Bind(R.id.touxiang)
    CircleImageView mTouxiang;
    private boolean isTopHidden=false;
    private float mLastY;
    private Activity mactivity;
    private String[] mTitles = new String[]{"最新", "活动", "娱乐", "官方", "攻略", "收藏"};
    private Fragment[] mFragments = new Fragment[mTitles.length];
    private LatstNewsFragment2 latstNewsFragment2;

    private NewsFragment() {
        mactivity = getActivity();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //获取最新信息
                    break;

            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_news2;
    }


    @Override
    public void initView() {
        getLunboInfo(false);
        mNewsToolbar.getBackground().setAlpha(0);
        mToolbarTitle.setVisibility(View.INVISIBLE);
        latstNewsFragment2 = new LatstNewsFragment2();
        mFragments[0] = latstNewsFragment2;
        mFragments[1] = new ActivityFragment();
        mFragments[2] = new YuleFragment();
        mFragments[3] = new GuanfangFragment();
        mFragments[4] = new GonglueFragment();
        mFragments[5] = new BeautyFragment();
        FirstAdapter newsContentAdapter = new FirstAdapter(
                getChildFragmentManager());
        mIdStickynavlayoutViewpager.setAdapter(newsContentAdapter);
        mIdStickynavlayoutViewpager.setCurrentItem(0);
        mTablayout.setupWithViewPager(mIdStickynavlayoutViewpager);
            for (int i = 0; i < 6; i++) {
                TabLayout.Tab tab = mTablayout.getTabAt(i);
                if (tab != null) {
                    tab.setCustomView(newsContentAdapter.getTabView(i));
                }
            }
        mTablayout.setTabMode(MODE_SCROLLABLE);
        mTablayout.getTabAt(0).getCustomView().setSelected(true);
        View view = mTablayout.getTabAt(0).getCustomView();
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
        if (txt_title.getText().toString().equals("最新")) {
            mIdStickynavlayoutViewpager.setCurrentItem(0);
            LogUtil.e(mIdStickynavlayoutViewpager.getCurrentItem() + "=====");
        } else if (txt_title.getText().toString().equals("活动")) {
            mIdStickynavlayoutViewpager.setCurrentItem(1);
        } else if (txt_title.getText().toString().equals("娱乐")) {
            mIdStickynavlayoutViewpager.setCurrentItem(2);
        } else if (txt_title.getText().toString().equals("官方")) {
            mIdStickynavlayoutViewpager.setCurrentItem(3);
        } else if (txt_title.getText().toString().equals("攻略")) {
            mIdStickynavlayoutViewpager.setCurrentItem(4);
        } else {
            mIdStickynavlayoutViewpager.setCurrentItem(5);
        }

    }

    private void getLunboInfo(final boolean isDownRefresh) {
//         mProgressbar.setVisibility(View.VISIBLE);
        //从网络获取数据
        RetrofitHelper.builder().getLunboServices().getLunboInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        LogUtil.e("走到这里了");
                        if (!isDownRefresh) {
                            //showProgress();
                        }
                    }
                })
                .subscribe(new Action1<LunboBean>() {
                               @Override
                               public void call(LunboBean lunboBean) {
                                   LogUtil.e("=====" + lunboBean.getList());
                                   if (lunboBean.getList() == null) {
                                       //加载失败
//                                       hideProgress();
                                       //显示数据拉去失败请重新加载
                                   } else {
//                                       hideProgress();
                                       List<LunboBean.BodyBean> list = lunboBean.getList();
                                       LunboHeaderAdapter headerAdapter = new LunboHeaderAdapter(list, getContext());
                                       mViewpagerHead.setAdapter(headerAdapter);
                                   }

                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   LogUtil.e("获取数据失败" + throwable.getMessage());
//                                    hideProgress();
                               }
                           }
                );
    }

//    private void showProgress() {
//        mProgressbar.setVisibility(View.VISIBLE);
//    }
//
//    private void hideProgress() {
//        mProgressbar.setVisibility(View.GONE);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class FirstAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = new String[]{"最新", "活动", "娱乐", "官方", "攻略", "收藏"};

        public FirstAdapter(FragmentManager fm) {
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

    public static NewsFragment getInstance() {
        newsFragment = new NewsFragment();
        return newsFragment;
    }
}
