package com.lxt.handlol.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxt.handlol.utils.LogUtil;

import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 * 先不使用懒加载fragment,后面再说
 */

public abstract class BaseFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
      view=inflater.inflate(getLayoutId(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        LogUtil.e("onViewCreated=====");
        initView();
    }
    public abstract int getLayoutId();
    public abstract void initView();
}
