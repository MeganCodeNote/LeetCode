import java.util.Arrays;

public class Candy {
    public int candy(int[] ratings) {
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
}

// ERROR: off-by-one error  if (i == 0 || ratings[i] < ratings[i - 1])
//                      --> if (i == 0 || ratings[i] <= ratings[i - 1])