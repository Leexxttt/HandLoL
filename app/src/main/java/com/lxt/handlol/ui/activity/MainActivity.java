package com.lxt.handlol.ui.activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.ui.fragment.FoundFragment;
import com.lxt.handlol.ui.fragment.FriendsFragment;
import com.lxt.handlol.ui.fragment.MyFragment;
import com.lxt.handlol.ui.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager mViewpager;
    private SampleFragmentPagerAdapter mAdapter;
    private TabLayout mTablayout;
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉标题, 必须在setContentView之前执行
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initDrawerLayout();
        initViewPager();
        initView();
       StatusBar();
    }

    private void initDrawerLayout() {
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Resources resource=getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(R.color.white);
        navigationView.setItemTextColor(csl);
        navigationView.setItemBackgroundResource(R.drawable.menu_item_selector);

    }


    public void closeLeftMenu(){
        drawerlayout.openDrawer(Gravity.LEFT);
    }

    private void StatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
    private void initViewPager() {
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        fragmentList.add(NewsFragment.getInstance());
        fragmentList.add(FriendsFragment.getInstance());
        fragmentList.add(FoundFragment.getInstance());
        fragmentList.add(MyFragment.getInstance());
        mAdapter=new SampleFragmentPagerAdapter(getSupportFragmentManager(),this);
        mViewpager.setAdapter(mAdapter);
    }

    public void initView() {
        mTablayout.setupWithViewPager(mViewpager);
        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mAdapter.getTabView(i));
            }
        }
        mTablayout.getTabAt(0).getCustomView().setSelected(true);
        View view = mTablayout.getTabAt(0).getCustomView();
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.news_title);
        img.setImageResource(R.drawable.tab_news_checked);
        textView.setTextColor(getResources().getColor(R.color.btn_pressed_color));
        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabNormal(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    /**
     * 当tab没有被选中的时候
     *
     * @param tab
     */
    private void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.imageView);
        TextView txt_title = (TextView) view.findViewById(R.id.news_title);
        txt_title.setTextColor(getResources().getColor(R.color.text_normal));
        if (txt_title.getText().toString().equals("资讯")) {
            img_title.setImageResource(R.drawable.tab_news);
        } else if (txt_title.getText().toString().equals("好友")) {
            img_title.setImageResource(R.drawable.tab_friend);
        } else if (txt_title.getText().toString().equals("发现")) {
            img_title.setImageResource(R.drawable.tab_discovery);
        } else {
            img_title.setImageResource(R.drawable.tab_me);
        }
    }

    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.imageView);
        TextView txt_title = (TextView) view.findViewById(R.id.news_title);
        txt_title.setTextColor(getResources().getColor(R.color.btn_pressed_color));
        if (txt_title.getText().toString().equals("资讯")) {
            img_title.setImageResource(R.drawable.tab_news_checked);
            mViewpager.setCurrentItem(0);
        } else if (txt_title.getText().toString().equals("好友")) {
            img_title.setImageResource(R.drawable.tab_friend_checked);
            mViewpager.setCurrentItem(1);
        } else if (txt_title.getText().toString().equals("发现")) {
            img_title.setImageResource(R.drawable.tab_discovery_checked);
            mViewpager.setCurrentItem(2);
        } else {
            img_title.setImageResource(R.drawable.tab_me_checked);
            mViewpager.setCurrentItem(3);
        }
    }


    /**
     * viewpager的adapter
     */

    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;
        private String tabTitles[] = new String[]{"资讯", "好友", "发现", "我"};
        private int imageResId[] = new int[]{
                R.drawable.selector_tab_news,
                R.drawable.tab_friend,
                R.drawable.tab_discovery,
                R.drawable.tab_me
        };
        private Context ctx;

        public SampleFragmentPagerAdapter(FragmentManager fm, Context ctx) {
            super(fm);
            this.ctx = ctx;
        }

        public View getTabView(int position) {
            View v = LayoutInflater.from(ctx).inflate(R.layout.customer_tab, null);
            TextView tv = (TextView) v.findViewById(R.id.news_title);
            tv.setText(tabTitles[position]);
            ImageView img = (ImageView) v.findViewById(R.id.imageView);
            img.setImageResource(imageResId[position]);
            return v;
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
