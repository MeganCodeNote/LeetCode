import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathSum2 {
    //-------------------- Solution 1 --------------------//
    // use classic backtrace
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }
        helper(res, new ArrayList<Integer>(), root, sum);
        return res;
    }

    private void helper(List<List<Integer>> result, List<Integer> path, TreeNode root, int sum) {
        // base cases
        if (root == null) {
            return;
        }
        // leaf node: path has to end with a leaf node
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                List<Integer> newPath = new ArrayList<Integer>(path);
                newPath.add(root.val);
                result.add(newPath);
            }
            return;  // remember to return here
        }

        // general case
        path.add(root.val); // add current value
        helper(result, path, root.left, sum - root.val);
        helper(result, path, root.right, sum - root.val);
        path.remove(path.size() - 1);   // remove current value
    }

    //-------------------- Solution 2 --------------------//
    // use classic postorder stack traversal
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        // input checking
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode cur = root, pre = null;
        int curSum = 0;
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                s.push(cur);
                curSum += cur.val;
                cur = cur.left;
            } else {
                TreeNode node = s.peek();
                // 1. haven't visited right subtree yet
                if (node.right != null && node.right != pre) {
                    cur = node.right;

                // 2. visited right subtree already, visit cur
                } else {
                    // for each leaf, if it's valid, collect it
                    if (node.left == null && node.right == null && curSum == sum) {
                        List<Integer> item = new ArrayList<Integer>();
                        for (TreeNode n : s) {
                            item.add(n.val);
                        }
                        res.add(item);
                    }
                    pre = s.pop();
                    curSum -= pre.val;
                }
            }
        }
        return res;
    }
}

// NOTE : test case, the value could be negative!!
//    Input:    {-2,#,-3}, -5
//    Output:   []
//    Expected: [[-2,-3]]
