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
import com.lxt.handlol.module.huodong.MainContent;
import com.lxt.handlol.ui.activity.ArticleDetatileActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/11 0011.
 * e-mail:871281347@qq.com
 */

public class RecycleHuodongAdapter extends RecyclerView.Adapter<RecycleHuodongAdapter.ItemContentViewHolder> {

    private Context context;
    private List<MainContent.ListBean> listBeen;

    public RecycleHuodongAdapter(Context context, List<MainContent.ListBean> listBeen) {
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
        return new ItemContentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_huodong, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemContentViewHolder holder, int position) {
        final MainContent.ListBean bean = listBeen.get(position);
        ItemContentViewHolder itemContentViewHolder= (ItemContentViewHolder) holder;
        Glide.with(context).load(bean.getImage_url_small())
                .placeholder(R.mipmap.mastery_default_l)
                .into(itemContentViewHolder.mHuodongimg);
        itemContentViewHolder.mHuodongTitle.setText(bean.getTitle());
        itemContentViewHolder.mSamllTitle.setText(bean.getSummary());
        itemContentViewHolder.mData.setText(bean.getPublication_date());
        String is_direct = bean.getIs_direct();
        if(is_direct.equals("True")){
            itemContentViewHolder.mIshuodong.setVisibility(View.VISIBLE);
        }else{
            itemContentViewHolder.mIshuodong.setVisibility(View.INVISIBLE);
        }
        itemContentViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情界面
                ArticleDetatileActivity.lanuch(context,bean.getArticle_url());
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
    public void updateData(List<MainContent.ListBean> listBeen){
        this.listBeen=listBeen;
        notifyDataSetChanged();
    }
    public void addData(List<MainContent.ListBean> listBeen)
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
