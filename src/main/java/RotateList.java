
public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        // input checking
        if (head == null || n <= 0) {
            return head;
        }
        // count number of nodes in the list
        int num = 1;
        ListNode iter = head;
        while (iter.next != null) { // stop at tail node
            num++;
            iter = iter.next;
        }
        // make the list a cycle and find the new tail
        iter.next = head;
        for (int i = 0; i < num - n % num; i++) {
            iter = iter.next;
        }
        head = iter.next;
        iter.next = null;
        return head;
    }
    
    
    ///////////////////  TEST  ///////////////////
    public static void test(RotateList solution, int[] x, int k) {
        ListNode n = ListNode.fromArray(x);
        System.out.println(solution.rotateRight(n, k));

    }
    public static void main(String[] args) {
        RotateList solution = new RotateList();
        int[] x = {1, 2, 3, 4, 5};
        // k <= 0
        test(solution, x, -1);
        test(solution, x, 0);
        // k = 1
        test(solution, x, 1);
        // k < len
        test(solution, x, 3);
        // k == len
        test(solution, x, 5);
        // k > len
        test(solution, x, 8);
    }
}

