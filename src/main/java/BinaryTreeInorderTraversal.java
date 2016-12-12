import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

    //----------------- Solution 1 ------------------//
    // recursion
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }
    
    private void helper(TreeNode node, List<Integer> res) {
        // base case
        if (node == null) {
            return;
        }

        // general case
        helper(node.left, res);  // 1. visit left subtree
        res.add(node.val);       // 2. visit current node
        helper(node.right, res); // 3. visit right subtree
    }


    //----------------- Solution 2 ------------------//
    // Stack + two pointers (prev, curr)
    // it's like a call stack, stack.push(node) --> recursive call
    // pop() means return, push() means recursive call
    // Another solution : change TreeNode structure to contain a visit flag
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode pre = new TreeNode(0); // use a dummy pre initially
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            //--- base cases:
            if (cur == null) {
                stack.pop();
            } else if (cur.left == null && cur.right == null) {
                result.add(cur.val);
                stack.pop();
            //--- general cases:
            } else if (pre == cur.left) {   // case 1: return from left child
                result.add(cur.val);
                stack.push(cur.right);
            } else if (pre == cur.right) {  // case 2: return from right child
                stack.pop();
            } else {                        // case 3: call from parent node
                stack.push(cur.left);
            }
            // remember to update pre
            pre = cur;
        }
        return result;
    }


    //----------------- Solution 3 ------------------//
    // Classic Stack: stack + cur pointer
    // iterative
    // if (cur == null) means reaching leftmost
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                res.add(node.val);  // visit
                cur = node.right;
            }
        }
        return res;
    }

    
    //----------------- Solution 4 ------------------//
    // Morris traversal
    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        while (cur != null) {
            TreeNode pre = cur.left;
            if (pre != null) {
                // find predecessor
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // case 1: haven't visited left subtree
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                // case 2: returned from left subtree
                if (pre.right == cur) {
                    res.add(cur.val);  // visit
                    pre.right = null;
                    cur = cur.right;
                }
            } else {
                res.add(cur.val); // visit
                cur = cur.right;
            }
        }
        return res;
    }


    /////////////////  TEST  //////////////////
    public static void main(String[] args) {
        BinaryTreeInorderTraversal solution = new BinaryTreeInorderTraversal();
        String[] s = {"1", "2", "3", "#", "#", "4", "#", "#", "5"};
        TreeNode root = TreeNode.buildTree(s);
        List<Integer> result = solution.inorderTraversal3(root);
        ListPrinter.printList(result);
    }
}