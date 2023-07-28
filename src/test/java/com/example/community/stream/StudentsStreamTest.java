package com.example.community.stream;

import cn.hutool.core.bean.BeanUtil;
import com.example.community.model.entity.Student;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author: lichengcan
 * @date: 2023-07-28 13:47
 * @description
 **/
public class StudentsStreamTest {

    public static List<Student> createStudentList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "林高禄", 20, 90.5, 90.5));
        studentList.add(new Student(11, "林高禄", 20, 90.5, 90.5));
        studentList.add(new Student(1, "1林高禄", 20, 90.5, 90.5));
        studentList.add(new Student(2, "林高禄", 10, 80.0, 90.0));
        studentList.add(new Student(1, "林高禄", 30, 90.5, 90.0));
        studentList.add(new Student(1, "陈文文", 10, 100.0, 90.0));
        studentList.add(new Student(2, "陈文文", 20, 90.0, 70.0));
        studentList.add(new Student(1, "蔡金鑫", 30, 80.0, 90.0));
        return studentList;
    }

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        List<Student> studentList1 = createStudentList();
        //1.分组求和：求相同姓名的学生的年龄之和（姓名组合）
        Map<String, List<Student>> collect = studentList1.stream()
                .collect(Collectors.groupingBy(Student::getName, Collectors.toList()));

//        collect.forEach((key,value)-> System.out.println(key+":"+value.stream().mapToInt(Student::getAge).sum()));

        List<Student> studentList2 = createStudentList();
        //2.求相同姓名和相同编号的学生的年龄之和（姓名+编号组合）
        Map<String, List<Student>> collect1 = studentList2.stream()
                .collect(Collectors.groupingBy(StudentsStreamTest::combination, Collectors.toList()));
        collect1.forEach(
                (key, value) -> System.out.println(key + ":" + value.stream().mapToInt(Student::getAge).sum())
        );
        //2.中的11林高禄:40 是有点问题的
        //改造combination 加一个student.getNo()

        //3.组合排序—comparing—>thenComparing
        //学生按编号排序，再按年龄排序
        List<Student> studentList3 = createStudentList();
        //3.1 使用list集合自带的sort
        studentList3.sort(Comparator.comparing(Student::getNo).thenComparing(Student::getAge));
        //3.2 使用stream流排序
        studentList3.stream()
                .sorted(Comparator.comparing(Student::getNo).thenComparing(Student::getAge)).collect(Collectors.toList());

        //4 求出学生数学分数的各个值（最大，最小，平均，总数，个数）
        List<Student> studentList4 = createStudentList();
        //最大
        Double maxMathScore = studentList4.stream()
                .max(Comparator.comparing(Student::getMathScore)).get().getMathScore();
        //最小
        Double minMathScore = studentList4.stream()
                .min(Comparator.comparing(Student::getMathScore)).get().getMathScore();
        //总数
        double sum = studentList4.stream()
                .mapToDouble(Student::getMathScore).sum();
        //个数
        Integer listSize = studentList4.size();
        //平均
        double agv = sum /listSize;
        DoubleSummaryStatistics collect3 = studentList4.stream().collect((Collectors.summarizingDouble(Student::getMathScore)));
        System.out.println("collect3 = " + collect3);

        //5 求相同年龄学生的语数总分的排名，倒序
        List<Student> studentList5= createStudentList();
        final Map<Integer, DoubleSummaryStatistics> collect2 = studentList5.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.summarizingDouble(s -> s.getChineseScore())));
        List<DoubleSummaryStatistics> list = new ArrayList<>();
        collect2.forEach((key,value)->list.add(value));
        list.forEach(System.out::println);
    }

    public static String combination(Student student) {
        return student.getNo() + student.getName() + student.getNo();
    }

}
