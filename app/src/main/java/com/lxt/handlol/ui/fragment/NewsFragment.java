package com.lxt.handlol.ui.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.lxt.handlol.adapter.viewpager.LunboHeaderAdapter;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.module.LunboBean;
import com.lxt.handlol.net.RetrofitHelper;
import com.lxt.handlol.ui.activity.MainActivity;
import com.lxt.handlol.ui.fragment.newspagr.ActivityFragment;
import com.lxt.handlol.ui.fragment.newspagr.GonglueFragment;
import com.lxt.handlol.ui.fragment.newspagr.GuanfangFragment;
import com.lxt.handlol.ui.fragment.newspagr.LatstNewsFragment2;
import com.lxt.handlol.ui.fragment.newspagr.YuleFragment;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.CircleImageView;
import com.lxt.handlol.widget.MyPterHeader;
import com.lxt.handlol.widget.StickyNavLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public class NewsFragment extends BaseFragment  {
    public static NewsFragment newsFragment;
    @Bind(R.id.viewpager_head)
    ViewPager mViewpagerHead;
    @Bind(R.id.focus_point)
    LinearLayout mFocusPoint;
    @Bind(R.id.id_stickynavlayout_topview)
    RelativeLayout mIdStickynavlayoutTopview;
    @Bind(R.id.id_stickynavlayout_indicator)
    TabLayout mTablayout;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager mIdStickynavlayoutViewpager;
    @Bind(R.id.main_content)
    StickyNavLayout mMainContent;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.search)
    ImageView mSearch;
    @Bind(R.id.news_toolbar)
    Toolbar mNewsToolbar;
    @Bind(R.id.round_header)
    FrameLayout mRoundHeader;
    @Bind(R.id.topview)
    LinearLayout mTopview;
    @Bind(R.id.touxiang)
    CircleImageView mTouxiang;
    @Bind(R.id.framelayout)
    FrameLayout mFramelayout;
    @Bind(R.id.store_house_ptr_frame)
    PtrFrameLayout mPtrFrame;
    private Activity mactivity;
    private String[] mTitles = new String[]{"最新", "活动", "娱乐", "官方", "攻略"};
    private Fragment[] mFragments = new Fragment[mTitles.length];
    private LatstNewsFragment2 latstNewsFragment2;
    private ImageView imageview;
    protected int lastPosition = 0;
    private Timer mTimer;
    private BannerTask mBannertask;
    private int mPagerPosition = 0;
    private int size;
    private boolean mIsUserTouched = false;

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
                case 1:

            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_news2;
    }

    @Override
    public void initView() {
//        MyPterHeader header=new MyPterHeader(getContext());
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
//                return mMainContent.getScrollY() == 0&&mMainContent.getScrollX()==0;
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



        mTopview.getBackground().setAlpha(0);
        mToolbarTitle.setAlpha(0);
        mRoundHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).closeLeftMenu();
            }
        });
        getLunboInfo(false);
        latstNewsFragment2 = new LatstNewsFragment2();
        mFragments[0] = latstNewsFragment2;
        mFragments[1] = new ActivityFragment();
        mFragments[2] = new YuleFragment();
        mFragments[3] = new GuanfangFragment();
        mFragments[4] = new GonglueFragment();
//        mFragments[5] = new BeautyFragment();
        FirstAdapter newsContentAdapter = new FirstAdapter(
                getChildFragmentManager());
        mIdStickynavlayoutViewpager.setAdapter(newsContentAdapter);
        mIdStickynavlayoutViewpager.setCurrentItem(0);
        mTablayout.setupWithViewPager(mIdStickynavlayoutViewpager);
        for (int i = 0; i < 5; i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(newsContentAdapter.getTabView(i));
            }
        }
        mTablayout.setTabMode(MODE_SCROLLABLE);
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
                 LogUtil.e("这是滑动的距离：" + scrollY);
                mToolbarTitle.setAlpha(scrollY * (float) 0.004);
                mTopview.getBackground().setAlpha(scrollY);
                if (scrollY >= 255) {
                    mToolbarTitle.setAlpha(1);
                    mTopview.getBackground().setAlpha(255);
                }
                return false;
            }
        });
    }


    private class Work extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(2000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(mPtrFrame!=null) {
                mPtrFrame.refreshComplete();
            }
        }
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
        //从网络获取数据
        RetrofitHelper.builder().getLunboServices().getLunboInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (!isDownRefresh) {
                            //showProgress();
                        }
                    }
                })
                .subscribe(new Action1<LunboBean>() {
                               @Override
                               public void call(LunboBean lunboBean) {
                                   if (lunboBean.getList() == null) {
                                       //加载失败
                                       //显示数据拉去失败请重新加载
                                   } else {
                                       List<LunboBean.BodyBean> list = lunboBean.getList();
                                       //给他设置point
                                       size = list.size();
                                       for (int i = 0; i < list.size(); i++) {
                                           imageview = new ImageView(getContext());
                                           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                   LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                           params.rightMargin = 20;
                                           imageview.setLayoutParams(params);
                                           if (i == 0) {
                                               imageview.setBackgroundResource(R.mipmap.golden_focus);
                                           } else {
                                               imageview.setBackgroundResource(R.mipmap.golden_unfocus);
                                           }
                                           mFocusPoint.addView(imageview);
                                       }
                                       LunboHeaderAdapter headerAdapter = new LunboHeaderAdapter(list, getContext());
                                       mViewpagerHead.setAdapter(headerAdapter);
                                       startViewPagerRun();
                                       mViewpagerHead.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                           @Override
                                           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                           }

                                           @Override
                                           public void onPageSelected(int position) {
                                               mFocusPoint.getChildAt(lastPosition).setBackgroundResource(R.mipmap.golden_unfocus);
                                               mFocusPoint.getChildAt(position).setBackgroundResource(R.mipmap.golden_focus);
                                               lastPosition = position;
                                           }

                                           @Override
                                           public void onPageScrollStateChanged(int state) {

                                           }
                                       });
                                       mViewpagerHead.setOnTouchListener(new View.OnTouchListener() {
                                           @Override
                                           public boolean onTouch(View v, MotionEvent event) {
                                               int action = event.getAction();
                                               if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
                                                   mIsUserTouched = true;
//                                                   mSwipeRefresh.setEnabled(false);
                                               } else if (action == MotionEvent.ACTION_UP) {
                                                   mIsUserTouched = false;
                                               } else if (action == MotionEvent.ACTION_CANCEL) {
//                                                   mSwipeRefresh.setEnabled(true);
                                               }
                                               return false;
                                           }
                                       });
                                   }
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   LogUtil.e("获取数据失败" + throwable.getMessage());
                                   for (int i = 0; i < 5; i++) {
                                       imageview = new ImageView(getContext());
                                       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                               LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                       params.rightMargin = 20;
                                       imageview.setLayoutParams(params);
                                       if (i == 0) {
                                           imageview.setBackgroundResource(R.mipmap.golden_focus);
                                       } else {
                                           imageview.setBackgroundResource(R.mipmap.golden_unfocus);
                                       }
                                       mFocusPoint.addView(imageview);
                                   }
                                   mViewpagerHead.setAdapter(new PagerAdapter() {
                                       @Override
                                       public int getCount() {
                                           return 5;
                                       }

                                       @Override
                                       public Object instantiateItem(ViewGroup container, int position) {
                                           View view = LayoutInflater.from(getContext()).inflate(R.layout.pager_item, container, false);
                                           ImageView pagerImg = (ImageView) view.findViewById(R.id.pager_img);
                                           pagerImg.setBackgroundResource(R.mipmap.default_lol2);
                                           container.addView(view);
                                           return view;
                                       }

                                       @Override
                                       public void destroyItem(ViewGroup container, int position, Object object) {
                                           container.removeView((View) object);
                                       }

                                       @Override
                                       public boolean isViewFromObject(View view, Object object) {
                                           return view == object;
                                       }
                                   });
                               }
                           }
                );
    }

    private void startViewPagerRun() {

        //这是一个计时器
        mTimer = new Timer();
        mBannertask = new BannerTask();
        mTimer.schedule(mBannertask, 5000, 5000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }



    private class BannerTask extends TimerTask {

        @Override
        public void run() {
//            //计时器之后要执行的操作
            if (!mIsUserTouched) {
                //用户没有在触摸
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mPagerPosition == size) {
                                mViewpagerHead.setCurrentItem(0);
                                mPagerPosition = 0;
                            } else {
                                mViewpagerHead.setCurrentItem(mPagerPosition++);
                            }
                        }
                    });
                }

            }

//
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBannertask != null) {
            mBannertask.cancel();
        }
    }

    class FirstAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = new String[]{"最新", "活动", "娱乐", "官方", "攻略"};

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
