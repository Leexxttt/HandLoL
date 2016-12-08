package com.lxt.handlol.ui.fragment.newspagr;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lxt.handlol.R;
import com.lxt.handlol.adapter.recycleview.RecycleHuodongAdapter;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.base.BaseFragment2;
import com.lxt.handlol.base.LazyBaseFragment;
import com.lxt.handlol.module.huodong.MainContent;
import com.lxt.handlol.net.RetrofitHelper;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.AutoLoadOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ${reallyCommon} on 2016/10/11 0011.
 * e-mail:871281347@qq.com
 */

public class ActivityFragment extends BaseFragment2 {

    private AutoLoadOnScrollListener autoLoadOnScrollListener;
    List<MainContent.ListBean> huodonglist=new ArrayList<>();
    private RecycleHuodongAdapter adapter;
    private String next_page;

    public ActivityFragment(){
    }

    private void getInfo() {
        //第一次加载的时候

        RetrofitHelper.builder().getHuodongServices().getHuodonginfo("c23_list_1.shtml")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MainContent>() {
                               @Override
                               public void call(MainContent mainContent) {
                                   if (mainContent.getList() == null) {
                                       LogUtil.e("加载失败");
                                   } else {
                                       loadfail.setVisibility(View.INVISIBLE);
                                       progressbar.setVisibility(View.INVISIBLE);
                                       LogUtil.e("加载成功");
                                       next_page = mainContent.getNext();
                                       huodonglist = mainContent.getList();
                                       LogUtil.e(huodonglist.size()+"==");
                                       LinearLayoutManager manager=new LinearLayoutManager(getContext());
                                       mListView.setLayoutManager(manager);
                                       autoLoadOnScrollListener=new AutoLoadOnScrollListener(manager) {
                                           @Override
                                           public void onLoadMore(int currentPage) {
                                                    loadmore(currentPage);
                                           }
                                       } ;
                                       mListView.addOnScrollListener(autoLoadOnScrollListener);
                                       adapter = new RecycleHuodongAdapter(getContext(), huodonglist);
                                       mListView.setAdapter(adapter);

                                   }
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   LogUtil.e("再一次加载失败啦活动");
                                   progressbar.setVisibility(View.INVISIBLE);
                                   initEmptyView();
                               }
                           }
                );

    }
    private void initEmptyView() {
        //页面加载失败要加载的页面
        progressbar.setVisibility(View.INVISIBLE);
        loadfail.setVisibility(View.VISIBLE);
        loadfail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("点击重新加载");
                progressbar.setVisibility(View.VISIBLE);
                getInfo();

            }
        });

    }
    private void loadmore(final int currentPage) {
        RetrofitHelper.builder().getHuodongServices().getHuodonginfo(next_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MainContent>() {
                    @Override
                    public void call(MainContent mainContent) {
                        if (mainContent.getList() == null) {
                            LogUtil.e("加载失败");
                        } else {
                           next_page= mainContent.getNext();
                            autoLoadOnScrollListener.setLoading(false);
                            List<MainContent.ListBean> list = mainContent.getList();
                            adapter.addData(list);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtil.e("加载更多是时候加载失败");
                    }
                });
    }

        @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void initPage() {
        getInfo();
    }
}
