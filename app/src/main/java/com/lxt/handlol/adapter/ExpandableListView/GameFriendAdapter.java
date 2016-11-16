package com.lxt.handlol.adapter.ExpandableListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.lxt.handlol.R;

/**
 * Created by ${reallyCommon} on 2016/10/10 0010.
 * e-mail:871281347@qq.com
 * GameFriendAdapter
 */

public class GameFriendAdapter extends BaseExpandableListAdapter {
    private Context context;

    public GameFriendAdapter(Context context){
        this.context=context;
    }


    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int position) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
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
            convertView = layoutInflater.inflate(R.layout.game_ziliao, null);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        //子列表控件通过界面文件设计
        if(convertView ==null){//convert在运行中会重用，如果不为空，则表明不用重新获取
            LayoutInflater layoutInflater;//使用这个来载入界面
            layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.game_ziliao_child, null);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
