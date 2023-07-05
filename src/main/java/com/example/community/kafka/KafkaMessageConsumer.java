package com.example.community.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author lichengcan
 */
@Component
@Slf4j
public class KafkaMessageConsumer {

    /**
     * kafka的信息消费
     * @param record
     */
    @KafkaListener(topics = "${xiaocan.analyze.device.flow.topic.consumer}", groupId = "dc-device-flow-analyze-0228")
    public void consumeMessage(ConsumerRecord<String, String> record) {
        String key = record.key();
        String value = record.value();
        String topic = record.topic();
        int partition = record.partition();
        long offset = record.offset();

        log.info("开始消费");
        log.info("Topic: " + topic);
        log.info("Partition: " + partition);
        log.info("Offset: " + offset);
        log.info("Key: " + key);
        log.info("Value: " + value);
    }
}
