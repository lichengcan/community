package com.example.community.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.DoubleSummaryStatistics;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer no;
    private String name;
    private Integer age;
    private Double mathScore;
    private Double chineseScore;

    public Student(DoubleSummaryStatistics x) {
    }
}
