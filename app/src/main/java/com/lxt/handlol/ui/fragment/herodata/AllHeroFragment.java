package com.lxt.handlol.ui.fragment.herodata;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lxt.handlol.R;
import com.lxt.handlol.adapter.filter.AllHeroAdapter;
import com.lxt.handlol.adapter.filter.ConstellationAdapter;
import com.lxt.handlol.adapter.filter.ListDropDownAdapter;
import com.lxt.handlol.base.BaseFragment;
import com.lxt.handlol.module.hero.AllHero;
import com.lxt.handlol.net.RetrofitHelper;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.widget.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.lxt.handlol.R.id.constellation;

/**
 * Created by ${reallyCommon} on 2016/11/4 0004.
 * e-mail:871281347@qq.com
 */

public class AllHeroFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"英雄类型", "位置", "价格", "排序"};
    private List<View> popupViews = new ArrayList<>();
    private ConstellationAdapter locationAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;
    private String citys[] = {"全部类型", "新手", "战士", "法师", "刺客", "坦克", "辅助", "射手"};
    private String location[] = {"全部", "上单", "中单", "ADC", "辅助", "打野"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"默认", "物攻", "法伤", "防御", "操作"};
    private List<AllHero.ListBean> list = new ArrayList<>();
    private int constellationPosition = 0;
    private GridView gridView;
    private AllHeroAdapter adapter;
    List<Button> btnlist = new ArrayList<>();
    private Button click1;
    private Button click2;
    private Button click0;
    private Button click3;
    private Button click4;
    private Button click5;
    private Button click6;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all_hero;
    }

    @Override
    public void initView() {
        //init herotype menu
        View herotypeview = getActivity().getLayoutInflater().inflate(R.layout.hero_type, null);
        click1 = (Button) herotypeview.findViewById(R.id.zhanshi);
        click2 = (Button) herotypeview.findViewById(R.id.fashi);
        click0 = (Button) herotypeview.findViewById(R.id.all_type);
        click3 = (Button) herotypeview.findViewById(R.id.cike);
        click4 = (Button) herotypeview.findViewById(R.id.tank);
        click5 = (Button) herotypeview.findViewById(R.id.fuzhu);
        click6 = (Button) herotypeview.findViewById(R.id.sheshou);
        click0.setBackgroundResource(R.drawable.back_btn_press);
        click0.setTextColor(Color.WHITE);
        click1.setOnClickListener(this);
        click2.setOnClickListener(this);
        click0.setOnClickListener(this);
        click3.setOnClickListener(this);
        click4.setOnClickListener(this);
        click5.setOnClickListener(this);
        click6.setOnClickListener(this);
        btnlist.add(click0);
        btnlist.add(click1);
        btnlist.add(click2);
        btnlist.add(click3);
        btnlist.add(click4);
        btnlist.add(click5);
        btnlist.add(click6);
        //init age menu
        View locationview = getActivity().getLayoutInflater().inflate(R.layout.location_type, null);
        //init sex menu
        View priceview=getActivity().getLayoutInflater().inflate(R.layout.price_type,null);

        //init constellation
        View paixuview=getActivity().getLayoutInflater().inflate(R.layout.paixu_type,null);
        //init popupViews
        popupViews.add(herotypeview);
        popupViews.add(locationview);
        popupViews.add(priceview);
        popupViews.add(paixuview);
        RetrofitHelper.builder().getheroInfo().getAllHeroInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<AllHero>() {
                    @Override
                    public void call(AllHero allHero) {
                        if (allHero.getList() == null) {
                            LogUtil.e("加载英雄信息失败1");
                        } else {
                            LogUtil.e("这是英雄的数量" + allHero.getList().size());
                            list = allHero.getList();
                            adapter = new AllHeroAdapter(getContext(), list);
                            gridView = new GridView(getContext());
                            gridView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            gridView.setGravity(Gravity.CENTER);
                            gridView.setNumColumns(2);
                            gridView.setAdapter(adapter);
                            mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, gridView);
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtil.e("加载英雄信息失败2" + throwable.getMessage());
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_type:
               setBackound(0);
                gridView.setAdapter(adapter);
                mDropDownMenu.setTabText("英雄类型");
                mDropDownMenu.closeMenu();
                break;
            case R.id.zhanshi:
                setBackound(1);
                List<AllHero.ListBean> zhanshilist = new ArrayList<>();
                setNewAdapter("战士",zhanshilist);
                break;
            case R.id.fashi:
                setBackound(2);
                List<AllHero.ListBean> fashilist = new ArrayList<>();
                setNewAdapter("法师",fashilist);
                break;
            case R.id.cike:
                setBackound(3);
                List<AllHero.ListBean>cikeilist = new ArrayList<>();
                setNewAdapter("刺客",cikeilist);
                break;
            case R.id.tank:
                setBackound(4);
                List<AllHero.ListBean>tankilist = new ArrayList<>();
                setNewAdapter("坦克",tankilist);
                break;
            case R.id.fuzhu:
                setBackound(5);
                List<AllHero.ListBean>fuzhuilist = new ArrayList<>();
                setNewAdapter("辅助",fuzhuilist);
                break;
            case R.id.sheshou:
                setBackound(6);
                List<AllHero.ListBean>sheshoulist = new ArrayList<>();
                setNewAdapter("射手",sheshoulist);
                break;
        }
    }

    private void setBackound(int position) {
        for (int i = 0; i < btnlist.size(); i++) {
            if (i == position) {
                btnlist.get(i).setBackgroundResource(R.drawable.back_btn_press);
                btnlist.get(i).setTextColor(getResources().getColor(R.color.white));
            } else {
                btnlist.get(i).setBackgroundResource(R.drawable.back_btn);
                btnlist.get(i).setTextColor(getResources().getColor(R.color.gray));
            }
        }
    }

    private void setNewAdapter(String name,List<AllHero.ListBean> listBeen){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTag1().equals(name)) {
                listBeen.add(list.get(i));
            }
        }
        AllHeroAdapter adapter2 = new AllHeroAdapter(getContext(), listBeen);
        gridView.setAdapter(adapter2);
        mDropDownMenu.setTabText(name);
        mDropDownMenu.closeMenu();
    }

}
