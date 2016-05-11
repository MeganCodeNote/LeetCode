/*  Created by meganlee on 9/14/14. */
public class ConvertSortedArrayToBST {
    //--------------------- Solution 1 ------------------------//
    // binary recursion: preorder
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0) {
            return null;
        }

        return buildBST(num, 0, num.length - 1);
    }

    private TreeNode buildBST(int[] num, int start, int end) {
        // base case
        if (start > end) {
            return null;
        }

        // general case
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = buildBST(num, start, mid - 1);
        root.right = buildBST(num, mid + 1, end);
        return root;
    }

    //--------------------- Solution 2 ------------------------//
    // inorder traversal
    public TreeNode sortedArrayToBST2(int[] num) {
        // input checking
        if (num == null || num.length == 0) {
            return null;
        }

        // use the helper method buildBST
        return buildBST(num, num.length);
    }

    private int cur; // ATTENTION: use a field here!!
    private TreeNode buildBST(int[] num, int count) {
        // base case
        if (count == 0) {
            return null;
        }

        // general case
        // step 1: traverse left
        TreeNode left = buildBST(num, count / 2);

        // step 2: build root
        TreeNode root = new TreeNode(num[cur++]);
        root.left = left;

        // step 3: traverse right
        root.right = buildBST(num, count - count / 2 - 1);
        return root;
    }
}

