package com.example.community.策略模式.simplecash;

/**
 * @author: lichengcan
 * @date: 2023-07-17 16:48
 * @description 收费工厂 决定使用哪一个优惠策略
 **/
public class CashFactory {
    public static CashSuper createCashSuper(String type){
        CashSuper cashSuper = null;
        switch (type){
            case "正常收费":
                cashSuper=new CashNormal();
                break;
            case "打8折":
                cashSuper = new CashRebate(0.8d);
                break;
            case "满300-100":
                cashSuper = new CashReturn(100d,300d);
        }
        return cashSuper;
    }
}
