package com.example.community.多线程;


import org.springframework.scheduling.config.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: lichengcan
 * @date: 2023-08-14 23:34
 * @description
 **/
public class 线程池 {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
    }
}
