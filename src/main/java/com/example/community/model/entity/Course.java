package com.example.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * @author lichengcan
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("course")
public class Course {
    private Integer c_id;
    private String course;
    private Integer stuId;

}
