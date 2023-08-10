package com.example.community.array;

public class Main {
    public static void main(String[] args) {
//        final String[] array = new String[1];
//        array[0] = "Hello";
//        System.out.println("数组初始值：" + array[0] + array);
//
//        // 修改数组中的元素值
//        array[0] = "World";
//        System.out.println("修改后的数组值：" + array[0] + array);

        // 尝试重新赋值数组，使其指向新的对象
        // 这将会抛出编译错误，因为array是一个final数组，不能被重新赋值
        // array = new String[]{"New Value"};

        // 字符串常量池中已存在字符串对象“abc”的引用
        String s1 = "abc";
        String s3 = "abc";
// 下面这段代码只会在堆中创建 1 个字符串对象“abc”
        String s2 = new String("abc");
    }
}
