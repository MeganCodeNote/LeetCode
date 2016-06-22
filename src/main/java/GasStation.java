/* Created by meganlee on 11/6/14. */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // input checking
        if (gas == null || cost == null || gas.length != cost.length) {
            return -1;
        }

        // get the diff array
        int total = 0, subTotal = 0;
        int start = -1;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            total += diff;
            subTotal += diff;
            if (subTotal < 0) {
                start = i;
                subTotal = 0;
            }
        }
        return (total >= 0) ? start + 1 : -1;
    }
}
