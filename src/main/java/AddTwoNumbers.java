
public class AddTwoNumbers {
    //--------------- Solution 1  ----------------//
    // recursion
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2){
        return helper(l1,l2,0);
    }

    private ListNode helper(ListNode l1, ListNode l2, int carry) {
        // base case
        if((l1 == null) && (l2 == null) && (carry == 0)) {
            return null;
        }

        // general case
        int digitA = (l1 == null) ? 0 : l1.val;
        int digitB = (l2 == null) ? 0 : l2.val;
        int sum = digitA + digitB + carry;
        ListNode res = new ListNode(sum % 10);

        l1 = (l1 == null) ? null : l1.next;
        l2 = (l2 == null) ? null : l2.next;
        res.next = helper(l1, l2, sum / 10);
        return res;
    }

    //--------------- Solution 2  ----------------//
    // iterative 1
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int carry = 0;                                  // carry
        ListNode dummyHead = new ListNode(0);           // result
        ListNode p1 = l1, p2 = l2, pSum = dummyHead;    // 3 pointers
        while((p1 != null) || (p2 != null) || (carry != 0)) {
            // compute current digit
            int digitA = (l1 == null) ? 0 : l1.val;
            int digitB = (l2 == null) ? 0 : l2.val;
            int sum = digitA + digitB + carry;
            carry = sum / 10;
            pSum.next = new ListNode(sum % 10);

            // update states for next iteration
            p1 = (p1 == null) ? null : p1.next;
            p2 = (p2 == null) ? null : p2.next;
            pSum = pSum.next;
        }
        return dummyHead.next;
    }

    //--------------- Solution 3  ----------------//
    // iterative 2
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        int carry = 0;                                  // carry
        ListNode dummyHead = new ListNode(0);           // result
        ListNode p1 = l1, p2 = l2, pSum = dummyHead;    // 3 pointers

        // sum 2 digits
        while (p1 != null && p2 != null) {
            int sum = p1.val + p2.val + carry;
            carry = sum / 10;
            pSum.next = new ListNode(sum % 10);
            pSum = pSum.next;
            p1 = p1.next;
            p2 = p2.next;
        }
        // remainder of the longer number
        ListNode pLeftover = (p1 == null) ? p2 : p1;
        while (pLeftover != null) {
            int sum = pLeftover.val + carry;
            carry = sum / 10;
            pSum.next = new ListNode(sum % 10);
            pSum = pSum.next;
            pLeftover = pLeftover.next;
        }
        // the final carry
        if (carry == 1) {
            pSum.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    ////////////////// TEST ///////////////////////
    private static void test(AddTwoNumbers solution, int[] l1, int[] l2) {
        ListNode head1 = ListNode.fromArray(l1);
        ListNode head2 = ListNode.fromArray(l2);
        ListNode sum = solution.addTwoNumbers3(head1, head2);
        System.out.println(sum);
    }

    public static void main(String[] args) {
        // create the testing data
        AddTwoNumbers solution = new AddTwoNumbers();
        int[] list1 = {6,1,3};
        int[] list2 = {2,9,7};
        int[] list3 = {2};
        int[] list4 = {9,9,9};

        // test
        test(solution, list1, list2);
        test(solution, list1, list3);
        test(solution, list3, list4);
    }
}


