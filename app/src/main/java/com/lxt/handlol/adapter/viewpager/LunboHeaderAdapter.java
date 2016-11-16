package com.lxt.handlol.adapter.viewpager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lxt.handlol.R;
import com.lxt.handlol.module.LunboBean;
import com.lxt.handlol.ui.activity.ArticleDetatileActivity;
import com.lxt.handlol.utils.LogUtil;

import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public class LunboHeaderAdapter extends PagerAdapter {
    private List<LunboBean.BodyBean> list;
    private Context context;
    public LunboHeaderAdapter(List<LunboBean.BodyBean> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public int getCount() {
        LogUtil.e("这是list的大小"+list.size());
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, container, false);
        ImageView pagerImg = (ImageView) view.findViewById(R.id.pager_img);
        final LunboBean.BodyBean bodyBean = list.get(position);
        Glide.with(context).load(bodyBean.getImage_url_big())
                .into(pagerImg);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleDetatileActivity.lanuch(context,bodyBean.getArticle_url());
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
