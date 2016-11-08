
public class RemoveElement {

    //---------------- Solution 1 --------------------//
    public int removeElement(int[] A, int elem) {
        if (A == null) {
            return 0;
        }

        int len = 0;        // len of the valid pre-subarray
        for (int i = 0; i < A.length; i++) { // move one step a time, check char-by-char
            if (A[i] != elem) {
                A[len++] = A[i];
            }
        }
        return len;
    }

    //---------------- Solution 2 -----------------//
    // non-stable (allowing changing the order)
    // sparse delete, move all bad items to the end of array
    public int removeElement2(int[] A, int elem) {
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
                j--;                 // reduce array size by one
            }
        }
        return i;
    }
}
