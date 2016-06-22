/* Created by meganlee on 10/30/14. */

import java.util.HashMap;
import java.util.Map;

public class ConstructPostorderInorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // assume inorder and postorder are valid
        if (inorder == null || inorder.length == 0) {
            return null;
        }

        int N = inorder.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < N; i++) {
            map.put(inorder[i], i);
        }
        return helper(inorder, 0, N - 1, postorder, 0, N - 1, map);
    }

    private TreeNode helper(int[] inorder, int i1, int i2, int[] postorder,
                            int p1, int p2, Map<Integer, Integer> map) {
        // base case
        if (i1 > i2) {
            return null;
        }

        // general case
        TreeNode root = new TreeNode(postorder[p2]);
        int i3 = map.get(postorder[p2]);
        root.left = helper(inorder, i1, i3 - 1, postorder, p1, p1 + i3 - i1 - 1, map);
        root.right = helper(inorder, i3 + 1, i2, postorder, p1 + i3 - i1, p2 - 1, map);
        return root;
    }
}
