package com.example.community.stream;

import com.example.community.model.entity.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lichengcan
 * @date: 2023-07-28 17:00
 * @description buildTree
 **/
public class TreeStreamTest {

    public static List<Tree> createTreeList() {
        List<Tree> TreeList = new ArrayList<>();
        TreeList.add(new Tree("f", "e", "fff"));
        TreeList.add(new Tree("a", "0", "lcc"));
        TreeList.add(new Tree("b", "0", "bbb"));
        TreeList.add(new Tree("c", "0", "ccc"));
        TreeList.add(new Tree("h", "g", "hhhhh"));
        TreeList.add(new Tree("d", "a", "ddd"));
        TreeList.add(new Tree("e", "b", "eee"));
        TreeList.add(new Tree("g", "e", "fff"));
        return TreeList;
    }

    public static void main(String[] args) {
        //初始化数据
        List<Tree> treeList = createTreeList();
        //获取根节点
        List<Tree> trees = getRoot(treeList);
        System.out.println("trees = " + trees);
    }

    public static List<Tree> getRoot(List<Tree> trees) {
        List<Tree> roots = trees.stream().filter(x -> x.getParentId().equals("0")).collect(Collectors.toList());
        //构建树形结构
        roots.stream().forEach(root -> buildTree(root, trees));
        //只保留根节点对象
        return trees.stream().filter(tree -> tree.getParentId().equals("0")).collect(Collectors.toList());
    }

    public static void buildTree(Tree root, List<Tree> trees) {
        trees.stream()
                .forEach(tree -> {
                    if (tree.getParentId().equals(root.getId())) {
                        if (root.getChild() == null) {
                            root.setChild(new ArrayList<>());
                        }
                        root.getChild().add(tree);
                        //把当前节点和整个数组重新
                        buildTree(tree, trees);
                    }
                });
    }
}
