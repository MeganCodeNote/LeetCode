import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
	//----------------  Solution 1 --------------------//
	// classic recursion = very similar to SameTree solution
    public boolean isSymmetric(TreeNode root) {
        if (root  == null) {
            return true;
        }
        return sameTree(root.left, root.right);
    }
    
    private boolean sameTree(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        if (n1.val != n2.val) {
            return false;
        }
        return sameTree(n1.left, n2.right) && sameTree(n1.right, n2.left);
    }

    //-------------------- Solution 2 -----------------------//
    // use level order traversal
    public boolean isSymmetric2(TreeNode root) {
        // input validation
        if (root == null) {
            return true;
        }

        Queue<TreeNode> left = new LinkedList<TreeNode>();
        Queue<TreeNode> right = new LinkedList<TreeNode>();
        left.offer(root.left);
        right.offer(root.right);
        while (left.size() == right.size() && !left.isEmpty()) {
            TreeNode l = left.poll();
            TreeNode r = right.poll();
            String lval = (l == null) ? "#" : (l.val + "");
            String rval = (r == null) ? "#" : (r.val + "");
            if (!lval.equals(rval)) {
                return false;
            }
            if (l != null) {
                left.offer(l.left);
                left.offer(l.right);
            }
            if (r != null) {
                right.offer(r.right);  // attention: add right first and then left
                right.offer(r.left);
            }
        }
        return left.isEmpty() && right.isEmpty();
    }
}

