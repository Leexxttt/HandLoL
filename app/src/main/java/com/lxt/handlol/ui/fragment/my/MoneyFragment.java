package com.lxt.handlol.ui.fragment.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.base.BaseFragment2;
import com.lxt.handlol.ui.activity.HeroDataActivity;

/**
 * Created by ${reallyCommon} on 2016/10/23 0023.
 * e-mail:871281347@qq.com
 */

public class MoneyFragment extends Fragment {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null)
        {
            initView(inflater, container);
        }
        return mView;
    }
    private void initView(LayoutInflater inflater, ViewGroup container)
    {
        mView = inflater.inflate(R.layout.layout_money, container, false);
        TextView showAllHero = (TextView) mView.findViewById(R.id.show_all_hero);
        showAllHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HeroDataActivity.class));
            }
        });
    }
}
