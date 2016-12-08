package com.lxt.handlol.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lxt.handlol.R;
import com.lxt.handlol.utils.LogUtil;

import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 * 先不使用懒加载fragment,后面再说
 */

public abstract class BaseFragment2 extends Fragment {
    private View view;
    public RecyclerView mListView;
    public LinearLayout progressbar;
    public RelativeLayout loadfail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
      view=inflater.inflate(R.layout.fragment_tab,container,false);
        mListView = (RecyclerView) view
                .findViewById(R.id.id_stickynavlayout_innerscrollview);
        progressbar= (LinearLayout) view.findViewById(R.id.progressbar);
        loadfail= (RelativeLayout) view.findViewById(R.id.loadingfail);
        initPage();
        return view;
    }
    public abstract void  initPage();

}
