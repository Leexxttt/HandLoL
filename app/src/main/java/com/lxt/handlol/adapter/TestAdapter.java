package com.lxt.handlol.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.module.huodong.MainContent;

import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/10/12 0012.
 * e-mail:871281347@qq.com
 */

public class TestAdapter extends PagerAdapter {
    private Context ctx;
    private String tabTitles[] = new String[]{"最新", "活动", "娱乐", "官方","攻略","美女"};
    private List<Fragment>fragmentList;
    public TestAdapter (Context ctx, List<Fragment> fragmentList){
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
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = fragmentList.get(position);
        View view= LayoutInflater.from(ctx).inflate(R.layout.news_tab,container,false);
        TextView title = (TextView) view.findViewById(R.id.news_tab_title);
       // title.setText(listBean.getTitle());
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
