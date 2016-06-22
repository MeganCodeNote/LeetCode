

public class RemoveListDupes {
    //------------------- Solution 1 ----------------------//
    // two pointers, 1 points to end of valid list,
    // another move one-by-one along original list
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (head != null) {
            if (dummy.next == null || head.val != tail.val) {
                tail.next = head;
                tail = tail.next;
            }
            head = head.next;
        }
        tail.next = null; // don't forget this!
        return dummy.next;
    }

    //------------------- Solution 2 ----------------------//
    // one pinter, examine my next node to determine what to do
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode p = head;
        while (p != null) {
            if (p.next == null || p.val != p.next.val) {
                p = p.next;
            } else {
                p.next = p.next.next;
            }
        }
        return head;
    }


    ///////////////////////  TEST  //////////////////////
    private static void test(RemoveListDupes solution, int[] list) {
        ListNode head = ListNode.fromArray(list);
        ListNode l = solution.deleteDuplicates(head);
        System.out.println(l);
    }
    public static void main(String[] args) {
        RemoveListDupes solution = new RemoveListDupes();

        int[] x1 = {1,1,2,3,3};
        test(solution, x1);

        int[] x2 = {1,1,1,1,1};
        test(solution, x2);

        int[] x3 = {1,1,1,2,3,3,3,3,4,4,5};
        test(solution, x3);
    }
}