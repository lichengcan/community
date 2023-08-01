package com.example.community.排序算法;

import com.example.community.model.entity.ListNode;

import java.util.*;

class Solution {

    public static void main(String[] args) {

        //test1-删除每行中最大的值
        int[][] grid = new int[][]{{2, 1, 4}, {3, 3, 1}};
        final int i = deleteGreatestValue(grid);
//        System.out.println("i = " + i);

        //test2-和等于目标值的质数对
        final List<List<Integer>> primePairs = findPrimePairs(100);
//        System.out.println("primePairs = " + primePairs);
        final boolean prime = isPrime(95);
//        System.out.println("prime = " + prime);

        //test3-相交链表
//        给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
        ListNode nodeSta = new ListNode(0);//创建首节点
        ListNode nextNode;//声明一个变量用来在移动过程中指向当前节点
        nextNode=nodeSta;//指向首节点
        //创建链表
        for (int h = 1; h <= 10; h++) {
            ListNode node = new ListNode(h);
            nextNode.next=node;
            nextNode=nextNode.next;
        }

        nextNode=nodeSta;//重新赋值让它指向首节点
        print(nextNode);
    }

    static void print(ListNode listNoed){
        //创建链表节点
        while(listNoed!=null){
            System.out.println("节点:"+listNoed.val);
            listNoed=listNoed.next;
        }
        System.out.println();
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //创建set集合
        Set<ListNode> listNodes= new HashSet<>();
        //将A放到temp中
        ListNode temp = headA;
        //temp放到set集合中
        while (temp!=null){
            listNodes.add(temp);
            temp=temp.next;
        }
        temp = headB;
        //遍历headB，判断set集合中是否包含B
        while (temp != null) {
            if (listNodes.contains(temp)) {
                return temp;
            }
        }
        return null;
    }


    /**
     * 计算质数对
     *
     * @param n
     * @return
     */
    public static List<List<Integer>> findPrimePairs(int n) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (1 <= j && j <= i && i <= n) {
                    if (i + j == n) {
                        if (isPrime(i) && isPrime(j)) {
                            List<Integer> list = new ArrayList<>();
                            list.add(j);
                            list.add(i);
                            result.add(list);
                        }
                    }
                }
            }
        }
        for (List<Integer> list : result) {
            Collections.sort(list);
        }
        return result;
    }

    /**
     * 判断一个数是否是质数
     * 质数：质数的约数只有1和本身
     *
     * @param n
     * @return
     */
    public static boolean isPrime(int n) {
        //n<3时，质数有2和3
        if (n < 3) {
            return n > 1;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除每行中最大的值
     * 删除的元素中最大值与答案相加
     *
     * @param grid
     * @return
     */
    public static int deleteGreatestValue(int[][] grid) {
        //行
        int m = grid.length;
        //列
        int n = grid[0].length;
        //先排序
        for (int i = 0; i < m; i++) {
            Arrays.sort(grid[i]);
        }
        //删除每一列中最大的
        int res = 0;
        for (int j = 0; j < n; j++) {
            int mx = 0;
            for (int i = 0; i < m; i++) {
                //拿到列中最大值
                mx = Math.max(mx, grid[i][j]);
            }
            //每列最大值累加
            res += mx;
        }
        return res;
    }


}
