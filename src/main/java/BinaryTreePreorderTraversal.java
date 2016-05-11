import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {

    //----------------- Solution 1 --------------------//
    // recursion
    public List<Integer> preorderTraversal(TreeNode root) {
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
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }


    //----------------- Solution 2 --------------------//
    // call stack
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode pre = new TreeNode(0);  // use a dummy previous at the beginning
        while (!s.isEmpty()) {
            TreeNode cur = s.peek();
            //--- base cases:
            if (cur == null) {
                s.pop();  // pop mean return
            } else if (cur.left == null && cur.right == null) {
                res.add(cur.val);
                s.pop();
            //--- general cases:
            } else if (pre == cur.left){    // 1) return from left subtree
                s.push(cur.right);  // push means recursive call
            } else if (pre == cur.right) {  // 2) return from right subtree
                s.pop();
            } else {                        // 3) called by parent
                res.add(cur.val);
                s.push(cur.left);
            }
            // remember to update pre
            pre = cur;
        }
        return res;
    }


    //----------------- Solution 3 --------------------//
    // Classic Stack: stack + cur pointer
    // iterative
    // if (cur == null) means reaching leftmost, { pop / update cur} 
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                res.add(cur.val);   // visit
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                cur = cur.left;
            } else {
                cur = stack.pop();
            }
        }
        return res;
    }


    //----------------- Solution 4 --------------------//
    // Morris traversal
    public List<Integer> preorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        while (cur != null) {
            TreeNode pre = cur.left;
            if (pre != null) {
                // find predecessor
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // case 1: haven't visited the left subtree
                if (pre.right == null) {
                    res.add(cur.val);  // visit
                    pre.right = cur;
                    cur = cur.left;
                }
                // case 2: returned from left subtree
                if (pre.right == cur) {
                    pre.right = null;
                    cur = cur.right;
                }
            } else {
                res.add(cur.val); // visit
                cur = cur.right;
            }
        }
        return res;
    }
}

