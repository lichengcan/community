package com.example.community.十大排序算法;

/**
 * @author: lichengcan
 * @date: 2023-08-07 15:04
 * @description
 **/
public class 冒泡排序 {
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("原始数组：");
        printArray(arr);

        bubbleSort(arr);

        System.out.println("\n排序后的数组：");
        printArray(arr);
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // 标记当前循环是否有交换操作，如果没有说明数组已经有序，可以提前结束排序
            boolean swapped = false;

            // 每次循环把未排序部分的最大值冒泡到数组的末尾
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换两个元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // 标记有交换操作
                    swapped = true;
                }
            }

            // 如果没有交换操作，说明数组已经有序，直接退出循环
            if (!swapped) {
                break;
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

}
