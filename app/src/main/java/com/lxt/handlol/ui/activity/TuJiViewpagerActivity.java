package com.lxt.handlol.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.adapter.viewpager.TujiAdapter;
import com.lxt.handlol.base.BaseActivity;
import com.lxt.handlol.module.tuji.TujiDetailBean;
import com.lxt.handlol.net.RetrofitHelper;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.ClickFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ${reallyCommon} on 2016/10/15 0015.
 * e-mail:871281347@qq.com
 */

public class TuJiViewpagerActivity extends BaseActivity {
    private static final String EXTRA_ID = "extra_id";
    private static final String EXTRA_URL = "extra_url";
    @Bind(R.id.toolbar_tuji)
    Toolbar mToolbarTuji;
    @Bind(R.id.pager_tuji)
    ViewPager mPagerTuji;
    @Bind(R.id.curr_page)
    TextView mCurrPage;
    @Bind(R.id.page_num)
    TextView mPageNum;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.auther)
    TextView mAuther;
    @Bind(R.id.info_botom)
    LinearLayout mInfoBotom;
    @Bind(R.id.content)
    RelativeLayout mContent;
    @Bind(R.id.framelayout)
    ClickFrameLayout mFramelayout;
    @Bind(R.id.back)
    ImageButton mBack;
    @Bind(R.id.share)
    ImageButton mShare;
    private String articleid;
    private boolean isshow = true;
    private List<TujiDetailBean.ListBean> listbean = new ArrayList<>();
    private int startX;
    private boolean isMOve = false;
    private String arturl;

    @Override
    public int getLayoutId() {
        return R.layout.layout_tuji;
    }

    @Override
    public void initToolBar() {

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"分享");
                intent.putExtra(Intent.EXTRA_TEXT,"来自「掌上英雄联盟」的分享:"+listbean.get(0).getImg_title()+","+arturl);
                startActivity(Intent.createChooser(intent,listbean.get(0).getImg_title()));
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            //获取要加载图集的id
            articleid = intent.getStringExtra(EXTRA_ID);
            arturl = intent.getStringExtra(EXTRA_URL);
        }
        getInfo();
        mFramelayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isshow) {
                    mInfoBotom.setVisibility(View.INVISIBLE);
                    isshow = false;
                } else {
                    mInfoBotom.setVisibility(View.VISIBLE);
                    isshow = true;
                }
                return false;
            }
        });
        mPagerTuji.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtil.e("ACTION_DOWN");
                        startX = (int) (event.getX());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtil.e("ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtil.e("ACTION_UP");
                        int endx = (int) event.getX();
                        int d2x = Math.abs(startX - endx);
                        if (d2x <= 2) {
                            LogUtil.e("点击w事件");
                            if (mInfoBotom.getVisibility() == View.VISIBLE) {
                                //变为不可见
                                mInfoBotom.setVisibility(View.INVISIBLE);
                            } else {
                                //变为可见
                                mInfoBotom.setVisibility(View.VISIBLE);
                            }
                            if (mToolbarTuji.getVisibility() == View.VISIBLE) {
                                //变为不可见
                                mToolbarTuji.setVisibility(View.INVISIBLE);
                            } else {
                                mToolbarTuji.setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                }
                return false;
            }
        });

    }

    private void getInfo() {
        LogUtil.e("这是文章的id:" + articleid);
        RetrofitHelper.builder().gettujidetail().getDetailInfo(articleid, "android", "9709")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TujiDetailBean>() {
                    @Override
                    public void call(TujiDetailBean detailbean) {
                        if (detailbean.getList() == null) {
                            LogUtil.e("联网失败");
                        } else {
                            listbean = detailbean.getList();
                            mPagerTuji.setAdapter(new TujiAdapter(getApplicationContext(), listbean));
                            mTitle.setText(listbean.get(0).getImg_title());
                            mAuther.setText(listbean.get(0).getImg_desc());
                            mCurrPage.setText(1 + "");
                            mPageNum.setText(listbean.size() + "");
                            mPagerTuji.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {
                                    mTitle.setText(listbean.get(position).getImg_title());
                                    mAuther.setText(listbean.get(position).getImg_desc());
                                    mCurrPage.setText(position + 1 + "");
                                    mPageNum.setText(listbean.size() + "");
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtil.e("获取图集失败");
                    }
                });
    }


    public static void lanuch(Context context, String address,String arturl) {

        Intent mIntent = new Intent(context, TuJiViewpagerActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra(EXTRA_ID, address);
        mIntent.putExtra(EXTRA_URL,arturl);
        context.startActivity(mIntent);
    }


}
