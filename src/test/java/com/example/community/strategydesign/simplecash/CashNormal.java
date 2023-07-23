package com.example.community.strategydesign.simplecash;

/**
 * @author: lichengcan
 * @date: 2023-07-17 16:34
 * @description 正常收费子类
 **/
public class CashNormal extends CashSuper{

    @Override
    public double acceptCash(double money) {
        return money;
    }
}
