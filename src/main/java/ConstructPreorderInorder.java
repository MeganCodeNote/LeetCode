import java.util.HashMap;
import java.util.Map;

public class ConstructPreorderInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // assume that preorder and inorder are valid
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        // construct a fast querying data structure for inorder!!
        int N = preorder.length;
        Map<Integer, Integer> indexTable = new HashMap<Integer, Integer>();
        for (int i = 0; i < N; i++) {
            indexTable.put(inorder[i], i);
        }
        return helper(preorder, 0, N - 1, inorder, 0, N - 1, indexTable);
    }

    private TreeNode helper(int[] preorder, int p1, int p2, int[] inorder,
                            int i1, int i2, Map<Integer, Integer> indexTable) {
        // base case:
        if (i1 > i2) {
            return null;
        }

        // general case:
        int rootVal = preorder[p1];
        int rootIndex = indexTable.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = helper(preorder, p1 + 1, p1 + rootIndex - i1, inorder,
                            i1, rootIndex - 1, indexTable);
        root.right = helper(preorder, p1 + rootIndex - i1 + 1, p2, inorder,
                            rootIndex + 1, i2, indexTable);
        return root;
    }

    /////////////////////////  TEST  ///////////////////////////
    private static void test(ConstructPreorderInorder solution, int[] pre, int[] in) {
        TreeNode root = solution.buildTree(pre, in);
        TreeNode.inorder(root);
    }

    public static void main(String[] args) {
        ConstructPreorderInorder solution = new ConstructPreorderInorder();
        int[] preorder = {7, 10, 4, 3, 1, 2, 8, 11};
        int[] inorder = {4, 10, 3, 1, 7, 11, 8, 2};

        test(solution, preorder, inorder);
    }
}