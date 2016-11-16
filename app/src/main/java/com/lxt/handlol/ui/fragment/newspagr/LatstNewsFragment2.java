package com.lxt.handlol.ui.fragment.newspagr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxt.handlol.R;
import com.lxt.handlol.adapter.recycleview.RecycleLatestAdapter;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.base.BaseFragment2;
import com.lxt.handlol.module.latest.ZuixinBean;
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

public class LatstNewsFragment2 extends Fragment {
    private String  next_page;
    private AutoLoadOnScrollListener autoLoadOnScrollListener;
    List<ZuixinBean.ListBean> Latestlist=new ArrayList<>();
    private RecycleLatestAdapter adapter;
    private View view;
    public RecyclerView mListView;
    private LinearLayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tab,container,false);
        mListView = (RecyclerView) view
                .findViewById(R.id.id_stickynavlayout_innerscrollview);
        getInfo();
        return view;
    }
//    public  View getView(){
//        int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
//        View childAt = mListView.getChildAt(firstVisibleItemPosition);
//        return childAt;
//    }
    private void getInfo() {
        //第一次加载的时候

        RetrofitHelper.builder().getLatestServices().getLatestinfo("12","0","android","9709")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ZuixinBean>() {
                    @Override
                    public void call(ZuixinBean zuixinBean) {
                        LogUtil.e(zuixinBean.getThis_page_num()+"+======");
                        if (zuixinBean.getList() == null) {
                            LogUtil.e("加载失败这是最新界面");
                        } else {
                            LogUtil.e("加载成功");
                            if(zuixinBean.getNext().equals("True")){
                                //还有下一页
                                next_page = zuixinBean.getNextpage();
                            }

                            Latestlist= zuixinBean.getList();
                            manager = new LinearLayoutManager(getContext());
                            mListView.setLayoutManager(manager);
                            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                            View childAt = mListView.getChildAt(firstVisibleItemPosition);
                            autoLoadOnScrollListener = new AutoLoadOnScrollListener(manager) {
                                @Override
                                public void onLoadMore(int currentPage) {
                                    loadmore();
                                }
                            };
                            mListView.addOnScrollListener(autoLoadOnScrollListener);
                            adapter = new RecycleLatestAdapter(getContext(), Latestlist);
                            mListView.setAdapter(adapter);

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtil.e("最新页面加载失败");
                    }
                });

    }

    private void loadmore() {
        RetrofitHelper.builder().getLatestServices().getLatestinfo("12",next_page,"android","9709")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ZuixinBean>() {
                    @Override
                    public void call(ZuixinBean zuixinBean) {
                        if (zuixinBean.getList() == null) {
                            LogUtil.e("请求跟多失败");
                        } else {
                            if(zuixinBean.getNext().equals("True")){
                                next_page=zuixinBean.getNextpage();
                            }else {
                                LogUtil.e("没有更多信息了");
                            }

                            List<ZuixinBean.ListBean> list = zuixinBean.getList();
                            autoLoadOnScrollListener.setLoading(false);
                            adapter.addData(list);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtil.e("请求更多失败");
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
