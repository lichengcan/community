package com.example.community.链表练习;

public class LinkedList {

    //表示头节点
    private Node head;

    /**
     * 节点
     */
    private class Node {
        //数据
        int data;
        //指针（指向下一个节点）
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        head = null;
    }

    /**
     * 向链表中添加节点
     * @param data
     */
    public void addNode(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            //判断是不是到了链表的末尾
            while (current.next != null) {
                //一直找到末尾
                current = current.next;
            }
            //链表末尾的指针指向新添加的节点
            current.next = newNode;
        }
    }

    /**
     * 打印所有节点数据
     */
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }



    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.printList(); // Output: 1 2 3
    }
}
