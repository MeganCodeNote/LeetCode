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
        // base case: stop condition
        // 1) no more chars in s
        // 2) more than 4 nums
        if (s.length() == 0) {
            if (nums.size() == 4) {
                res.add(nums.get(0) + "." + nums.get(1) + "." + nums.get(2) + "." + nums.get(3));
            }
            return;
        }
        if (nums.size() >= 4) {
            return;
        }

        // general case
        // condition: there are <= 3 nums and there cur < s.length()
        for (int i = 1; i <= Math.min(3, s.length()); i++) {
            int val = Integer.valueOf(s.substring(0, i));
            if ((s.charAt(0) == '0' && i != 1) || val > 255) {  // check if the val is valid
                continue;
            }
            nums.add(val);
            helper(res, nums, s.substring(i));
            nums.remove(nums.size() - 1);
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