package com.example.community.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: lichengcan
 * @date: 2023-07-28 17:00
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tree {
    String id;
    String parentId;
    String name;
    List<Tree> child;

    public List<Tree> getChild() {
        return child;
    }

    public Tree(String id, String parentId, String name) {
        this.id=id;
        this.parentId=parentId;
        this.name=name;
    }
}
