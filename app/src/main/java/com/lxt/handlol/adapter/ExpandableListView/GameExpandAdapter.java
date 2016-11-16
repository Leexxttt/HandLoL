package com.lxt.handlol.adapter.ExpandableListView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxt.handlol.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ${reallyCommon} on 2016/10/10 0010.
 * e-mail:871281347@qq.com
 */

public class GameExpandAdapter extends BaseExpandableListAdapter{
    private Context context;
    public String[] groupStrings = {"西游记"};
    public String[][] childStrings = {
            {"大星宇", "闪电侠艾弗森", "放狼的耶稣", "战鹰8","彼岸花雨","Log函数","Sky呆","冰封之心zlp"},

    };

    public GameExpandAdapter(Context context){
        //初始化组、子列表项
        this.context=context;
    }


    @Override
    public int getGroupCount() {
        return groupStrings.length;
    }

    @Override
    public int getChildrenCount(int position) {
        return childStrings[position].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupStrings[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childStrings[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater;//使用这个来载入界面
            layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.gamefriendgroup, null);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder childViewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater;//使用这个来载入界面
            layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.gamefriendchild, parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.gamename = (TextView) convertView.findViewById(R.id.gamename);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.gamename.setText(childStrings[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static  class GroupViewHolder {

    }
    static class ChildViewHolder{
        TextView gamename;
        ImageView gamephoto;
    }
}
