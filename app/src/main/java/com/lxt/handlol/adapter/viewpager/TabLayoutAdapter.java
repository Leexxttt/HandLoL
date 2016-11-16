package com.lxt.handlol.adapter.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public class TabLayoutAdapter extends PagerAdapter {
    private static String [] tabtitle={"资讯","好友","发现","我"};
    @Override
    public int getCount() {
        return tabtitle.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

}
