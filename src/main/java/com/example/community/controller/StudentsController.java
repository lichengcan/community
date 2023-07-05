package com.example.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.model.entity.Students;
import com.example.community.mapper.StudentsMapper;
import com.example.community.service.StudentsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lichengcan
 * @date: 2023-07-04 17:26
 * @description
 **/
@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentsService studentsService;

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
                .eq("name", "name_a2frd"));
        return userIPage;
    }
}
