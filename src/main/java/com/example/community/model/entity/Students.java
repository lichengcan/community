package com.example.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author lichengcan
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("students")
public class Students{
    private Integer id;

    private String name;

    private Integer age;

    private String tel;

    private Integer grade;


}
