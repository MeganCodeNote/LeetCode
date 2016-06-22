
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        // input validation
        if (head == null) {
            return null;
        }
        // partition
        ListNode dummy1 = new ListNode(0), tail1 = dummy1; // shortcut: h for head, t for tail
        ListNode dummy2 = new ListNode(0), tail2 = dummy2;
        while (head != null) {
            if (head.val < x) {
                tail1.next = head;
                tail1 = tail1.next;
            } else {
                tail2.next = head;
                tail2 = tail2.next;
            }
            head = head.next;
        }
        tail1.next = dummy2.next;
        tail2.next = null;     // don't forget to set the tail.next = null
        return dummy1.next;
    }

    ////////////////  TEST  /////////////////////
    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, 5, 2, 1, 3};
        ListNode list = ListNode.fromArray(x);
        PartitionList solution = new PartitionList();
        System.out.println(solution.partition(list, 3));
    }
}
