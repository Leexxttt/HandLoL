package com.lxt.handlol;

import android.app.Application;
import android.content.Context;

/**
 * Created by ${reallyCommon} on 2016/10/3 0003.
 * e-mail:871281347@qq.com
 * 自定义application，获取一些全局的信息
 */

public class HandLoLAPP extends Application {

    public static Context mAppContext;

    @Override
    public void onCreate()
    {

        super.onCreate();
        mAppContext = this;
    }
    /**
     *
     * 提供一个上下文对象
     * @return
     */
    public static Context getContext()
    {

        return mAppContext;
    }
}
