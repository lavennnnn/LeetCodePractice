package Methods;

import Structures.ListNode;

public class ListMethod {

    //反转链表
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while(cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    //反转链表II
    public static ListNode reverseListII(ListNode head, int left, int right) {
        //头节点有很多情况，所以用虚拟头节点避免分类讨论
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode p0 = dummyNode;
        //step1：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        for (int i = 0; i < left - 1; i++) {
            p0 = p0.next;
        }

        ListNode pre = null, cur = p0.next;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }

        p0.next.next = cur;
        p0.next = pre;
        return dummyNode.next;
    }

    // 234. 回文链表
    public static boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode slow = head, fast = head;
        //找到前半部分链表
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //反转后半部分链表
        ListNode secondHalfStart = reverseList(slow.next);

        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        slow.next = reverseList(secondHalfStart);
        return true;
    }

}
