package com.example.community.链表练习;

public class LinkListNode {

    private Integer data; // 结点的数据域

    private LinkListNode next; // 下一个结点

    public LinkListNode(){
    }

    public LinkListNode(Integer data){
        this.data = data;
        this.next = null;
    }
    public LinkListNode(Integer data,LinkListNode next){
        this.data = data;
        this.next = next;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public LinkListNode getNext() {
        return next;
    }

    public void setNext(LinkListNode next) {
        this.next = next;
    }


}

