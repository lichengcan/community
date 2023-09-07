package com.example.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.model.dto.StudentsCourseDTO;
import com.example.community.model.entity.Students;
import com.example.community.result.ResponseResult;
import com.example.community.service.StudentsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lichengcan
 * @date: 2023-07-04 17:26
 * @description 测试mybatis-plus mongodb
 **/
@RestController
@ResponseResult
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentsService studentsService;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/test")
    public void test(@RequestBody Students students) {
        studentsService.insert(students);
    }

    /**
     * 测试分页查询
     */
    @GetMapping("/testPage")
    public IPage<Students> testPage() {
        Page<Students> page = new Page<>(1, 2);
        IPage<Students> userIPage = studentsService.selectPage(page, new QueryWrapper<Students>()
                .eq("name", "xiaoli"));
        return userIPage;
    }

    /**
     * 创建集合
     */
    @PostMapping("createCollection")
    public void createCollection(String collectName) {
        if (!mongoTemplate.collectionExists(collectName)) {
            mongoTemplate.createCollection(collectName);
        } else {
            System.out.println("集合已存在;");
        }
    }

    /**
     * 文档的添加
     */
    @PostMapping("addDocument")
    public void addDocument() {
        Students user = new Students(123, "xiao", 13, "123442131", 2);
        //_id存在时会把旧数据进行覆盖；
        mongoTemplate.save(user);
//        _id存在时会提示主键重复的异常；
//        mongoTemplate.insert(user);
    }

    @GetMapping("testSelectAll")
    public PageInfo testSelectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentsCourseDTO> students = studentsService.selectAll();
        PageInfo pageInfo = new PageInfo(students);
        pageInfo.setSize(students.size());
        return pageInfo;
    }
}
