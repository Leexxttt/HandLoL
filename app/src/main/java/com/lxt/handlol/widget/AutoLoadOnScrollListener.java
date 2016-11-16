package com.lxt.handlol.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lxt.handlol.utils.LogUtil;

/**
 * Created by ${reallyCommon} on 2016/9/17 0017.
 * e-mail:871281347@qq.com
 * 这是用来监听recycleView 的滑动监听事件
 */
public abstract class AutoLoadOnScrollListener extends RecyclerView.OnScrollListener{
    private LinearLayoutManager mManager;
    private int mItemCount;
    private int mLastVisibleItemPosition;
    private boolean loading = false;
    private int currentPage = 1;
    public AutoLoadOnScrollListener(LinearLayoutManager linearLayoutManager){
       mManager=linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mItemCount = mManager.getItemCount();
        mLastVisibleItemPosition = mManager.findLastVisibleItemPosition();

        if(!loading&&mLastVisibleItemPosition>mItemCount-2&&dy>0){
            //需要加载更多
            currentPage++;
            //告诉现在要加载第几页
            onLoadMore(currentPage);
            loading=true;
        }

    }
    public abstract void onLoadMore(int currentPage);

    public boolean isLoding(){
        return loading;
    }
    public void setLoading(boolean loading){
        this.loading=loading;
    }
}
