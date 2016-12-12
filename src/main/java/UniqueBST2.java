import java.util.ArrayList;
import java.util.List;

public class UniqueBST2 {
    //--------------------  Solution 1 ---------------------//
    // pure recursion
    public List<TreeNode> generateTrees(int n) {
        // input checking
        if (n <= 0) {
            return new ArrayList<TreeNode>();
        }

        return helper(1, n);   // pay attention to the range 1 - n
    }
    
    private List<TreeNode> helper(int start, int end) {
        // base case
        List<TreeNode> trees = new ArrayList<TreeNode>();
        if (start > end) {
            trees.add(null); // ATTENTION! must have this!
            return trees;
        } 
        
        // general case: the trees are sharing the same sub-structure!!!
        for (int val = start; val <= end; val++) {
            List<TreeNode> leftTrees = helper(start, val - 1);
            List<TreeNode> rightTrees = helper(val + 1, end);
            for (TreeNode l: leftTrees) {
                for (TreeNode r: rightTrees) {
                    TreeNode root = new TreeNode(val);
                    root.left = l;
                    root.right = r;
                    trees.add(root);
                }
            }
        } 
        return trees;
    }

    //--------------------  Solution 2 ---------------------//
    // recursion + cache
    public List<TreeNode> generateTrees2(int n) {
        // input checking
        if (n <= 0) {
            return new ArrayList<TreeNode>();
        }

        List[][] cache = new List[n + 1][n + 1]; // each cell restore a list, refered as cache[start][end]
        return helper(1, n, cache);
    }

    private List<TreeNode> helper(int start, int end, List[][] cache) {
        List<TreeNode> trees = new ArrayList<TreeNode>();

        // base case
        if (start > end) {
            trees.add(null);
            return trees;
        }

        // general case
        // --- 1) update cache
        if (cache[start][end] == null) {
            for (int val = start; val <= end; val++) {
                List<TreeNode> leftTrees = helper(start, val - 1, cache);
                List<TreeNode> rightTrees = helper(val + 1, end, cache);
                for (TreeNode right : rightTrees) {
                    for (TreeNode left : leftTrees) {
                        TreeNode root = new TreeNode(val);
                        root.right = right;
                        root.left = left;
                        trees.add(root);
                    }
                }
            }
            cache[start][end] = trees;
        }

        // --- 2) compute result
        return cache[start][end];
    }


    //--------------------  Solution 3 ---------------------//
    // dp[start][len] --> really tricky, not recommended, but good practice for dp thoughts
    public List<TreeNode> generateTrees3(int n) {
        // input validation
        List<TreeNode> trees = new ArrayList<TreeNode>();
        if (n <= 0) {
            return trees;
        }
        trees.add(null); // trees in not mutated later, used for null sub trees

        // create the dp table and initialize
        List[][] dp = new List[n + 1][n + 1];  // dp[start][end], each cell store a list of trees

        // 1) create all 1-len trees
        for (int i = 1; i <= n; i++) {
            dp[i][i] = new ArrayList<TreeNode>();
            dp[i][i].add(new TreeNode(i));
        }
        // 2) create all 2, 3, ... n-len trees
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int end = start + len - 1;
                dp[start][end] = new ArrayList<TreeNode>();
                for (int val = start; val <= end; val++) {
                    List<TreeNode> leftTrees = (val == start) ? trees : dp[start][val - 1];
                    List<TreeNode> rightTrees = (val == end)  ? trees : dp[val + 1][end];
                    for (TreeNode l : leftTrees) {
                        for (TreeNode r : rightTrees) {
                            TreeNode root = new TreeNode(val);
                            root.left = l;
                            root.right = r;
                            dp[start][end].add(root);
                        }
                    }
                }
            }
        }
        return dp[1][n];
    }
}

