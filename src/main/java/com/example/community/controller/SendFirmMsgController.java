package com.example.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lichengcan
 * @date: 2023-07-04 17:26
 * @description rabbitmq 生产者
 **/
@Slf4j
@RestController
@RequestMapping("/firm")
public class SendFirmMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 1、定义交换机
     */
    private String exchangeName = "exchange_firm";
    // 2、定义路由key
    private String routeKey1 = "爱国.小灿";
    private String routeKey2 = "哈哈.小峰";
    private String routeKey3 = "动作.小花";
    private String routeKey4 = "喜剧.小艹";

    @PostMapping("/send")
    public void sendMsg(){

        //发送信息到指定的交换器
        //并使用路由键进行匹配
        for (int i = 1; i <=50; i++) {
            // @params1: 交换机名称
            // @params2: 表示消息的路由键
            // @params3: 表示要发送的消息内容
            if(i%4==0){
                rabbitTemplate.convertAndSend(exchangeName,routeKey1,("爱国.小灿，说第"+i+"遍。").getBytes());
            }else if(i%4 ==1){
                rabbitTemplate.convertAndSend(exchangeName,routeKey2,("哈哈.小峰，说第"+i+"遍。").getBytes());
            }else if(i%4 ==2){
                rabbitTemplate.convertAndSend(exchangeName,routeKey3,("动作.小花，说第"+i+"遍。").getBytes());
            }else if(i%4 ==3){
                rabbitTemplate.convertAndSend(exchangeName,routeKey4,("喜剧.小艹，说第"+i+"遍。").getBytes());
            }
            log.info("发送第"+i);
        }
    }
}
