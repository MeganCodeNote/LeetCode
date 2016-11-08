
public class ContainerWithMostWater {
    //----------------- Solution 1 ------------------//
    // brute-force: Time Limit Exceeded
    public int maxArea(int[] height) {
        // input validation
        if (height == null || height.length <= 1) {
            return 0;
        }

        // 2 pointers
        int res = 0, N = height.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                res = Math.max(res, area);
            }
        }
        return res;
    }

    //----------------- Solution 2 ------------------//
    // 2-pointer-window shrinking
    public int maxArea2(int[] height) {
        // input checking
        if (height == null || height.length <= 1) {
            return 0;
        }

        // shrink window
        int l = 0, h = height.length - 1;
        int max = 0;
        while (l < h) {
            max = Math.max(max, area(height, l, h));
            if (height[l] < height[h]) {
                l++;
            } else {
                h--;
            }
        }
        return max;
    }

    private int area(int[] height, int lo, int hi) {
        return (hi - lo) * Math.min(height[lo], height[hi]);
    }

    ///////////////////  TEST //////////////////////
    private static void test(ContainerWithMostWater solution, int[] bars) {
        ArrayUtils.print1D(bars);
        System.out.println(solution.maxArea2(bars));
    }


    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();
        int[] bars1 = {9,6,8,8,5,6,3};
        int[] bars2 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        test(solution, bars1);
        test(solution, bars2);
    }
}

// think: when lo == hi, we should both lo++ and hi-- e.g: (9, 100, 102, 9)