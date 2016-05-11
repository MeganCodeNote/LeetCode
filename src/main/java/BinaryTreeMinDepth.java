import java.util.ArrayList;
import java.util.List;

public class BinaryTreeMinDepth {
    //-------------------  Solution 1 --------------------------//
    // Use classic recursion
    // ATTENTION: PATH, MUSH END WITH A LEAF !!!
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0; // null pointer
        }
        if (root.left == null)  {
            return 1 + minDepth(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }
        return 1 + Math.min(minDepth(root.right), minDepth(root.left));
    }
    

    //-------------------  Solution 2 --------------------------//
    // Level-by-level traversal
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;

        List<TreeNode> level = new ArrayList<TreeNode>();
        level.add(root);    // level only contains non-null nodes
        int min = 0;
        while (!level.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            for (TreeNode node: level) {
                if (node.left == null && node.right == null) {
                    return min + 1;  // leaf encountered, return result
                }
                if (node.left != null)  {
                    nextLevel.add(node.left);
                }
                if (node.right != null){
                    nextLevel.add(node.right);
                }
            }
            level = nextLevel;
            min++; 
        }
        return min;
    }
}