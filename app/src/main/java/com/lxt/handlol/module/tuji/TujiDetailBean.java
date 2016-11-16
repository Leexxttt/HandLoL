package com.lxt.handlol.module.tuji;

import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/10/15 0015.
 * e-mail:871281347@qq.com
 */

public class TujiDetailBean {

    /**
     * list : [{"img_url":"http://ossweb-img.qq.com/upload/qqtalk/news/201610/141613323815799.jpg","img_desc":"暴龙电竞 ","img_title":"八强场馆：绝美壮丽芝加哥剧院"},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}]
     * article_id : 23950
     * title : 八强场馆：绝美壮丽芝加哥剧院
     * targetid : 1577150353
     * summary : 古色古香的剧院里进行最新潮的电竞赛事，两种文化的冲击为观众带来最劲的观赛感受。
     */

    private String article_id;
    private String title;
    private String targetid;
    private String summary;
    /**
     * img_url : http://ossweb-img.qq.com/upload/qqtalk/news/201610/141613323815799.jpg
     * img_desc : 暴龙电竞
     * img_title : 八强场馆：绝美壮丽芝加哥剧院
     */

    private List<ListBean> list;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetid() {
        return targetid;
    }

    public void setTargetid(String targetid) {
        this.targetid = targetid;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String img_url;
        private String img_desc;
        private String img_title;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getImg_desc() {
            return img_desc;
        }

        public void setImg_desc(String img_desc) {
            this.img_desc = img_desc;
        }

        public String getImg_title() {
            return img_title;
        }

        public void setImg_title(String img_title) {
            this.img_title = img_title;
        }
    }
}
