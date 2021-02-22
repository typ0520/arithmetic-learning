package com.example;

public class he_bing_liang_ge_pai_xu_de_lian_biao_lcof {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode node = new Solution().mergeTwoLists(l1, l2);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode head = null, tail = null, tmp = null;
            while (l1 != null || l2 != null) {
                if (l1 == null) {
                    tmp = l2;
                    l2 = l2.next;
                } else if (l2 == null) {
                    tmp = l1;
                    l1 = l1.next;
                } else {
                    if (l1.val <= l2.val) {
                        tmp = l1;
                        l1 = l1.next;
                    } else {
                        tmp = l2;
                        l2 = l2.next;
                    }
                }
                if (head == null) {
                    head = tmp;
                } else {
                    tail.next = tmp;
                }
                tail = tmp;
            }
            return head;
        }
    }
}
