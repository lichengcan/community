package com.example.community.jisuanqi;

/**
 * @author: lichengcan
 * @date: 2023-07-25 14:19
 * @description
 **/
public class 计算器 {




    public static Double compute(Double s1, Double s2, String sign) {
        if (sign.equals("+")) {
            return s1 + s2;
        } else if (sign.equals("-")) {
            return s1 - s2;
        } else if (sign.equals("*")) {
            return s1 * s2;
        }else if (sign.equals("/")){
            return s1/s2;
        }
        return null;
    }

    public static void main(String[] args) {
        String sign = "+";
        Double d1 = 10.2D;
        Double d2 = 2.1D;
        //普通写法
        final Double compute = compute(d1, d2, sign);
        System.out.println("普通写法 = " + compute);
        //简单工厂
        ComputeEntity computeEntity = ComputeFactory.ComputeFactory(sign);
        computeEntity.setD1(d1);
        computeEntity.setD2(d2);
        Double result = computeEntity.getResult(computeEntity);
        System.out.println("工厂写法 = " + result);
    }
}
