package com.lxt.handlol.adapter.filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lxt.handlol.R;
import com.lxt.handlol.module.hero.AllHero;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/11/17 0017.
 * e-mail:871281347@qq.com
 */

public class MyHeroAdapter extends RecyclerView.Adapter<MyHeroAdapter.MyHeroHolder> {


    private Context context;
    private List<AllHero.ListBean> list;

    public MyHeroAdapter(Context context, List<AllHero.ListBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public MyHeroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHeroHolder(LayoutInflater.from(context).inflate(R.layout.item_myhero, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHeroHolder holder, int position) {
        AllHero.ListBean listBean = list.get(position);
        Glide.with(context)
                .load("http://183.230.77.160/dlied1.qq.com/qqtalk/lolApp/img/champion/"+list.get(position).getEn_name()+".png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.default_lol2)
                .into(holder.mHeroPhoto);
        holder.mHeroName.setText(listBean.getName());
        holder.mHeroNickName.setText(listBean.getNick());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyHeroHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.hero_photo)
        ImageView mHeroPhoto;
        @Bind(R.id.hero_nick_name)
        TextView mHeroNickName;
        @Bind(R.id.hero_name)
        TextView mHeroName;
        @Bind(R.id.count)
        TextView mCount;
        @Bind(R.id.winrate)
        TextView mWinrate;
        public MyHeroHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
