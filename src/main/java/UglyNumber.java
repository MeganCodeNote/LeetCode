import java.util.Arrays;
import java.util.List;

public class UglyNumber {
    public boolean isUgly(int num) {
        List<Integer> seeds = Arrays.asList(2, 3, 5);
        if (num <= 0) return false;
        if (num == 1 || seeds.contains(num)) return true;

        for (int seed: seeds) {
            while (num % seed == 0) {
                num /= seed;
            }
        }
        return num == 1;
    }

    public boolean isUgly2(int num) {
        if (num <= 0) return false;
        if (num == 1) return true;

        while (num >= 2 && num % 2 == 0) num /= 2;
        while (num >= 3 && num % 3 == 0) num /= 3;
        while (num >= 5 && num % 5 == 0) num /= 5;

        return num == 1;
    }

    public static void main(String[] args) {
        UglyNumber solution = new UglyNumber();
        for (int i = 0; i <= 100; i++)
            System.out.println("Number: " + i + " Ugly: " + solution.isUgly(i));
    }
}