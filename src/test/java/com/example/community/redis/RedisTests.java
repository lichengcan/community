package com.example.community.redis;

import com.example.community.util.RedisTemplateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;

@SpringBootTest
class RedisTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    RedisTemplateUtil redisTemplateUtil;

    @Test
    public void testAdd() {
        redisTemplateUtil.lSet("name", "lichengcan");
    }

    @Test
    public void testQuery() {
        final DataType type = redisTemplateUtil.getType("name");
        System.out.println("type = " + type);
        System.out.println(redisTemplateUtil.lGet("name",0,1));
    }
}
