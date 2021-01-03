package com.erica.summary.practice;

/**
 * @author Erica
 * @date 2021/1/2 21:10
 * @description
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 输入例子1:
 * [2,4,3],[1,1,1]
 *
 * 输出例子1:
 * {3,5,4}
 */
public class liangshuadd {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(1);
        l2.next.next = new ListNode(1);
        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    /**
     * .
     * @param l1 ListNode类 参数1
     * @param l2 ListNode类 参数2
     * @return ListNode类
     */
    public static ListNode addTwoNumbers (ListNode l1, ListNode l2) {
        // write code here
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head;
        int num = 0;
        if (l1.val + l2.val >= 10) {
            num = (l1.val + l2.val)%10;
            head = new ListNode(num);
            head.next = addTwoNumbers(l1.next,l2.next);
            head.next.val += 1;
        } else {
            num = l1.val + l2.val;
            head = new ListNode(num);
            head.next = addTwoNumbers(l1.next,l2.next);
        }
        return head;
    }
}
