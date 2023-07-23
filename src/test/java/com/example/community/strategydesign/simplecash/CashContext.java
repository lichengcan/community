package com.example.community.strategydesign.simplecash;

/**
 * @author: lichengcan
 * @date: 2023-07-17 16:32
 * @description 收费策略
 **/
public class CashContext {
    private CashSuper cashSuper;

    public CashContext(CashSuper cashSuper) {
        this.cashSuper = cashSuper;
    }
}
