package com.lxt.handlol.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.ImageButton;

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
    @Bind(R.id.video_fullView)
    FrameLayout mVideoFullView;
    @Bind(R.id.back)
    ImageButton mBack;
    @Bind(R.id.share)
    ImageButton mShare;
    @Bind(R.id.webview)
    ProgressWebView mWebview;
    private View xCustomView;
    private String articleurl;
    private WebChromeClient.CustomViewCallback xCustomViewCallback;

    @Override
    public int getLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    public void initToolBar() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"分享");
                intent.putExtra(Intent.EXTRA_TEXT,"来自「掌上英雄联盟」的分享:"+articleurl);
                startActivity(Intent.createChooser(intent,"来自「掌上英雄联盟」的分享"));
            }
        });

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
