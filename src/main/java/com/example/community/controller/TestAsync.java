package com.example.community.controller;

import com.example.community.result.ResponseResult;
import com.example.community.service.impl.StudentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.sleep;

/**
 * @author: lichengcan
 * @date: 2023-09-05 15:59
 * @description
 **/
@RestController
@ResponseResult
@RequestMapping("/async")
public class TestAsync {

    @Autowired
    TestAsync2 testAsync2;


    @GetMapping("/test")
    public Object test() throws InterruptedException {
        testAsync2.test1();
        return "123";
    }

    @Async
    public void test1() throws InterruptedException {
        sleep(6000);
        System.out.println("1");
    }

}
