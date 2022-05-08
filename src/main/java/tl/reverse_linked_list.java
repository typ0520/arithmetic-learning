package tl;

public class reverse_linked_list {
    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        node = new Solution().reverseList(node);

        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    static class Solution {
        public ListNode reverseList(ListNode head) {
            //return reverse1(head);
//            reverse2(head);
//            return ans;
            return reverse3(head);
        }

        public ListNode reverse1(ListNode head) {
            ListNode prev = null, next = null, cur = head;
            while (cur != null) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }

        ListNode ans = null;

        public ListNode reverse2(ListNode head) {
            if (head == null) return null;
            ListNode result = reverse2(head.next);
            if (result != null) {
                result.next = head;
                head.next = null;
            } else {
                ans = head;
            }
            return head;
        }

        public ListNode reverse3(ListNode head) {
            if (head == null) return null;
            ListNode result = reverse3(head.next);
            if (result != null) {
                result.next = head;
                head.next = null;
            }
            return head;
        }
    }
}
