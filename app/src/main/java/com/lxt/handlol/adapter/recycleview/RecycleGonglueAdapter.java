package com.lxt.handlol.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lxt.handlol.R;
import com.lxt.handlol.module.gonglue.GonglueBean;
import com.lxt.handlol.module.huodong.MainContent;
import com.lxt.handlol.ui.activity.ArticleDetatileActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/11 0011.
 * e-mail:871281347@qq.com
 */

public class RecycleGonglueAdapter extends RecyclerView.Adapter<RecycleGonglueAdapter.ItemContentViewHolder> {

    private Context context;
    private List<GonglueBean.ListBean> listBeen;

    public RecycleGonglueAdapter(Context context, List<GonglueBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public boolean onFailedToRecycleView(ItemContentViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ItemContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemContentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_gonglue, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemContentViewHolder holder, int position) {
        final GonglueBean.ListBean bean = listBeen.get(position);
        ItemContentViewHolder itemContentViewHolder= (ItemContentViewHolder) holder;
        Glide.with(context).load(bean.getImage_url_small())
                .placeholder(R.mipmap.mastery_default_l)
                .into(itemContentViewHolder.mHuodongimg);
        itemContentViewHolder.mHuodongTitle.setText(bean.getTitle());
        itemContentViewHolder.mSamllTitle.setText(bean.getSummary());
        itemContentViewHolder.mData.setText(bean.getPublication_date());
        final String article_url = bean.getArticle_url();
        boolean isVideo=article_url.contains("iVideoId");
        if(isVideo){
            //这是视屏
            itemContentViewHolder.mIshuodong.setVisibility(View.VISIBLE);
        }else{
            itemContentViewHolder.mIshuodong.setVisibility(View.INVISIBLE);
        }
        itemContentViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(article_url.contains("http://")){
                        ArticleDetatileActivity.lanuch(context,"http://qt.qq.com/static/pages/news/phone/"+article_url);
                    }else {
                        ArticleDetatileActivity.lanuch(context,article_url);
                    }
            }
        });

    }


    @Override
    public int getItemCount() {
        return listBeen.size() ;
    }

    public static class ItemContentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.huodongimg)
        ImageView mHuodongimg;
        @Bind(R.id.huodongTitle)
        TextView mHuodongTitle;
        @Bind(R.id.samllTitle)
        TextView mSamllTitle;
        @Bind(R.id.data)
        TextView mData;
        @Bind(R.id.ishuodong)
        LinearLayout mIshuodong;
        @Bind(R.id.huodong_pager)
        CardView cardView;
        public ItemContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public void updateData(List<GonglueBean.ListBean> listBeen){
        this.listBeen=listBeen;
        notifyDataSetChanged();
    }
    public void addData(List<GonglueBean.ListBean> listBeen)
    {

        if (this.listBeen == null)
        {
            updateData(listBeen);
        } else
        {
            this.listBeen.addAll(listBeen);
            notifyDataSetChanged();
        }
    }
}
