package com.example.community.链表练习;

public class SingleLinkedList {

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

    public SingleLinkedList() {
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


    /**
     * 从链表中删除值为val的节点
     *
     * @param headNode 链表头节点
     * @param val 要删除的节点值
     * @return 删除后的链表头节点
     */
    public static Node removeElements(Node headNode, int val) {
        //判断头节点是否为空
        if (headNode == null) {
            return null;
        }
        //前驱节点
        Node prev = headNode;
        //当前节点
        Node cur = headNode.next;
        //当前节点不为空：
        while(cur != null) {
            //判断当前节点是否为需要删除的节点
            if (cur.data == val) {
                //前驱节点指向当前节点的下一个节点，跳过当前节点
                prev.next = cur.next;
                //当前节点指向下一个节点，继续进行遍历链表
                cur = cur.next;
            } else {
                //前驱节点指向当前节点
                prev = cur;
                //当前节点指向下一节点
                cur = cur.next;
            }
        }
        //只包含头节点的情况
        if (headNode.data == val) {
            //头节点指向下一个节点，下一个节点是空
            headNode = headNode.next;
        }
        // 返回删除后的链表头节点
        return headNode;
    }



    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.printList(); // Output: 1 2 3
        Node node = removeElements(list.head, 1);
        while (node!= null) {
            System.out.println("node = " + node.data);
            node=node.next;
        }
    }
}
