package com.lxt.handlol.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/9/21 0021.
 * e-mail:871281347@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局文件
        setContentView(getLayoutId());
        //初始化黄油刀控件
        ButterKnife.bind(this);
        //初始化toolbar
        initToolBar();
        //初始化布局
        initView();
        //适配4.4系统状态栏
        //使用沉浸式状态栏
      //  StatusBarCompat.compat(this);
    }



    public abstract int getLayoutId();
    public abstract void initToolBar();
    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除黄油刀的绑定
        ButterKnife.unbind(this);
    }

    ///主题选择

}
