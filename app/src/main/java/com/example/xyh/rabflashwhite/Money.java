package com.example.xyh.rabflashwhite;

/**
 * Created by xyh on 2017-04-19.
 */

public class Money {
    private double money;
    private String dingdanhao;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDingdanhao() {
        return dingdanhao;
    }

    public void setDingdanhao(String dingdanhao) {
        this.dingdanhao = dingdanhao;
    }

    public Money(double money, String dingdanhao) {
        this.money = money;
        this.dingdanhao = dingdanhao;
    }

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                ", dingdanhao='" + dingdanhao + '\'' +
                '}';
    }
}
