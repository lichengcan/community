package com.example.community.strategydesign.simplecash;

/**
 * @author: lichengcan
 * @date: 2023-07-17 16:55
 * @description 策略模式客户端测试
 **/
public class ClientTest {

    public static void main(String[] args) {
         CashSuper cashSuper = CashFactory.createCashSuper("打8折");
        final double v = cashSuper.acceptCash(1024d);
        System.out.println("v = " + v);
    }
}
