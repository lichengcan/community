package com.example.community.链表练习.双链表;

/**
 * @author: lichengcan
 * @date: 2023-08-02 10:22
 * @description
 **/
public class TowWayLinkList<T> {
    //首结点
    Node head;
    //链表的长度
    int N;
    Node last;
    //结点类
    public class Node {
        //数据域
        public T item;
        //头指针
        public Node pre;
        //尾指针
        public Node next;

        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }
    //初始化链表
    public void TwoWayLinkList() {
        //初始化头结点和尾结点
        this.head = new Node(null, null, null);
        this.last = new Node(null, null, null);
        //初始化元素个数
        N = 0;
    }

    //清空链表
    public void clear() {
        this.head.next = null;
        this.last = null;
        this.N = 0;
    }

    //获取链表长度
    public int getLength() {
        return N;
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return N == 0;
    }
    //获取第一个元素
    public T getFirst() {
        //需要判断链表是否为空
        if (isEmpty()) {
            return null;
        }
        return head.next.item;
    }

    //获取最后一个元素
    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.item;
    }

    //插入元素t
    public void add(T t) {
        //如果链表为空
        if (isEmpty()) {
            //创建新结点
            Node newNode = new Node(t, head, null);
            //让新节点为尾结点
            last = newNode;
            //让头结点指向尾结点
            head.next = last;
        } else {
            //如果链表不为空
            Node oldLast = last;
            //创建新节点
            Node newNode = new Node(t, oldLast, null);
            //让当前的尾结点指向新结点
            oldLast.next = newNode;
            //让新节点成为尾结点
            last = newNode;
        }
        N++;
    }

    //向指定位置i位置插入元素t
    public void insert(int i,T t){
        //找到i位置的前一个节点
        Node a=head;
        for (int j = 0; j < i; j++) {
            a=a.next;
        }
        //找到i位置的节点
        Node curr = a.next;
        //创建新节点
        Node newNode = new Node(t, a, curr);
        //让i位置的前一个节点的下一个节点变为新节点
        a.next=newNode;
        //让i位置的前一个节点变为新节点
        curr.pre=newNode;
        //元素个数加1
        N++;
    }

    //获取指定位置i处的元素
    public T get(int i){
        Node a=head;
        for (int j = 0; j <i; j++) {
            a=a.next;
        }
        return a.item;
    }

    //找到元素t第一次出现的位置
    public int indexOf(T t){
        Node a=head;
        for (int i = 0; i < N; i++) {
            a=a.next;
            if(a.item==t) return i;
        }
        return -1;
    }

    //删除位置i处的元素,并返回该元素
    public T remove(int i){
        Node a=head;
        //找到i节点
        for (int j = 0; j < i; j++) {
            a=a.next;
        }
        if (a.next!=null) {
            //如果删除2
            //（1 2 3 等于是 3->1）
            a.next.pre=a.pre;
            //(1 2 3 等于是 1 -> 3)
            a.pre.next=a.next;
        }

        N--;
        return a.item;
    }

    public static void main(String[] args) {
        TowWayLinkList towWayLinkList = new TowWayLinkList();
        towWayLinkList.TwoWayLinkList();
        towWayLinkList.add(1);
        towWayLinkList.add(2);
        towWayLinkList.add(3);
        towWayLinkList.remove(1);
        System.out.println(towWayLinkList.getLength());
        System.out.println("towWayLinkList = " + towWayLinkList);
        System.out.println(towWayLinkList.get(0));
        System.out.println(towWayLinkList.get(1));
        System.out.println(towWayLinkList.get(2));

    }
}
