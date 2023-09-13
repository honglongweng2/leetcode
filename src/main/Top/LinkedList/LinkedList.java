package Top.LinkedList;

import pre.ListNode;

public class LinkedList {
    /**
     * No.141 判断是否成环
     *
     * 思路：经典快慢指针
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fastPoint = head, slowPoint = head;
        while (fastPoint != null && fastPoint.next != null) {
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
            if (fastPoint == slowPoint) {
                return true;
            }
        }
        return false;
    }

    /**
     * No.2 add two number（有进位）
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            sum = sum % 10;
            prev.next = new ListNode(sum);
            prev = prev.next;
        }
        if (carry == 1) {
            prev.next = new ListNode(1);
        }
        return head.next;
    }

    /**
     * No.21 链表合并
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                prev.next = list2;
                prev = prev.next;
                list2 = list2.next;
            } else {
                prev.next = list1;
                prev = prev.next;
                list1 = list1.next;
            }
        }
        if (list1 != null) prev.next = list1;
        if (list2 != null) prev.next = list2;
        return head.next;
    }
}
