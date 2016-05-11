
public class RemoveListDupes2 {
	//--------------  Solution 1 ------------------//
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0), tail = dummy;
        tail.next = head;
        ListNode p = head;
        while (p != null) {
            // move p to the furthest duplicate node
            while(p.next != null && p.next.val == p.val) {
                p = p.next;
            }
            
            // add new node to the de-dupe list
            if (tail.next == p) 
            		tail = p;
            else 
            		tail.next = p.next;
            
            // update p
            p = p.next;
        }
        return dummy.next;
    }
    
	//--------------  Solution 2 ------------------//
    // older solution, used a counter
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(0), tail = dummy;
        ListNode p1 = head, p2 = p1;
        while (p1 != null) {
            // move p2 to the furthest duplicate node
            int counter = 1;
            while(p2.next != null && p2.next.val == p2.val) {
                p2 = p2.next;
                counter++;
            }
            // add new node to the de-dupe list
            if (counter == 1) {
                tail.next = p1;
                tail = p1;
            }
            
            // update p1 and p2
            p1 = p2.next;
            p2 = p1;
        }
        
        tail.next = null;
        return dummy.next;
    }
    
    /////////////////   TEST /////////////////
    public static void main(String[] args) {
    		int[] x = {1,2,2,3};
		ListNode list = ListNode.fromArray(x);
		System.out.println((new RemoveListDupes2()).deleteDuplicates(list));
	}
}