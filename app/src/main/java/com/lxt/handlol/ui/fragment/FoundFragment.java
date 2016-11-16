package com.lxt.handlol.ui.fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.adapter.ExpandableListView.GameExpandAdapter;
import com.lxt.handlol.adapter.ExpandableListView.GameFriendAdapter;
import com.lxt.handlol.adapter.ExpandableListView.ShequExpandAdapter;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public class FoundFragment extends BaseFragment {
    public static FoundFragment instance;
    @Bind(R.id.touxiang)
    CircleImageView mTouxiang;
    @Bind(R.id.toolbar_found)
    Toolbar mToolbar;
    @Bind(R.id.id_gallery)
    LinearLayout mIdGallery;
    @Bind(R.id.expanded_list)
    ExpandableListView mExpandedList;
    @Bind(R.id.expanded_list_shequ)
    ExpandableListView mExpandedListShequ;
    private int[] mImgIds;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_found;
    }

    @Override
    public void initView() {
        initToolBar();
        initHorizontalScrollView();
        initExpndListView();
    }

    private void initToolBar() {
      //  mToolbar.getBackground();
//       mToolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
        mToolbar.setBackgroundResource(R.drawable.header_bg);
        AppCompatActivity activity= (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
    }

    private void initExpndListView() {
        //将箭头的位置设置在右边
        Display newDisplay = getActivity().getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        mExpandedList.setIndicatorBounds(width - 100, width);
        mExpandedListShequ.setIndicatorBounds(width - 100, width);
        GameFriendAdapter adapter = new GameFriendAdapter(getActivity());
        mExpandedList.setAdapter(adapter);
        ShequExpandAdapter adapter1=new ShequExpandAdapter(getActivity());
        mExpandedListShequ.setAdapter(adapter1);
    }

    private void initHorizontalScrollView() {
        //初始化HorizontalScrollView
        mImgIds = new int[]{R.mipmap.nearbydefaulthead,
                R.mipmap.nearbydefaulthead,
                R.mipmap.nearbydefaulthead,
                R.mipmap.nearbydefaulthead,
                R.mipmap.nearbydefaulthead,
                R.mipmap.nearbydefaulthead};
        for (int i = 0; i < mImgIds.length; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_horizontalscro, mIdGallery, false);
            ImageView clubphoto = (ImageView) view.findViewById(R.id.club_img);
            TextView clubname = (TextView) view.findViewById(R.id.club_name);
            clubphoto.setImageResource(mImgIds[i]);
            mIdGallery.addView(view);
        }

    }

    private FoundFragment() {
    }

    public static FoundFragment getInstance() {
        instance = new FoundFragment();
        return instance;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
