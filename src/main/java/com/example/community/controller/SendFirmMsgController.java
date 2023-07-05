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
    private String routeKey1 = "小灿";
    private String routeKey2 = "小猫";
    private String routeKey3 = "小狗";
    private String routeKey4 = "小猪";

    @PostMapping("/send")
    public void sendMsg(){

        for (int i = 1; i <=40; i++) {
            // @params1: 交换机exchange
            // @params2: 队列名称/routing
            // @params3: 属性配置
            // @params4: 发送消息的内容
            if(i%4==0){
                rabbitTemplate.convertAndSend(exchangeName,routeKey1,("小灿"+i+"号。").getBytes());
            }else if(i%4 ==1){
                rabbitTemplate.convertAndSend(exchangeName,routeKey2,("小猫，"+i+"号。").getBytes());
            }else if(i%4 ==2){
                rabbitTemplate.convertAndSend(exchangeName,routeKey3,("小狗"+i+"号。").getBytes());
            }else if(i%4 ==3){
                rabbitTemplate.convertAndSend(exchangeName,routeKey4,("小猪，"+i+"号。").getBytes());
            }
            log.info("发送第"+i);
        }
    }
}
