package com.example.community.simpledesign;

/**
 * @author: lichengcan
 * @date: 2023-07-14 17:34
 * @description 测试
 **/
public class Compute {
    //简单的计算器

    //面向对象的特性

    //封装：将运算符 和 计算数据封装成对象
    //继承：运算类继承 运算符类
    //多态：一个类有不同的实现
    //简单工厂模式： 根据不同的情况 + - * / 来实例化

    public static void main(String[] args) {
        final Operation operate = OperationFactory.createOperate("-");
        operate.set_numberA(10);
        operate.set_numberB(12);
        final double result = operate.getResult();
        System.out.println("result = " + result);


        final Operation operateMul = OperationFactory.createOperate("*");
        operateMul.set_numberA(2);
        operateMul.set_numberB(5);
        final double mulResult = operateMul.getResult();
        System.out.println("mulResult = " + mulResult);


    }
}
