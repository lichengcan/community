package com.example.community.十大排序算法;

/**
 * @author: lichengcan
 * @date: 2023-08-07 15:04
 * @description 它的工作原理是将未排序的元素逐个插入到已排序部分的正确位置，直到所有元素都被排序。这个过程类似于我们打扑克牌时整理牌的过程。
 **/
public class InsertionSort {


    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("原始数组：");
        printArray(array);

        insertionSort(array);

        System.out.println("\n排序后的数组：");
        printArray(array);
    }

    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // 将比key大的元素向右移动，为key腾出插入位置
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            // 插入key到正确位置
            array[j + 1] = key;
        }
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
