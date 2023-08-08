package com.example.community.十大排序算法;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("原始数组：");
        printArray(array);

        quickSort(array, 0, array.length - 1);

        System.out.println("\n排序后的数组：");
        printArray(array);
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // 分区，获取基准元素的索引位置
            int pivotIndex = partition(array, low, high);

            // 对基准元素左侧的子数组进行排序
            quickSort(array, low, pivotIndex - 1);

            // 对基准元素右侧的子数组进行排序
            quickSort(array, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        //
        int pivot = array[high]; // 选择最右边的元素作为基准
        //定义一个指针
        int i = low - 1;

        for (int j = low; j < high; j++) {
            //当前数和最后一个元素比较
            if (array[j] <= pivot) {
                //指针右移
                i++;
                // 交换元素，将小于等于基准元素的元素放到基准元素的左侧
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // 交换基准元素到正确的位置
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1; // 返回基准元素的索引位置
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
