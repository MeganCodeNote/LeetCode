
public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return findSum(root, 0);
    }
    
    private int findSum(TreeNode root, int parentSum) {
        // null node
        if (root == null) {
            return 0;  // case 1: initial root is null, will return 0 here
                       // case 2: root's sibling is non-null, not a path, return 0
        }

        // leaf node
        int val = parentSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return val;
        }
        
        // non-leaf node
        return findSum(root.left, val) + findSum(root.right, val);
    }
}