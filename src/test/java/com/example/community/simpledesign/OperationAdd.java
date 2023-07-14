package com.example.community.simpledesign;

/**
 * @author: lichengcan
 * @date: 2023-07-14 17:32
 * @description 加法计算类
 **/
public class OperationAdd extends Operation{
    @Override
    public double getResult(){
        double result = 0;
        result = get_numberA()+get_numberB();
        return result;
    }
}
