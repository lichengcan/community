package com.example.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("students")
public class Students {
    private Integer id;

    private String name;

    private Integer age;

    private String tel;

    private Integer grade;
}