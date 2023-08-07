package com.example.community.十大排序算法;

/**
 * @author: lichengcan
 * @date: 2023-08-07 15:04
 * @description 每次从未排序部分选择最小（或最大）的元素，并将其放在已排序部分的末尾。
 **/
public class SelectionSort {

    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("原始数组：");
        printArray(array);

        selectionSort(array);

        System.out.println("\n排序后的数组：");
        printArray(array);
    }

    public static void selectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            // 假设当前未排序部分的第一个元素是最小的
            int minIndex = i;

            // 在未排序部分中寻找最小元素的索引
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // 将最小元素交换到已排序部分的末尾
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

}
