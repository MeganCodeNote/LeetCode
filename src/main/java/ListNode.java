public class ListNode {
    public int val; 
    public ListNode next;   
    public ListNode(int x) {
        val = x;
    }
    
    // Easy to make a single-linked-list
    static public ListNode fromArray(int[] list) {
        if (list == null) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        for (int i = 0; i < list.length; i++) {
            ListNode node = new ListNode(list[i]);
            cur.next = node;
            cur = node;
        }
        return dummyHead.next;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode iter = this;
        while (iter != null) {
            sb.append(iter.val + "->");
            iter = iter.next;
        }
        return sb.append("null").toString();
    }
}