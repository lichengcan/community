package com.example.community.strategydesign.example;

/**
 * @author: lichengcan
 * @date: 2023-07-17 16:15
 * @description 客户端代码
 **/
public class ClientTest {

    public static void main(String[] args) {
        Context context;
        context =new Context(new ConCreateStrategyA());
        System.out.println("context = " + context.strategy);
    }
}
