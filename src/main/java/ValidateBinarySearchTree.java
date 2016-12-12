import java.util.Stack;

public class ValidateBinarySearchTree {
    //------------------- Solution 1 -----------------------//
    // recursion + pass values down
    public boolean isValidBST(TreeNode root) {
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean helper(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        
        // 1. check if current node's value is in range
        if (root.val < min || root.val > max) {
            return false;
        }
        
        // 2. check if sub tree's value is in range
        return helper(root.left, min, root.val - 1)   // use root.val - 1, we do not allow duplicates
            && helper(root.right, root.val + 1, max); // use root.val + 1, we do not allow duplicates
    }

    //------------------- Solution 2 -----------------------//
    // in-order traversal
    public boolean isValidBST2(TreeNode root) {
        int pre = Integer.MIN_VALUE;
        TreeNode cur = root;
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                s.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = s.pop();
                if (pre >= node.val) {
                    return false;
                } else {
                    pre = node.val;
                }
                cur = node.right;
            }
        }
        return true;
    }
}
