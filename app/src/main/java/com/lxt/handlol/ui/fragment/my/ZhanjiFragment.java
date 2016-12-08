package com.lxt.handlol.ui.fragment.my;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lxt.handlol.adapter.recycleview.ZhanjiAdapter;
import com.lxt.handlol.base.BaseFragment2;
import com.lxt.handlol.module.my.ZhanjiBean;
import com.lxt.handlol.net.RetrofitHelper;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.AutoLoadOnScrollListener;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ${reallyCommon} on 2016/10/23 0023.
 * e-mail:871281347@qq.com
 */

public class ZhanjiFragment extends BaseFragment2 {
    private String next_page;
    private AutoLoadOnScrollListener autoLoadOnScrollListener;
    private ZhanjiAdapter adapter;
    @Override
    public void initPage() {
       getInfo();
    }

    private void getInfo() {
        //第一次加载的时候
        RetrofitHelper.builder().getzhanji().getzhanjiInfo("zhanji.json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ZhanjiBean>() {
                               @Override
                               public void call(ZhanjiBean mainContent) {
                                   if (mainContent.getList() == null) {
                                       LogUtil.e("加载失败");
                                       progressbar.setVisibility(View.INVISIBLE);
                                   } else {
                                       progressbar.setVisibility(View.INVISIBLE);
                                       LogUtil.e("加载成功");
                                       next_page = mainContent.getNext();
                                       List<ZhanjiBean.ListBean> list =mainContent.getList();
                                       LogUtil.e(list.size()+"==");
                                       LinearLayoutManager manager=new LinearLayoutManager(getContext());
                                       mListView.setLayoutManager(manager);
                                       autoLoadOnScrollListener=new AutoLoadOnScrollListener(manager) {
                                           @Override
                                           public void onLoadMore(int currentPage) {
                                              // loadmore(currentPage);
                                           }
                                       } ;
                                       mListView.addOnScrollListener(autoLoadOnScrollListener);
                                       adapter = new ZhanjiAdapter(getContext(), list);
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

//    private void loadmore(final int currentPage) {
//        RetrofitHelper.builder().getHuodongServices().getHuodonginfo(next_page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<MainContent>() {
//                    @Override
//                    public void call(MainContent mainContent) {
//                        if (mainContent.getList() == null) {
//                            LogUtil.e("加载失败");
//                        } else {
//                            next_page= mainContent.getNext();
//                            autoLoadOnScrollListener.setLoading(false);
//                            List<MainContent.ListBean> list = mainContent.getList();
//                            adapter.addData(list);
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        LogUtil.e("加载更多是时候加载失败");
//                    }
//                });
//    }
}
