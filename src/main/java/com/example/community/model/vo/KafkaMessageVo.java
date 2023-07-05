package com.example.community.model.vo;

import lombok.Data;

/**
 * @author: lichengcan
 * @date: 2023-07-05 11:27
 * @description
 **/
@Data
public class KafkaMessageVo {
     private String topic;
     private String message;
}
