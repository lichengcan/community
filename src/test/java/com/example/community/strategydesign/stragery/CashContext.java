package com.example.community.strategydesign.stragery;


/**
 * @author: lichengcan
 * @date: 2023-07-17 17:00
 * @description
 **/
public class CashContext {

    private CashSuper cashSuper;

    public CashContext(CashSuper cashSuper) {
        this.cashSuper = cashSuper;
    }

    public double GetResult(double money){
        return cashSuper.acceptCash(money);
    }
}
