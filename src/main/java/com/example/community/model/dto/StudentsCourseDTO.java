package com.example.community.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.community.model.entity.Course;
import lombok.*;

import java.util.List;

/**
 * @author lichengcan
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentsCourseDTO {
    private Integer id;

    private String name;

    private Integer age;

    private String tel;

    private Integer grade;

    List<Course> courseList;

}
