package com.lxt.handlol.ui.fragment.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.lxt.handlol.R;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.Polygonsview;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/23 0023.
 * e-mail:871281347@qq.com
 */

public class NengLiFragment extends Fragment {
    public RecyclerView mListView;
    @Bind(R.id.mv)
    Polygonsview mv;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            initView(inflater, container);
        }
        ButterKnife.bind(this, mView);
        return mView;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.layout_nengli, container, false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
