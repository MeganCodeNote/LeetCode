import java.util.ArrayList;
import java.util.List;


public class BinaryTreeMaxDepth {
	//-------------------  Solution 1 --------------------------//
	// Use classic recursion
    public int maxDepth(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }

        // general case
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    
	//-------------------  Solution 2 --------------------------//
	// Level-by-level traversal
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<TreeNode> level = new ArrayList<TreeNode>();
        level.add(root);    // nodes will only hold non-null TreeNode objects
        int max = 0;
        while (!level.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            for (TreeNode node: level) {
                if (node.left != null)  {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            level = nextLevel;
            max++;
        }
        return max;
    }
}

