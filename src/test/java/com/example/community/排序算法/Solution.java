package com.example.community.排序算法;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{2, 1, 4}, {3, 3, 1}};
        final int i = deleteGreatestValue(grid);
        System.out.println("i = " + i);
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
