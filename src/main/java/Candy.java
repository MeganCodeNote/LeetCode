import java.util.Arrays;

public class Candy {
    //-------------------- Solution 1 -----------------------//
    // incremental ( Time Limit Exceeded )
    public int candy(int[] ratings) {
        // input checking fast response
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        if (ratings.length == 1) {
            return 1;
        }

        int[] candy = new int[ratings.length];
        candy[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candy[i] = candy[i - 1] + 1;
            } else if (ratings[i - 1] == ratings[i]) {
                candy[i] = 1;
            } else {
                candy[i] = 1;
                // adjust predecessors
                for (int p = i - 1; p >= 0; p--) {
                    if (ratings[p] > ratings[p + 1] && candy[p] <= candy[p + 1]) {
                        candy[p] = candy[p + 1] + 1;
                    } else {
                        break;
                    }
                }
            }
        }

        int sum = 0;
        for (int num: candy) sum += num;
        return sum;
    }


    //-------------------- Solution 2 -----------------------//
    // Greedy
    public int candy2(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        // think about one child
        // go left to the first valley, go right to the first valley.
        int N = ratings.length;
        int[] L = new int[N], R = new int[N];
        Arrays.fill(L, 1);
        Arrays.fill(R, 1);
        for (int i = 1, j = N - 2; i < N; i++, j--) {
            // calculate L
            if (ratings[i] > ratings[i - 1]) {
                L[i] = L[i - 1] + 1;
            }
            // calculate R
            if (ratings[j] > ratings[j + 1]) {
                R[j] = R[j + 1] + 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.max(L[i], R[i]);
        }
        return sum;
    }

    ///////////////////  TEST //////////////////////
    private static void test(Candy solution, int[] ratings) {
        ArrayUtils.print1D(ratings);
        System.out.println(solution.candy(ratings));
    }

    public static void main(String[] args) {
        Candy solution = new Candy();
        int[] ratings1 = {1, 2, 3, 3, 1};
        int[] ratings2 = {1, 2, 2};

        test(solution, ratings1);
        test(solution, ratings2);
    }
}

// ERROR: off-by-one error  if (i == 0 || ratings[i] < ratings[i - 1])
//                      --> if (i == 0 || ratings[i] <= ratings[i - 1])