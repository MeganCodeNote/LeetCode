
public class ReverseNodesInKGroup {
    //------------------- Solution 1 ------------------//
    // this is the solution for reversing all groups
    // even if that last group has less than k nodes!!
    // (this is for algorithm note, not meet the LeetCode requirement)
    public ListNode reverseKGroup(ListNode head, int k) {
        // input validation
        if (head == null || k <= 1) {
            return head;
        }

        // reverse
        ListNode dummy = new ListNode(0), pre = dummy;
        dummy.next = head;
        while (pre.next != null) {
            ListNode tail = pre.next;
            for (int i = 0; i < k - 1 && tail.next != null; i++) {
                ListNode cur = tail.next;
                tail.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            pre = tail;
        }
        return dummy.next;
    }

    //------------------- Solution 2 ------------------//
    // This solution meet the requirement that the last group that
    // has less than k nodes won't be reversed
    // this solution is a revised version of solution 1
    public ListNode reverseKGroup2(ListNode head, int k) {
        // input validation
        if (head == null || k <= 1) {
            return head;
        }

        // reverse
        ListNode dummy = new ListNode(0), pre = dummy;
        dummy.next = head;
        while (pre.next != null) {
            ListNode tail = pre.next;
            // see if there are enough nodes in the current group
            int n = k;
            while (tail != null && n > 0) {
                tail = tail.next;
                n--;
            }
            if (n > 0) {
                break;
            }
            // if there are enough nodes, reverse them
            tail = pre.next;
            for (int i = 0; i < k - 1; i++) {
                ListNode cur = tail.next;
                tail.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            pre = tail;
        }
        return dummy.next;
    }


    ///////////////////// TEST ///////////////////////
    private static void test(ReverseNodesInKGroup solution, int[] x, int k) {
        ListNode list = ListNode.fromArray(x);
        ListNode res = solution.reverseKGroup2(list, k);
        System.out.println(res);
    }

    public static void main(String[] args) {
        ReverseNodesInKGroup solution = new ReverseNodesInKGroup();
        int[] x = {1, 2, 3, 4, 5};
        // k <= 1
        test(solution, x, -1);
        test(solution, x, 0);
        test(solution, x, 1);
        // valid k
        test(solution, x, 3);
        // k == len of list
        test(solution, x, 5);
        // k > len of list
        test(solution, x, 7);
    }
}

