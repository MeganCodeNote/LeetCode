import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left, right;
    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode buildTree(String[] s) {
        // assume s is not null
        TreeNode root = new TreeNode(Integer.valueOf(s[0]));
        Queue<TreeNode> queue = new LinkedList<TreeNode>();  // use a queue, not a stack!!
        queue.offer(root);
        for (int i = 1; i < s.length - 1; i += 2) {
            TreeNode node = queue.poll();
            String left = s[i];
            String right = s[i + 1];
            if (!left.equals("#")) {
                node.left = new TreeNode(Integer.valueOf(left));
                queue.offer(node.left);
            }
            if (!right.equals("#")) {
                node.right = new TreeNode(Integer.valueOf(right));
                queue.offer(node.right);
            }
        }
        return root;
    }

    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    //////////////////////  TEST  ////////////////////////
    public static void main(String[] args) {
        String[] s = {"1", "2", "3", "#", "#", "4", "#", "#", "5"};
        TreeNode root = TreeNode.buildTree(s);
        TreeNode.inorder(root);
    }
}