package com.example.community.计算器;

/**
 * @author: lichengcan
 * @date: 2023-07-25 14:35
 * @description
 **/
public class AddMethod extends ComputeEntity{
    @Override
    public Double getResult(ComputeEntity computeEntity) {
        Double result=0D;
        result=  computeEntity.getD1()+computeEntity.getD2();
        return result;
    }
}
