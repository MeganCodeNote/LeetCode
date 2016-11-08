import java.util.Arrays;
import java.util.Stack;


public class LargestRectangleInHistogram {
    // ---------------  Solution 1 ---------------------//
    // brute-force with one optimization T=O(N^2)
    // Time Limit Exceeded: could not pass LeetCode for time limits for acsending order {0, 1, 2, 3, 4, .., 1000}
    public int largestRectangleArea(int[] height) {
        // input checking
        if (height == null || height.length == 0) {
            return 0;
        }
        // i points to right most bar, j points to left most bar
        int area = 0;
        for (int i = 0; i < height.length; i++) {
            // optimization, if cur-bar < prev-bar, prune calculation
            if (i != 0 && height[i] <= height[i - 1]) {
                continue;
            }
            int min = height[i];
            for (int j = i; j < height.length; j++) {
                min = Math.min(min, height[j]);
                area = Math.max(area, min * (j - i + 1));
            }
        }
        return area;
    }

    //---------------  Solution 2 ---------------------//
    // fix one bar, expand on both sides:  Time Limit Exceeded
    public int largestRectangleArea2(int[] height) {
        // input checking
        if (height == null || height.length == 0) {
            return 0;
        }
        // i points to right most bar, j points to left most bar
        int area = 0, N = height.length;
        for (int i = 0; i < N; i++) {
            int l = i, r = i;
            while (l >= 0 && height[l] >= height[i]) {
                l--;
            }
            while (r < N  && height[r] >= height[i]) {
                r++;
            }
            area = Math.max(area, (r - l - 1) * height[i]);
        }
        return area;
    }


    // ---------------  Solution 3 ---------------------//
    // base on brute-force, use incremental to develop this method
    // pay attention to duplicates (think about all the same elements)
    public int largestRectangleArea3(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();              // for getting the left and right boundaries
        int[] h = Arrays.copyOf(height, height.length + 1); // add a padding 0 at the end for simplify the code
        int maxArea = 0;
        for(int i = 0; i < h.length; ) { // no i++ here
            if (stack.isEmpty() || h[i] >= h[stack.peek()]) { // > || >= both ok
                stack.push(i);
                i++;    // i++ here
            } else {    // no i++
                // pop top element and use the popped element as the shortest bar
                int minHeight = h[stack.pop()];
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, minHeight * width);
            }
        }
        return maxArea;
    }

    ////////////////   TEST  //////////////////
    private static void test(LargestRectangleInHistogram solution, int[] height) {
        ArrayUtils.print1D(height);
        System.out.println(solution.largestRectangleArea3(height) + "\n");
    }
    public static void main(String[] args) {
        LargestRectangleInHistogram solution = new LargestRectangleInHistogram();

        int[] height1 = {1, 1, 1, 1, 1, 1, 1, 1};   // all the same
        int[] height2 = {2, 1, 5, 6, 2, 3};         // random
        int[] height3 = {0, 0, 9};                  // contains zero
        int[] height4 = {1, 2, 3, 4, 5, 6, 7, 8};   // ascending
        int[] height5 = {8, 7, 6, 5, 4, 3, 2, 1};   // descending
        int[] height6 = {3};                        // 1- item
        int[] height7 = {};                         // empty

        test(solution, height1);
        test(solution, height2);
        test(solution, height3);
        test(solution, height4);
        test(solution, height5);
        test(solution, height6);
        test(solution, height7);
	}
}

