public class ReverseLinkedList {
  //--------------------- Solution 1 -----------------------//
  // Recursive: Time O(n) Space O(n)
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode newHead = reverseList(head.next);

    ListNode newTail = head.next;
    newTail.next = head;
    head.next = null;

    return newHead;
  }

  //--------------------- Solution 2 -----------------------//
  // Recursive: Time O(n) Space O(1)
  // Iterative
  public ListNode reverseList2(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode pre = null;
    ListNode cur = head;
    while (cur != null) {
      // reverse
      ListNode post = cur.next;
      cur.next = pre;

      // update pointer
      pre = cur;
      cur = post;
    }

    return pre;
  }


  //--------------------- Solution 3 -----------------------//
  // Tail to Head: A basic step for reversing in GROUPs
  public ListNode reverseList3(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode newHead = head;
    ListNode newTail = head;
    while (newTail.next != null) {
      ListNode cur = newTail.next;
      newTail.next = cur.next;
      cur.next = newHead;
      newHead = cur;
    }

    return newHead;
  }

  /////////////////////    TEST    /////////////////////////
  private static void test(ReverseLinkedList solution, int[] x) {
    ListNode list = ListNode.fromArray(x);
//    ListNode res = solution.reverseList(list);
    ListNode res = solution.reverseList3(list);

    if (res == null) System.out.println("null");
    else System.out.println(res.toString());
  }

  public static void main(String[] args) {
    ReverseLinkedList solution = new ReverseLinkedList();
    int[] list1 = {};
    int[] list2 = {1};
    int[] list3 = {1, 2, 3, 4, 5};
    test(solution, list1);
    test(solution, list2);
    test(solution, list3);
  }
}