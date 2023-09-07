package com.example.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.community.model.dto.StudentsCourseDTO;
import com.example.community.model.entity.Students;

import java.util.List;

/**
 * @author lichengcan
 */
public interface StudentsMapper extends BaseMapper<Students> {

    List<StudentsCourseDTO> selectAll();
}
