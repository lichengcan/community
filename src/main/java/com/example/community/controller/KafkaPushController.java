package com.example.community.controller;

import cn.hutool.json.JSONUtil;
import com.example.community.model.vo.KafkaMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * kafka信息管理
 *
 * @author zhengwen
 **/
@Slf4j
@RestController
@RequestMapping("/kafka/push")
public class KafkaPushController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * kafka的信息push发送
     *
     * @param kafkaMessageVo kafka信息对象
     * @return 推送结果
     */
    @PostMapping("/sendMsg")
    public void sendMsg(@RequestBody KafkaMessageVo kafkaMessageVo) {
        String topic = kafkaMessageVo.getTopic();
        String msg = kafkaMessageVo.getMessage();
        //SpringBoot3的写法
        CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(topic, UUID.randomUUID().toString(), msg);
        //执行成功回调
        completableFuture.thenAccept(result -> {
            log.info("发送成功:{}", JSONUtil.toJsonStr(kafkaMessageVo));
        });
        //执行失败回调
        completableFuture.exceptionally(e -> {
            log.info("发送失败", JSONUtil.toJsonStr(kafkaMessageVo), e);
            return null;
        });
    }
}
