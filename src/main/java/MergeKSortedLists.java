import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class MergeKSortedLists {

    /*------------------- Solution 1 ------------------------*/
    // O(nklg(k))
    public ListNode mergeKLists(ListNode[] lists) {
        // input validation
        if (lists == null || lists.length == 0) {
            return null;
        }
        // create a min heap for mergeing
        Queue<ListNode> q = new PriorityQueue<ListNode>(lists.length,
            new Comparator<ListNode>(){
                @Override
                public int compare(ListNode a, ListNode b) {
                    return a.val - b.val;
                }
            });
        // put fist node of each list in to the queue
        ListNode dummy = new ListNode(0), tail = dummy;
        for (ListNode node : lists) {
            if (node != null) {
                q.offer(node);
            }
        }
        // poll min node X out, add X.next to the queue (always keep queue size == k)
        while (!q.isEmpty()) {
            ListNode x = q.poll();
            if (x.next != null) {
                q.offer(x.next);
            }
            tail.next = x;
            tail = tail.next;
        }
        return dummy.next;
    }
    
    
    /*------------------- Solution 2 ------------------------*/
    // Divide and conquer: O(nklg(k)) competition tree
    public ListNode mergeKLists2(ListNode[] lists) {
        // input validation
        if (lists == null || lists.length == 0) {
            return null;
        }
        return helper(lists, 0, lists.length - 1);
    }

    private ListNode helper(ListNode[] lists, int l, int r) {
        // think about [l=0,r=0], [l=0,r=1], [l=0,r=2]..
        if (l == r) {
            return lists[l];
        } else {
            int m = l + (r - l) / 2;
            ListNode n1 = helper(lists, l, m);
            ListNode n2 = helper(lists, m + 1, r);
            return merge(n1, n2);
        }
    }

    private ListNode merge(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                tail.next = n1;
                n1 = n1.next;
            } else {
                tail.next = n2;
                n2 = n2.next;
            }
            tail = tail.next;
        }
        tail.next = (n1 == null) ? n2 : n1;
        return dummy.next;
    }
}
