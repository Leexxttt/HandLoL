package com.lxt.handlol.utils;

import com.lxt.handlol.module.filter.FilterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/11/14 0014.
 * e-mail:871281347@qq.com
 */

public class ModelUtil {


    // 分类数据
    public static List<FilterEntity> getCategoryData() {
        List<FilterEntity> list = new ArrayList<>();
        list.add(new FilterEntity("英雄1", "1"));
        list.add(new FilterEntity("英雄2", "2"));
        list.add(new FilterEntity("英雄3", "3"));
        list.add(new FilterEntity("英雄4", "4"));
        return list;
    }

    // hero数据
    public static List<FilterEntity> getHeroInfoData() {
        List<FilterEntity> list = new ArrayList<>();
        list.add(new FilterEntity("英雄1", "1"));
        list.add(new FilterEntity("英雄2", "2"));
        list.add(new FilterEntity("英雄3", "3"));
        list.add(new FilterEntity("英雄4", "4"));
        return list;
    }
    // 排序数据
    public static List<FilterEntity> getSortData() {
        List<FilterEntity> list = new ArrayList<>();
        list.add(new FilterEntity("排序从高到低", "1"));
        list.add(new FilterEntity("排序从低到高", "2"));
        return list;
    }

    // 筛选数据
    public static List<FilterEntity> getFilterData() {
        List<FilterEntity> list = new ArrayList<>();
        list.add(new FilterEntity("中国", "1"));
        list.add(new FilterEntity("美国", "2"));
        list.add(new FilterEntity("英国", "3"));
        list.add(new FilterEntity("德国", "4"));
        list.add(new FilterEntity("西班牙", "5"));
        list.add(new FilterEntity("意大利", "6"));
        return list;
    }
}
