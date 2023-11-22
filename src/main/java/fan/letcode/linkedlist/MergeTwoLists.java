package fan.letcode.linkedlist;

/**
 * 合并两个有序链表 <br>
 * 题目: 将两个升序链表合并为一个新的升序链表并返回, 新链表是通过拼接给定的两个链表的所有节点组成的 <br>
 * 如: l1 = [1,2,4], l2 = [1,3,4], 输出: [1,1,2,3,4,4]
 *
 * @author Fan
 * @since 2023/5/19 16:25
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        ListNode possibilities = mergeTwoLists(new ListNode(1), new ListNode(2));

        while (null != possibilities) {
            System.out.println(possibilities.val);
            possibilities = possibilities.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode tail = head;

        while (null != list1 && null != list2) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        tail.next = null == list1 ? list2 : list1;
        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
