package com.lxt.handlol.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/11 0011.
 * e-mail:871281347@qq.com
 */

public  abstract class LazyBaseFragment extends Fragment {
    private View parentView;

    private FragmentActivity activity;

    private LayoutInflater inflater;

    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;

    //标志位 fragment是否可见
    protected boolean isVisible;

    public abstract int getLayoutResId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state)
    {

        this.inflater = inflater;
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        return parentView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
    }

    public abstract void finishCreateView(Bundle state);

    @Override
    public void onResume()
    {

        super.onResume();
    }

    @Override
    public void onDestroyView()
    {

        super.onDestroyView();
    }

    @Override
    public void onAttach(Activity activity)
    {

        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach()
    {

        super.onDetach();
        this.activity = null;
    }

    public FragmentActivity getSupportActivity()
    {

        return super.getActivity();
    }

    public android.app.ActionBar getSupportActionBar()
    {

        return getSupportActivity().getActionBar();
    }

    public Context getApplicationContext()
    {

        return this.activity == null ? (getActivity() == null ? null :
                getActivity().getApplicationContext()) : this.activity.getApplicationContext();
    }

    protected LayoutInflater getLayoutInflater()
    {

        return inflater;
    }

    protected View getParentView()
    {

        return parentView;
    }


    /**
     * Fragment数据的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {

        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint())
        {
            isVisible = true;
            onVisible();
        } else
        {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible()
    {

        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible()
    {

    }
    public <T extends View> T $(int id)
    {

        return (T) parentView.findViewById(id);
    }
}
