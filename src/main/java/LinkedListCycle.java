import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    //------------------ Solution 1 ------------------//
    // Two-pointers (walker & runner)
    // NOTE: this is a very basic template for using slow and fast walker!!
    // Time: O(n)  Space: O(1)
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode walker = head, runner = head;
        while (runner != null && runner.next != null) {
            // try to first move
            walker = walker.next;
            runner = runner.next.next;

            // then compare (other wise, will stop on initial enter!!)
            if (runner == walker) {
                return true;
            }
        }
        return false;
    }

    //------------------ Solution 2 ------------------//
    // HashTable
    // Time/Space: O(n)
    public boolean hasCycle2(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<ListNode>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }
}
