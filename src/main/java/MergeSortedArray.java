
public class MergeSortedArray {
    //------------ Solution 1 ------------//
    // while loop
    public void merge(int A[], int m, int B[], int n) {
        // input checking
        if (A == null || B == null) {
            return;
        }

        // merge two
        int a = m - 1, b = n - 1, c = m + n - 1;
        while (a >= 0 && b >= 0) {
            if (A[a] > B[b]) {
                A[c--] = A[a--];
            } else {
                A[c--] = B[b--];
            }
        }

        // copy leftovers
        while(b >= 0) {
            A[c--] = B[b--];
        }
    }

    //------------ Solution 2 ------------//
    // for loop
    public void merge2(int A[], int m, int B[], int n) {
        // input checking
        if (A == null || B == null) {
            return;
        }

        // assume A and B are sorted in ascending order
        int i = m - 1, j = n - 1, k;
        for (k = m + n - 1; i >= 0 && j >= 0; k--) {
            if (A[i] > B[j]) {
                A[k] = A[i--];
            } else {
                A[k] = B[j--];
            }
        }
        while (j >= 0) {
            A[k--] = B[j--];
        }
    }
}