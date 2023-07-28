package com.example.community.stream;

import com.example.community.model.dto.TestDTO;
import com.example.community.model.entity.Actor;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

/**
 * @author: lichengcan
 * @date: 2023-07-24 10:42
 * @description stream 流练习
 **/
@SpringBootTest
public class StreamTest {
    //数据库联表查询
    //一对多的关系，某些id可能会重复
    //重复的id数据留下一条create_time最新的一个
    public static void main(String[] args) throws InterruptedException {
        //test1
//        StreamTest streamTest = new StreamTest();
//        streamTest.groupBy();
//        test2();
//        test3();
//        test5();
        test6();
    }


    public void groupBy() throws InterruptedException {
        List<TestDTO> list = new ArrayList<>();
        TestDTO dto1 = new TestDTO();

        dto1.setId("1");
        dto1.setName("lee");
        dto1.setCreateTime(new Date());
        list.add(dto1);

        sleep(1000);

        TestDTO dto2 = new TestDTO();
        dto2.setId("1");
        dto2.setName("cccc");
        dto2.setCreateTime(new Date());
        list.add(dto2);

        TestDTO dto3 = new TestDTO();
        dto3.setId("2");
        dto3.setName("aaaa");
        dto3.setCreateTime(new Date());
        list.add(dto3);

        final List<Object> collect = list.stream()
                .collect(Collectors.groupingBy(TestDTO::getId))
                .values().stream()
                .map(x -> x.stream().max(Comparator.comparing(TestDTO::getCreateTime)).get()).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    public static void test2() {
        //有如下7个元素黄药师，冯蘅，郭靖，黄蓉，郭芙，郭襄，郭破虏，使用Stream将以郭字开头的元素存入新数组
        Stream<String> stream = Stream.of("黄药师", "冯蘅", "郭靖", "黄蓉", "郭芙", "郭襄", "郭破虏");
        final List<String> 郭 = stream.filter(x -> x.startsWith("郭")).collect(Collectors.toList());
        for (String s : 郭) {
            System.out.println("s = " + s);
        }
    }

    public static void test3() {
//        已知ArrayList集合中有如下元素{陈玄风、梅超风、陆乘风、曲灵风、武眠风、冯默风、罗玉风}，使用Stream
//        1、取出前2个元素并在控制台打印输出。
//        2、取出后2个元素并在控制台打印输出。
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("陈玄风");
        arrayList.add("梅超风");
        arrayList.add("陆乘风");
        arrayList.add("曲灵风");
        arrayList.add("武眠风");
        arrayList.add("冯默风");
        arrayList.add("罗玉风");
        arrayList.stream()
                .limit(2).forEach(s -> System.out.println("s = " + s));

        arrayList.stream()
                .skip(arrayList.size() - 2).forEach(s -> System.out.println("s = " + s));
    }


    public static void test4() {
//        有如下整数1，-2，-3，4，-5
//        使用Stream取元素绝对值并打印
        Stream<Integer> stream = Stream.of(1, -2, -3, 4, -5);
        stream.forEach(x -> System.out.println(Math.abs(x)));
    }

    public static void test5() {
//        给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
//        比如给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
                .forEach(x -> {
                    System.out.println(x * x);
                });
    }

    public static void test6() {
//        有两个集合，一个有6个男演员，一个有6个女演员，完成下面的功能
        ArrayList<String> manArray = new ArrayList<>();
        manArray.add("刘德华");
        manArray.add("成龙");
        manArray.add("吴彦祖");
        manArray.add("周润发");
        manArray.add("周星驰");
        manArray.add("吴京");

        ArrayList<String> womanList = new ArrayList<>();
        womanList.add("林心如");
        womanList.add("孙俪");
        womanList.add("柳岩");
        womanList.add("林青霞");
        womanList.add("王祖贤");
        womanList.add("张曼玉");
//        男演员只要名字为3个字的前三人
        Stream<String> streamMan = manArray.stream()
                .filter(x -> x.length() == 3).limit(3);
        //        女演员只要姓林的，并且不要第一个.
        Stream streamWoman = womanList.stream()
                .filter(x -> x.startsWith("林")).skip(1);
//                把过滤后的男演员姓名和女演员姓名合并到一起
        Stream<String> concat = Stream.concat(streamMan, streamWoman);
//        concat.forEach(x-> System.out.println(x));
//        把上一步操作后的元素作为构造方法的参数创建演员对象（实例化一个Actor类，此处尝试使用map），遍历数组
        List<Actor> actors = concat.map(x -> new Actor(x)).collect(Collectors.toList());
        actors.forEach(System.out::println);
    }
}
