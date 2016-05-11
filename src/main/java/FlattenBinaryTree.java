/* Created by meganlee on 9/14/14. */

public class FlattenBinaryTree {
    //--------------- Solution 1 ---------------//
    //
    private TreeNode pre;
    public void flatten(TreeNode root) {
        // base case
        if (root == null) {
            return;
        }

        // connect previous node to this node
        if (pre != null) {
            pre.left = null;
            pre.right = root;
        }

        // update pre
        pre = root;

        // recursively flatten left and right tree
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }

    //--------------- Solution 1 ---------------//
    //
    public void flatten2(TreeNode root) {
        TreeNode[] pre = new TreeNode[1];
        helper(root, pre);
    }

    private void helper(TreeNode root, TreeNode[] pre) {
        if (root == null) {
            return;
        }

        if (pre[0] != null) {
            pre[0].left = null;
            pre[0].right = root;
        }
        pre[0] = root;  // update pre!!

        TreeNode right = root.right;
        helper(root.left, pre);
        helper(right, pre);
    }
}

