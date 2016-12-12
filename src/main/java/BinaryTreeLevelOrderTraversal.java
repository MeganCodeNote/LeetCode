/*  Created by meganlee on 10/30/14. */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    //------------------- Solution 1 -------------------//
    // BFS: level + next level (most recommended)
    public List<List<Integer>> levelOrder(TreeNode root) {
        // input validation
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        // traverse level by level
        List<TreeNode> level = new ArrayList<TreeNode>();
        level.add(root);
        while (!level.isEmpty()) {
            List<Integer> item = new ArrayList<Integer>();
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            for (TreeNode cur : level) {
                item.add(cur.val);
                if (cur.left != null) {
                    nextLevel.add(cur.left);
                }
                if (cur.right != null) {
                    nextLevel.add(cur.right);
                }
            }
            res.add(item);
            level = nextLevel;
        }
        return res;
    }

    //------------------- Solution 2 -------------------//
    // BFS: size + queue  (ok for most time)
    public List<List<Integer>> levelOrder2(TreeNode root) {
        // input validation
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        // traverse level by level
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            List<Integer> item = new ArrayList<Integer>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                item.add(cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(item);
        }
        return res;
    }


    //------------------- Solution 2 -------------------//
    // sentinel -> null  (practice your coding capability)
    public List<List<Integer>> levelOrder3(TreeNode root) {
        // input validation
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        // traverse level by level
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        q.add(null);
        List<Integer> item = new ArrayList<Integer>();
        while (!q.isEmpty()) {
            // case 1: end of level reached
            TreeNode cur = q.poll();
            if (cur == null) {
                res.add(new ArrayList<Integer>(item));  // attention, need to new a List
                item.clear();
                if (!q.isEmpty()) { // add sentinel for next level: only when q is not empty!!!
                    q.offer(null);
                }

            // case 2: in current level
            } else {
                item.add(cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return res;
    }

    ////////////////////////   TEST  ////////////////////////
    private static void test(BinaryTreeLevelOrderTraversal solution, String[] s) {
        TreeNode root = TreeNode.buildTree(s);
        List<List<Integer>> result = solution.levelOrder(root);
        ListPrinter.print2DList(result);
        System.out.println();
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

        String[] s1 = {"1", "2", "3", "#", "#", "4", "#", "#", "5"};
        String[] s2 = {"3", "9", "20", "#", "#", "15", "7"};

        test(solution, s1);
        test(solution, s2);
    }
}
