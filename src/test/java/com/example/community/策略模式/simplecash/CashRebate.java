package com.example.community.策略模式.simplecash;

/**
 * @author: lichengcan
 * @date: 2023-07-17 16:34
 * @description 打折收费子类
 **/
public class CashRebate extends CashSuper{

    private double moneyRebate = 1D;

    /**
     * 打折
     * @param moneyRebate 如打9折,就是0.9
     */
    public CashRebate(double moneyRebate){
        this.moneyRebate = moneyRebate;
    }

    /**
     * 打折后的付款金额
     * @param money
     * @return
     */
    @Override
    public double acceptCash(double money) {
        return money*moneyRebate;
    }

}
