package com.example.community.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

/**
 * @author: lichengcan
 * @date: 2023-09-05 15:59
 * @description
 **/
@Component
public class TestAsync2 {

    @Async
    public void test1() throws InterruptedException {
        sleep(6000);
        System.out.println("1");
    }

}
