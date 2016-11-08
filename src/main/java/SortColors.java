/* 10/24/2014 Megan Lee */

public class SortColors {
    //-------------  SOLUTION 1: 2 SCANs ----------------//
    // count sort
    public void sortColors(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        // count
        int[] counts = new int[3];
        for (int i: A) {
            counts[i]++;
        }

        // fill
        for (int i = 0; i < A.length; i++) {
            if (i < counts[0]) {
                A[i] = 0;
            } else if (i < counts[0] + counts[1]) {
                A[i] = 1;
            } else {
                A[i] = 2;
            }
        }
    }

    //-------------  SOLUTION 2: 1 SCAN ----------------//
    public void sortColors2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        
        int i = 0, p = 0, j = A.length - 1;
        while (p <= j) {   
            switch (A[p]) {
                case 0: swap(A, i++, p++); 
                        break; 
                case 1: p++; 
                        break;
                case 2: swap(A, p, j--); 
                        break;
            }
        }
    }
    
    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    //------------------ Solution 3 --------------------//
    // Code Ganker
    public void sortColors3(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        int index0 = 0, index1 = 0;
        for (int i = 0; i < A.length; i++) {
            switch(A[i]) {
                case 0:
                    A[i] = 2;
                    A[index1++] = 1;
                    A[index0++] = 0;
                    break;
                case 1:
                    A[i] = 2;
                    A[index1++] = 1;
                    break;
            }
        }
    }
}

