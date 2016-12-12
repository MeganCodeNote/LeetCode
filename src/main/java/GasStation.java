import java.util.Arrays;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // input checking
        if (gas == null || cost == null || gas.length != cost.length) {
            return -1;
        }

        int total = 0, subTotal = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];  // get the diff array
            total += diff;
            subTotal += diff;
            if (subTotal < 0) {
                start = i + 1;
                subTotal = 0;
            }
        }
        return (total >= 0) ? start : -1;
    }

    ///////////////////  TEST //////////////////////
    private static void test(GasStation solution, int[] gas, int[] cost, int expected) {
        int actual = solution.canCompleteCircuit(gas, cost);
        System.out.println(Arrays.toString(gas) + "\n" + Arrays.toString(cost));
        System.out.println("Expected: " + expected + "\nActual:   " + actual);
        System.out.println(expected == actual ? "Pass" : "Fail" + "\n");
    }

    public static void main(String[] args) {
        GasStation solution = new GasStation();
        int[] gas  = {3, 1, 2, 5, 4};
        int[] cost = {4, 1, 1, 2, 3};
        test(solution, gas, cost, 1);  // expected result is  1
    }
}
