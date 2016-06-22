
public class PopulateNextPointer2 {
    public void connect(TreeLinkNode root) {
        TreeLinkNode levelHead = root;
        while (levelHead != null) {
            TreeLinkNode cur = levelHead;
            TreeLinkNode pre = null;
            levelHead = null;

            while (cur != null) {
                TreeLinkNode[] children = {cur.left, cur.right};
                for (TreeLinkNode node: children) {
                    if (node != null) {
                        if (levelHead == null) {
                            levelHead = node;  // update levelHead
                        } else {
                            pre.next = node;
                        }
                        pre = node;
                    }
                }
                cur = cur.next;
            }
        }
    }
}