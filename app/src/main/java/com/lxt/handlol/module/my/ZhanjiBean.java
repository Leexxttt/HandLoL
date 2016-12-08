package com.lxt.handlol.module.my;

import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/10/28 0028.
 * e-mail:871281347@qq.com
 */

public class ZhanjiBean {


    /**
     * next : True
     * nextPage : 1
     * list : [{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"true","data":"10-27"},{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"false","data":"10-27"},{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"false","data":"10-27"},{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"false","data":"10-27"},{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"true","data":"10-26"},{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"false","data":"10-26"},{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"false","data":"10-26"},{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"false","data":"10-26"},{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"false","data":"10-26"},{"hreoimg":"http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png","isVictory":"true","type":"大乱斗","iswithFriend":"true","zhangji":"12/7/23","isDatawith":"false","data":"10-26"}]
     */

    private String next;
    private String nextPage;
    /**
     * hreoimg : http://7xtztz.com1.z0.glb.clouddn.com/nearbydefaulthead.png
     * isVictory : true
     * type : 大乱斗
     * iswithFriend : true
     * zhangji : 12/7/23
     * isDatawith : true
     * data : 10-27
     */

    private List<ListBean> list;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String hreoimg;
        private String isVictory;
        private String type;
        private String iswithFriend;
        private String zhangji;
        private String isDatawith;
        private String data;

        public String getHreoimg() {
            return hreoimg;
        }

        public void setHreoimg(String hreoimg) {
            this.hreoimg = hreoimg;
        }

        public String getIsVictory() {
            return isVictory;
        }

        public void setIsVictory(String isVictory) {
            this.isVictory = isVictory;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIswithFriend() {
            return iswithFriend;
        }

        public void setIswithFriend(String iswithFriend) {
            this.iswithFriend = iswithFriend;
        }

        public String getZhangji() {
            return zhangji;
        }

        public void setZhangji(String zhangji) {
            this.zhangji = zhangji;
        }

        public String getIsDatawith() {
            return isDatawith;
        }

        public void setIsDatawith(String isDatawith) {
            this.isDatawith = isDatawith;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
