
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // assume 1) head is non-null 2) 1 <= m <= n <= len of list
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // step 1: move the pre pinter to the node previous to the node needed to be reversed
        ListNode pre = dummy;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        // step 2: reverse the target sub list one by one using the classic reverse algorithm
        ListNode tail = pre.next;
        for (int i = 0; i < n - m; i++) {
            ListNode cur = tail.next;
            tail.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
        }
        return dummy.next;
    }
    
    /////////////////////    TEST    /////////////////////////
    private static void test(ReverseLinkedList2 solution, int[] x, int m, int n) {
        ListNode list = ListNode.fromArray(x);
        ListNode res = solution.reverseBetween(list, m, n);
        System.out.println(res.toString());
    }

    public static void main(String[] args) {
        ReverseLinkedList2 solution = new ReverseLinkedList2();
        int[] x1 = {1, 2, 3, 4, 5};
        test(solution, x1, 1, 2);

        int[] x2 = {1, 2, 3, 4, 5};
        test(solution, x2, 1, 5);
    }
}
