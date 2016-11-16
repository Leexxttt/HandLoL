package com.lxt.handlol.module;

import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 * 轮播所获得的信息数据
 */

public class LunboBean {

    /**
     * next :
     * this_page_num : 5
     * list : []
     */

    private String next;
    private String this_page_num;
    private List<BodyBean> list;

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

    public List<BodyBean> getList() {
        return list;
    }

    public void setList(List<BodyBean> list) {
        this.list = list;
    }
    public static class BodyBean {

        /**
         * channel_id : <2>:<13>,<2>:<169>,<2>:<229>
         * channel_desc : 推荐
         * article_id : 23118
         * content_id : 23118
         * insert_date : 2016-09-21 15:45:37
         * publication_date : 2016-09-30 14:35:16
         * is_direct : False
         * is_top : True
         * article_url : 18/article_23118.shtml
         * title : 2016全球总决赛赛事预告及回顾
         * image_url_small : http://ossweb-img.qq.com/upload/qqtalk/news/201610/031344252866094_282.jpg
         * image_url_big : http://ossweb-img.qq.com/upload/qqtalk/news/201610/031344252866094_480.jpg
         * image_spec : 1
         * image_with_btn : False
         * score : 3
         * summary : 16支全球顶级战队，将为职业赛事最高荣耀发起最后的冲击。
         * targetid : 1548574607
         * is_act : 0
         * is_hot : 0
         * is_subject : 0
         * is_report : True
         * is_new : 0
         */

        private String channel_id;
        private String channel_desc;
        private String article_id;
        private String content_id;
        private String insert_date;
        private String publication_date;
        private String is_direct;
        private String is_top;
        private String article_url;
        private String title;
        private String image_url_small;
        private String image_url_big;
        private String image_spec;
        private String image_with_btn;
        private String score;
        private String summary;
        private String targetid;
        private String is_act;
        private String is_hot;
        private String is_subject;
        private String is_report;
        private String is_new;

        public String getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        public String getChannel_desc() {
            return channel_desc;
        }

        public void setChannel_desc(String channel_desc) {
            this.channel_desc = channel_desc;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getInsert_date() {
            return insert_date;
        }

        public void setInsert_date(String insert_date) {
            this.insert_date = insert_date;
        }

        public String getPublication_date() {
            return publication_date;
        }

        public void setPublication_date(String publication_date) {
            this.publication_date = publication_date;
        }

        public String getIs_direct() {
            return is_direct;
        }

        public void setIs_direct(String is_direct) {
            this.is_direct = is_direct;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage_url_small() {
            return image_url_small;
        }

        public void setImage_url_small(String image_url_small) {
            this.image_url_small = image_url_small;
        }

        public String getImage_url_big() {
            return image_url_big;
        }

        public void setImage_url_big(String image_url_big) {
            this.image_url_big = image_url_big;
        }

        public String getImage_spec() {
            return image_spec;
        }

        public void setImage_spec(String image_spec) {
            this.image_spec = image_spec;
        }

        public String getImage_with_btn() {
            return image_with_btn;
        }

        public void setImage_with_btn(String image_with_btn) {
            this.image_with_btn = image_with_btn;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTargetid() {
            return targetid;
        }

        public void setTargetid(String targetid) {
            this.targetid = targetid;
        }

        public String getIs_act() {
            return is_act;
        }

        public void setIs_act(String is_act) {
            this.is_act = is_act;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getIs_subject() {
            return is_subject;
        }

        public void setIs_subject(String is_subject) {
            this.is_subject = is_subject;
        }

        public String getIs_report() {
            return is_report;
        }

        public void setIs_report(String is_report) {
            this.is_report = is_report;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }
    }
}
