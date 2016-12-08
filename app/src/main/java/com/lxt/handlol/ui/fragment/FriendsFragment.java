package com.lxt.handlol.ui.fragment;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.adapter.ExpandableListView.GameExpandAdapter;
import com.lxt.handlol.adapter.ExpandableListView.ZhangmenFrieng;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.ui.activity.MainActivity;
import com.lxt.handlol.ui.fragment.friends.HaoyouFragment;
import com.lxt.handlol.ui.fragment.friends.XiaoxiFragment;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.CircleImageView;
import com.lxt.handlol.widget.NonScrollExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public class FriendsFragment extends BaseFragment implements View.OnClickListener {
    public static FriendsFragment friendsFragment;
    @Bind(R.id.toolbar_friend)
    Toolbar mToolbar;
    @Bind(R.id.touxiang)
    CircleImageView mTouxiang;
    @Bind(R.id.tv_friends)
    TextView mTvFriends;
    @Bind(R.id.tv_news)
    TextView mTvNews;
    @Bind(R.id.add_info)
    ImageView mAddInfo;
    @Bind(R.id.friend_img)
    ImageView mFriendImg;
    @Bind(R.id.news_img)
    ImageView mNewsImg;
    @Bind(R.id.fl_friends)
    FrameLayout mFlFriends;
    @Bind(R.id.fl_news)
    FrameLayout mFlNews;
    @Bind(R.id.round_header)
    FrameLayout mRoundHeader;
    @Bind(R.id.status_bar_friend)
    LinearLayout mStatusBarFriend;
    private List<Fragment> list=new ArrayList<>();
    private View popupview;
    private PopupWindow mWindow;

    private FriendsFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_friends;
    }

    @Override
    public void initView() {
        list.add(new HaoyouFragment());
        list.add(new XiaoxiFragment());

        mTvFriends.setTextColor(getResources().getColor(R.color.white));
        mTvFriends.setOnClickListener(this);
        mTvNews.setOnClickListener(this);
        mRoundHeader.setOnClickListener(this);
        mAddInfo.setOnClickListener(this);
        initFramelayout();

    }


    private void initFramelayout() {
        Display newDisplay = getActivity().getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pager_news, null, false);
        mFlNews.addView(view);
        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.pager_friends, null, false);
        mFlFriends.addView(view2);
        NonScrollExpandableListView zhangmenExpandList =
                (NonScrollExpandableListView) view2.findViewById(R.id.expanded_lis_zhangmen);
        zhangmenExpandList.setIndicatorBounds(width - 1600, width);
        zhangmenExpandList.setAdapter(new ZhangmenFrieng(getActivity()));
        NonScrollExpandableListView gameExpandList =
                (NonScrollExpandableListView) view2.findViewById(R.id.expanded_lis_game);
        gameExpandList.setIndicatorBounds(width - 1600, width);
        gameExpandList.setAdapter(new GameExpandAdapter(getActivity()));
    }



    public static FriendsFragment getInstance() {
        friendsFragment = new FriendsFragment();
        return friendsFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mStatusBarFriend.getBackground().setAlpha(255);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
//
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_friends) {
            //点击好友按钮
            mTvFriends.setTextColor(getResources().getColor(R.color.white));
            mTvFriends.setTextSize(20);
            mFriendImg.setVisibility(View.VISIBLE);
            mTvNews.setTextColor(getResources().getColor(R.color.btn_pressed_color));
            mTvNews.setTextSize(16);
            mNewsImg.setVisibility(View.INVISIBLE);
            mFlNews.setVisibility(View.INVISIBLE);
            mFlFriends.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.tv_news) {
            //点击了消息按钮
            mTvNews.setTextColor(getResources().getColor(R.color.white));
            mTvNews.setTextSize(20);
            mNewsImg.setVisibility(View.VISIBLE);
            mTvFriends.setTextColor(getResources().getColor(R.color.btn_pressed_color));
            mTvFriends.setTextSize(16);
            mFriendImg.setVisibility(View.INVISIBLE);
            mFlNews.setVisibility(View.VISIBLE);
            mFlFriends.setVisibility(View.INVISIBLE);
        } else if(v.getId()==R.id.round_header){
            //点击了头像
            ((MainActivity) getActivity()).closeLeftMenu();
        } else if(v.getId()==R.id.add_info){
            if(mWindow==null) {
                popupview = getActivity().getLayoutInflater().inflate(R.layout.add_friends, null);
                mWindow = new PopupWindow(popupview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                mWindow.setBackgroundDrawable(new ColorDrawable());
            }
            mWindow.showAsDropDown(mAddInfo);

        }

    }


}
