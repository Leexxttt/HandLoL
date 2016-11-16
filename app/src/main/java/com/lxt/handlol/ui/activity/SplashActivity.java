package com.lxt.handlol.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lxt.handlol.HandLoLAPP;
import com.lxt.handlol.R;
import com.lxt.handlol.utils.NetWorkUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作为app的启动界面
 * 作用：加载一张启动界面的图片，当没有网络的时候加载默认的图片
 */
public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.activity_splash)
    RelativeLayout mActivitySplash;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //实现沉浸式状态栏
        StatusBar();
    }
    @Override
    protected void onResume() {
        super.onResume();
        initImg();
    }
    private void StatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void initImg() {
        Glide.with(HandLoLAPP.getContext())
                .load("http://183.203.24.22/dlied1.qq.com/qqtalk/lolApp/images/qidong/qidong-android.jpg?mkey=57ef5fd4740a3e85&f=b110&c=0&p=.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.start_up)
                .into(mImageView);
        boolean mobileConnected = NetWorkUtil.isMobileConnected();
        if (!mobileConnected) {
            Toast.makeText(this, "没有可用的网络哦！", Toast.LENGTH_SHORT).show();
        }
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}
