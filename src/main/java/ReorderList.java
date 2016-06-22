
public class ReorderList {
    //------------------ Solution 1 ---------------------//
    // this is O(N^2)
    // Time Limit Exceed
    // But this is the most obvious solution
    public void reorderList(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            // step 1: find the node previous to the tail node
            ListNode beforeTail = cur;
            while (beforeTail.next.next != null) {
                beforeTail = beforeTail.next;
            }

            // step 2: insert the tail node after cur
            ListNode tail = beforeTail.next;
            beforeTail.next = tail.next;

            ListNode afterCur = cur.next;
            cur.next = tail;
            tail.next = afterCur;

            // step 3: update cur
            cur = cur.next.next;
        }
    }

    //------------------ Solution 2 ---------------------//
    // O(N)
    public void reorderList2(ListNode head) {
        // input validation: num(nodes) <= 1
        if (head == null || head.next == null) {
            return;
        }

        // step 1: find the middle node
        ListNode tail1 = findMiddle(head);
        ListNode head2 = tail1.next;
        tail1.next = null; // don't forget this!!!

        // step 2: reverse the 2nd half
        head2 = reverse(head2);

        // step 3: merge 1st and 2nd halves
        merge(head, head2);
    }

    private ListNode findMiddle(ListNode head) {
        // assume that there are at least 2 nodes
        ListNode walker = head, runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }
        return walker;
    }

    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    private void merge(ListNode head1, ListNode head2) {
        // len(head1) >= len(head2) according to problem requirment
        ListNode dummy = new ListNode(0), tail = dummy;
        while (head2 != null) {
            ListNode a = head1;
            ListNode b = head2;
            head1 = head1.next;
            head2 = head2.next;
            tail.next = a;
            a.next = b;
            tail = b;
        }
        tail.next = head1;
    }


    ///////////////////  TEST  ///////////////////
    public static void test(ReorderList solution, int[] x) {
        ListNode list = ListNode.fromArray(x);
        solution.reorderList2(list);
        System.out.println(list);
    }
    public static void main(String[] args) {
        ReorderList solution = new ReorderList();
        int[] x1 = {1};
        int[] x2 = {1, 2};
        int[] x3 = {1, 2, 3, 4, 5};
        int[] x4 = {1, 2, 3, 4, 5, 6};

        test(solution, x1);
        test(solution, x2);
        test(solution, x3);
        test(solution, x4);
    }
}
