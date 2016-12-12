
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode walker = head, runner = head;
        boolean foundCycle = false;
        while (runner != null && runner.next != null) {
            // try to first move
            walker = walker.next;
            runner = runner.next.next;

            // then compare (other wise, will stop on initial enter!!)
            if (runner == walker) {
                foundCycle = true;
                break;
            }
        }
        if (!foundCycle) {
            return null;
        }

        runner = head;
        while (runner != walker) {
            runner = runner.next;
            walker = walker.next;
        }
        return runner;
    }
}

// ERROR : in the loop
//          while (runner != null && runner.next != null) {...}
//          need to update first and then compare
//          otherwise when we first enter the loop, runner == walker satisfied!! Bug!!
//
