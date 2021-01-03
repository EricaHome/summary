package com.erica.summary.practice;

/**
 * @author Erica
 * @date 2021/1/2 21:09
 * @description TODO
 */
public class ListNode {
    int val;
    ListNode next = null;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
