package com.example.community.mysql;

import com.example.community.mapper.StudentsMapper;
import com.example.community.model.dto.StudentsCourseDTO;
import com.example.community.model.entity.Students;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    public PageInfo testSelectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentsCourseDTO> students = studentsMapper.selectAll();
        PageInfo pageInfo = new PageInfo(students);
        pageInfo.setSize(students.size());
        return pageInfo;
    }

}
