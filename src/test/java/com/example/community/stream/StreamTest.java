package com.example.community.stream;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * @author: lichengcan
 * @date: 2023-07-24 10:42
 * @description stream 流练习
 **/
@SpringBootTest
public class StreamTest {
    //数据库联表查询
    //一对多的关系，某些id可能会重复
    //重复的id数据留下一条create_time最新的一个
    public static void main(String[] args) throws InterruptedException {
        StreamTest streamTest = new StreamTest();
        streamTest.groupBy();
    }


    public void groupBy() throws InterruptedException {
        List<TestDTO> list = new ArrayList<>();
        TestDTO dto1 = new TestDTO();

        dto1.setId("1");
        dto1.setName("lee");
        dto1.setCreateTime(new Date());
        list.add(dto1);

        sleep(1000);

        TestDTO dto2 = new TestDTO();
        dto2.setId("1");
        dto2.setName("cccc");
        dto2.setCreateTime(new Date());
        list.add(dto2);

        TestDTO dto3 = new TestDTO();
        dto3.setId("2");
        dto3.setName("aaaa");
        dto3.setCreateTime(new Date());
        list.add(dto3);

        final List<Object> collect = list.stream()
                .collect(Collectors.groupingBy(TestDTO::getId))
                .values().stream()
                .map(x -> x.stream().max(Comparator.comparing(TestDTO::getCreateTime)).get()).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }
}
