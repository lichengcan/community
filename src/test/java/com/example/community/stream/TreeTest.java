package com.example.community.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeTest {
    private int id;
    private int parentId;
    private List<TreeTest> children = new ArrayList<>();

    public TreeTest(int id, int parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public List<TreeTest> getChildren() {
        return children;
    }

    public static List<TreeTest> buildTree(List<TreeTest> nodes) {
        List<TreeTest> rootNodes = nodes.stream()
                .filter(node -> node.getParentId() == 0)
                .collect(Collectors.toList());

        for (TreeTest rootNode : rootNodes) {
            buildTree(rootNode, nodes);
        }

        return rootNodes;
    }

    private static void buildTree(TreeTest parent, List<TreeTest> nodes) {
        for (TreeTest node : nodes) {
            if (node.getParentId() == parent.getId()) {
                parent.getChildren().add(node);
                buildTree(node, nodes);
            }
        }
    }

    public static void main(String[] args) {
        // 构造测试数据
        List<TreeTest> nodes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            nodes.add(new TreeTest(i, (i % 3 == 0) ? 0 : i - 1));
        }

        // 构建树形结构
        List<TreeTest> rootNodes = buildTree(nodes);

        // 输出树形结构
        for (TreeTest rootNode : rootNodes) {
            printTree(rootNode, 0);
        }
    }

    private static void printTree(TreeTest node, int level) {
        for (TreeTest child : node.getChildren()) {
            printTree(child, level + 1);
        }
    }
}
