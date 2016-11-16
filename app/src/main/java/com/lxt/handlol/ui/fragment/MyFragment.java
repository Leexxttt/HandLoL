package com.lxt.handlol.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxt.handlol.R;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.ui.fragment.newspagr.ActivityFragment;
import com.lxt.handlol.ui.fragment.newspagr.BeautyFragment;
import com.lxt.handlol.ui.fragment.newspagr.GonglueFragment;
import com.lxt.handlol.ui.fragment.newspagr.GuanfangFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public class MyFragment extends BaseFragment {
    public static MyFragment myFragment;
    @Bind(R.id.viewpager_my)
    ViewPager mViewpagerMy;
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_my;
    }

    @Override
    public void initView() {
        fragmentList.add(new ActivityFragment());
        fragmentList.add(new GonglueFragment());
        fragmentList.add(new GuanfangFragment());
        fragmentList.add(new BeautyFragment());
        mViewpagerMy.setAdapter(new Myadapter(getChildFragmentManager()));

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
    class Myadapter extends FragmentPagerAdapter{

        public Myadapter(FragmentManager fm) {
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
    }
}
