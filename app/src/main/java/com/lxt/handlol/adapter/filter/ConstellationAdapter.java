package com.lxt.handlol.adapter.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lxt.handlol.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/11/14 0014.
 * e-mail:871281347@qq.com
 */

public class ConstellationAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private int checkItemPosition = 0;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public ConstellationAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_constellation_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        fillValue(position, viewHolder);
        return convertView;
    }
    private void fillValue(int position, ViewHolder viewHolder) {
        viewHolder.button.setText(list.get(position));
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                viewHolder.button.setTextColor(context.getResources().getColor(R.color.btn_pressed_color));
                viewHolder.button.setBackgroundResource(R.drawable.back_btn_press);
            } else {
                viewHolder.button.setTextColor(context.getResources().getColor(R.color.white));
                viewHolder.button.setBackgroundResource(R.drawable.back_btn);
            }
        }
    }

    class ViewHolder {
        @Bind(R.id.text)
        Button button;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
