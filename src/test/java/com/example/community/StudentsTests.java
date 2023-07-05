package com.example.community;

import com.example.community.mapper.StudentsMapper;
import com.example.community.model.entity.Students;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentsTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private StudentsMapper studentsMapper;

    /**
     * 测试单条查询
     */
    @Test
    public void testSelectOne() {
        Students students = studentsMapper.selectById(200023L);
        System.out.println(students);
    }
}
