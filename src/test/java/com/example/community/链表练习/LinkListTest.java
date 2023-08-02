package com.example.community.链表练习;

import java.util.Scanner;

public class LinkListTest {

    public static void main(String[] args){
        // 初始化单链表
        SingleLinkList singleLinkList = new SingleLinkList();

        // 在链表尾部新增结点
        singleLinkList.insert(15);
        singleLinkList.insert(16);
        singleLinkList.insert(17);
        singleLinkList.insert(18);

        Scanner scanner = new Scanner(System.in);
        int operation = 0;
        printList(singleLinkList);
        while (operation!=-1) {
            System.out.println("请选择一个操作：\n 1、遍历输出单链表 \n 2、根据位置查找结点的值 \n 3、根据值查找结点的位置 " +
                    "\n 4、在指定位置插入一个数据元素 \n 5、根据位置删除结点 \n 6、合并一个链表给你看看就行 \n 7、退出程序");
            operation = scanner.nextInt();
            switch (operation) {
                case 1:
                    // 遍历输出单链表
                    printList(singleLinkList);
                    break;
                case 2:
                    System.out.println("请问你要查找第几个结点的值？");
                    int index = scanner.nextInt();
                    System.out.println("第" + index + "个结点的值：" + singleLinkList.getByIndex(index));
                    break;
                case 3:
                    System.out.println("请问你要查找值为多少的结点位置？");
                    Integer data = scanner.nextInt();
                    System.out.println("值为" + data + "的结点位置：" + singleLinkList.getByData(data));
                    break;
                case 4:
                    System.out.println("请问你要在第几个位置插入结点？");
                    index = scanner.nextInt();
                    System.out.println("请问你要插入值为多少的结点？");
                    data = scanner.nextInt();
                    singleLinkList.insertByIndex(index, data);
                    break;
                case 5:
                    System.out.println("请问你要删除哪个位置的结点？");
                    index = scanner.nextInt();
                    singleLinkList.deleteByIndex(index);
                    break;
                case 6:
                    SingleLinkList singleLinkList2 = new SingleLinkList();
                    singleLinkList2.insert(1);
                    singleLinkList2.insert(2);
                    singleLinkList2.insert(3);
                    singleLinkList.marge(singleLinkList2);
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    public static void printList(SingleLinkList singleLinkList){
        System.out.println("单链表的长度："+singleLinkList.getLength());
        singleLinkList.traverse();
    }

}

