
public class MedianOfTwoSortedArray {
    //----------------- Solutin 1 -------------------//
    // O(N) space and time, Merge Sort
    // remember this solution!!
    public double findMedianSortedArrays(int A[], int B[]) {
        // assume A and B are not null
        // assume m + n >= 1
        int m = A.length, n = B.length;
        int[] C = new int[m + n];
        int k = 0, i = 0, j = 0; // i == len of items in A already merged
                                 // i == index of next to be merged item
        while (i < m && j < n) {
            if (A[i] < B[j]) {
                C[k++] = A[i++];
            } else {
                C[k++] = B[j++];
            }
        }
        while (i < m) {
            C[k++] = A[i++];
        }
        while (j < n) {
            C[k++] = B[j++];
        }

        int mid1 = (m + n - 1) / 2;
        int mid2 = (m + n) % 2 == 0 ? mid1 + 1 : mid1;
        return (C[mid1] + C[mid2]) / 2.0;
    }


    //----------------- Solution 2 ---------------------//
    // Same solution with merge sort, didn't use extra sapce
    public double findMedianSortedArrays2(int A[], int B[]) {
        // assume A and B are not null
        // assume m + n >= 1
        int m = A.length, n = B.length;
        int mid1 = (m + n - 1) / 2;                     // index of median1
        int mid2 = (m + n) % 2 == 0 ? mid1 + 1 : mid1;  // index of median2
        int k = 0, i = 0, j = 0;
        int median1 = 0, median2 = 0;
        while (i < m && j < n) {    // end condition 1
            int target;
            if (A[i] < B[j]) {
                target = A[i++];
            } else {
                target = B[j++];
            }
            k++;
            if (k == mid1 + 1) {    // effective condition 2
                median1 = target;
            }
            if (k == mid2 + 1) {    // end condition 3
                median2 = target;
                break;
            }
        }

        if (k < mid1 + 1) {
            if (i == m) {
                median1 = B[mid1 - m];
            } else {
                median1 = A[mid1 - n];
            }
        }

        if (k < mid2 + 1) {
            if (i == m) {
                median2 = B[mid2 - m];
            } else {
                median2 = A[mid2 - n];
            }
        }

        return (median1 + median2) / 2.0;
    }


    //----------------- Solution 3---------------------//
    // Same solution with merge sort, didn't use extra sapce
    public double findMedianSortedArrays3(int A[], int B[]) {
        // assume A and B are not null
        // assume m + n >= 1
        int len = A.length + B.length;
        if (len % 2 == 1){
            return findKth(A, 0, B, 0, len / 2 + 1);
        } else {
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
        }
    }

    private double findKth(int[] A, int startA, int[] B, int startB, int k) {
        // make sure m < n
        int m = A.length - startA, n = B.length - startB;
        if (m > n) {
            return findKth(B, startB, A, startA, k);
        }

        // special case 1: A is empty
        if (m <= 0) {
            return B[startB + k - 1];
        }

        // special case 2: k == 1 (both are non-empty)
        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }

        // special case 3: m < k / 2
        int countA = Math.min(k / 2, m); // assume k >= 2
        int countB = k - countA;
        if (A[startA + countA - 1] == B[startB + countB - 1]) {
            return A[startA + countA - 1];
        } else if (A[startA + countA - 1] < B[startB + countB - 1]) {
            return findKth(A, startA + countA, B, startB, countB);
        } else {
            return findKth(B, startB + countB, A, startA, countA);
        }
    }

    //////////////////  TEST  /////////////////
    public static void main(String[] args) {
        int[] A1 = {};
        int[] B1 = {1};
        int[] A2 = {1};
        int[] B2 = {1};
        int[] A3 = {3, 6, 8};
        int[] B3 = {1, 2};
        int[] A4 = {3, 6, 8};
        int[] B4 = {1, 2, 9};
        MedianOfTwoSortedArray solution = new MedianOfTwoSortedArray();
        System.out.println(solution.findMedianSortedArrays2(A1, B1));
        System.out.println(solution.findMedianSortedArrays2(A2, B2));
        System.out.println(solution.findMedianSortedArrays2(A3, B3));
        System.out.println(solution.findMedianSortedArrays2(A4, B4));
    }
}

//=========  TAG: ==========//
// Array / Sort / Search
// MergeSort, binary search, topK, QuickSelect, QuickSort
//
//=========  Design: =========//
// a.--- Pseudo-merge sort
//      1) merge sort then locate median (this a typical merge sort template)
//      2) O(n) space and time
//
// b.--- Kth element
//      1) find K/2th element of each array, and throw the smaller half
//      2) recursively do step 1 until we find the right target
//
// c.--- Kth element (topK)
//      1) QuickSelection: please refer to Robert-Algorithm
//      2) O(n) time
//
//=========  Error/Note: =========//
//      1) type error
//          wrong: return (C[mid1] + C[mid2]) / 2;
//          right: return (C[mid1] + C[mid2]) / 2.0;
//
//      2) stackOverFlow: infinite recursive calling, pushing
//           function on top of stack
//              if (m >= n) { // should be m > n, other wise, can't pass when m == n!!
//                  return findKth(B, startB, A, startA, k);
//              }
//      2) mid
//          odd: [0, 1, 2]            --> mid1 = 1, mid2 = 1
//          even: [0, 1, 2, 3, 4, 5]  --> mid1 = 2, mid2 = 3
//

