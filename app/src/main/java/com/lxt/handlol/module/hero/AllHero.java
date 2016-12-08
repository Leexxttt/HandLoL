package com.lxt.handlol.module.hero;

import java.util.List;

/**
 * Created by ${reallyCommon} on 2016/11/15 0015.
 * e-mail:871281347@qq.com
 */

public class AllHero {

    /**
     * tag4 : longxuewujixiwanalxwjxwn龙女longnvln
     * en_name : Shyvana
     * magic : 3
     * name : 希瓦娜
     * tag2 : 坦克
     * tag3 : 上单
     * money : 3500.0
     * newhero : false
     * newmoney : 2250
     * nick : 龙血武姬
     * attack : 8
     * defense : 6
     * difficulty : 4
     * coin : 4800.0
     * discount : false
     * id : 102
     * tag1 : 战士
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String tag4;
        private String en_name;
        private String magic;
        private String name;
        private String tag2;
        private String tag3;
        private String money;
        private boolean newhero;
        private String newmoney;
        private String nick;
        private String attack;
        private String defense;
        private String difficulty;
        private String coin;
        private boolean discount;
        private int id;
        private String tag1;

        public String getTag4() {
            return tag4;
        }

        public void setTag4(String tag4) {
            this.tag4 = tag4;
        }

        public String getEn_name() {
            return en_name;
        }

        public void setEn_name(String en_name) {
            this.en_name = en_name;
        }

        public String getMagic() {
            return magic;
        }

        public void setMagic(String magic) {
            this.magic = magic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag2() {
            return tag2;
        }

        public void setTag2(String tag2) {
            this.tag2 = tag2;
        }

        public String getTag3() {
            return tag3;
        }

        public void setTag3(String tag3) {
            this.tag3 = tag3;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public boolean isNewhero() {
            return newhero;
        }

        public void setNewhero(boolean newhero) {
            this.newhero = newhero;
        }

        public String getNewmoney() {
            return newmoney;
        }

        public void setNewmoney(String newmoney) {
            this.newmoney = newmoney;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getAttack() {
            return attack;
        }

        public void setAttack(String attack) {
            this.attack = attack;
        }

        public String getDefense() {
            return defense;
        }

        public void setDefense(String defense) {
            this.defense = defense;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public boolean isDiscount() {
            return discount;
        }

        public void setDiscount(boolean discount) {
            this.discount = discount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTag1() {
            return tag1;
        }

        public void setTag1(String tag1) {
            this.tag1 = tag1;
        }
    }
}
