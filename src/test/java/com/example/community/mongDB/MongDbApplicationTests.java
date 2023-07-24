package com.example.community.mongDB;

import com.example.community.model.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@SpringBootTest
class MongDbApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    MongoTemplate mongoTemplate;


    /**
     * 创建集合
     */
    @Test
    public void createCollection() {
        if (!mongoTemplate.collectionExists("lichengcan")) {
            mongoTemplate.createCollection("lichengcan");
        } else {
            System.out.println("集合已存在;");
        }
    }

    /**
     * 文档的添加
     */
    @Test
    public void addDocument() {
        User user = new User(6, "ccc", 666, new Date());
        //_id存在时会把旧数据进行覆盖；
        mongoTemplate.save(user);
//        _id存在时会提示主键重复的异常；
//        mongoTemplate.insert(user);
    }


    /**
     * 文档的批量添加
     * insert批量添加效率 > save (save需要遍历整个数据)
     */
    @Test
    public void addDocuments() {
        List<User> users = Arrays.asList(
                new User(1, "hahaha", 7768, new Date()),
                new User(2, "今天吃什么", 77777, new Date()),
                new User(3, "好饿", 12345, new Date())
        );
        mongoTemplate.insert(users, User.class);
    }

    /**
     * 文档的查询
     */
    @Test
    public void queryDocument() {
//        List<User> all = mongoTemplate.findAll(User.class);
        List<User> all = mongoTemplate.findAll(User.class, "user");
        all.forEach(System.out::println);
    }

    /**
     * 文档的查询-根据id
     */
    @Test
    public void queryDocumentById() {
        User byId = mongoTemplate.findById(888, User.class);
        System.out.println(byId);
    }

    /**
     * 文档的查询-条件查询
     */
    @Test
    public void queryDocumentByParam() {
        Query query = new Query();
        //排序
        query.with(Sort.by(new Sort.Order(Sort.Direction.DESC, "password")));
        List<User> users = mongoTemplate.find(query, User.class);
        users.forEach(System.out::println);
    }

    /**
     * 等值查询
     */
    @Test
    public void queryDocumentWhere() {
        Query query = Query.query(Criteria.where("username").is("ccc"));
        List<User> users = mongoTemplate.find(query, User.class);
        users.forEach(System.out::println);
    }

    /**
     * AND查询
     */
    @Test
    public void queryDocumentWhereAnd() {
        Query query = Query.query(Criteria.where("username").is("ccc").and("_id").is(6));
        List<User> users = mongoTemplate.find(query, User.class);
        users.forEach(System.out::println);

    }

    /**
     * OR查询
     */
    @Test
    public void queryDocumentOr() {
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("username").is("好饿"),
                Criteria.where("username").is("ccc")
        );
        Query query = Query.query(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        users.forEach(System.out::println);
    }

    /**
     * 分页
     */
    @Test
    public void queryDocumentPage() {
        Query query = new Query().with(Sort.by(Sort.Order.asc("username")));
        query.limit(10).skip(0);
        List<User> users = mongoTemplate.find(query, User.class);
        users.forEach(System.out::println);
    }

    /**
     * 查询总条数
     * 去重
     */
    @Test
    public void queryAllAndDistinct() {
        //查询总条数
        System.out.println(mongoTemplate.count(new Query(), User.class));
        //去重
        mongoTemplate.findDistinct(new Query(), "username", User.class, String.class).forEach(System.out::println);
    }

}
