import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // input validation
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        // add result level by level
        List<TreeNode> level = new ArrayList<TreeNode>();
        level.add(root);
        int curLevel = 0;
        while (!level.isEmpty()) {
            List<Integer> item = new ArrayList<Integer>();
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            for (TreeNode n : level) {
                item.add(n.val);
                if (n.left != null) {
                    nextLevel.add(n.left);
                }
                if (n.right != null) {
                    nextLevel.add(n.right);
                }
            }
            if (curLevel % 2 == 1) {
                Collections.reverse(item);
            }
            curLevel++;
            res.add(item);
            level = nextLevel;
        }
        return res;
    }
    
    //////////////////////   TEST  ////////////////////////
    public static void main(String[] args) {
        BinaryTreeZigzagLevelOrderTraversal solution = new BinaryTreeZigzagLevelOrderTraversal();
        String[] s = {"1", "2", "3", "4", "5", "6", "7", "8", "#", "9", "10"};
        TreeNode root = TreeNode.buildTree(s);
        List<List<Integer>> result = solution.zigzagLevelOrder(root);
        ListPrinter.print2DList(result);
    }
}

// ERROR 1: line 30 reversed != reversed ---> reversed = !reversed
// NOTE  2: DO USE A CONCREATE EAXAMPLE !!! DRAW THE PICTURE!!
