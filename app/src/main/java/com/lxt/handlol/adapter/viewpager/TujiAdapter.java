package com.lxt.handlol.adapter.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lxt.handlol.R;
import com.lxt.handlol.module.tuji.TujiDetailBean;
import com.lxt.handlol.utils.LogUtil;

import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/10/15 0015.
 * e-mail:871281347@qq.com
 */

public class TujiAdapter extends PagerAdapter {
    private Context context;
    private List<TujiDetailBean.ListBean> listBeen;
    private int num2;
    private boolean isShow=true;

    public TujiAdapter(Context context, List<TujiDetailBean.ListBean> listBeen){
        LogUtil.e("加载图集信息"+listBeen.size());
        this.listBeen=listBeen;
        this.context=context;
    }
    @Override
    public int getCount() {
        num2 = listBeen.size();
        return listBeen.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.pager_tuji,container,false);
        ImageView pagerImg = (ImageView) view.findViewById(R.id.pager_img);
        TujiDetailBean.ListBean bean = listBeen.get(position);
        Glide.with(context).load(bean.getImg_url())
                .into(pagerImg);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
