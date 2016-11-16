package com.lxt.handlol.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.lxt.handlol.R;
import com.lxt.handlol.base.BaseActivity;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.ProgressWebView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by ${reallyCommon} on 2016/10/12 0012.
 * e-mail:871281347@qq.com
 */

public class ArticleDetatileActivity extends BaseActivity {
    private static final String EXTRA_DETAIL = "extra_detail";
    //    private MyWebViewClient webViewClient = new MyWebViewClient();
    private static final String EXTRA_ID = "extra_id";
    //    @Bind(R.id.progressBar)
//    ProgressBar mProgressBar;
    @Bind(R.id.webview)
    ProgressWebView mWebview;
    @Bind(R.id.video_fullView)
    FrameLayout mVideoFullView;
    private View xCustomView;
    private String articleurl;
    private WebChromeClient.CustomViewCallback xCustomViewCallback;
    @Override
    public int getLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            //获取要加载的文章的url
            articleurl = intent.getStringExtra(EXTRA_ID);
        }
        setUpWebView();
    }

    private void setUpWebView() {

        mWebview.setVerticalScrollbarOverlay(true);
        WebSettings ws = mWebview.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
        ws.setAllowFileAccess(true);
        ws.setBuiltInZoomControls(true);
        ws.setUseWideViewPort(true);// 可任意比例缩放
        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.setSavePassword(true);
        ws.setSaveFormData(true);// 保存表单数据
        mWebview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                if (url != null
                        && (url.startsWith("http://") || url
                        .startsWith("file:///")))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
        LogUtil.e("texstlllll");

        mWebview.loadUrl(articleurl);

    }

    public static void lanuch(Context context, String address) {

        Intent mIntent = new Intent(context, ArticleDetatileActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra(EXTRA_ID, address);
        context.startActivity(mIntent);
    }

}
