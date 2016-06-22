
public class RemoveElement {
    //---------------- Solution 1 -----------------//
    // non-stable (allowing changing the order)
    // like partition in quick sort
    public int removeElement(int[] A, int elem) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int i = 0, j = A.length - 1;
        while (i <= j) {
            if (A[i] != elem) {
                i++;
            } else {
                // swap(A, i, j--);  // no need to swap!, discard target
                A[i] = A[j];
                j--;
            }
        }
        return i;
    }

    //---------------- Solution 2 -----------------//
    // stable (same order with previous)
    public int removeElement2(int[] A, int elem) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int len = A.length;
        for (int i = 0; i < len; ) {
            if (A[i] == elem) {
                for (int j = i + 1; j < len; j++) {
                    A[j - 1] = A[j];
                }
                len--;
            } else {
                i++;
            }
        }
        return len;
    }

    //---------------- Solution 3 --------------------//
    public int removeElement3(int[] A, int elem) {
        if (A == null) {
            return 0;
        }

        // len is the len of the valid pre-subarray
        // i will move one step a time, check char-by-char
        int len = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem) {
                A[len++] = A[i];
            }
        }
        return len;
    }
}
