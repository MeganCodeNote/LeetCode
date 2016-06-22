
public class PopulateNextPointer {
    //--------------- Solution 1 -----------------//
    // divide and conquer
    public void connect(TreeLinkNode root) {
        // base case
        if (root == null) {
            return;
        }

        // general case: divide and conquer
        // divide
        connect(root.left);
        connect(root.right);

        // conquer (connect left and right parts)
        TreeLinkNode left = root.left, right = root.right;
        while (left != null && right != null) { // left and right would be both null or both non-null
            left.next = right;
            left = left.right;
            right = right.left;
        }
    }

    //------------------- Solution 2 ----------------//
    // level-by-level traversal (use .next pointer)
    public void connect2(TreeLinkNode root) {
        TreeLinkNode levelHead = root;
        // we will stop at leaf-parent level
        while (levelHead != null && levelHead.left != null) {
            // 1. link all nodes
            TreeLinkNode curNode = levelHead;
            while (curNode != null) {
                curNode.left.next = curNode.right;
                if (curNode.next != null) {
                    curNode.right.next = curNode.next.left;
                }
                curNode = curNode.next;
            }
            // 2. update level
            levelHead = levelHead.left;
        }
    }
}

