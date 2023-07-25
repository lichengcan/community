package com.example.community.策略模式.example;

/**
 * @author: lichengcan
 * @date: 2023-07-17 16:13
 * @description 维护对Strategy对象的引用
 **/
public class Context {
    Strategy strategy;
    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * 具体的策略对象，调用其具体的算法
     */
    public void ContextInterface(){
        strategy.AlgorithmInterface();
    }
}
