package com.example.community.链表练习;

public class SingleLinkList {

    private LinkListNode head;//定义单链表的头结点

    private int length;//用来记录单链表的长度

    /**
     * 单链表的初始化 init() 无参
     */
    public SingleLinkList(){
        this.head = new LinkListNode();
    }

    /**
     * 单链表的初始化 init() 有参
     * */
    public SingleLinkList(Integer data,LinkListNode next){
        this.head = new LinkListNode();
        LinkListNode node = new LinkListNode();
        node.setData(data);
        node.setNext(next);
        head.setNext(node);
    }

    /**
     * 求单链表长度
     */
    public int getLength(){
        return length;
    }

    /**
     * 新增：默认在最后插入一个数据元素；
     */
    public LinkListNode insert(Integer data){
        LinkListNode newNode = new LinkListNode(data);
        LinkListNode p = head;
        while (p.getNext()!=null){
            p = p.getNext();
        }
        p.setNext(newNode);
        length++;
        return newNode;
    }

    /**
     * 新增：在位置i插入一个数据元素；
     */
    public void insertByIndex(int index,Integer data){
        // 如果位置i大于了单链表的长度 或者 位置不合理 则直接将新结点添加到最后
        if(index>length||index-1<0){
            insert(data);
            return;
        }
        index--;
        // 将指针p指向首元结点
        LinkListNode p = head;
        while (index!=0){
            p=p.getNext();
            index--;
        }
        LinkListNode newNode = new LinkListNode(data,p.getNext());
        p.setNext(newNode);
        length++;
    }

    /**
     * 查找：按位置查找
     */
    public Integer getByIndex(int index){
        // 如果查找的元素大于了单链表的长度则返回null
        if(index>length){
            return null;
        }
        // 将指针p指向头结点
        LinkListNode p = head;
        while (index!=0){
            p=p.getNext();
            index--;
        }
        return p.getData();
    }

    /**
     * 查找：按值查找
     */
    public int getByData(Integer data){
        // 将指针p指向首元结点
        LinkListNode p = head;
        int index = 0;
        while (p.getNext()!=null){
            p=p.getNext();
            index++;
            if(p.getData().equals(data)){
                return index;
            }
        }
        if(index==length&&!p.getData().equals(data)){
            return 0;
        }
        return index;
    }

    /**
     * 查找：遍历单链表
     */
    public void traverse(){
        LinkListNode p = head;
        while (p.getNext()!=null){
            p = p.getNext();
            System.out.println("结点值："+p.getData());
        }
    }

    /**
     * 删除：按位置i删除数据元素；
     */
    public Integer deleteByIndex(int index){
        // 如果位置i大于了单链表的长度则返回null
        if(index>length||index-1<0){
            return null;
        }
        // 提前将index-- 是为了将p指针移到被删除元素的前一个位置
        index--;
        // 将指针p指向头结点
        LinkListNode p = head;
        while (index!=0){
            p=p.getNext();
            index--;
        }
        int res = p.getNext().getData();
        p.setNext(p.getNext().getNext());
        length--;
        return res;
    }

    /**
     * 合并链表
     */
    public void marge(SingleLinkList singleLinkList){
        LinkListNode p = head;
        length=length+singleLinkList.length;
        while (p.getNext()!=null){
            p = p.getNext();
        }
        p.setNext(singleLinkList.head.getNext());
    }
}

