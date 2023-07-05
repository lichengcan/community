package com.example.community;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    DataSource dataSource;

    @Test
    void contextLoadsOne() throws Exception{
        System.out.println("获取的数据库连接为:"+dataSource.getConnection());
    }
}
