package com.example.community.design;

/**
 * @author: lichengcan
 * @date: 2023-07-14 17:44
 * @description 乘法计算类
 **/
public class OperationMul extends Operation{

    @Override
    public double getResult(){
        double result = 0;
        result = get_numberA()*get_numberB();
        return result;
    }
}
