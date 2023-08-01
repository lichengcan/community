package com.example.community.简单工厂;

import lombok.Data;

/**
 * 运算类
 */
@Data
class Operation {
    private double _numberA = 0;
    private double _numberB = 0;

    /**
     * 获取结果
     */
    public double getResult() {
        double result = 0;
        return result;
    }
}
