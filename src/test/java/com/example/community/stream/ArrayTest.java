package com.example.community.stream;

import com.example.community.model.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lichengcan
 * @date: 2023-07-25 16:44
 * @description
 **/
public class ArrayTest {

    public static void main(String[] args) throws ParseException {
        final ArrayList<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setPassword(123);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String t1 = "2003-01-22 11:11:11";
        user.setCreationDate(simpleDateFormat.parse(t1));
        users.add(user);
        User user1 = new User();
        user1.setId(2);
        user1.setPassword(123);
        String t2 = "2003-01-22 11:11:12";
        user1.setCreationDate(simpleDateFormat.parse(t2));
        users.add(user1);

        final List<User> collect = users.stream().collect(Collectors.groupingBy(User::getId))
                .values()
                .stream()
                .map(x -> x.stream().max(Comparator.comparing(User::getCreationDate)).get()).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }
}
