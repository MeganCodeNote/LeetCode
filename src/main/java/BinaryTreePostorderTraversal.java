import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {

    //--------------------- Solution 1 ----------------------//
    // recursion
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        // base case
        if (root == null) {
            return;
        }

        // general case
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }

    //--------------------- Solution 2 ----------------------//
    // mimic call stack
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode pre = new TreeNode(0);
        while (!s.isEmpty()) {
            TreeNode cur = s.peek();
            //--- base cases:
            if (cur == null) {
                s.pop();
            } else if (cur.left == null && cur.right == null) {
                res.add(cur.val);
                s.pop();
            //--- general cases:
            } else if (pre == cur.left) {   // 1. return from left subtree
                s.push(cur.right);
            } else if (pre == cur.right) {  // 2. return from right subtree
                res.add(cur.val);
                s.pop();
            } else {                        // 3. call from parent node
                s.push(cur.left);
            }
            // remember to update pre
            pre = cur;
        }
        return res;
    }

    //--------------------- Solution 3 ----------------------//
    // Classic Stack: stack + cur pointer + lastVisited
    // iterative
    // if (cur == null) means reaching leftmost, { pop / update cur} 
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root, lastVisited = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode peekNode = stack.peek();
                // case 1: haven't visited right part
                if (peekNode.right != null && peekNode.right != lastVisited) {
                    cur = peekNode.right;

                // case 2: right subtree visited already
                } else {
                    lastVisited = stack.pop();
                    res.add(lastVisited.val);
                }
            }
        }
        return res;
    }


    //--------------------- Solution 4 ----------------------//
    // Morris traversal
    public List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode cur = new TreeNode(0);  // use a dumpy node
        cur.left = root;
        while (cur != null) {
            TreeNode pre = cur.left;
            if (pre != null) {
                // find predecessor
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // case 1: haven't visited left subtree
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                // case 2: returned from left subtree
               if (pre.right == cur) {
                    add(cur.left, pre, res);
                    pre.right = null;
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        return res;
    }

    // a very typical reverse linkedlist code snippet
    private void reverse (TreeNode from, TreeNode to) {
        TreeNode pre = null, cur = from;
        while (cur != to) {
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        cur.right = pre;
    }

    // add in a reversed order
    private void add(TreeNode from, TreeNode to, List<Integer> res) {
        reverse(from, to);
        TreeNode iter = to;
        while (iter != from) {
            res.add(iter.val);
            iter = iter.right;
        }
        res.add(iter.val);
        reverse(to, from);
    }
}
