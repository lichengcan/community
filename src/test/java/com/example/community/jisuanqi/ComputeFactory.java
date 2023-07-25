package com.example.community.jisuanqi;

/**
 * @author: lichengcan
 * @date: 2023-07-25 14:28
 * @description
 **/
public class ComputeFactory extends ComputeEntity {


    public static ComputeEntity ComputeFactory(String sign){
        ComputeEntity computeEntity = new ComputeEntity();
        switch (sign){
            case "+":
                computeEntity=new AddMethod();
        }
        return computeEntity;
    }
}
