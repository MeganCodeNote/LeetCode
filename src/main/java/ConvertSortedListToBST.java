import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListToBST {
    //------------------- Solution 1 --------------------//
    // List --> Array, then binary recursion
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        // first convert the List into an Array
        List<Integer> vals = new ArrayList<Integer>();
        while (head != null) {
            vals.add(head.val);
            head = head.next;
        }
        return helper(vals, 0, vals.size() - 1);
    }

    private TreeNode helper(List<Integer> vals, int start, int end) {
        // base case
        if (start > end) {
            return null;
        }

        // general case
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(vals.get(mid));
        root.left = helper(vals, start, mid - 1);
        root.right = helper(vals, mid + 1, end);
        return root;
    }

    //------------------- Solution 2 --------------------//
    // List --> Array, then binary recursion
    private ListNode cur; // points to the next value to be filled
    public TreeNode sortedListToBST2(ListNode head) {
        cur = head;
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return buildBST(size); // cur must either be a instance variable or a ListNode[1]
    }

    private TreeNode buildBST(int size) {
        // base case
        if (size == 0) {
            return null;
        }

        // general case
        // step 1: recursively build left tree
        TreeNode leftRoot = buildBST(size / 2);

        // step 2: construct current node
        TreeNode root = new TreeNode(cur.val);
        root.left = leftRoot;
        cur = cur.next;

        // step 3: recursively build right tree
        root.right = buildBST(size - size / 2 - 1);
        return root;
    }
}


// 1. ERROR: TreeNode rightRoot = buildBST(numOfLeft)
//                   ===>       = buildBST(numOfRight)
//
// 2. ERROR: if you use a instance variable, do not pass it in
//          private TreeNode buildBST(ListNode cur, int numOfNodes) {...}
//                   ===>    buildBST(int numOfNodes)


