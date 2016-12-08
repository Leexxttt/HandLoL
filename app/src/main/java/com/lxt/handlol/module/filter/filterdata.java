package com.lxt.handlol.module.filter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/11/14 0014.
 * e-mail:871281347@qq.com
 */

public class filterdata implements Serializable {
    private List<FilterEntity> category;
    private List<FilterEntity> sorts;
    private List<FilterEntity> filters;
    private List<FilterEntity> heroinfo;

    public List<FilterEntity> getHeroinfo() {
        return heroinfo;
    }

    public void setHeroinfo(List<FilterEntity> heroinfo) {
        this.heroinfo = heroinfo;
    }
    public List<FilterEntity> getCategory() {
        return category;
    }

    public void setCategory(List<FilterEntity> category) {
        this.category = category;
    }

    public List<FilterEntity> getSorts() {
        return sorts;
    }

    public void setSorts(List<FilterEntity> sorts) {
        this.sorts = sorts;
    }

    public List<FilterEntity> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterEntity> filters) {
        this.filters = filters;
    }


}
