package com.lxt.handlol.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lxt.handlol.R;
import com.lxt.handlol.module.my.ZhanjiBean;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.CircleImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/28 0028.
 * e-mail:871281347@qq.com
 */

public class ZhanjiAdapter extends RecyclerView.Adapter {
    private static final int SUM_ZHANJI = 0;
    private static final int MY_ZHANJI = 1;
    private static final int DATA_ZHANJI = 2;
    private static final int COMMON_DATA = 3;
    List<ZhanjiBean.ListBean> list;

    private Context context;
    private int pos;

    public ZhanjiAdapter(Context context, List<ZhanjiBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == SUM_ZHANJI) {
            return new SumViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sum, parent, false));
        } else if (viewType == MY_ZHANJI) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my, parent, false));
        } else if (viewType == DATA_ZHANJI) {
            return new DataViewHolder(LayoutInflater.from(context).inflate(R.layout.item_data, parent, false));
        } else {
            return new CommonViewHolder(LayoutInflater.from(context).inflate(R.layout.item_common, parent, false));
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position>=list.size()){
            return;
        }
        ZhanjiBean.ListBean listBean = list.get(position);
        if (listBean == null) {
            return;
        }
        if (holder instanceof DataViewHolder) {
            DataViewHolder dataViewHolder= (DataViewHolder) holder;
            Glide.with(context).load(listBean.getHreoimg()).into(dataViewHolder.mHeroPhoto);
            dataViewHolder.mMode.setText(listBean.getType());
            dataViewHolder.mData.setText(listBean.getData());
            if (listBean.getIswithFriend().equals("true")) {
                dataViewHolder.mIsWithFriend.setVisibility(View.VISIBLE);
            }else {
                dataViewHolder.mIsWithFriend.setVisibility(View.INVISIBLE);
            }
            if (listBean.getIsVictory().equals("true")) {
                dataViewHolder.mIconWin.setImageResource(R.mipmap.battle_win_color);
                dataViewHolder.mZhanjiData.setText(listBean.getZhangji());
            }

        }
        if (holder instanceof CommonViewHolder) {
            CommonViewHolder holder1 = (CommonViewHolder) holder;
            Glide.with(context).load(listBean.getHreoimg()).into(holder1.mHeroPhoto);
            holder1.mModle.setText(listBean.getType());
            if (listBean.getIswithFriend().equals("true")) {
                holder1.mIsWithFriend.setVisibility(View.VISIBLE);
            }else {
                holder1.mIsWithFriend.setVisibility(View.INVISIBLE);
            }
            if (listBean.getIsVictory().equals("true")) {
                holder1.mIconWin.setImageResource(R.mipmap.battle_win_color);
                holder1.mDataZhanji.setText(listBean.getZhangji());
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size()+2;
    }

    @Override
    public int getItemViewType(int position) {
        LogUtil.e("这是posetion"+position);
        if (position == 0) {
            //第一条肯定是综合战绩
            return SUM_ZHANJI;
        }
        if (position == 1) {
            //这里显示我的战绩这一标题
            return MY_ZHANJI;
        }
        int index=position-2;
        if(index==7||index==6){
            index=position;
        }
        LogUtil.e("这是index"+index);
         if(list.get(index).getIsDatawith().equals("true")){
            pos=DATA_ZHANJI;
        }  else{
            pos = COMMON_DATA;
        }
        return pos;
    }

    public class SumViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.shenlv)
        TextView mShenlv;
        @Bind(R.id.sum_game)
        TextView mSumGame;
        @Bind(R.id.text)
        RelativeLayout mText;
        @Bind(R.id.icon)
        LinearLayout mIcon;

        public SumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.chose_which)
        ImageView mChoseWhich;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.data)
        TextView mData;
        @Bind(R.id.icon_win)
        ImageView mIconWin;
        @Bind(R.id.hero_photo)
        CircleImageView mHeroPhoto;
        @Bind(R.id.is_win)
        TextView mIsWin;
        @Bind(R.id.mode)
        TextView mMode;
        @Bind(R.id.win_modle)
        LinearLayout mWinModle;
        @Bind(R.id.zhanji_data)
        TextView mZhanjiData;
        @Bind(R.id.is_with_friend)
        ImageView mIsWithFriend;
        @Bind(R.id.arrow)
        ImageView mArrow;

        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.icon_win)
        ImageView mIconWin;
        @Bind(R.id.hero_photo)
        CircleImageView mHeroPhoto;
        @Bind(R.id.is_win)
        TextView mIsWin;
        @Bind(R.id.modle)
        TextView mModle;
        @Bind(R.id.win_modle)
        LinearLayout mWinModle;
        @Bind(R.id.data_zhanji)
        TextView mDataZhanji;
        @Bind(R.id.is_with_friend)
        ImageView mIsWithFriend;
        @Bind(R.id.arrow)
        ImageView mArrow;

        public CommonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
