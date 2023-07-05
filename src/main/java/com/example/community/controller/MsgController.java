package com.example.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lichengcan
 * @date: 2023-07-04 17:26
 * @description rabbitmq 消费者
 **/
@Slf4j
@RestController
public class MsgController {

    @RabbitListener(bindings = @QueueBinding(
            // 指定队列名字
            value = @Queue(value = "queue1",autoDelete = "false"),
            // 指定交换机的名字
            exchange = @Exchange(value = "exchange_firm",type = ExchangeTypes.TOPIC)
    ))
    @RabbitHandler
    public void consumrmsg1(String msg){
        log.info(" -------------->" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue2",autoDelete = "false"),exchange = @Exchange(value = "exchange_firm",type = ExchangeTypes.TOPIC)))
    @RabbitHandler
    public void consumrmsg2(String msg){
        log.info(" -------------->" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue3",autoDelete = "false"),exchange = @Exchange(value = "exchange_firm",type = ExchangeTypes.TOPIC)))
    @RabbitHandler
    public void consumrmsg3(String msg,String id){
        log.info(" -------------->" + msg+"------------"+id);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue4",autoDelete = "false"),exchange = @Exchange(value = "exchange_firm",type = ExchangeTypes.TOPIC)))
    @RabbitHandler
    public void consumrmsg4(String msg){
        log.info(" -------------->" + msg);
    }


    /**
     * 手动消费
     */
    public void consumrmsg6(String msg){

    }
}
