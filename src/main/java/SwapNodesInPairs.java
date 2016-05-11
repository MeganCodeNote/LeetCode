
public class SwapNodesInPairs {
    //------------- 1. Space constrain & could not change val ---------------//
    public ListNode swapPairs(ListNode head) {
        // input validation, if there is 0 or 1 node
        if(head == null || head.next == null) {
            return head;
        }

        // for there are >= 2 nodes
        // pre: node previous to the pair, post: node after the pair
        // n1, n2: nodes to be swapped
        ListNode dummy = new ListNode(0), pre = dummy;
        dummy.next = head;
        while(pre.next != null && pre.next.next != null) {
            // 1. record
            ListNode n1 = pre.next, n2 = n1.next, post = n2.next;
            // 2. swap
            pre.next = n2;
            n2.next = n1;
            n1.next = post;
            // 3. update pre
            pre = n1;  // ATTENTION: pre = n1 not n2!!!
        }
        return dummy.next;
    }

    //------------- 2. Recursion Version, no space constraint ---------------//
    public ListNode swapPairs2(ListNode head) {
        // base case: num(nodes) <= 1
        if (head == null || head.next == null) {
            return head;
        }

        // general case: num(nodes) >= 2
        ListNode newHead = head.next;
        ListNode rest = head.next.next;
        newHead.next = head;
        head.next = swapPairs2(rest);
        
        return newHead;
    }
}