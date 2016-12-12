import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        // input checking
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }
        helper(res, new ArrayList<Integer>(), s);
        return res;
    }

    private void helper(List<String> res, List<Integer> nums, String s) {
        //---- base case: stop condition
        // 4 nums collected
        if (nums.size() == 4) {
            if (s.length() == 0) { // no more chars in s
                res.add(nums.get(0) + "." + nums.get(1) + "." + nums.get(2) + "." + nums.get(3));
            }
            return;
        }

        //---- general case
        // condition: there are <= 3 nums and there cur < s.length()
        for (int i = 1; i <= Math.min(3, s.length()); i++) {
            String valStr = s.substring(0, i);
            int val = Integer.valueOf(valStr);
            if (0 <= val && val <= 255 && valStr.equals(val + "")) { // no heading zeros
                nums.add(val);
                helper(res, nums, s.substring(i));
                nums.remove(nums.size() - 1);
            }
        }
    }

    //////////////////////  TEST  /////////////////////////////
    public static void main(String[] args) {
        RestoreIPAddress solution = new RestoreIPAddress();
        List<String> result = solution.restoreIpAddresses("25525511135");
        for (String s: result)
            System.out.println(s);
    }
}