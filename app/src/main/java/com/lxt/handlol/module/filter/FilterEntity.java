package com.lxt.handlol.module.filter;

import java.io.Serializable;

/**
 * Created by ${reallyCommon} on 2016/11/14 0014.
 * e-mail:871281347@qq.com
 */

public class FilterEntity implements Serializable {
    private String key;
    private String value;
    private boolean isSelected;

    public FilterEntity() {
    }

    public FilterEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
