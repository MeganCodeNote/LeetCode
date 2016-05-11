
public class RemoveNthFromEnd {
    //------------------- Solution 1 --------------------//
    // 1-pass, 2-pointers, same speed, one is k steps ahead of the other
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (n <= 0 || head == null) {
            return head;
        }

        // move the first pointer n steps into the list
        ListNode iter1 = head;
        while (iter1 != null && n > 0) {
            iter1 = iter1.next;
            n--;
        }
        if (n > 0) {
            return head; // invalid n: n > len
        }

        // move both pointer until iter1 points to null
        ListNode dummy = new ListNode(0); // have to use a dummy to get the the node PREVIOUS to the target node
        dummy.next = head;
        ListNode iter2 = dummy;
        while (iter1 != null) {
            iter2 = iter2.next;
            iter1 = iter1.next;
        }

        iter2.next = iter2.next.next;
        return dummy.next;
    }

    //------------------- Solution 2 --------------------//
    // 2-pass, first count len, then move to the node PREVIOUS to the node to be delete
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // count the len
        ListNode iter = head;
        int len = 0;
        while (iter != null) {
            iter = iter.next;
            len++;
        }
        // check n's validation
        if (n <= 0 || n > len) {
            return head;
        }
        // move to the node previous to the target node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        iter = dummy;
        int steps = len - n;
        while (steps > 0) {
            steps--;
            iter = iter.next;
        }
        // delete the node
        iter.next = iter.next.next;
        return dummy.next;
    }
}

