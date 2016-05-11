
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        // input checking
        if (height == null || height.length == 0) {
            return 0;
        }

        // shrink window
        int lo = 0, hi = height.length - 1;
        int max = 0;
        while (lo < hi) {
            max = Math.max(max, area(height, lo, hi));
            if (height[lo] < height[hi]) {
                lo++;
            } else {
                hi--;
            }
        }
        return max;
    }

    private int area(int[] height, int lo, int hi) {
        return (hi - lo) * Math.min(height[lo], height[hi]);
    }
}

// think: when lo == hi, we should both lo++ and hi-- e.g: (9, 100, 102, 9)