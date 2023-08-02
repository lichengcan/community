package com.example.community.stream;

import com.example.community.model.entity.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        TreeList.add(new Tree("i", "x", "iiii"));
        return TreeList;
    }

    public static void main(String[] args) {
        //初始化数据
        List<Tree> treeList = createTreeList();
        buildTreeV2(treeList);
        //获取根节点
        List<Tree> trees = getRoot(treeList);
        System.out.println("trees = " + trees);
    }

    public static List<Tree> getRoot(List<Tree> trees) {
        List<Tree> roots = trees.stream().filter(x -> x.getParentId().equals("0")).collect(Collectors.toList());
        //构建树形结构
        roots.stream().forEach(root -> buildTreeV1(root, trees));
        //只保留根节点对象
        return trees.stream().filter(tree -> tree.getParentId().equals("0")).collect(Collectors.toList());
    }

    public static void buildTreeV1(Tree root, List<Tree> trees) {
        trees.stream()
                .forEach(tree -> {
                    if (tree.getParentId().equals(root.getId())) {
                        if (root.getChild() == null) {
                            root.setChild(new ArrayList<>());
                        }
                        root.getChild().add(tree);
                        //把当前节点和整个数组重新
                        buildTreeV1(tree, trees);
                    }
                });
    }

    public static List<Tree> buildTreeV2(List<Tree> trees) {
        //将List转换成map,id作为key可以更方便的拿到父节点、子节点，减少了重复扫描整个列表，降低了时间复杂度
        Map<String, Tree> treeMap = trees.stream().collect(Collectors.toMap(Tree::getId, t -> t));
        //开始构建树
        List<Tree> roots = new ArrayList<>();
        trees.stream().forEach(tree -> {
            //当前节点的爸爸id
            String parentId = tree.getParentId();
            //是不是老大
            if ("0".equals(parentId)) {
                roots.add(tree);
            } else {
                //不是老大
                //拿到爸爸
                Tree parent = treeMap.get(parentId);
                //爸爸没有儿子
                if (parent != null) {
                    //初始化空儿子
                    if (parent.getChild() == null) {
                        parent.setChild(new ArrayList<>());
                    }
                    //给他一个亲生儿子
                    parent.getChild().add(tree);
                }
            }
        });
        return roots;
    }
}
