package com.lxt.handlol.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lxt.handlol.HandLoLAPP;

/**
 * Created by ${reallyCommon} on 2016/9/13 0013.
 * e-mail:871281347@qq.com
 * 这是一个工具类用来判断当前的网络状态
 */
public class NetWorkUtil {

private NetWorkUtil()
{

}




    //判断当前网络是否连接
    public static boolean isNetworkConnected()
    {

        if (HandLoLAPP.getContext() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) HandLoLAPP.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null)
            {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    //判断当前是否是WiFi连接
    public static boolean isWifiConnected()
    {

        if (HandLoLAPP.getContext() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) HandLoLAPP.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null)
            {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isMobileConnected()
    {

        if (HandLoLAPP.getContext() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) HandLoLAPP.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null)
            {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     *
     * 获取连接类型
     * @return
     */
    public static int getConnectedType()
    {

        if (HandLoLAPP.getContext() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) HandLoLAPP.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable())
            {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }



}
