
public class MaxPathSum {
    public int maxPathSum(TreeNode root) {
        int[] max = new int[1]; // global max path sum
        max[0] = Integer.MIN_VALUE; // DON"T FORGET TO ADD THIS LINE!!
        findSum(root, max);
        return max[0];
    }
    
    private int findSum(TreeNode root, int[] max) {
        // 1) find 'local': the best downward path starting at (including) root
        //      - downward left path including root: root.val + left
        //      - downward right path including root: root.val + right
        //      - downward path only containing root: root.val + 0
        // 2) update the global max solution
        //      - local
        //      - an arch path across root  : root.val + left + right
        
        // base case
        if (root == null) return 0;
        
        // general case
        int left = findSum(root.left, max);
        int right = findSum(root.right, max);
        int local = root.val + triMax(left, right, 0);
        max[0] = triMax(max[0], local, left + right + root.val);
        return local;
    }
    
    private int triMax(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}

// NOTE 1: max sum could be negative!!!
//		   do remember to add max[0] = Integer.MIN_VALUE;
//			Input:	{-3}
//			Output:	0
//			Expected:	-3