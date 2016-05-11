
public class IsBalancedTree {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    
    private int height(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        
        // general case
        int leftH = height(root.left);
        int rightH = height(root.right);
        if (leftH == -1 || rightH == -1 || Math.abs(leftH - rightH) > 1) {
            return -1;
        }
        return Math.max(leftH, rightH) + 1;  // error: return leftH + 1;
    }
}

