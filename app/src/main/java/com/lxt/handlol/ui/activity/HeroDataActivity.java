package com.lxt.handlol.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.base.BaseActivity;
import com.lxt.handlol.ui.fragment.FoundFragment;
import com.lxt.handlol.ui.fragment.FriendsFragment;
import com.lxt.handlol.ui.fragment.NewsFragment;
import com.lxt.handlol.ui.fragment.herodata.AllHeroFragment;
import com.lxt.handlol.ui.fragment.herodata.MyHeroFragment;
import com.lxt.handlol.ui.fragment.herodata.WeekFreeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HeroDataActivity extends BaseActivity {


    @Bind(R.id.tablayout)
    TabLayout mTablayout;
    @Bind(R.id.hero_viewpager)
    ViewPager mHeroViewpager;
    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.toolbar_found)
    Toolbar mToolbarFound;
    @Bind(R.id.activity_hero_data)
    FrameLayout mActivityHeroData;
    List<Fragment>fragmentList=new ArrayList<>();
    private HeroViewPagerAdapter adapter;
    private String tabTitles[] = new String[]{"周免英雄", "我的英雄", "全部英雄"};
    @Override
    public int getLayoutId() {
        return R.layout.activity_hero_data;
    }

    @Override
    public void initToolBar() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initView() {
        initViewpager();
        mTablayout.setupWithViewPager(mHeroViewpager);
        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        mTablayout.getTabAt(0).getCustomView().setSelected(true);
        View view = mTablayout.getTabAt(0).getCustomView();
        TextView textView = (TextView) view.findViewById(R.id.news_title);
        textView.setTextColor(getResources().getColor(R.color.btn_pressed_color));
        textView.setTextSize(16);
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
    /**
     * 当tab没有被选中的时候
     *
     * @param tab
     */
    private void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        TextView txt_title = (TextView) view.findViewById(R.id.news_title);
        txt_title.setTextColor(getResources().getColor(R.color.color_5));
        txt_title.setTextSize(14);
    }

    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        TextView txt_title = (TextView) view.findViewById(R.id.news_title);
        txt_title.setTextColor(getResources().getColor(R.color.btn_pressed_color));
        txt_title.setTextSize(16);
        if (txt_title.getText().toString().equals("周免英雄")) {
            mHeroViewpager.setCurrentItem(0);
        } else if (txt_title.getText().toString().equals("我的英雄")) {
            mHeroViewpager.setCurrentItem(1);
        } else {
            mHeroViewpager.setCurrentItem(2);
        }
    }

    private void initViewpager() {
        mHeroViewpager.setOffscreenPageLimit(3);
        fragmentList.add(new WeekFreeFragment());
        fragmentList.add(new MyHeroFragment());
        fragmentList.add(new AllHeroFragment());
        adapter = new HeroViewPagerAdapter(getSupportFragmentManager());
        mHeroViewpager.setAdapter(adapter);
    }

class HeroViewPagerAdapter extends FragmentPagerAdapter{

    public HeroViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public View getTabView(int position) {
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.hero_data_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.news_title);
        tv.setText(tabTitles[position]);
        return v;
    }
}
}
