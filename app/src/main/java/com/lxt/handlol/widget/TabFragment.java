package com.lxt.handlol.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lxt.handlol.R;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment {
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private RecyclerView mListView;
    // private TextView mTextView;
    private List<String> mDatas = new ArrayList<String>();
    private LayoutInflater mInflater;
    private int pageNum = -1;
    LinearLayoutManager manager = new LinearLayoutManager(getContext());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mListView = (RecyclerView) view
                .findViewById(R.id.id_stickynavlayout_innerscrollview);
        initPage();
        return view;

    }
    private void initPage() {
                mTitle = "GridView的上拉加载";
                for (int i = 0; i <= 100; i++) {
                    mDatas.add("zhjjsj" + i);
                }
                mListView.setLayoutManager(manager);
                mListView.setAdapter(new RecyclerView.Adapter() {
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        mInflater = LayoutInflater.from(getContext());
                        View view = mInflater.inflate(R.layout.test_viewholder,
                                parent, false);
                        SmallViewHolder viewHolder = new SmallViewHolder(view);
                        viewHolder.text = (TextView) view.findViewById(R.id.text);
                        return viewHolder;
                    }

                    @Override
                    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                        SmallViewHolder holder1 = (SmallViewHolder) holder;
                        holder1.text.setText("xinxi" + position);
                    }

                    @Override
                    public int getItemCount() {
                        return mDatas.size();
                    }
                });
    }

    class SmallViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public SmallViewHolder(View itemView) {
            super(itemView);
        }
    }

}
