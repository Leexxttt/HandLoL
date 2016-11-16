package com.lxt.handlol.module.latest;

import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/10/14 0014.
 * e-mail:871281347@qq.com
 */

public class ZuixinBean {

    /**
     * next : True
     * nextpage : 1
     * list : [{"article_id":"22589","content_id":"22589","newstype":"专题","newstypeid":"ordinary","channel_desc":"","channel_id":"<2>:<12>","insert_date":"2016-09-09 22:54:39","title":"英雄联盟2016全球总决赛专题","article_url":"http://qt.qq.com/static/pages/news/phone/89/article_22589.shtml","summary":"15日6:00直播八强战：SKT VS RNG。","score":"3","publication_date":"2016-09-30 10:07:49","targetid":"","intent":"qtpage://news_subject?subject=229&gallery=230&title=2016全球总决赛","is_act":"0","is_hot":"0","is_subject":"1","is_new":"0","is_top":"True","image_with_btn":"False","image_spec":"1","is_report":"True","is_direct":"False","image_url_small":"http://ossweb-img.qq.com/upload/qqtalk/news/201609/291742206875332_282.png","image_url_big":"http://ossweb-img.qq.com/upload/qqtalk/news/201609/291742206875332_480.png","pv":"53357970","bmatchid":"0","v_len":"","pics_id":"0"},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}]
     * this_page_num : 21
     */

    private String next;
    private String nextpage;
    private String this_page_num;
    /**
     * article_id : 22589
     * content_id : 22589
     * newstype : 专题
     * newstypeid : ordinary
     * channel_desc :
     * channel_id : <2>:<12>
     * insert_date : 2016-09-09 22:54:39
     * title : 英雄联盟2016全球总决赛专题
     * article_url : http://qt.qq.com/static/pages/news/phone/89/article_22589.shtml
     * summary : 15日6:00直播八强战：SKT VS RNG。
     * score : 3
     * publication_date : 2016-09-30 10:07:49
     * targetid :
     * intent : qtpage://news_subject?subject=229&gallery=230&title=2016全球总决赛
     * is_act : 0
     * is_hot : 0
     * is_subject : 1
     * is_new : 0
     * is_top : True
     * image_with_btn : False
     * image_spec : 1
     * is_report : True
     * is_direct : False
     * image_url_small : http://ossweb-img.qq.com/upload/qqtalk/news/201609/291742206875332_282.png
     * image_url_big : http://ossweb-img.qq.com/upload/qqtalk/news/201609/291742206875332_480.png
     * pv : 53357970
     * bmatchid : 0
     * v_len :
     * pics_id : 0
     */

    private List<ListBean> list;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getNextpage() {
        return nextpage;
    }

    public void setNextpage(String nextpage) {
        this.nextpage = nextpage;
    }

    public String getThis_page_num() {
        return this_page_num;
    }

    public void setThis_page_num(String this_page_num) {
        this.this_page_num = this_page_num;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String article_id;
        private String content_id;
        private String newstype;
        private String newstypeid;
        private String channel_desc;
        private String channel_id;
        private String insert_date;
        private String title;
        private String article_url;
        private String summary;
        private String score;
        private String publication_date;
        private String targetid;
        private String intent;
        private String is_act;
        private String is_hot;
        private String is_subject;
        private String is_new;
        private String is_top;
        private String image_with_btn;
        private String image_spec;
        private String is_report;
        private String is_direct;
        private String image_url_small;
        private String image_url_big;
        private String pv;
        private String bmatchid;
        private String v_len;
        private String pics_id;
        private String big_image_url;
        private String small_image_url;
        private String count_image_url;
        private String count;

        public String getBig_image_url() {
            return big_image_url;
        }

        public void setBig_image_url(String big_image_url) {
            this.big_image_url = big_image_url;
        }
        public String getSmall_image_url() {
            return small_image_url;
        }

        public void setSmall_image_url(String small_image_url) {
            this.small_image_url = small_image_url;
        }
        public String getCount_image_url() {
            return count_image_url;
        }

        public void setCount_image_url(String count_image_url) {
            this.count_image_url = count_image_url;
        }
        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
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

        public String getNewstype() {
            return newstype;
        }

        public void setNewstype(String newstype) {
            this.newstype = newstype;
        }

        public String getNewstypeid() {
            return newstypeid;
        }

        public void setNewstypeid(String newstypeid) {
            this.newstypeid = newstypeid;
        }

        public String getChannel_desc() {
            return channel_desc;
        }

        public void setChannel_desc(String channel_desc) {
            this.channel_desc = channel_desc;
        }

        public String getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        public String getInsert_date() {
            return insert_date;
        }

        public void setInsert_date(String insert_date) {
            this.insert_date = insert_date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getPublication_date() {
            return publication_date;
        }

        public void setPublication_date(String publication_date) {
            this.publication_date = publication_date;
        }

        public String getTargetid() {
            return targetid;
        }

        public void setTargetid(String targetid) {
            this.targetid = targetid;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
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

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public String getImage_with_btn() {
            return image_with_btn;
        }

        public void setImage_with_btn(String image_with_btn) {
            this.image_with_btn = image_with_btn;
        }

        public String getImage_spec() {
            return image_spec;
        }

        public void setImage_spec(String image_spec) {
            this.image_spec = image_spec;
        }

        public String getIs_report() {
            return is_report;
        }

        public void setIs_report(String is_report) {
            this.is_report = is_report;
        }

        public String getIs_direct() {
            return is_direct;
        }

        public void setIs_direct(String is_direct) {
            this.is_direct = is_direct;
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

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getBmatchid() {
            return bmatchid;
        }

        public void setBmatchid(String bmatchid) {
            this.bmatchid = bmatchid;
        }

        public String getV_len() {
            return v_len;
        }

        public void setV_len(String v_len) {
            this.v_len = v_len;
        }

        public String getPics_id() {
            return pics_id;
        }

        public void setPics_id(String pics_id) {
            this.pics_id = pics_id;
        }
    }
}
