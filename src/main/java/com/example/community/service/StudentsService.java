package com.example.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.community.model.entity.Students;

/**
 * @author: lichengcan
 * @date: 2023-07-04 17:27
 * @description
 **/
public interface StudentsService extends IService<Students> {
    IPage<Students> selectPage(Page<Students> page, QueryWrapper<Students> eq);

    void insert(Students students);


}
