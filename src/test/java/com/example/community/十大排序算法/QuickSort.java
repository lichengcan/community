package com.example.community.十大排序算法;

public class QuickSort {

    public static void main(String[] args) {
//        int[] array = {64, 34, 25, 12, 22, 11, 90};
        int[] array = {64, 12,34, 25, 12, 22, 11, 90,111,2,7,1,88,33};
        int[] array2 = {64, 12,34, 25, 12, 22, 11, 90,111,2,7,1,88,33};


        System.out.println("原始数组：");
        printArray(array2);

        quickSort2(array2,0,array2.length-1);

        System.out.println("\n排序后的数组：");
        printArray(array2);

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
        //基准元素
        int pivot = array[high]; // 选择最右边的元素作为基准
        //定义一个指针 left
        int left = low - 1;

        for (int j = low; j < high; j++) {
            //当前数和最后一个元素比较
            if (array[j] <= pivot) {
                left++;
                // 交换元素，将小于等于基准元素的元素放到基准元素的左侧
                int temp = array[left];
                array[left] = array[j];
                array[j] = temp;
                //指针右移
            }
        }

        // 交换基准元素到正确的位置
        int temp = array[left + 1];
        array[left + 1] = array[high];
        array[high] = temp;

        return left + 1; // 返回基准元素的索引位置
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void quickSort2(int[] a, int left, int right)
    {
        if(left>right)
            return;
        int pivot=a[left];//定义基准值为数组第一个数
        int i=left;
        int j=right;

        while(i<j)
        {
            while(pivot<=a[j]&&i<j)//从右往左找比基准值小的数
                j--;
            while(pivot>=a[i]&&i<j)//从左往右找比基准值大的数
                i++;
            if(i<j)                     //如果i<j，交换它们
            {
                int temp=a[i];
                a[i]=a[j];
                a[j]=temp;
            }
        }
        a[left]=a[i];
        a[i]=pivot;//把基准值放到合适的位置
        quickSort(a,left,i-1);//对左边的子数组进行快速排序
        quickSort(a,i+1,right);//对右边的子数组进行快速排序
    }
}
