import java.util.HashMap;
import java.util.Map;

public class CopyListRandomPointer {
    //------------------- Solution 1 --------------------------//
    // O(n) time, O(1) space
    // use the old structure to guide the new structure
    public RandomListNode copyRandomList(RandomListNode head) {
        // input validation
        if (head == null) {
            return head;
        }

        // step 1: interleave new clone of node after the old one,
        RandomListNode iter = head;
        while (iter != null) {
            RandomListNode afterIter = iter.next;
            RandomListNode newNode = new RandomListNode(iter.label);
            iter.next = newNode;
            newNode.next = afterIter;
            newNode.random = iter.random;
            iter = afterIter;
        }

        // step 2: link the new node's random link to the right one
        iter = head;
        while (iter != null) {
            if (iter.random != null) {  // REMEMBER FOR checking!!
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // step 3: separate two list
        RandomListNode newHead = head.next;
        iter = head;
        while (iter != null && iter.next != null) {  // think about the condition
            RandomListNode afterIter = iter.next;
            iter.next = iter.next.next;
            iter = afterIter;
        }

        // step 3: return the new head
        return newHead;
    }


    //------------------- Solution 2 --------------------------//
    // O(n) time, O(n) space
    // use a hash map to indicate which node to direct to
    public RandomListNode copyRandomList2(RandomListNode head) {
        // input validation
        if (head == null) {
            return head;
        }

        // map old node to copied node
        RandomListNode iter = head;
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        while (iter != null) {
            map.put(iter, new RandomListNode(iter.label));
            iter = iter.next;
        }

        // link next and random
        iter = head;
        while (iter != null) {
            map.get(iter).next = map.get(iter.next);    // ATTENTION: map.get(null) --> null
            map.get(iter).random = map.get(iter.random);
            iter = iter.next;
        }
        return map.get(head);
    }
}

// 1. ERROR: Whenever you are using .next, make sure it's non-null
// 2. ERROR: new RandomListNode() --> new RandomListNode(0)
// 3. ERROR:    newIter.random = oldToNew.get(oldIter);
//           -> newIter.random = oldToNew.get(oldIter.random);
// 4. DESIGN: interleave or parallel?
