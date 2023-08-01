package com.example.community.策略模式.simplecash;

/**
 * @author: lichengcan
 * @date: 2023-07-17 16:40
 * @description
 **/
public class CashReturn extends CashSuper {


    private double moneyReturn = 0.0d;
    private double moneyCondition = 0.0d;

    public CashReturn(Double moneyReturn, Double moneyCondition) {
        this.moneyReturn = moneyReturn;
        this.moneyCondition = moneyCondition;
    }


    @Override
    public double acceptCash(double money) {
        double result = money;
        if (money > moneyCondition) {
            result = money - money % moneyCondition * moneyReturn;
        }

        return result;
    }
}
