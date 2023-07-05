package com.example.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.community.model.entity.Students;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentsMapper extends BaseMapper<Students> {

}