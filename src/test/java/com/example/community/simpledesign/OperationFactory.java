package com.example.community.simpledesign;

/**
 * @author: lichengcan
 * @date: 2023-07-14 17:34
 * @description 简单工厂模式
 **/
public class OperationFactory {
    public static Operation createOperate(String operate){
        Operation operation = null;
        switch (operate){
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationSub();
                break;
            case "*":
                operation = new OperationMul();
                break;
        }
        return operation;
    }
}
