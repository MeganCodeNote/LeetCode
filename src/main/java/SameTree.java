import java.util.LinkedList;
import java.util.Queue;

public class SameTree {
    //----------------  Solution 1 --------------------//
    // classic recursion
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // base case
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        // general case
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    

    //----------------  Solution 2 --------------------//
    // serialization comparison
    // BFS: level-by-level with "#" indicating a null node
    public boolean isSameTree3(TreeNode p, TreeNode q) {
        Queue<TreeNode> levelP = new LinkedList<TreeNode>();
        Queue<TreeNode> levelQ = new LinkedList<TreeNode>();
        levelP.offer(p); 
        levelQ.offer(q);
        while (levelP.size() == levelQ.size() && !levelP.isEmpty()) {
            int N = levelP.size();
            for (int i = 0; i < N; i++) {
                TreeNode pNode = levelP.poll();
                TreeNode qNode = levelQ.poll();
                String pNodeStr = pNode == null ? "#" : pNode.val + "";
                String qNodeStr = qNode == null ? "#" : qNode.val + "";
                if (!pNodeStr.equals(qNodeStr)) {
                    return false;
                }
                if (pNode != null) {
                    levelP.offer(pNode.left);
                    levelP.offer(pNode.right);
                }
                if (qNode != null) {
                    levelQ.offer(qNode.left);
                    levelQ.offer(qNode.right);
                }
            }
        }
        return (levelP.isEmpty() && levelQ.isEmpty());
    }

}

// NOTE : Alternatively: we could compute in-order and pre-order to compare
