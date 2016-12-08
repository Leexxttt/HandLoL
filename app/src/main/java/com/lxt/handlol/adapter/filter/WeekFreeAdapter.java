package com.lxt.handlol.adapter.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lxt.handlol.R;
import com.lxt.handlol.module.filter.WeekFree;
import com.lxt.handlol.module.hero.AllHero;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/11/15 0015.
 * e-mail:871281347@qq.com
 */

public class WeekFreeAdapter extends BaseAdapter {

    private Context context;
    private List<WeekFree.ListBean> list;

    public WeekFreeAdapter(Context context, List<WeekFree.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_grid, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.mHeroName.setText(list.get(position).getNick());
        holder.mHeroRealName.setText(list.get(position).getName());
        holder.mTag1.setText(list.get(position).getTag1());
        holder.mTag2.setText(list.get(position).getTag2());
        Glide.with(context).load
                ("http://183.230.77.160/dlied1.qq.com/qqtalk/lolApp/img/champion/"+list.get(position).getEn_name()+".png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.default_lol2)
                .into(holder.mHeroPhoto);
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.hero_name)
        TextView mHeroName;
        @Bind(R.id.hero_real_name)
        TextView mHeroRealName;
        @Bind(R.id.tag_1)
        TextView mTag1;
        @Bind(R.id.tag_2)
        TextView mTag2;
        @Bind(R.id.hero_photo)
        ImageView mHeroPhoto;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
