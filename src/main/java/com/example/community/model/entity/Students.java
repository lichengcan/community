package com.example.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichengcan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("students")
public class Students {
    private Integer id;

    private String name;

    private Integer age;

    private String tel;

    private Integer grade;
}