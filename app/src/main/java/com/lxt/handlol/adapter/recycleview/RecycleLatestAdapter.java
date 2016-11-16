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
import com.lxt.handlol.module.latest.ZuixinBean;
import com.lxt.handlol.ui.activity.ArticleDetatileActivity;
import com.lxt.handlol.ui.activity.TuJiViewpagerActivity;
import com.lxt.handlol.utils.LogUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/11 0011.
 * e-mail:871281347@qq.com
 */

public class RecycleLatestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ZuixinBean.ListBean> listBeen;
    private boolean isVideo = false;
    private boolean isTuji=false;
    private static final int CONTENT = 0;

    private static final int TUJI = 1;
    private int state = 0;
    private ZuixinBean.ListBean bean;

    public RecycleLatestAdapter(Context context, List<ZuixinBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public int getItemViewType(int position) {
        String newstype = listBeen.get(position).getNewstype();
        if (newstype.equals("图集")) {
            isTuji=true;
            return TUJI;
        } else {
            isTuji=false;
            return CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CONTENT) {

            return new ItemContentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_yule, parent, false));
        } else {
            return new TujiViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tuji, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //展示图集的holder
        bean = listBeen.get(position);
        if(isTuji){
            TujiViewHolder tujiViewHolder = (TujiViewHolder) holder;
            tujiViewHolder.mTime.setText(bean.getPublication_date());
            tujiViewHolder.mTujiTitle.setText(bean.getTitle());
            tujiViewHolder.mTujiTitlesmall.setText(bean.getSummary());
            Glide.with(context).load(bean.getBig_image_url())
                    .placeholder(R.mipmap.mastery_default_l)
                    .into(tujiViewHolder.mTujiBig);
            Glide.with(context).load(bean.getSmall_image_url())
                    .placeholder(R.mipmap.mastery_default_l)
                    .into(tujiViewHolder.mImgTop);
            Glide.with(context).load(bean.getCount_image_url())
                    .placeholder(R.mipmap.mastery_default_l)
                    .into(tujiViewHolder.mPicBot);
            tujiViewHolder.mNumPic.setText( bean.getCount());
            final String artid=bean.getArticle_id();
            tujiViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e("点击了图集"+bean.getArticle_id());
                    //将文章的id传过去即可
                    TuJiViewpagerActivity.lanuch(context,artid);

                }
            });
        } else {
            showNoTuji(holder);
        }
    }

    private void showNoTuji(RecyclerView.ViewHolder holder) {
        ItemContentViewHolder itemContentViewHolder= (ItemContentViewHolder) holder;
        Glide.with(context).load(bean.getImage_url_small())
                .placeholder(R.mipmap.mastery_default_l)
                .into(itemContentViewHolder.mHuodongimg);
        itemContentViewHolder.mHuodongTitle.setText(bean.getTitle());
        itemContentViewHolder.mSamllTitle.setText(bean.getSummary());
        itemContentViewHolder.mData.setText(bean.getPublication_date());
        final String arturl=bean.getArticle_url();
        LogUtil.e("这是信息的内容"+bean.getNewstype()+bean.getArticle_url());
        switch (bean.getNewstype()) {
            case "专题":
                state = 1;
                itemContentViewHolder.mZhuanti.setVisibility(View.VISIBLE);
                itemContentViewHolder.mIshuodong.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mClub.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mZhanbao.setVisibility(View.INVISIBLE);
                break;
            case "视频":
                state = 2;
                itemContentViewHolder.mZhuanti.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mIshuodong.setVisibility(View.VISIBLE);
                itemContentViewHolder.mClub.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mZhanbao.setVisibility(View.INVISIBLE);
                break;
            case "俱乐部":
                state = 3;
                itemContentViewHolder.mZhuanti.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mIshuodong.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mClub.setVisibility(View.VISIBLE);
                itemContentViewHolder.mZhanbao.setVisibility(View.INVISIBLE);
                break;
            case "战报":
                state = 4;
                itemContentViewHolder.mZhuanti.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mIshuodong.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mClub.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mZhanbao.setVisibility(View.VISIBLE);
                break;
            default:
                itemContentViewHolder.mZhuanti.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mIshuodong.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mClub.setVisibility(View.INVISIBLE);
                itemContentViewHolder.mZhanbao.setVisibility(View.INVISIBLE);
                break;
        }
        itemContentViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情界面
                LogUtil.e(bean.getArticle_url()+"");
                        ArticleDetatileActivity.lanuch(context, arturl);
                }
        });

    }


    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    public  class ItemContentViewHolder extends RecyclerView.ViewHolder {
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

        @Bind(R.id.icon_player)
        ImageView mIconPlayer;
        @Bind(R.id.zhanbao)
        LinearLayout mZhanbao;
        @Bind(R.id.zhuanti)
        LinearLayout mZhuanti;
        @Bind(R.id.club)
        LinearLayout mClub;

        public ItemContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class TujiViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tujicard)
        CardView cardView;
        @Bind(R.id.tujiTitle)
        TextView mTujiTitle;
        @Bind(R.id.tujiTitlesmall)
        TextView mTujiTitlesmall;
        @Bind(R.id.img_top)
        ImageView mImgTop;
        @Bind(R.id.pic_bot)
        ImageView mPicBot;
        @Bind(R.id.num_pic)
        TextView mNumPic;
        @Bind(R.id.tuji_big)
        ImageView mTujiBig;
        @Bind(R.id.time)
        TextView mTime;
        @Bind(R.id.tuji)
        LinearLayout mTuji;
        public TujiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void updateData(List<ZuixinBean.ListBean> listBeen) {
        this.listBeen = listBeen;
        notifyDataSetChanged();
    }

    public void addData(List<ZuixinBean.ListBean> listBeen) {

        if (this.listBeen == null) {
            updateData(listBeen);
        } else {
            this.listBeen.addAll(listBeen);
            notifyDataSetChanged();
        }
    }
}
