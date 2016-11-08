
public class FirstMissingPositive {
    public int firstMissingPositive(int[] A) {
        // input checking
        if (A == null || A.length == 0) {
            return 1;
        }

        // put i at index i - 1
        for (int i = 0; i < A.length; ) {
            int targetIndex = A[i] - 1;
            if (A[i] <= 0 || A[i] > A.length || i == targetIndex || A[i] == A[targetIndex]) {
                i++;
            } else {
                swap(A, i, targetIndex);
            }
        }

        // find the first number that is not the same with the index
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        return A.length + 1;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    ///////////////////////  TEST  ////////////////////////
    public static void main(String[] args) {
        int[] A = {3, 4, -1, 1};
        int[] B = {4, 3, 5, -1, 0, 3, 7, 2, 8, -2, 1};

        int x = (new FirstMissingPositive()).firstMissingPositive(B);
        System.out.println(x);
    }
}
