package com.lxt.handlol.widget;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.lxt.handlol.R;
import com.lxt.handlol.ui.activity.ArticleDetatileActivity;
import com.lxt.handlol.utils.LogUtil;

/**
 * Created by ${reallyCommon} on 2016/10/12 0012.
 * e-mail:871281347@qq.com
 */

public class ProgressWebView extends WebView {
    private ProgressBar progressbar;
    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                16, 0, 0));
        addView(progressbar);
        setWebViewClient(new WebCustomClient());
        setWebChromeClient(new WebChromeFutherClient());
        // 发布取消
        //Web开发调试之Chrome远程调试(Remote Debugging)会用到
        // setWebContentsDebuggingEnabled(true);
        getSettings().setDomStorageEnabled(true);//解决总是跳转第三方浏览器
    }
    public class WebCustomClient extends android.webkit.WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            // 页面下载完毕,却不代表页面渲染完毕显示出来
            // WebChromeClient中progress==100时也是一样
            if (view.getContentHeight() != 0) {
                // 这个时候网页才显示
            }
            view.loadUrl(BrowserJsInject.fullScreenByJs(url));
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            // 自身加载新链接,不做外部跳转
            view.loadUrl(url);
            return true;
        }

    }
    public class WebChromeFutherClient extends android.webkit.WebChromeClient {
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
