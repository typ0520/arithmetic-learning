package tl;

public class swap_nodes_in_pairs {
    public static void main(String[] args) {
        ListNode head = new ListNode(1){{
          next = new ListNode(2){{
              next = new ListNode(3){{
                  next = new ListNode(4){{
                      next = new ListNode(5){{

                      }};
                  }};
              }};
          }};
        }};
        ListNode cur = swapPairs(head);
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode prev = null, cur = head, res = head != null && head.next != null ? head.next : head;;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next.next;
            if (prev != null) prev.next = cur.next;
            cur.next.next = cur;
            cur.next = next;
            prev = cur;
            cur = next;
        }
        return res;
    }
}
