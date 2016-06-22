import java.util.Arrays;
import java.util.Stack;


public class LargestRectangleInHistogram {
    //---------------  Solution 1 ---------------------//
    // fix one bar, expand on both sides
    // NOTE: could not pass LeetCode for time limits when all elements are the same
    public int largestRectangleArea(int[] height) {
        // input checking
        if (height == null || height.length == 0) {
            return 0;
        }
        // i points to righ tmost bar, j points to left most bar
        int area = 0;
        for (int i = 0; i < height.length; i++) {
            int l = i, r = i;
            while (l >= 0 && height[l] >= height[i]) {
                l--;
            }
            while (r < height.length && height[r] >= height[i]) {
                r++;
            }
            area = Math.max(area, (r - l - 1) * height[i]);
        }
        return area;
    }

    // ---------------  Solution 2 ---------------------//
    // brute-force with one optimization T=O(N^2)
    // NOTE: could not pass LeetCode for time limits for acsending order {0, 1, 2, 3, 4, .., 1000}
    public int largestRectangleArea2(int[] height) {
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

    // ---------------  Solution 3 ---------------------//
    // base on brute-force, use incremental to develop this method
    // pay attention to duplicates (think about all the same elements)
    public int largestRectangleArea3(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();              // for getting the left and right boundaries
        int[] h = Arrays.copyOf(height, height.length + 1); // add a padding 0 at the end for simplify the code
        int maxArea = 0;
        for(int i = 0; i < h.length; ) { // do not use i++ here
            if (stack.isEmpty() || h[i] > h[stack.peek()])
                stack.push(i++); // i increment here: i++
            else {
                // pop top element and calculate the maxArea
                // using the popped element as the shortest bar
                int minHeight = h[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, minHeight*(i - left - 1));
            }
        }
        return maxArea;
    }

    ////////////////   TEST  //////////////////
    public static void main(String[] args) {
        int[] height1 = {1, 1, 1, 1, 1, 1, 1, 1};   // all the same
        int[] height2 = {2, 1, 5, 6, 2, 3};         // random
        int[] height3 = {0, 0, 9};                  // contains zero
        // more test case: descending / ascending / one element
        LargestRectangleInHistogram solution = new LargestRectangleInHistogram();
        int res1 = solution.largestRectangleArea(height1);
        int res2 = solution.largestRectangleArea(height2);
        int res3 = solution.largestRectangleArea(height3);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
	}
}

