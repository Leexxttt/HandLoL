package com.lxt.handlol.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.adapter.ExpandableListView.GameFriendAdapter;
import com.lxt.handlol.adapter.ExpandableListView.ShequExpandAdapter;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.ui.activity.HeroDataActivity;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public class FoundFragment extends BaseFragment {
    public static FoundFragment instance;
    @Bind(R.id.id_gallery)
    LinearLayout mIdGallery;
    @Bind(R.id.expanded_list)
    ExpandableListView mExpandedList;
    @Bind(R.id.expanded_list_shequ)
    ExpandableListView mExpandedListShequ;
    @Bind(R.id.nestedscroll)
    NestedScrollView mNestedscroll;
    @Bind(R.id.touxiang)
    CircleImageView mTouxiang;
    @Bind(R.id.round_header)
    FrameLayout mRoundHeader;
    @Bind(R.id.toolbar_found)
    Toolbar mToolbarFound;
    @Bind(R.id.status_bar_found)
    LinearLayout mStatusBarFound;
    @Bind(R.id.hero_info)
    LinearLayout mHeroInfo;
    private int[] mImgIds;
    private float mLastY;
    private List<String> strings;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_found;
    }

    @Override
    public void initView() {
        initToolBar();
        initHorizontalScrollView();
        initExpndListView();
        mHeroInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HeroDataActivity.class));
            }
        });
    }

    private void initToolBar() {
        mNestedscroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LogUtil.e("这是scrollview" + scrollX + "=====" + scrollY + "====" + oldScrollX + "====" + oldScrollY);
                mStatusBarFound.getBackground().setAlpha(scrollY);
                if (scrollY >= 200) {
                    mStatusBarFound.getBackground().setAlpha(255);
                }
            }
        });
    }

    private void initExpndListView() {
        //将箭头的位置设置在右边
        Display newDisplay = getActivity().getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        mExpandedList.setIndicatorBounds(width - 100, width);
        mExpandedListShequ.setIndicatorBounds(width - 100, width);
        GameFriendAdapter adapter = new GameFriendAdapter(getActivity());
        mExpandedList.setAdapter(adapter);
        ShequExpandAdapter adapter1 = new ShequExpandAdapter(getActivity());
        mExpandedListShequ.setAdapter(adapter1);
    }

    private void initHorizontalScrollView() {
        //初始化HorizontalScrollView
        mImgIds = new int[]{R.mipmap.edg,
                R.mipmap.omg,
                R.mipmap.snake,
                R.mipmap.vg,
                R.mipmap.rng,
                R.mipmap.lgd,
                R.mipmap.nb,
                R.mipmap.ig,
                R.mipmap.we,
                R.mipmap.im,
                R.mipmap.up,
                R.mipmap.m3};
        strings = new ArrayList<>();
        strings.add("EDG");
        strings.add("OMG");
        strings.add("Snake");
        strings.add("VG");
        strings.add("RNG");
        strings.add("LGD");
        strings.add("Newbee");
        strings.add("IG");
        strings.add("WE");
        strings.add("IM");
        strings.add("UP");
        strings.add("M3");
        for (int i = 0; i < mImgIds.length; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_horizontalscro, mIdGallery, false);
            ImageView clubphoto = (ImageView) view.findViewById(R.id.club_img);
            TextView clubname = (TextView) view.findViewById(R.id.club_name);
            clubphoto.setImageResource(mImgIds[i]);
            clubname.setText(strings.get(i));
            mIdGallery.addView(view);
        }

    }

    private FoundFragment() {
    }

    public static FoundFragment getInstance() {
        instance = new FoundFragment();
        return instance;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
