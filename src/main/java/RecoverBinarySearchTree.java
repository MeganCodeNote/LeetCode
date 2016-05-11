
public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        TreeNode[] pair = new TreeNode[2];  // recored the mis-positioned pair
        TreeNode[] pre = new TreeNode[1];   // recored the previous node
                                            // use an array type as a wrapper
        // swap and recover
        findPair(root, pair, pre);  
        if (pair[0] != null) {
            int temp = pair[0].val;
            pair[0].val = pair[1].val;
            pair[1].val = temp;
        }
    }
    
    private void findPair(TreeNode root, TreeNode[] pair, TreeNode[] pre) {
        // base case
        if (root == null) {
            return;
        }

        // general case: inorder traverse
        findPair(root.left, pair, pre);
        if (pre[0] != null && pre[0].val > root.val) {
            if (pair[0] == null) {  // the first pair of consecutive invalid pair
                pair[0] = pre[0];
                pair[1] = root;
            } else {                // the second pair of consecutive invalid pair
                pair[1] = root;
            }
        }
        pre[0] = root;
        findPair(root.right, pair, pre);
    }
}
