import java.util.Stack;

public class PathSum {
    //------------------- Solution 1 --------------------//
    // recursion: (preorder)
    public boolean hasPathSum(TreeNode root, int sum) {
        // base case
        if (root == null) {
            return false; // if root is null, it's sibling must be non null
        }
        // leaf!
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }

        // general case
        int leftover = sum - root.val;
        return hasPathSum(root.left, leftover) || hasPathSum(root.right, leftover);
    }


    //------------------- Solution 2 --------------------//
    // stack - postorder traversal
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode cur = root, pre = null;
        int curSum = 0;
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                s.push(cur);
                curSum += cur.val;
                cur = cur.left;
            } else {
                TreeNode node = s.peek();
                // 1. haven't visited right subtree yet
                if (node.right != null && node.right != pre) {
                    cur = node.right;

                // 2. returned from right subtree
                } else {
                    // leaf, mark the end of a path, see if it's a valid path
                    if (node.left == null && node.right == null && curSum == sum) {
                        return true;
                    }
                    pre = s.pop();
                    curSum -= pre.val;
                }
            }
        }
        return false;
    }
}

// NOTE 1: test cases
// {}, 0 --> false
// {1,2}, 1 --> false
//
// NOTE 2: pay attention of the definition of PATH
// has to end with a LEAF!!!