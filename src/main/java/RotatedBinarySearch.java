

public class RotatedBinarySearch {
    public static int search(int[] A, int target) {
        if (A == null) return -1; // input checking
        return helper(A, 0, A.length-1, target);
    }
    
    public static int helper(int[] A, int lo, int hi, int target) {
        // base case
        if (lo == hi)
            return (target==A[lo]) ? lo : -1;
        
        // general case
        int mid = (lo + hi) / 2;
        if (target == A[mid]) return mid;
        else if (target < A[mid]) {
            if (A[mid] > A[hi] && target < A[lo])
                return helper(A, mid+1, hi, target); // go to second part
            else
                return helper(A, lo, mid, target); // go to first part
        } else {
            if(A[lo] > A[mid+1] && target > A[hi])
                return helper(A, lo, mid, target); // go to first part
            else
                return helper(A, mid+1, hi, target); // go to second part
        }
    }
    
    
    public static void main(String[] args) {
		int[] a = {0,1,2,3,4,5,6,7};
		System.out.println(search(a, 3));
	}
}

// NOTE: linear search 
// what about if there is not enough space
// what about if there are duplicates in the array
// draw a line a histogram to find the patterns