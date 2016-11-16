package com.lxt.handlol.module.yule;

import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/10/12 0012.
 * e-mail:871281347@qq.com
 */

public class YuleBean {

    /**
     * next : c18_list_2.shtml
     * this_page_num : 20
     * list : []
     */

    private String next;
    private String this_page_num;
    private List<ChildBean> list;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getThis_page_num() {
        return this_page_num;
    }

    public void setThis_page_num(String this_page_num) {
        this.this_page_num = this_page_num;
    }

    public List<ChildBean> getList() {
        return list;
    }

    public void setList(List<ChildBean> list) {
        this.list = list;
    }
}
