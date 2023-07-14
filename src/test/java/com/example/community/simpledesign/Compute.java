package com.example.community.simpledesign;

/**
 * @author: lichengcan
 * @date: 2023-07-14 17:34
 * @description 测试
 **/
public class Compute {
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
