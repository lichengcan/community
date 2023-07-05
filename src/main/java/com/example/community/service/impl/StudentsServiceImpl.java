package com.example.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.community.model.entity.Students;
import com.example.community.mapper.StudentsMapper;
import com.example.community.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lichengcan
 * @date: 2023-07-04 17:28
 * @description
 **/
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements StudentsService {

    @Autowired
    StudentsMapper studentsMapper;


    @Override
    public IPage<Students> selectPage(Page<Students> page, QueryWrapper<Students> eq) {
        return studentsMapper.selectPage(page, new QueryWrapper<Students>()
                .eq("name", "name_a2frd"));
    }

    @Override
    public void insert(Students students) {
        studentsMapper.insert(students);
    }
}