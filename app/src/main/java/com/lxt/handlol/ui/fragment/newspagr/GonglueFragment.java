package com.lxt.handlol.ui.fragment.newspagr;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.lxt.handlol.R;
import com.lxt.handlol.adapter.recycleview.RecycleGonglueAdapter;
import com.lxt.handlol.adapter.recycleview.RecycleHuodongAdapter;
import com.lxt.handlol.base.BaseFragment2;
import com.lxt.handlol.base.LazyBaseFragment;
import com.lxt.handlol.module.gonglue.GonglueBean;
import com.lxt.handlol.module.huodong.MainContent;
import com.lxt.handlol.net.RetrofitHelper;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.AutoLoadOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ${reallyCommon} on 2016/10/11 0011.
 * e-mail:871281347@qq.com
 */

public class GonglueFragment extends BaseFragment2 {
    private String next_page;
    private AutoLoadOnScrollListener autoLoadOnScrollListener;
    private List<GonglueBean.ListBean> listBean=new ArrayList<>();
    private RecycleGonglueAdapter adapter;
    @Override
    public void initPage() {
        getInfo();
    }

    private void getInfo() {
        RetrofitHelper.builder().getGonglue().getGonglueInfo("c10_list_1.shtml")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GonglueBean>() {
                               @Override
                               public void call(GonglueBean mainContent) {
                                   if (mainContent.getList() == null) {
                                       LogUtil.e("加载失败");
                                   } else {
                                       LogUtil.e("加载成功");
                                       next_page = mainContent.getNext();
                                       listBean = mainContent.getList();
                                       LinearLayoutManager manager=new LinearLayoutManager(getContext());
                                       mListView.setLayoutManager(manager);
                                       autoLoadOnScrollListener=new AutoLoadOnScrollListener(manager) {
                                           @Override
                                           public void onLoadMore(int currentPage) {
                                               loadmore(currentPage);
                                           }
                                       } ;
                                       mListView.addOnScrollListener(autoLoadOnScrollListener);
                                       adapter = new RecycleGonglueAdapter(getContext(), listBean);
                                       mListView.setAdapter(adapter);

                                   }
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   LogUtil.e("再一次加载失败啦");
                               }
                           }
                );

    }

    private void loadmore(final int currentPage) {
        RetrofitHelper.builder().getGonglue().getGonglueInfo(next_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GonglueBean>() {
                    @Override
                    public void call(GonglueBean mainContent) {
                        if (mainContent.getList() == null) {
                            LogUtil.e("加载失败");
                        } else {
                            next_page= mainContent.getNext();
                            autoLoadOnScrollListener.setLoading(false);
                            List<GonglueBean.ListBean> list = mainContent.getList();
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

}
