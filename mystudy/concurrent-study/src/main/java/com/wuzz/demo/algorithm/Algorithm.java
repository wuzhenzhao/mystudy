package com.wuzz.demo.algorithm;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/25 11:22
 * @since 1.0
 **/
public class Algorithm {


    /**
     * 判断是否有环
     * 首先创建两个指针p1和p2（在Java里就是两个对象引用），让它们同时指向这
     * 个链表的头节点。然后开始一个大循环，在循环体中，让指针p1每次向后移动1个节
     * 点，让指针p2每次向后移动2个节点，然后比较两个指针指向的节点是否相同。如果
     * 相同，则可以判断出链表有环，如果不同，则继续下一次循环。
     * <p>
     * 环长 = 每一次速度差 × 前进次数 = 前进次数。
     * <p>
     * 也就是说，从链表头结点到入环点的距离，等于从首次相遇点绕环n-1圈再回到
     * 入环点的距离。
     * 这样一来，只要把其中一个指针放回到头节点位置，另一个指针保持在首次相
     * 遇点，两个指针都是每次向前走1步。那么，它们最终相遇的节点，就是入环节点。
     *
     * @param head 链表头节点
     */
    public static boolean isCycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 链表节点
     */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws Exception {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        System.out.println(isCycle(node1));
    }
}
