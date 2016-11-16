package com.lxt.handlol.adapter.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.utils.LogUtil;

import java.util.List;


/**
 * Created by ${reallyCommon} on 2016/10/11 0011.
 * e-mail:871281347@qq.com
 */

public class NewsContentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 6;
    private String tabTitles[] = new String[]{"最新", "活动", "娱乐", "官方","攻略","美女"};
    private Context ctx;
    private List<Fragment>fragmentList;
    public NewsContentAdapter(FragmentManager fm,Context ctx,List<Fragment> fragmentList) {
        super(fm);
        this.ctx=ctx;
        this.fragmentList=fragmentList;

    }


    public View getTabView(int position) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.news_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.news_tab_title);
        tv.setText(tabTitles[position]);
        return v;
    }
    @Override
    public Fragment getItem(int position) {
        LogUtil.e(fragmentList.get(position).isAdded()+"====");
        return fragmentList.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public long getItemId(int position) {
        LogUtil.e("==="+position);
        return super.getItemId(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
